# Selenium Webdriver + Cucumber + Java

## Project description
Hello,
my name is Bartosz, and this project is about my path in learning programming.
<br>This is an end-to-end automated test project written in Java, covering everything from user creation to placing an order and verifying its status.

## Getting Started
### Prerequisites
Make sure you have the following installed:
* Java 24 (Oracle OpenJDK)
* Maven 3.x
* Google Chrome and a compatible ChromeDriver available in your system PATH

### Installation
1. Clone the repository:
   https://github.com/BartoszPrzy/SeleniumWebdriverProject.git
2. All required dependencies are listed in the pom.xml file. Maven will automatically download them on first run.

To install dependencies manually (optional step):

bash:
mvn dependency:resolve
Or simply proceed with the test run (Maven will resolve them during mvn test).

##  How to Run the Test
1. Move to a project directory
   cd SeleniumWebdriverProject
2. Run tests
   mvn test

## Features
* Creates a user by randomly selecting gender and corresponding data for the chosen gender
* Creates an address using data provided in the examples table
* Verifies that the entered data matches the data from the table
* Selects a product from the store’s homepage
* Selects the size and quantity from the examples table
* Adds the product to the cart
* Deletes the old address and verifies that the deletion confirmation message is displayed
* Enters a new address
* Selects in-store pickup and payment by check
* Places the order
* Takes a screenshot of the order, saves it in a local folder, and creates the folder if it does not already exist
* Navigates to the order status and verifies that the amount and status are correct
* Closes the browser

## Technologies 
Project is created with:
* HTML
* Java
* CSS
* Cucumber
* Selenium
* Gherkin
