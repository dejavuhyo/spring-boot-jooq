# Spring Boot jOOQ

## 1. 설명
Spring Boot 3.5 jOOQ CRUD 예제이다. 포트는 8080을 사용한다.

## 2. 개발환경

* OpenJDK 17

* spring-boot 3.5.4

* spring-boot-starter-jooq

* PostgreSQL 17.5

* org.projectlombok 1.18.38

## 3. DB (PostgreSQL)

* /db/table.sql

## 4. Rest API 실행

### 1) 전체 조회

* URL: `http://localhost:8080/api/users`

* Method: `GET`

### 2) ID 조회

* URL: `http://localhost:8080/api/users/1`

* Method: `GET`

### 3) 등록

* URL: `http://localhost:8080/api/users`

* Method: `POST`

* Params

```json
{
  "name": "hyo",
  "email": "hyo@example.com"
}
```

### 4) 수정

* URL: `http://localhost:8080/api/users/1`

* Method: `PUT`

* Params

```json
{
  "name": "Ahn",
  "email": "ahn@example.com"
}
```

### 5) 삭제

* URL: `http://localhost:8080/api/users/1`

* Method: `DELETE`

* DELETE
