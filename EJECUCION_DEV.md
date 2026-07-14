# Comandos para ejecutar el sistema

## 1. Limpiar y compilar el proyecto

Ejecuta el siguiente comando desde la raíz del proyecto:

```bash
.\mvnw.cmd clean package -DskipTests
```

> **Nota:** Se omiten las pruebas (`skipTests`) para acelerar la compilación.

---

## 2. Ejecutar la aplicación

Una vez generado el archivo `.war`, ejecuta:

```bash
java -jar target\GestionDocumentosApi-0.0.1-SNAPSHOT.war
```

---

## Requisitos

- Java 21 (o la versión utilizada por el proyecto).
- Maven Wrapper (`mvnw.cmd`).

---

## Estructura esperada

```
proyecto/
│── src/
│── target/
│   └── backoffice_system-0.0.1-SNAPSHOT.war
│── mvnw.cmd
│── pom.xml
└── README.md
```

## Forzar Rebuild limpio

```bash
.\mvnw.cmd clean install -U
```

## BORRAR CACHE LOCAL DE JWT
Ve a esta ruta:
```bash
C:\Users\TU_USUARIO\.m2\repository\io\jsonwebtoken
```

## VERIFICAR DEPENDENCIAS REALES
```bash
.\mvnw.cmd dependency:tree | findstr jjwt
```

