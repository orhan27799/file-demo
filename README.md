# Spring Boot JWT Authentication file upload  with Spring Security & Spring Data JPA
## Spring REST Sample with Swagger


## Dependency
– If you want to use PostgreSQL:
```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```
– or MySQL:
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
```
## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`
- For PostgreSQL:
```
spring.datasource.url= jdbc:postgresql://localhost:5432/filedemodb
spring.datasource.username= postgres
spring.datasource.password= 123456

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto= update

## MULTIPART (MultipartProperties)
# Enable multipart uploads
spring.servlet.multipart.enabled=true
# Threshold after which files are written to disk.
spring.servlet.multipart.file-size-threshold=2KB
# Max file size.
spring.servlet.multipart.max-file-size=5MB
# Max Request Size
spring.servlet.multipart.max-request-size=5MB

upload.file.extensions=png,jpeg,jpg,docx,pdf,xlsx



# Jwt  Properties
filedemo.app.jwtSecret=filedemoSecretKey
filedemo.app.jwtExpirationMs=86400000
```
- For MySQL
```
spring.datasource.url= jdbc:mysql://localhost:3306/filedemodb?useSSL=false
spring.datasource.username= root
spring.datasource.password= 123456

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

## MULTIPART (MultipartProperties)
# Enable multipart uploads
spring.servlet.multipart.enabled=true
# Threshold after which files are written to disk.
spring.servlet.multipart.file-size-threshold=2KB
# Max file size.
spring.servlet.multipart.max-file-size=5MB
# Max Request Size
spring.servlet.multipart.max-request-size=5MB

upload.file.extensions=png,jpeg,jpg,docx,pdf,xlsx

# Jwt  Properties
filedemo.app.jwtSecret=filedemoSecretKey
filedemo.app.jwtExpirationMs=86400000
```
## Run Spring Boot application
```
mvn spring-boot:run
```

## Run following SQL insert statements
```
INSERT INTO roles(id,name) VALUES(1,'ROLE_USER');
INSERT INTO roles(id,name) VALUES(2,'ROLE_MODERATOR');
INSERT INTO roles(id,name) VALUES(3,'ROLE_ADMIN');
```

## User example signup
```
http://localhost:8080/api/auth/signup
   
{ 
 "username":"admin",
 "email":"admin@gmail.com",
 "password":"12345678",
 "role":["ROLE_ADMIN","ROLE_USER"]
 
}
Response :{"message":"User registered successfully!"
```


## User example signin
```
http://localhost:8080/api/auth/signin
   
{
"username":"admin",
 "password":"12345678"
}
Response :{"id":12,"username":"admin","email":"admin@gmail.com","roles":["ROLE_USER","ROLE_ADMIN"],"tokenType":"Bearer","accessToken":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYxMDMwNjU3OCwiZXhwIjoxNjEwMzkyOTc4fQ.w_9OpC2kwdBUvGOK7A6xEzpmxqNZHwtvM0uKABYb3EOspYdHOH35x8IbH0KFomzZQlZmyUczoiZoZNB8d2rhmQ"}
```

## Spring REST Sample with Swagger
```
http://localhost:8080/swagger-ui/
```


## File Upload  
```
http://localhost:8080/api/file/upload
```


## File Upload 
```
http://localhost:8080/api/file/upload
```


## Get Upload File List
```
http://localhost:8080/api/file/list
```
## Get Upload File List
```
http://localhost:8080/api/file/list

```


## Delete File 
```
http://localhost:8080/api/file/delete/filename.png
```


