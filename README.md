# Employee Reimbursment System (ERS)

## Project Description

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

The back-end system uses JDBC to connect to a Postgres database. The middle tier uses Javalin technology for dynamic Web application development. The front-end view uses HTML/CSS/JavaScript to make an application that can call server-side components in a generally RESTful manner. The middle tier follows proper layered architecture, and has reasonable JUnit test coverage of the service layer. Webpages are styled to be functional and readable.

### State-chart Diagram (Reimbursement Statuses)
![alt text](https://github.com/onivose/project1ERS/blob/main/Project1Specs/state-chart.jpg

### Activity Diagram
![alt text](https://github.com/onivose/project1ERS/blob/main/Project1Specs/activity.jpg)

## Technologies Used

* Javalin - version 4.3.0
* Jackson - version 2.12.4
* PosgreSQL - version 42.3.3
* JUnit - version 5.8.2
* Mockito - version 4.3.1
* JDBC
* HTML/CSS/JavaScript

## Features

List of features ready and TODOs for future development
* Users can log into the application
* Users can view all their past reimbursement requests
* Users can submit new reimbursement requests based on type and ammount requested
* Finance Managers can log into a seperarte portal
* Finance Managers view and filter all past requests based on type or status
* Finance Managers can approve or deny pending requests

To-do list:
* Restructure Manager Dashboard page into a table view rather then card view
* Add improved JS functionality to Register Page

## Getting Started

> Note: You will need openjdk11 or later

### Git Commands:

* git clone https://github.com/onivose/project1ERS
* git pull 

### Environment Setup:

* Open the project in any IDE
* Run MainDriver.java
* Navigate to http://localhost:7220/ in any web browser
* Enjoy!

## Usage
### Use Case Diagram
![alt text](https://github.com/onivose/project1ERS/blob/main/Project1Specs/use-case.jpg)

### Login Page
![alt text](https://github.com/onivose/project1ERS/blob/main/Project1Specs/login.jpg)

### Register Page
![alt text](https://github.com/onivose/project1ERS/blob/main/Project1Specs/register.jpg)

### Employee Dashboard
![alt text](https://github.com/onivose/project1ERS/blob/main/Project1Specs/employee.jpg)

### Manager Dashboard
![alt text](https://github.com/onivose/project1ERS/blob/main/Project1Specs/manager.jpg)
