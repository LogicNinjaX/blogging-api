---
# 📖 Blogging API - Spring Boot + MongoDB + JWT Authentication

## Overview

A RESTful Blogging API built with Spring Boot, MongoDB, JWT Authentication, and Spring Security. This API allows users to register, authenticate, create, read, update, and delete blog posts securely.

---
## Features 🎯📌✨

- **User Authentication & Authorization**: Implemented using **Spring Security with JWT**.
- **RUD Operations**: Create, Read, Update, Delete Blog Posts.
- **Secure API**: Uses JWT Authentication.
- **Database Integration**: Uses **MongoDb** for storing user details and blog post details.
- **DTO Mapping**: Utilizes **ModelMapper** for efficient object mapping.

---
## Technologies Used 🛠️📡💾

- **Spring Boot**
- **Spring Security (JWT-based authentication)**
- **MongoDB - NoSQL Database**
- **ModelMapper**
---
## Dependencies 📦🔗📜

The project includes the following dependencies in `pom.xml`:

```xml
<dependencies>
    <!-- Spring Data Mongodb & Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Security & JWT -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.6</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.12.6</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.12.6</version>
        <scope>runtime</scope>
    </dependency>

    <!-- Model Mapper -->
    <dependency>
        <groupId>org.modelmapper</groupId>
        <artifactId>modelmapper</artifactId>
        <version>3.1.1</version>
    </dependency>

    <!-- Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>

```

## Setup Instructions ⚙️🛠️🚀

### Prerequisites 📝💻🔍

- **Java 22** (or compatible version)
- **Maven**
- **MongoDB (Installed & Running)**

### Installation Steps 📥📌🔧

1. Clone the repository:
   ```sh
   git clone https://github.com/LogicNinjaX/blogging-api.git
   cd blogging-api
   ```


2. Configure MySQL database in `application.yml`:
   ```yml
     data:
    mongodb:
      host: ${mongo-host}  -> (host name for mongo db)
      port: ${mongo-port}  -> (post name for mongo db)
      database: ${db-name} -> (database name)
   ```

3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## 🛠️ API Endpoints
**User Authentication**

| HTTP Method | Endpoint                       | Description             | Authentication |
| ----------- | ------------------------------ | ----------------------- | -------------- |
| POST        | `/api/v1/auth/register`        | User registration       | ❌ No          |
| POST        | `/api/v1/auth/login`           | User login              | ❌ No          |


**Blog Post Management**

| HTTP Method | Endpoint                       | Description             | Authentication |
| ----------- | ------------------------------ | ----------------------- | -------------- |
| POST        | `api/v1/blog/posts`            | Create a new blog post  | ✅ Yes          |
| GET        | `api/v1/blog/posts`             | Get all blog posts      | ✅ Yes          |
| GET        | `api/v1/blog/posts/{post-id}`   | Get a single post by ID | ✅ Yes          |
| DELETE      | `api/v1//blog/posts/{post-id}` | Delete a blog post by Id| ✅ Yes          |
| PUT         | `api/v1/blog/posts/{post-id}`  | Update a blog post by Id| ✅ Yes          |

## Security & Authentication 🔐🛡️🔑

- Uses **JWT (JSON Web Tokens)** for authentication.
- Secure endpoints require **Authorization: Bearer <token>** header.

## Contributing 🤝🌍💡

Contributions are welcome! Please fork the repository and submit a pull request.

## License 📜⚖️✅

This project is licensed under the **MIT License**.

---

Feel free to modify and expand as needed! 🚀🎉💡
