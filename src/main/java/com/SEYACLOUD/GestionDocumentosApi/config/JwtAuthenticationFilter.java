package com.SEYACLOUD.GestionDocumentosApi.config;

import com.SEYACLOUD.GestionDocumentosApi.common.security.JwtTokenProvider;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestRolByUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseRolByUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.services.RolService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final RolService rolService;

    public JwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider,
            RolService rolService
    ) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.rolService = rolService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getServletPath();

        if (isPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = getJwtFromRequest(request);

        try {

            if (jwt != null && jwtTokenProvider.validateToken(jwt)) {

                String usuario = jwtTokenProvider.getUserIdFromToken(jwt);
                String nombre = jwtTokenProvider.getNombreFromToken(jwt);
                String ruc = jwtTokenProvider.getRucFromToken(jwt);
                String rol = jwtTokenProvider.getRolFromToken(jwt);

                RequestRolByUsuario requestRol = new RequestRolByUsuario();
                requestRol.setIdUsuario(usuario);

                ResponseRolByUsuario responseRol = rolService.obtenerRolesPorUsuario(requestRol);

                List<GrantedAuthority> authorities = responseRol.getRol()
                        .stream()
                        .map(r -> (GrantedAuthority) new SimpleGrantedAuthority("ROLE_" + r.getDescripcion()))
                        .toList();


                request.setAttribute("userId", usuario);
                request.setAttribute("nombreUsuario", nombre);
                request.setAttribute("rucEmpresa", ruc);
                request.setAttribute("rol", rol);
                request.setAttribute("isAuthenticated", true);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                usuario,
                                null,
                                authorities
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {

                System.out.println("\nTOKEN INVÁLIDO O AUSENTE");
                SecurityContextHolder.clearContext();
            }

        } catch (Exception e) {

            System.out.println("\n========== ERROR EN JWT FILTER ==========");
            e.printStackTrace();

            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        return path.startsWith("/api/auth/login")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/swagger-ui.html")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/webjars");
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}