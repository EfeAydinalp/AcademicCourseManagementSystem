# Academic Course Management System (ACMS)

## Overview

This project is an implementation of an **Academic Course Management System (ACMS)** designed for the **BBM104: Introduction to Programming Laboratory II** course (Fall 2023). The primary objective of this assignment is to apply the concepts of **Inheritance** and **Polymorphism** in object-oriented programming (OOP) using Java. Additionally, the assignment focuses on implementing the **Decorator Design Pattern** and the **Data Access Object (DAO) Design Pattern**.

## Features

The ACMS provides the following functionalities:

1. **Student Management:**
   - Create a new student record.
   - Remove a student from the database using their ID.
   - List all students in alphabetical order.

2. **Course Enrollment Management:**
   - Create a new course enrollment for an existing student.
   - Add various types of assessments to a student's course.
   - Calculate and display the total fees for a student's course enrollment.

3. **Assessment Types:**
   - **Essay-based Assessment:** 10 credits, with additional tasks like Literature Review and Analysis.
   - **Multiple Choice Assessment:** 15 credits, with tasks such as Question Set and Additional Tasks.

## Design Patterns

### 1. Decorator Design Pattern
The **Decorator Pattern** is used to dynamically add additional functionality to individual objects. In this project, it is employed to handle additional tasks for assessments in a flexible and scalable manner.

### 2. Data Access Object (DAO) Pattern
The **DAO Pattern** separates the persistence logic from the application logic, allowing the system to handle data storage and retrieval efficiently.

## Implementation Details

### Student Data File Format
- **Format:** `<studentID>\t<studentName> <studentSurname>\t<phoneNumber>\t<address>\n`
- **Storage:** The student data is stored in a file named `student.txt`.

### Course Enrollment Data File Format
- **Format:** `<enrollmentID>\t<studentID>\n<assessmentType>\t<tasks[1-4]>\n`
- **Storage:** The course enrollment data is stored in a file named `courseEnrollment.txt`.

### Input & Output
- The program reads commands from an input file (e.g., `input.txt`) and writes the results to an output file (`output.txt`).

## How to Run

The input file (input.txt) is going to be given as program arguments. There are text files
(student.txt, courseEnrollment.txt) in your working directory and when your program starts,
you will read these files, then update them according to the input file. In order to test your
program, you should follow the following steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/EfeAydinalp/AcademicCourseManagementSystem.git
    ```
 2. **Navigate to the project directory:**
   ```bash
   cd acms

  ```
3. **Compile the Java files:**
   ```bash
   javac *.java

   ```
4. **Run the program:**
   ```bash
   java Main input.txt

   ```

5. **Check the results**
   The output will be available in output.txt.

   
