# Library Management TDD Kata Solution

Welcome to my solution for the Library Management Kata! This repository showcases the use of Test-Driven Development (TDD) to solve a programming problem, emphasizing small, incremental commits and clear, concise code.

## Table of Contents

- [Problem Statement](#problem-statement) 
- [Solution](#solution)
- [Features](#features)
- [Setup Instructions](#setup-instructions)
  - [Prerequisites]
  - [Clone the Repository]
  - [Import Project into IntelliJ IDEA]
  - [Build the Project]
  - [Running Tests]
- [Test Coverage](#test-coverage)

## Problem Statement

Create a simple library management system that allows users to perform basic operations such as adding books, borrowing books, returning books, and viewing available books.

For a detailed problem statement and requirements, [click here](Problem_Statement.md).

## Solution

This project follows TDD principles to solve the kata problem. The solution is built with small, incremental commits, ensuring that each feature is developed and tested in isolation, demonstrating effective TDD practices.

## Features

### User Management

- **addUser**:
  - Adds a new user to the library's user catalog.
  - Validates that the user is not null.
  - Throws an exception if the user already exists.

- **getUserByName**:
  - Retrieves a user from the library by their username.
  - Returns `null` if the user is not found.

### Book Management

- **addBook**:
  - Allows a user to add a book to the library's inventory.
  - Validates that the user is a librarian.
  - Ensures the book is not null.
  - Throws an exception if the user is unauthorized.

- **viewAvailableBooks**:
  - Returns a list of all books currently available in the library.
  - Ensures the list is unmodifiable.

- **getBookByISBN**:
  - Retrieves a book from the inventory using its ISBN.
  - Returns `null` if the book is not found.

### Borrowing and Returning Books

- **borrowBook**:
  - Allows a user to borrow a book from the library.
  - Validates that the book is not already borrowed.
  - Ensures the book exists in the inventory.
  - Throws an exception if the book is already borrowed.

- **returnBook**:
  - Allows a user to return a borrowed book to the library.
  - Validates that the book was borrowed by the same user.
  - Ensures the book is returned to the inventory.

- **getBorrowerNameByISBN**:
  - Retrieves the name of the user who borrowed a specific book.
  - Returns `null` if the book is not found.


## Setup Instructions

### Prerequisites

- **Java Development Kit (JDK)**: Ensure JDK 11 or later is installed on your machine.
- **Maven**: Ensure Maven is installed for managing dependencies and building the project.

### Import Project into IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Click on `File` > `Open...`.
3. Select the root directory of the project.
4. Click `OK` to import the project.

### Running Tests

- To run the tests, navigate to the `LibraryTest` & `BookTest` class and click on the run button.
  

## Test Coverage

The current test coverage of this project is **96%**

![image](https://github.com/user-attachments/assets/5714a387-b906-4158-b0ec-449e48f0a745)


