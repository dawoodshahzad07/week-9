# Gym Database Management System (GDMS)

A Java-based system for managing gym operations, including membership management, equipment tracking, and payment processing.

## Features

- User Management
  - Role-based access control (Admin, Trainer, Member)
  - Secure authentication with password encryption
  - User profile management

- Membership Management
  - Membership registration and renewal
  - Automated expiry tracking
  - Membership status verification

- Equipment Management
  - Equipment inventory tracking
  - Maintenance scheduling
  - Issue reporting and status updates

- Payment Processing
  - Secure payment handling
  - Multiple payment methods support
  - Receipt generation
  - Refund processing

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── gdms/
│               ├── model/
│               │   ├── User.java
│               │   ├── Member.java
│               │   ├── Equipment.java
│               │   └── Payment.java
│               └── service/
│                   ├── UserService.java
│                   └── EquipmentService.java
└── test/
    └── java/
        └── com/
            └── gdms/
                └── model/
                    ├── MemberTest.java
                    ├── EquipmentTest.java
                    └── PaymentTest.java
```

## Technical Stack

- Java 17
- Maven for dependency management
- JUnit 5 for testing
- Spring Security for encryption
- MySQL for database
- Hibernate for ORM

## Setup Instructions

1. Prerequisites:
   - Java 17 or higher
   - Maven
   - MySQL Server

2. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/gym-management-system.git
   cd gym-management-system
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run tests:
   ```bash
   mvn test
   ```

## Design Patterns Used

1. **Singleton Pattern**
   - Used for database connection management
   - Ensures single instance for resource management

2. **Factory Pattern**
   - Used for creating user instances
   - Supports different user types (Admin, Trainer, Member)

3. **Observer Pattern**
   - Used for notification system
   - Handles membership renewals and payment reminders

## Testing

The project includes comprehensive unit tests for all major components:

- User and Member management tests
- Equipment tracking tests
- Payment processing tests

Run tests using:
```bash
mvn test
```

## Security Features

- Password encryption using BCrypt
- Role-based access control
- Secure payment processing
- Data validation and sanitization

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 