# Data Visualization Tool - System Design

## High-Level Design (HLD)

### 1. System Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                    Data Visualization Tool                  │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────┐     │
│  │   Login     │    │   Signup    │    │   Main      │     │
│  │   Module    │    │   Module    │    │  Dashboard  │     │
│  └─────────────┘    └─────────────┘    └─────────────┘     │
│         │                   │                   │           │
│         └───────────────────┼───────────────────┘           │
│                             │                               │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              Chart Visualization Module             │   │
│  │  ┌─────────┐ ┌─────────┐ ┌─────────┐ ┌─────────┐  │   │
│  │  │   Pie   │ │   Bar   │ │  Line   │ │  Area   │  │   │
│  │  │  Chart  │ │  Chart  │ │  Chart  │ │  Chart  │  │   │
│  │  └─────────┘ └─────────┘ └─────────┘ └─────────┘  │   │
│  │  ┌─────────┐ ┌─────────┐                          │   │
│  │  │ Bubble  │ │Scatter  │                          │   │
│  │  │  Chart  │ │  Chart  │                          │   │
│  │  └─────────┘ └─────────┘                          │   │
│  └─────────────────────────────────────────────────────┘   │
│                             │                               │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              Database Layer (MySQL)                │   │
│  │  ┌─────────────────────────────────────────────┐   │   │
│  │  │              Users Table                    │   │   │
│  │  │  - id (Primary Key)                         │   │   │
│  │  │  - username (Unique)                        │   │   │
│  │  │  - password (Hashed)                        │   │   │
│  │  └─────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

### 2. Component Interaction Flow

```
User Input → Authentication → Main Dashboard → Chart Selection → Data Input → Visualization → Display
     ↓              ↓              ↓              ↓              ↓              ↓
  Login/Signup → DB Validation → UI Navigation → Chart Module → Data Processing → JavaFX Rendering
```

### 3. Key Design Principles

- **Separation of Concerns**: UI, Business Logic, and Data layers are separated
- **Modularity**: Each chart type is implemented as a separate module
- **Scalability**: Easy to add new chart types without modifying existing code
- **Security**: User authentication with database validation
- **User Experience**: Intuitive interface with consistent design patterns

---

## Low-Level Design (LLD)

### 1. Class Diagram

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│    MainApp      │    │   LoginController│    │  SignupController│
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ - start()       │    │ - handleLogin() │    │ - handleSignup()│
│ - main()        │    │ - validateUser()│    │ - createUser()  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   mainscreen    │    │     DBUtil      │    │   ItemData      │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ - initialize()  │    │ + getConnection()│   │ - name          │
│ - showCharts()  │    │ - URL           │    │ - value         │
│ - handleEvents()│    │ - USER          │    │ + getName()     │
└─────────────────┘    │ - PASSWORD      │    │ + getValue()    │
         │              └─────────────────┘    └─────────────────┘
         │                                              │
         ▼                                              │
┌─────────────────────────────────────────────────────┐ │
│              Chart Implementation Classes           │ │
├─────────────────┬─────────────────┬─────────────────┤ │
│   AreaChartApp  │   BarChartApp   │   LineChartApp  │ │
├─────────────────┼─────────────────┼─────────────────┤ │
│ - start()       │ - start()       │ - start()       │ │
│ - createChart() │ - createChart() │ - createChart() │ │
│ - addData()     │ - addData()     │ - addData()     │ │
└─────────────────┴─────────────────┴─────────────────┘ │
├─────────────────┬─────────────────┬─────────────────┤ │
│   PieChartApp   │ BubbleChartApp  │ScatterChartApp  │ │
├─────────────────┼─────────────────┼─────────────────┤ │
│ - start()       │ - start()       │ - start()       │ │
│ - createChart() │ - createChart() │ - createChart() │ │
│ - addData()     │ - addData()     │ - addData()     │ │
└─────────────────┴─────────────────┴─────────────────┘ │
                                                        │
                                                        ▼
                                              ┌─────────────────┐
                                              │  ObservableList │
                                              │   <ItemData>    │
                                              └─────────────────┘
```

### 2. Database Schema

```sql
-- Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Sample Data
INSERT INTO users (username, password) VALUES 
('admin', 'hashed_password_here'),
('user1', 'hashed_password_here');
```

### 3. File Structure

```
src/
├── MainApp.java              # Application entry point
├── mainscreen.java           # Main dashboard controller
├── mainscreen.fxml           # Main dashboard UI layout
├── LoginController.java      # Login logic
├── Login.fxml               # Login UI layout
├── SignupController.java     # Signup logic
├── Signup.fxml              # Signup UI layout
├── DBUtil.java              # Database connection utility
├── styles.css               # Application styling
├── AreaChartApp.java        # Area chart implementation
├── BarChartApp.java         # Bar chart implementation
├── LineChartApp.java        # Line chart implementation
├── PieChartApp.java         # Pie chart implementation
├── BubbleChartApp.java      # Bubble chart implementation
└── ScatterChartApp.java     # Scatter chart implementation
```

### 4. Data Flow Architecture

```
1. User Authentication Flow:
   User Input → FXML Controller → DBUtil → MySQL → Validation → UI Update

2. Chart Creation Flow:
   User Selection → Chart Controller → Data Processing → JavaFX Chart → Display

3. Data Processing Flow:
   Raw Data → ItemData Objects → ObservableList → Chart Rendering → Visualization
```

### 5. Key Design Patterns Used

- **MVC (Model-View-Controller)**: Separates UI (FXML), Logic (Controllers), and Data (Models)
- **Factory Pattern**: Chart creation based on user selection
- **Observer Pattern**: JavaFX ObservableList for real-time chart updates
- **Singleton Pattern**: Database connection management
- **Strategy Pattern**: Different chart implementations

### 6. Error Handling Strategy

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   User Input    │───▶│  Input Validation│───▶│  Error Display  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Database Error  │    │ Chart Error     │    │ System Error    │
│ - Connection    │    │ - Invalid Data  │    │ - Memory        │
│ - Query Failed  │    │ - Empty Dataset │    │ - File Access   │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### 7. Performance Considerations

- **Memory Management**: Efficient use of ObservableList for chart data
- **Database Optimization**: Connection pooling and prepared statements
- **UI Responsiveness**: Asynchronous chart rendering
- **Data Validation**: Client-side validation before server processing

### 8. Security Measures

- **Password Hashing**: Secure password storage in database
- **Input Validation**: Sanitize user inputs to prevent injection
- **Session Management**: User authentication state tracking
- **Error Handling**: Secure error messages without exposing system details

---

## Summary

**High-Level Design** focuses on system architecture, component interactions, and overall system behavior.

**Low-Level Design** delves into implementation details, class relationships, data structures, and specific coding patterns.

This design ensures the application is maintainable, scalable, and follows software engineering best practices. 