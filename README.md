# Interactive Data Visualisation Tool

A JavaFX application for creating various types of charts to visualize data interactively. The application features a user authentication system and connects to a MySQL database to manage user data.

## Features

- **User Authentication:** Secure login and signup system for users.
- **Multiple Chart Types:** Visualize data with a variety of charts:
  - Area Chart
  - Bar Chart
  - Bubble Chart
  - Line Chart
  - Pie Chart
  - Scatter Chart
- **Interactive Visualizations:** Charts are rendered using JavaFX, providing an interactive experience.

## Technologies Used

- **Java**
- **JavaFX**
- **MySQL** for the database.

## Prerequisites

Before you begin, ensure you have the following installed:

1.  **Java Development Kit (JDK)**: Version 11 or higher.
2.  **JavaFX SDK**: Version 11 or higher. You can download it from [here](https://gluonhq.com/products/javafx/).
3.  **MySQL Server**: The application requires a MySQL database.
4.  **MySQL Connector/J**: The JDBC driver for MySQL. You will need to add the JAR file to your project's classpath. You can download it from the [MySQL website](https://dev.mysql.com/downloads/connector/j/).

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/PranishaCoder/Data-Visualisation-Tool.git
cd Data-Visualisation-Tool
```


### 2. Database Setup

1.  Make sure your MySQL server is running.
2.  Create a new database named `datavisualisationdb`.
    ```sql
    CREATE DATABASE datavisualisationdb;
    ```
3.  The application expects a table for user data. You will need to create a `users` table. The exact schema depends on the application's needs for signup and login. A possible schema could be:
    ```sql
    USE datavisualisationdb;
    CREATE TABLE users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(50) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL
    );
    ```
4.  Update the database credentials in `src/DBUtil.java` if they differ from the defaults .

    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/datavisualisationdb";
    private static final String USER = "your_mysql_user";
    private static final String PASSWORD = "your_mysql_password";
    ```

### 3. Compiling and Running the Application

These instructions are for compiling and running the application from the command line.

#### Add JavaFX to your project

Make sure you have downloaded the JavaFX SDK and you know the path to its `lib` directory.

#### Add MySQL Connector/J to your project

Make sure you have downloaded the MySQL Connector/J JAR file and you know its path.

#### Compile

Open a terminal in the project's root directory.

**Windows:**
```powershell
javac --module-path "path\to\javafx-sdk\lib;path\to\mysql-connector-j.jar" --add-modules javafx.controls,javafx.fxml,java.sql -d out src/*.java
```

**Linux/macOS:**
```bash
javac --module-path /path/to/javafx-sdk/lib:/path/to/mysql-connector-j.jar --add-modules javafx.controls,javafx.fxml,java.sql -d out src/*.java
```

Replace `"path\to\javafx-sdk\lib"` and `"path\to\mysql-connector-j.jar"` with the actual paths. This command will compile the source files and place the `.class` files in an `out` directory.

#### Run

**Windows:**
```powershell
java --module-path "path\to\javafx-sdk\lib;path\to\mysql-connector-j.jar" --add-modules javafx.controls,javafx.fxml,java.sql -cp out MainApp
```

**Linux/macOS:**
```bash
java --module-path /path/to/javafx-sdk/lib:/path/to/mysql-connector-j.jar --add-modules javafx.controls,javafx.fxml,java.sql -cp out MainApp
```

The application should now start, displaying the login window.

## Usage

Once the application is running, you can:
1.  **Sign up** for a new account.
2.  **Login** with your credentials.
3.  After logging in, you will be taken to the main screen where you can choose a chart type to visualize data.

## Screenshots

Below are some screenshots of the application dashboard.

![Login and Signup](https://github.com/user-attachments/assets/b191c400-b8d5-48e1-b6c3-7a0d893b7942)
![Main Dashboard](https://github.com/user-attachments/assets/dad2df6b-124c-40f4-a3b4-3490ed4b025a)
![Pie Chart Example](https://github.com/user-attachments/assets/3d90cdca-653e-4810-9e3a-dd97c555dc67)

## Contributing

Contributions are welcome! Please feel free to submit a pull request. 