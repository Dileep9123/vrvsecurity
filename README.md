
# Spring Boot 3.0 Security with JWT Implementation
This project demonstrates the implementation of security using Spring Boot 3.0 and JSON Web Tokens (JWT). It includes the following features:

## Features
* User registration and login with JWT authentication
* Password encryption using BCrypt
* Role-based authorization with Spring Security

## Technologies
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven

## Roles
* ADMIN
* MANAGER
* USER

## Authorities
* ADMIN has highest privileges
* MANAGER second highest privileges, he cannot access admin resources but can access everything remaining
* USER has least privileges


## URLS That Can Be Accesses By Each Role
# ADMIN
* GET: http://localhost:8000/api/v1/admin
* POST: http://localhost:8000/api/v1/admin
* PUT: http://localhost:8000/api/v1/admin
* DELETE: http://localhost:8000/api/v1/admin
* GET: http://localhost:8000/api/v1/management
* POST: http://localhost:8000/api/v1/management
* PUT: http://localhost:8000/api/v1/management
* DELETE: http://localhost:8000/api/v1/management
* GET: http://localhost:8000/hello
* GET: http://localhost:8000/about
* POST: http://localhost:8000/login
* POST: http://localhost:8000/register

# MANAGER
* GET: http://localhost:8000/api/v1/management
* POST: http://localhost:8000/api/v1/management
* PUT: http://localhost:8000/api/v1/management
* DELETE: http://localhost:8000/api/v1/management
* GET: http://localhost:8000/hello
* GET: http://localhost:8000/about
* POST: http://localhost:8000/login
* POST: http://localhost:8000/register

# USER
* GET: http://localhost:8000/hello
* GET: http://localhost:8000/about
* POST: http://localhost:8000/login
* POST: http://localhost:8000/register




## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+


To build and run the project, follow these steps:

* Clone the repository: `git clone https://github.com/ali-bouali/spring-boot-3-jwt-security.git`
* Navigate to the project directory: cd spring-boot-security-jwt
* Add database "jwt_security" to postgres 
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run 

-> The application will be available at http://localhost:8080.
