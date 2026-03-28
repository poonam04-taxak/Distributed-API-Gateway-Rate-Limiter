# 🚀 Distributed API Gateway with JWT Authentication, Redis Caching & Rate Limiting

A production-ready Distributed API Gateway built with Spring Boot, featuring centralized routing, JWT authentication, Redis caching, and distributed rate limiting.

This project simulates a real-world backend architecture used in scalable systems such as job portals, microservices, and enterprise applications.

## 🛠️ **Tech Stack**
### **Backend**
- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT Authentication

### **Database**
- MySQL
- JPA / Hibernate ORM

### **Caching**
- Redis → Caching job search results + rate limiting

### **Others**
- Maven
- Postman
- Docker (Optional)
  
# 📚 **Features** ### 
🔐 **1. JWT Authentication**
- /linkedIn/login generates a JWT token
- Every protected API requires
- Authorization: Bearer <token>
- Custom JWT filter validates requests and injects authentication

### 🚦 **2. Distributed Rate Limiting (Redis)**

This API Gateway implements IP-based throttling using Redis.
Property	Value
Limit	5 requests
Time Window	60 seconds
Redis	Used for fixed-window counters

✔ Works across multiple backend instances
✔ Prevents abuse, flooding, DDoS-like traffic
✔ Stateless & scalable

### ⚡ **3. Redis Caching**
- Frequently fetched job listings are cached
- 40–60% faster repeated calls
- Reduces MySQL load significantly
  
### 🧩 **4. Job Management Microservice**

REST APIs:
- Add a job
- Get all jobs
- Get job by ID
- Delete a job

Connected to MySQL using Spring Data JPA.

### 🏗️ Architecture Overview:

🔷 API Gateway Layer
Validates JWT tokens
Enforces global rate limits
Routes requests to Job microservice
🔷 Job Service
CRUD job operations
MySQL + Hibernate
🔷 Redis Layer
Request throttling
Response caching
🔷 Security Layer
Custom JWT filter
Token decoding & validation

### ▶️ Run Locally
# Clone the repository
git clone https://github.com/poonam04-taxak/Distributed-API-Gateway.git

cd Distributed-API-Gateway

 Start Redis
redis-server

# Build & run the project
mvn clean install
mvn spring-boot:run

App runs on: http://localhost:8080
# 📡 **API Endpoints** ## 🔑 **Authentication** | Method | Endpoint | Description | |--------|----------|-------------| | POST | /linkedIn/login | Generates JWT token | --- ## 💼 **Job APIs** | Method | Endpoint | Description | |--------|----------|-------------| | POST | /linkedIn/addJob | Add new job | | GET | /linkedIn/findAll | Fetch all jobs | | GET | /linkedIn/findById/{id} | Fetch job by ID | | DELETE | /linkedIn/del/{id} | Delete a job |

### 🧠 Core Components Explained
1️⃣ JWT Authentication Filter
Extracts JWT from headers
Verifies signature
Loads user details
Attaches auth info to SecurityContext
2️⃣ Distributed Rate Limiting Using Redis

✔ Fully distributed
✔ Sliding window via TTL
✔ No memory stored in gateway

Limit:

⏱️ 5 requests / minute per IP

Benefits:

Prevents overload
Ensures fair usage
Works across multiple backend instances

3️⃣ Job Microservice
MySQL + Spring Data JPA
Clean layered architecture:
Controller → Service → Repository

### 📁 Project Structure
src/
├── main/java/com/security/security/
│   ├── config/          # Spring + Security + Redis Config
│   ├── controller/      # API Gateway Endpoints
│   ├── service/         # Business logic
│   ├── repository/      # Database layer
│   ├── filter/          # JWT + Redis Rate Limiter Filters
│   └── exception/       # Global exception handler
└── resources/
    ├── application.properties
    └── static/
🧪 Testing
Run Tests
mvn test
Test with Postman

### Import the collection:

/postman/API_Gateway_Collection.json

### 🎯 Key Learnings
Implementing distributed rate limiting with Redis
Securing microservices using JWT
Building scalable API gateway architecture
Redis caching for performance improvement
Handling high-concurrency API designs

👤 Connect with Me:

Poonam Taxak — Java Backend Developer

🔗 GitHub: https://github.com/poonam04-taxak

🔗 LinkedIn: https://www.linkedin.com/in/poonam-taxak

🔗 LeetCode: https://leetcode.com/poonam_taxak
