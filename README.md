# Student-Management-System-Java1
A console-based student management system using Java, JDBC, PostgreSQL, file I/O, and Maven.
# 🎓 Student Management System

![Java](https://img.shields.io/badge/Java-17-blue) 
![Maven](https://img.shields.io/badge/Maven-3.8.6-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![JDBC](https://img.shields.io/badge/JDBC-4.2-green)

The **Student Management System** is a **menu-driven console-based application** built using **Core Java**, **JDBC**, and **PostgreSQL**. It allows users to **add**, **update**, **view**, **import**, and **export** student records in a PostgreSQL database.

---

## 🛠 Technologies Used

- **Core Java**: For application logic and OOP concepts
- **JDBC (Java Database Connectivity)**: For connecting and interacting with the PostgreSQL database
- **PostgreSQL**: For storing student data
- **Maven**: For managing project dependencies

---

## 📦 Dependencies

The project uses the following dependency in `pom.xml`:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.6.0</version>
</dependency>
