# JWT Demo – Docker + MySQL 8.0.36 + Spring Boot

Dự án demo backend sử dụng **Spring Boot**, **MySQL 8.0.36 chạy bằng Docker**, build và chạy toàn bộ hệ thống bằng**Docker desktop**.

**Author:** Tú
---

##  Công nghệ sử dụng

- Java 21
- Spring Boot
- Maven
- MySQL 8.0.36 (Docker)
- Docker & Docker Compose
---

## Yêu cầu hệ thống

Trước khi bắt đầu, máy cần cài:

- Windows 10/11
- Docker Desktop (khuyến nghị dùng WSL2)
- Git (optional)

Kiểm tra Docker:
```terminal
docker --version
docker compose version
```

## Cài mysql tích hợp với docker
```terminal
docker pull mysql:8.0.36
docker run --name mysql-8.0.36 -p 3308:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.0.36-debian
```

## Tích hợp network:
```terminal
docker network create backend_network
docker network connect backend_network mysql-8.0.36
```
## Build và chạy BackEnd:
```terminal
docker compose up -d --build
```