# 🦁 Zoo Management App

![Project Preview](project%20preview.png)

A comprehensive JavaFX-based zoo management system that provides role-based access control and management capabilities for different types of users including visitors, zookeepers, managers, and super administrators.

## 📋 Table of Contents

- [Features](#features)
- [System Architecture](#system-architecture)
- [User Roles](#user-roles)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Screenshots](#screenshots)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### 🔐 Authentication System

- Secure login system with hashed passwords
- Role-based access control (Visitor, Zookeeper, Manager, Super Admin)
- User registration functionality
- Password recovery system

### 👥 User Management

- **Visitors**: Can browse zoo information and purchase tickets
- **Zookeepers**: Manage animals in their assigned departments
- **Managers**: Oversee zoo operations and staff
- **Super Admin**: Full system control and user management

### 🐾 Animal Management

- Support for multiple animal categories:
  - **Aquatic Animals**: Fish, marine mammals, etc.
  - **Birds**: Various bird species
  - **Wild Animals**: Land mammals and other wildlife
- Animal information tracking (name, ID, gender, nationality, insurance, food type, department)

### 🎫 Ticketing System

- Ticket purchasing and management
- Department-specific tickets
- Pricing management

### 💾 Data Persistence

- File-based data storage system
- Automatic data loading and saving
- Support for user data, animal data, and ticket information

## 🏗️ System Architecture

The application follows an object-oriented design with clear separation of concerns:

```
ZooApp/
├── Existings/          # Core domain classes
│   ├── Living.java     # Base class for all living entities
│   ├── Person.java     # Base class for all human users
│   ├── Animal.java     # Base class for all animals
│   └── ...specific implementations
├── sample/             # UI controllers and main application logic
│   ├── Main.java       # Application entry point
│   ├── Controller.java # Main login controller
│   └── ...other controllers
└── resources/          # FXML files and images
    ├── *.fxml          # UI layouts
    └── images/         # Application images
```

## 👤 User Roles

### 🎟️ Visitor

- Browse zoo information
- Purchase tickets for different departments
- View available animals and departments

### 👨‍🔬 Zookeeper

- Manage animals in assigned department
- Update animal information
- Track animal care and feeding

### 👔 Manager

- Oversee zoo operations
- Manage staff assignments
- Monitor department performance

### 🔑 Super Administrator

- Complete system access
- User account management
- System configuration
- Financial oversight

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java 8 or higher** installed
- **JavaFX** runtime (included in Java 8, separate download for Java 11+)
- **SQLite JDBC driver** (sqlite-jdbc-3.20.0.jar)
- An IDE that supports JavaFX development (IntelliJ IDEA, Eclipse, etc.)

## 🚀 Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/ZooApp.git
   cd ZooApp
   ```

2. **Set up JavaFX**

   - For Java 8: JavaFX is included
   - For Java 11+: Download JavaFX SDK and configure module path

3. **Add dependencies**

   - Ensure `sqlite-jdbc-3.20.0.jar` is in your classpath
   - Add JavaFX libraries to your project

4. **Configure IDE**

   - Import the project into your IDE
   - Set up run configurations for JavaFX application
   - Ensure proper module path and VM arguments

5. **Initialize data files**
   - The application will create necessary data files on first run
   - Default super admin credentials: `username: Admin, password: 2018166324`

## 🎮 Usage

### Starting the Application

1. Run the `Main.java` class
2. The login screen will appear with the welcome interface

### Login Process

1. Enter your username and password
2. Click "Enter" to login
3. The system will redirect you to your role-specific dashboard

### Default Credentials

- **Super Admin**:
  - Username: `Admin`
  - Password: `2018166324`

### Registration

1. Click "Register" on the main screen
2. Fill in required information
3. Choose your role type
4. Submit registration

## 📁 Project Structure

```
src/
├── Existings/                    # Domain model classes
│   ├── Living.java              # Base class for all living entities
│   ├── Person.java              # Base class for people
│   ├── Animal.java              # Base class for animals
│   ├── Visitor.java             # Visitor implementation
│   ├── Manager.java             # Manager implementation
│   ├── ZooKeeper.java           # Zookeeper implementation
│   ├── SuperAdmin.java          # Super admin implementation
│   ├── Bird.java                # Bird animal type
│   ├── Aquatic.java             # Aquatic animal type
│   ├── Wild.java                # Wild animal type
│   ├── Ticket.java              # Ticket system
│   ├── Department.java          # Department management
│   └── ZOO.java                 # Main zoo class with static data
├── sample/                      # UI controllers and application logic
│   ├── Main.java                # Application entry point and data management
│   ├── Controller.java          # Main login controller
│   ├── manager.java             # Manager dashboard controller
│   ├── visitor.java             # Visitor dashboard controller
│   ├── zookeeper.java           # Zookeeper dashboard controller
│   ├── superadmin.java          # Super admin dashboard controller
│   ├── register.java            # Registration controller
│   ├── forgetpass.java          # Password recovery controller
│   └── dbConnection.java        # Database utilities (commented out)
└── resources/                   # UI resources
    ├── sample.fxml              # Main login screen
    ├── visitor.fxml             # Visitor interface
    ├── manager.fxml             # Manager interface
    ├── zookeeper.fxml           # Zookeeper interface
    ├── superadmin.fxml          # Super admin interface
    ├── registration.fxml        # Registration form
    ├── forgetpass.fxml          # Password recovery form
    ├── Background.css           # Application styling
    └── images/                  # Application images
        ├── aquatic.png          # Animal category icons
        ├── bird.png
        ├── wild.png
        ├── visitor.png          # Role icons
        ├── manager.png
        ├── zookeeper.png
        ├── superadmin.png
        └── ...other UI images
```

## 🖼️ Screenshots

_(Add screenshots of your application here)_

- Login Screen
- Visitor Dashboard
- Manager Interface
- Animal Management
- Ticket Purchasing

## 🛠️ Technologies Used

- **Java 8+** - Core programming language
- **JavaFX** - Desktop application framework
- **FXML** - UI markup language
- **CSS** - Styling and theming
- **File I/O** - Data persistence
- **SQLite** - Database support (prepared but not actively used)

## 🔒 Security Features

- Password hashing using Java's `hashCode()`
- Role-based access control
- Input validation
- Secure session management

## 🐛 Known Issues

- Database connection code is commented out (file-based storage used instead)
- Some UI controllers have placeholder implementations
- Limited error handling in some areas

## 🚀 Future Enhancements

- [ ] Implement actual database integration
- [ ] Add more comprehensive error handling
- [ ] Implement advanced reporting features
- [ ] Add animal health tracking
- [ ] Include visitor analytics
- [ ] Mobile app integration
- [ ] Online ticket booking

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

For support, please contact:

- Email: meysamtaherinia@yahoo.com
- Phone: 921 604 3785

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**MeysamTN**

- Created the ZooApp management system
- Designed the role-based architecture
- Implemented the JavaFX user interface

---

_This project was created as a comprehensive zoo management solution using JavaFX and object-oriented programming principles._
