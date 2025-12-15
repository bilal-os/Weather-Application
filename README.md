# 🌦️ Weather Application (Java)

The Weather Application is a modular and extensible Java-based project designed with **clean architecture** and **SOLID principles** in mind. It supports multiple user interfaces and storage backends, making it easy to extend without modifying the core logic.

---

## 🚀 Key Features

- 🧩 Plug-in based architecture
- 🧱 Clean separation of concerns using layered design
- 🔌 Easily extendable without changing core components
- 💾 Multiple storage backends (database & file-based)
- 🖥️ Multiple user interfaces (Terminal & Java UI)

---

## 🛠️ Technologies Used

- Java
- JDBC (for database connectivity)
- File-based text storage

---

## 🏗️ Architecture Overview

The application follows a **three-layer architecture** to ensure maintainability and scalability:

### 1️⃣ User Interface (UI) Layer
- Terminal-based interface
- Java-based graphical/user interface
- Handles user interaction only

### 2️⃣ Business Logic Layer
- Core application logic
- Implements weather-related operations
- Follows SOLID principles
- Independent of UI and data storage implementations

### 3️⃣ Data Storage Layer
- SQL database adapter
- File-based (text) storage adapter
- Custom storage adapters supported via plug-in system

---

## 🔌 Extensibility

- New storage systems can be added by implementing adapter interfaces
- UI layers can be extended or replaced without touching business logic
- Core logic remains unchanged when adding new features

---

## 📂 Project Structure (High-Level)
```
Weather-App/
│── ui/          # Terminal and Java UI implementations
│── logic/       # Business logic layer
│── storage/     # Database and file-based adapters
│── interfaces/  # Common interfaces and abstractions
│── README.md
```

---

## ▶️ Getting Started

1. Clone the repository
2. Configure the desired storage backend (SQL or file-based)
3. Compile and run the Java application
4. Choose between Terminal UI or Java UI

---

## 🌟 Design Principles

- Single Responsibility Principle (SRP)
- Open/Closed Principle (OCP)
- Interface Segregation
- Dependency Inversion

---

This Weather Application is built for **simplicity, flexibility, and long-term maintainability**, making it ideal for experimenting with clean architecture and extensible system design.
