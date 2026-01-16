<div align="center">

  <img src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExbm95aGZsZnF4Z2Z4Z2Z4Z2Z4Z2Z4Z2Z4Z2Z4Z2Z4Z2Z4/3o7rc0qU6m5maIgg4o/giphy.gif" width="100" alt="Server Animation">

  <h1 style="font-size: 3rem; font-weight: 900; color: #6DB33F; margin-bottom: -10px;">
    School System REST API
  </h1>
  
  <p style="font-size: 1.2rem; font-style: italic; color: #888;">
    "The Secure, High-Performance Backbone of Modern Education."
  </p>

  <p>
    <img src="https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
    <img src="https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot">
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
    <img src="https://img.shields.io/badge/JWT_Security-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white" alt="JWT">
  </p>

  <a href="https://github.com/chamikathereal/schoolsystem-rest-api">
    <img src="https://img.shields.io/badge/🔴_View_Live_Demo-FF0000?style=for-the-badge&logoColor=white" alt="Live Demo">
  </a>
  <a href="https://github.com/chamikathereal/schoolsystem-rest-api/issues">
    <img src="https://img.shields.io/badge/🐞_Report_Bug-F44336?style=for-the-badge" alt="Report Bug">
  </a>
  <a href="https://github.com/chamikathereal/schoolsystem-rest-api/issues">
    <img src="https://img.shields.io/badge/✨_Request_Feature-FF9800?style=for-the-badge" alt="Request Feature">
  </a>

</div>

<br />

<div align="center">
  <h2>⚙️ The Engine Room</h2>
  <p>Architected for speed, secured by design. Witness the core logic in action.</p>
  
  <img src="https://placehold.co/800x250/2b2b2b/6DB33F?text=API+Endpoint+Logic+%7C+Spring+Security+Filter+Chain" alt="Code Preview" style="border-radius: 10px; box-shadow: 0 10px 30px rgba(0,0,0,0.5);">
</div>

---

## 🔮 The Vision

This backend isn't just a data store; it's the **guardian of institutional integrity**. Built on **Spring Boot 3.4**, it implements strict **Role-Based Access Control (RBAC)** to ensure that Principals, Clerks, and Teachers have exactly the access they need—no more, no less.

> **Performance Metric:** Optimized with **Spring Data JPA** and connection pooling to handle concurrent requests with sub-millisecond latency, ready for scale.

### 💎 The "Wow" Factors

| Feature | Description |
| :--- | :--- |
| **🛡️ Ironclad Security** | Implements **Spring Security 6** with a custom stateless **JWT Authentication Filter**, ensuring every request is verified cryptographically. |
| **🔑 Granular RBAC** | Fine-tuned `@PreAuthorize` annotations protect endpoints. Principals manage data, while Teachers view it—enforced at the controller level. |
| **⚡ API Precision** | Follows strict **RESTful** standards with standardized JSON responses, DTO mapping, and global exception handling for a clean frontend contract. |
| **🔌 CORS & Config** | Pre-configured `CorsConfigurationSource` to seamlessly integrate with modern frontends (Vite/React) running on localhost or production domains. |

---

## 🛠️ The Arsenal (Tech Stack)

<div align="center">

| Domain | Tech | Why? |
| :--- | :---: | :--- |
| **Framework** | ![Spring Boot](https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=flat&logo=spring-boot&logoColor=white) | Rapid, production-ready development. |
| **Language** | ![Java](https://img.shields.io/badge/Java_17-ED8B00?style=flat&logo=openjdk&logoColor=white) | Robust, typed, enterprise standard. |
| **Database** | ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white) | Reliable relational data storage. |
| **ORM** | ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat&logo=hibernate&logoColor=white) | Simplifies complex SQL operations. |
| **Security** | ![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat&logo=spring-security&logoColor=white) | Authentication & Authorization. |
| **Tooling** | ![Lombok](https://img.shields.io/badge/Lombok-BC0230?style=flat&logo=lombok&logoColor=white) | Reduces boilerplate code. |
| **Build** | ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white) | Dependency management. |

</div>

---

## 🏗️ Project Architecture

A clean Layered Architecture ensuring separation of concerns.

```bash
SCHOOLSYSTEM-REST-API/
├── 📂 src/main/java/io/github/chamikathereal/schoolsystem/
│   ├── 📂 config/          # ⚙️ Security & App Config
│   │   ├── JwtAuthenticationFilter.java
│   │   └── SecurityConfig.java
│   ├── 📂 controller/      # 🚦 REST Endpoints
│   │   ├── AuthController.java
│   │   └── StudentController.java
│   ├── 📂 dto/             # 📦 Data Transfer Objects
│   │   ├── request/
│   │   └── response/
│   ├── 📂 entity/          # 🗄️ Database Models
│   │   ├── Student.java
│   │   └── User.java
│   ├── 📂 repository/      # 💾 Data Access Layer
│   ├── 📂 service/         # 🧠 Business Logic
│   └── 📂 utils/           # 🛠️ Helpers (JwtService)
├── 📂 src/main/resources/
│   └── application.properties # 🔧 Env Variables
└── pom.xml                 # 📦 Dependencies

```

## 🧠 Leveling Up: Learning Outcomes

Building this API involved mastering enterprise-grade backend patterns.

* [x] **Advanced Security:** configuring `SecurityFilterChain` to handle CSRF, CORS, and stateless sessions manually.
* [x] **Token Logic:** Implementing JWT generation, parsing, and expiration handling from scratch using `jjwt`.
* [x] **Data Safety:** Using DTOs (Data Transfer Objects) to decouple the internal database entities from the external API surface.
* [x] **Service Abstraction:** leveraging Interfaces (`StudentService`, `AuthService`) to keep the code testable and modular.

---

## 🚀 Getting Started

Ready to power up the server?

**1. Clone the Repository**

```bash
git clone https://github.com/chamikathereal/schoolsystem-rest-api.git
cd schoolsystem-rest-api
```
**2. Configure Database**
Update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/school_db
spring.datasource.username=root
spring.datasource.password=your_password
```
**3. Build the Project**

```bash
./mvnw clean install
```
**4. Ignite the Engine**

```bash
./mvnw spring-boot:run
```

🧑‍💻 Author
<div align="center"> <img src="https://github.com/chamikathereal.png" width="120px" style="border-radius: 50%; border: 4px solid #6DB33F; box-shadow: 0 0 20px rgba(109, 179, 63, 0.6);" alt="Chamika Gayashan">

<h3>Chamika Gayashan</h3>
<p><strong>Software Engineer | Java & Spring Developer</strong></p>

<p><em>"Architecture is the art of making the complex feel simple."</em></p>

<p> <a href="https://www.linkedin.com/in/chamikathereal"> <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"> </a> <a href="https://github.com/chamikathereal"> <img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"> </a> <a href="https://medium.com/@chamikathereal"> <img src="https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white" alt="Medium"> </a> </p> <p> <a href="https://www.instagram.com/chamikathereal/"> <img src="https://img.shields.io/badge/Instagram-E4405F?style=for-the-badge&logo=instagram&logoColor=white" alt="Instagram"> </a> <a href="https://www.facebook.com/chamikathereaI"> <img src="https://img.shields.io/badge/Facebook-1877F2?style=for-the-badge&logo=facebook&logoColor=white" alt="Facebook"> </a> </p>

<br />

<code>Last Updated: Friday, January 16, 2026</code>

</div>
<p align="center">
  <sub>Built with ❤️ and ☕ by Chamika Gayashan — Star ⭐ this repo if you found it useful!</sub>
</p>
