# Stemshop 🛒

## 📌 Описание проекта
**Stemshop** — это e-commerce приложение для продажи STEM-товаров.  
Проект реализован на **Spring Boot** с использованием **PostgreSQL**, **Redis**, **JWT-аутентификации** и интеграции с **Stripe** для оплаты.  
Разработан с поддержкой кеширования корзины и избранного через Redis, а также документирован с помощью Swagger.

---

## ✨ Функциональность
- 🔐 Авторизация и регистрация пользователей (JWT)
- 🛍️ Управление товарами
- 📦 Каталог товаров
- ⭐ Отзывы на товары
- 👥 Управление пользователями
- 🛒 Корзина с кешированием через Redis
- ⭐ Избранное с хранением в Redis
- 🎟️ Система купонов и скидок
- 💳 Интеграция с Stripe (платежи)
- 📦 Управление заказами
- 📖 Swagger UI документация

---

## 🛠 Стек технологий
- **Java 21**
- **Spring Boot 3.4.2**
- **Spring Security (JWT)**
- **Spring Data JPA + Hibernate**
- **Spring Validation**
- **Lombok** - аннотации для классов
- **Flyway** - миграция
- **PostgreSQL** (основная база данных)
- **Redis (Upstash/локально)** — кеширование
- **Stripe API** — платежная система
- **Docker & Docker Compose**
- **Swagger (springdoc-openapi)**
- **SonarQube** - проверка кода

---

## 🚀 Установка и запуск

### 1. Клонировать репозиторий
```bash
git clone https://github.com/Te7Ro/Stemshop.git
cd Stemshop
```

### 2. Настроить окружение
Создать файл `.env` и указать переменные:
```bash
DB_HOST=your_db_host
DB_NAME=your_db_name
DB_USER=your_db_user
DB_PASSWORD=your_db_password

REDIS_HOST=your_redis_host

JWT_ACCESS=your_jwt_access_token
JWT_REFRESH=your_jwt_refresh_token

STRIPE_SECRET_KEY=your_stripe_secret_key
STRIPE_PUBLIC_KEY=your_stripe_public_key
STRIPE_SUCCESS_URL=your_stripe_success_url
STRIPE_CANCEL_URL=your_stripe_cancel_url

MAIL_USERNAME=your_mail_username
MAIL_PASSWORD=your_mail_app_password
```

### 3. Запуск через Docker Compose
```bash
docker-compose up --build
```

---

## 📌 API Endpoints
Swagger UI доступен по ссылке: [Swagger](https://stemshop.onrender.com/swagger-ui/index.html)  

---

## 👥 Роли пользователя и доступ
- 👤 **CUSTOMER** — просмотр товаров, добавление в корзину, оформление заказов
- 🧑‍💻 **CONTENT_MANAGER** — управление купонами, товарами
- 💬 **SUPPORT** — обновление статусов заказов и оплаты
- 🛠 **STORE_ADMIN** — Выше перечисленное и управление пользователями

---

## ✍️ Автор
Проект разработан **Otegen Kaldanov** 🚀  
GitHub: [Te7Ro](https://github.com/Te7Ro)
