# Student Record Management System

Welcome to the Student Record Management System repository! This Java application allows you to manage student information, register students for courses, and handle payments.

## Features

1. **Register for a Course:**
   - Collect student details, including name, year of joining, and course selection.
   - Generate a unique student ID for each registration.

2. **Payment:**
   - View pending fees for a student.
   - Make payments to reduce the pending fee balance.

3. **View Students Details:**
   - Retrieve and display all student records stored in the database.

## Prerequisites

- Java Development Kit (JDK)
- MySQL Database

## Setup

1. **Clone the repository:**

    ```bash
    git clone https://github.com/abdullah65347/Student_Record_Management.git
    ```

2. **Import the project into your preferred Java IDE.**

3. **Set up the MySQL database:**
   - Create a database named `StudentRecordManagement`.
   - Update the database connection details in the code (`Main.java`, `Student.java`, `Payment.java`).

4. **Run the `Main.java` file to start the application.**

## Database Structure
The system uses a MySQL database named StudentRecordManagement with the following table:

```sql
CREATE TABLE student (
    StudentID INT PRIMARY KEY,
    Name VARCHAR(255),
    YearOfJoining VARCHAR(4),
    CourseOpted VARCHAR(50),
    PendingFee DOUBLE
);
```

## Usage

1. Choose an option from the main menu:
   - Register for a course (Option 1)
   - Payment operations (Option 2)
   - View students' details (Option 3)
   - Exit the program (Option 4)

2. Follow the prompts to perform the selected operation.

3. Continue using the application until you choose to exit.

## Contributors

- [Abdullah Farooqi](https://github.com/abdullah65347)

As the sole contributor to this project, feel free to enhance and customize the system according to your needs. Contributions, suggestions, and feedback are always welcome.

## Acknowledgements
The project was developed individually with the aim of creating a practical and efficient student record management solution.
Thank you for choosing the Student Record Management System. Enjoy the simplicity of managing student records!
