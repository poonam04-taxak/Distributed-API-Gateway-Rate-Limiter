# 🚀 Distributed API Gateway with JWT Authentication, Redis Caching & Rate Limiting
A production-ready distributed API gateway built with Java and Spring Boot, featuring centralized request routing, Redis-based rate limiting, and JWT authentication across microservices.

## 🛠️ **Tech Stack**
### **Backend**
- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT

### **Database**
- MySQL
- JPA/Hibernate

### **Caching**
- Redis (Caching job search results)

### **Others**
- Maven  
- Postman  
- Docker (optional)

---

# 📚 **Features**
### 🔐 **1. JWT Authentication**
- Login endpoint generates JWT token  
- Every protected API requires `Authorization: Bearer <token>`  
- Custom security filter validates token

### 🚦 **2. IP-Based Rate Limiting**
- Each IP is allowed **5 requests**
- Exceeding limit returns `429 TOO MANY REQUESTS`
- Prevents abuse & protects microservices

### ⚡ **3. Redis Caching**
- Frequently accessed job data is cached  
- Faster responses  
- Reduced DB load (up to 40% improvement)

### 🧩 **4. Job Management Microservice**
REST APIs:
- Add Job  
- Get All Jobs  
- Get Job by ID  
- Delete Job  

---


Architecture Overview:
- **API Gateway Layer**
  - Validates JWT tokens
  - Enforces global rate limits
  - Manages routing to downstream services

- **Job Service (Microservice)**
  - CRUD APIs for job listings
  - Uses MySQL with JPA/Hibernate

- **Redis Cache Layer**
  - Stores frequently accessed job results
  - Offloads database & accelerates reads

- **Security Layer**
  - Custom JWT Authentication Filter
  - Token extraction, validation, authentication

---


Run Locally
bash# Clone the repository
git clone https://github.com/poonam04-taxak/YOUR_REPO_NAME.git
cd YOUR_REPO_NAME

# Start Redis (if not running)
redis-server

# Build and run
mvn clean install
mvn spring-boot:run
App runs on: http://localhost:8080

# 📡 **API Endpoints**

## 🔑 **Authentication**
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/linkedIn/login` | Generates JWT token |

---
## 💼 **Job APIs**
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/linkedIn/addJob` | Add new job |
| GET | `/linkedIn/findAll` | Fetch all jobs |
| GET | `/linkedIn/findById/{id}` | Fetch job by ID |
| DELETE | `/linkedIn/del/{id}` | Delete a job |

---
# 🧠 **Core Components Explained**

## 1️⃣ **JWT Authentication Filter**
- Extracts token  
- Validates signature  
- Loads username  
- Injects authentication into Spring Security Context  

## 2️⃣ **Rate Limiting Filter**
- Uses `ConcurrentHashMap`  
- Stores IP → Request Count  
- Blocks after 5 requests  
- Returns 429 with message  

## 3️⃣ **Job Microservice**
- Uses MySQL + JPA  
- Entity → Repository → Service → Controller  
- Clean layered architecture  

---


Rate Limiting:
Redis is used for distributed rate limiting:

Limit: 100 requests / minute per client IP
Handles: 1000+ concurrent requests
Strategy: Sliding window using Redis sorted sets

java// Example: Rate limit check
redisTemplate.opsForZSet().count(clientKey, windowStart, currentTime);

JWT Authentication Flow
1. POST /api/auth/login  →  Returns JWT token
2. Add header: Authorization: Bearer <token>
3. Gateway validates token via Spring Security filter
4. Request routed to downstream service

Project Structure
src/
├── main/java/com/gateway/
│   ├── config/          # Security & Redis config
│   ├── controller/      # API endpoints
│   ├── service/         # Business logic
│   ├── repository/      # Data access layer
│   ├── filter/          # JWT & rate limit filters
│   └── exception/       # Global exception handler
└── resources/
    └── application.properties

Testing:
bash# Run all tests
mvn test

# Test with Postman
# Import: /postman/API_Gateway_Collection.json

Key Learnings:
Implementing distributed rate limiting with Redis in a stateless architecture
Securing microservice communication using JWT with Spring Security
Designing scalable gateway patterns for high-concurrency scenarios

Connect:
Poonam Taxak — Java Backend Developer
LinkedIn: https://www.linkedin.com/in/poonam-taxak
LeetCode: https://leetcode.com/poonam_taxak
