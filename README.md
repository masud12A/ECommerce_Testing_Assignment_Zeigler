#  E-Commerce Functional Testing Automation Framework

This project is an automated functional testing framework for the [Automation Test Store](https://automationteststore.com/), developed using Java, Selenium WebDriver, TestNG, and ExtentReports. It follows the **Page Object Model (POM)** design pattern for maintainability and scalability.

---

##  Project Structure

├── pageObjects/ # All Page Object Model classes
├── testCases/ # TestNG test cases
├── testBase/ # BaseClass with driver setup & teardown
├── utilities/ # Data providers, Excel utilities, reporting
├── testdata/ # Excel file for data-driven testing
├── reports/ # ExtentReports output
├── screenshots/ # Captured screenshots on test failures
├── config.properties # Configuration for environment, browser, app URL
├── master.xml # TestNG suite file
├── pom.xml # Maven dependencies and plugins



---

## ✅ Features

- **Cross-browser & multi-platform testing** (local or remote execution)
- **Data-driven testing** using Excel
- **POM-based architecture** for modular and reusable code
- **ExtentReports 5** for detailed test reporting with screenshots
- **Negative test scenarios** for form validation
- **Console + File Logging** for easy debugging and traceability

---

##  Technologies Used

| Tool               | Purpose                            |
|--------------------|-------------------------------------|
| Java               | Programming language                |
| Selenium WebDriver | Web UI automation                   |
| TestNG             | Test orchestration and assertions   |
| Apache POI         | Excel file read/write               |
| ExtentReports 5    | Test reporting                      |
| Maven              | Build management and dependencies   |

---

##  How to Run the Tests

### 1. Prerequisites

- Java 11+ installed
- Maven installed
- Chrome/Edge/Firefox browsers installed
- (Optional) Selenium Grid for remote execution

---

### 2. Clone the Repository

git clone https://github.com/your-username/ecommerce-testing-assignment.git
cd ecommerce-testing-assignment


## 3. Configure Test Settings
Edit the config.properties file:

execution_env=local      # or 'remote'
browser=chrome           # chrome | firefox | edge
os=windows               # windows | linux | mac
appURL=https://automationteststore.com/

## 4. Run the Tests
Using Maven:

mvn test


## 5. View Reports
HTML Report: Opens automatically after test execution (under /reports/)

Screenshot Folder: All screenshots of failures or validations (/screenshots/)

Log File: report.txt for custom messages and logs


: Test Scenarios Covered: -
-----------------------------

Home page category validation

Random product selection from categories

Add multiple products to cart

Validate cart & proceed to checkout

Negative testing for registration form using data-driven approach




: Test Data :-
-----------
Test data is stored in Excel (/testdata/testdata.xlsx). You can add more negative or positive registration test cases.


Author:-

Name: Masud Ahamed
GitHub: https://github.com/masud12A
