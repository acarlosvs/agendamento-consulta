# Agendamento Consulta

## Steps to Setup

**1. Clone the repository**

**2. Local Dependencies**

In the run: `docker-compose -f ./docker/docker-compose.yml up -d --build` to up a dependencies containers

**3. Build**
```bash
mvn package
```

**4. Run Unit Tests**
```bash
mvn test
```

**5. Run the app**

Type the following command from the root directory of the project to run it -

```bash
mvn spring-boot:run
```

**6. Docs Open Api**

Request /swagger-ui/index.html for Swagger documentation

**7. Use app**
1. Request path /api/v1/singup
2. Request path /api/v1/login
3. Request path /api/v1/attendance
4. Request path /api/v1/logof
