# Student-Management-System-Java1
A console-based student management system using Java, JDBC, PostgreSQL, file I/O, and Maven.


Group member


Parth Pramod Bagale


Sanchit Sanjay Kale

Kartik Shankar Gade 
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
```
✅ Prerequisites
Before running the project, make sure you have:

Java Development Kit (JDK) 17 or higher

Apache Maven 3.8.6 or higher

PostgreSQL 15 or higher

A PostgreSQL database named javaproject with the following table:
```
CREATE TABLE studentdetails (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    course VARCHAR(100) NOT NULL,
    marks NUMERIC(5,2) NOT NULL
);
```
💻 Features
➕ Add Student

✏️ Update Student by ID

📃 Show All Students

📤 Export student records to .csv file

📥 Import student records from .csv file

🧵 Background Tip Thread (every 10 mins)


📁 File Structure

```

menuapp/
├── src/
│   └── main/
│       └── java/
│           └── com.javaproject.app/
│               ├── DBConnection.java
│               └── StudentManagement.java
├── pom.xml
├── README.md


```
#Project Screenshots


<img width="1859" height="854" alt="Screenshot 2025-07-10 191818" src="https://github.com/user-attachments/assets/26797e55-355c-45ee-9527-abd93edbe33b" />



<img width="1853" height="611" alt="Screenshot 2025-07-10 191906" src="https://github.com/user-attachments/assets/e9860962-7483-458b-ac9a-4d103a826608" />


📬 Contact
For any queries or feedback, feel free to reach out:

Parth Pramod Bagale
📧 parthbagale14@gmail.com

📧 sanchitkale452@gmai.com

📍 Badlapur, Maharashtra





