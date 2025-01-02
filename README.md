# ShieldMe-Backend

ShieldMe-Backend is the backend system for the **Emergency and Mental Wellbeing Application**. It is a **multi-module Spring Boot project** designed to support the application's four primary microservices:

1. **User Authentication**: Ensures secure access with robust authentication and authorization mechanisms.
2. **Secure Journal**: Allows users to create and manage private journal entries safely.
3. **SOS Button**: Provides emergency alerts with contact management and live tracking.
4. **Positive Affirmations**: Delivers motivational quotes and life inspiration to users.

---

## Features

### 1. **User Authentication**
- Secure access with role-based authentication.
- Supports user registration, login, and password management.
- Implements strong exception handling for seamless user experience.

### 2. **Secure Journal**
- Enables users to create, update, and delete personal journal entries.
- Data is stored securely in MongoDB to ensure privacy.

### 3. **SOS Button**
- Sends emergency alerts to saved contacts via email.
- Stores multiple email IDs and phone numbers for emergency contacts.
- Integrates with live location tracking to assist in emergencies.
- Uses Gmail's email service for reliable alert delivery.
- Implements strong exception handling for seamless user experience.

### 4. **Positive Affirmations**
- Provides daily motivational quotes and inspirational content.
- Helps enhance mental well-being by promoting positivity.

---

## Tech Stack

- **Java**: Version 17
- **Spring Boot**
- **MongoDB**: Database for storing journal entries, user data, SOS alerts and positive affirmations 
- **Maven**: Build and dependency management
- **Gmail API**: For sending emergency email alerts
- **IntelliJ IDEA IDE**


---

## System Architecture

<img src="https://github.com/ajaynegi45/ShieldMe-Backend/blob/main/System%20Architecture.png" alt="Database Design">

---

## Getting Started

### Prerequisites
- **Java 17**
- **Maven**
- **Java IDE**
- **MongoDB** running locally or accessible via a URI
- Make sure to check application.yaml file of each services to run it locally.

---

## License
This project is licensed under the MIT License. See the [LICENSE](https://github.com/ajaynegi45/ShieldMe-Backend/blob/main/LICENSE) file for more details.

