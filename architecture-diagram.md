# GDMS Architecture Diagram

```plantuml
@startuml GDMS Architecture

' Style settings
skinparam componentStyle uml2
skinparam component {
    BackgroundColor LightBlue
    BorderColor DarkBlue
}

' Title
title Gym Database Management System (GDMS) Architecture

' Package definitions
package "Presentation Layer" {
    [Web Interface] as web
    [Mobile App] as mobile
    [Desktop Client] as desktop
}

package "Application Layer" {
    package "Service Layer" {
        [UserService] as userService
        [MemberService] as memberService
        [EquipmentService] as equipmentService
        [PaymentService] as paymentService
    }

    package "Domain Layer" {
        abstract class User {
            +userId: int
            +name: String
            +email: String
            #password: String
            +isActive: boolean
            +{abstract} authenticate(password: String): boolean
            +{abstract} getRole(): String
        }

        class Member {
            +membershipType: String
            +membershipStartDate: LocalDate
            +membershipEndDate: LocalDate
            +isMembershipActive(): boolean
            +renewMembership(months: int): void
        }

        class Equipment {
            +equipmentId: int
            +name: String
            +status: String
            +location: String
            +description: String
            +lastMaintenanceDate: LocalDate
            +nextMaintenanceDate: LocalDate
            +performMaintenance(months: int): void
            +isMaintenanceDue(): boolean
            +reportIssue(issue: String): void
        }

        class Payment {
            +paymentId: int
            +memberId: int
            +amount: double
            +status: String
            +paymentMethod: String
            +transactionId: String
            +processPayment(transactionId: String): boolean
            +refundPayment(reason: String): boolean
            +generateReceipt(): String
        }
    }
}

package "Data Access Layer" {
    [UserRepository] as userRepo
    [MemberRepository] as memberRepo
    [EquipmentRepository] as equipmentRepo
    [PaymentRepository] as paymentRepo
}

package "Database" {
    [MySQL Database] as db
}

' Relationships
' Presentation Layer to Service Layer
web --> userService
web --> memberService
web --> equipmentService
web --> paymentService

mobile --> userService
mobile --> memberService
mobile --> equipmentService
mobile --> paymentService

desktop --> userService
desktop --> memberService
desktop --> equipmentService
desktop --> paymentService

' Service Layer to Domain Layer
userService --> User
memberService --> Member
equipmentService --> Equipment
paymentService --> Payment

' Domain Layer Relationships
User <|-- Member

' Service Layer to Repository Layer
userService --> userRepo
memberService --> memberRepo
equipmentService --> equipmentRepo
paymentService --> paymentRepo

' Repository Layer to Database
userRepo --> db
memberRepo --> db
equipmentRepo --> db
paymentRepo --> db

@enduml
```

## Architecture Description

The Gym Database Management System follows a layered architecture pattern with the following components:

### 1. Presentation Layer
- **Web Interface**: Browser-based access to the system
- **Mobile App**: Native mobile application
- **Desktop Client**: Standalone desktop application

### 2. Application Layer
#### Service Layer
- **UserService**: Manages user-related operations
- **MemberService**: Handles member management
- **EquipmentService**: Controls equipment operations
- **PaymentService**: Processes payments and transactions

#### Domain Layer
- **User**: Abstract base class for all users
- **Member**: Represents gym members
- **Equipment**: Manages gym equipment
- **Payment**: Handles payment processing

### 3. Data Access Layer
- **UserRepository**: User data access
- **MemberRepository**: Member data access
- **EquipmentRepository**: Equipment data access
- **PaymentRepository**: Payment data access

### 4. Database
- **MySQL Database**: Stores all system data

## Key Features
- Clear separation of concerns
- Modular and maintainable design
- Scalable architecture
- Secure data access
- Support for multiple client types

## Design Patterns Used
1. **Layered Architecture**: Clear separation between presentation, business logic, and data access
2. **Repository Pattern**: Abstract data access layer
3. **Service Pattern**: Business logic encapsulation
4. **Domain Model Pattern**: Rich domain objects with business logic 