# Nhận diện tài xế ngủ gục (Backend) (Spring Boot)

## Giới thiệu
Đây là hệ thống **Backend (API server)** cho ứng dụng phát hiện tài xế ngủ gật.

Backend chịu trách nhiệm:
- Xử lý logic nghiệp vụ
- Xác thực người dùng (authentication)
- Lưu trữ và quản lý dữ liệu lịch sử ngủ gật
- Cung cấp RESTful APIs cho Android client

---

## Kiến trúc
- Backend: Spring Boot (REST API)
- Client: Android App (repo riêng: https://github.com/khanhan0603/nhan-dien-tai-xe-ngu-guc-FE.git)
- AI Module: chạy trên thiết bị (client)

---

## Chức năng
- Đăng ký / đăng nhập người dùng
- Xác thực bằng JWT
- Lưu trữ lịch sử ngủ gật
- Lấy danh sách / chi tiết lịch sử
- API cho client gửi dữ liệu

---

## Công nghệ
- Java 21
- Spring Boot
- Spring Security
- Maven
- RESTful API
- MySQL
- Git, GitHub

---

## Chạy project

### Clone
```bash
git clone https://github.com/khanhan0603/nhan-dien-tai-xe-ngu-guc-E.git
cd nhan-dien-tai-xe-ngu-guc-E
```
### Chạy trực tiếp trong IntelliJ:
- Open project
- Run TaixengugucApplication
