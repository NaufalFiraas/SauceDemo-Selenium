# SauceDemo Test Automation Framework

Automation testing project for https://www.saucedemo.com/  
Built using Selenium, Java, TestNG with Page Object Model design pattern.


## 🚀 Project Overview
This project automates end-to-end test scenarios for SauceDemo web application including:

- Login (valid & invalid scenarios)
- Product listing validation
- Add to cart
- Remove item from cart
- Product detail page validation
- Checkout flow until checkout overview page
- Error message validation
- Screenshot capture on failure
- HTML execution report generation

The framework is designed with scalability and maintainability in mind.



## 🛠 Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Extent Reports
- Page Object Model (POM)
- Jenkins (CI ready)



## 🏗 Framework Design

The framework follows Page Object Model structure:
- src:
  - base
  - pages
  - tests
  - utils
  - listeners

Key Components:

- **BaseTest** → WebDriver setup & teardown
- **Pages** → Page interaction methods
- **Utils** → Screenshot utility & logging
- **Listener** → Capture screenshot on failure & generate report
- **ExtentReportManager** → Reporting configuration

## ▶ How to Run the Tests

### Run via Maven:
* mvn clean
* mvn test -Dsurefire.suiteXmlFiles=smoke_suite.xml (only run test case by group 'smoke')
* mvn test -Dsurefire.suiteXmlFiles=regression_suite.xml (only run test case by group 'regression')

### After execution, open the report on:
#### /reports/ExtentReport.html



## 📊 Reporting
* HTML report using Extent Reports
* Screenshot automatically captured for failed test cases
* Execution timestamp and detailed step logs
<img width="1280" height="684" alt="image" src="https://github.com/user-attachments/assets/420f7e96-602b-43a2-b005-318ebf3a8989" />



## 🎯 Automation Scope

 This project demonstrates:
- Functional UI automation
- Negative & positive test scenarios
- Assertion validation
- Structured test grouping
- Reusable test architecture
- Failure handling & evidence collection



## 👨‍💻 Author
#### Naufal Firaas
#### QA Engineer | Automation Enthusiast
#### Open to remote & freelance opportunities
