# Distributed API Gateway & Rate Limiter
A production-ready distributed API gateway built with Java and Spring Boot, featuring centralized request routing, Redis-based rate limiting, and JWT authentication across microservices.

Tech Stack: Java Spring Boot Spring Security Redis JWT Maven Postman

Features:
Centralized request routing and filtering across microservices
Redis-based distributed rate limiting — handles 1000+ concurrent requests
JWT authentication securing all API endpoints
MVC + Controller-Service-Repository architecture for modularity
Centralized exception handling and request validation


Architecture Overview:
Client Request
      │
      ▼
┌─────────────────────┐
│   API Gateway Layer  │  ← Rate Limiter (Redis)
│   (Spring Boot)      │  ← JWT Auth (Spring Security)
└────────┬────────────┘
         │
    ┌────┴─────┐
    ▼          ▼
Service A   Service B   (Microservices)

Getting Started
Prerequisites

Java 17+
Maven 3.8+
Redis (running on localhost:6379)

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

API Endpoints:
MethodEndpointDescriptionAuth RequiredPOST/api/auth/loginGet JWT tokenNoGET/api/gateway/routeRoute request to serviceYesGET/api/rate-limit/statusCheck rate limit statusYesPOST/api/gateway/filterApply request filtersYes

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
