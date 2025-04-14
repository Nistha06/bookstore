# Bookstore API Automation Framework 🚀📚

A TestNG-based automation framework for testing Bookstore API endpoints using REST Assured and Allure reporting.

---

## Features ✨
- ✅ **TestNG** test framework integration
- 🔧 **REST Assured** for API testing
- 📊 **Allure Reports** for detailed test reporting
- 📦 **Maven** for dependency management
- 🔄 Request chaining implementation
- ✔️ Positive & negative test scenarios
- 🧪 CRUD operations testing (Create, Read, Update, Delete)

---

## Project Structure 📂
```text
bookstore-api-automation/
├── 📁 src/test/java/
│   ├── 📁 com/apiautomation/baseTest        # Base test classes
│   ├── 📁 com/apiautomation/pojo           # POJO classes
│   ├── 📁 com/apiautomation/test           # Test cases
│   ├── 📁 endPointUrls                     # API endpoint URLs
│   └── 📁 utils                            # Utility classes
│
├── 📁 src/test/resources/
│   ├── 📄 config.properties                # Configuration file
│   └── 📄 testng.xml                       # TestNG configuration
│
├── 📁 allure-results                       # Allure test results
├── 📁 test-output/                         # TestNG HTML reports
│   └── 📄 emailable-report.html
└── 📄 pom.xml                              # Maven dependencies


<h2><b>Prerequisites</b></h2>

Java 11+
Maven
Eclipse IDE

Setup & Installation

Clone the repository
git clone https://github.com/yourusername/bookstore-api-automation.git

Import as Maven project
In Eclipse/IntelliJ:
File -> Import -> Existing Maven Project

Configure Credentials
Update src/test/resources/config.properties:
base.url=https://api.bookstore.com
username=your_username
password=your_password

Install Dependencies
mvn clean test


Run All Tests:
mvn clean test

Generate Allure Report:
allure serve allure-results

Run Specific Test Suite:
mvn test -Dsuite=testng.xml

Reporting
Allure Reports
Generates interactive HTML reports

Located in /allure-results directory

View report:
allure serve allure-results

TestNG Reports
HTML report: test-output/emailable-report.html

XML reports: test-output/testng-results.xml

Test Coverage ✅
Authentication
✔️ User signup

✔️ User login

✔️ Invalid credentials handling

Book Operations
📖 Create new book

🔍 Get book by ID

📚 Get all books

✏️ Update book

🗑️ Delete book





mvn clean test





Reporting

Allure reports are generated in allure-results
TestNG reports are generated in target/surefire-reports

Test Coverage

Authentication
User signup
User login
Invalid credentials handling
Book Operations
  -Create new book
  -Get book by ID
  -Get all books
  -Update book
  -Delete book

Contributing

Fork the project
Create your feature branch
Commit your changes
Push to the branch
Create a Pull Request
