# TestCase-Management

**Overview**
This is a Spring Boot-based Test Case Management Service that provides a RESTful API for managing test cases. The service is backed by MongoDB and supports CRUD operations, pagination, filtering, and Swagger API documentation.

📌 Features
Create, Read, Update, and Delete (CRUD) test cases.
Pagination and filtering based on status and priority.
Swagger API documentation for easy API exploration.
Uses MongoDB for storing test case data.

**Project Structure**
 TestCaseManagement
 ┣  src/main/java/com/example/testcasemanagement
 ┃ ┣  controller 
 ┃ ┣  service     
 ┃ ┣  repository 
 ┃ ┣  model       
 ┃ ┣  config     
 ┣  pom.xml       
 ┣  README.md    
 ┗ application.properties 

 **🛠️ Setup & Installation**
1️⃣ Prerequisites
Java 17+ (Make sure it's installed: java -version)
Maven (mvn -version)
MongoDB (Ensure MongoDB is running: mongod)
Git (To clone the repository)

2️⃣ Clone the Repository
git clone https://github.com/Gedala-Jayasrinu/TestCase-Management.git
cd TestCase-Management

3️⃣ Configure MongoDB Connection
Modify src/main/resources/application.properties:
spring.data.mongodb.uri=mongodb://localhost:27017/testcases_db

4️⃣ Build & Run
The service will start at http://localhost:8080.

**📜 API Endpoints**
Method	Endpoint	Description
POST	/api/testcases	Create a new test case
GET	/api/testcases?page=1&size=10	Get paginated test cases
GET	/api/testcases?status=Passed&priority=High	Get filtered test cases
PUT	/api/testcases/{id}	Update a test case
DELETE	/api/testcases/{id}	Delete a test case

**After starting the application, visit:**
http://localhost:8080/swagger-ui/index.html

**📝 Sample Test Data**
You can manually insert test cases into MongoDB or use Postman.
Here’s a sample request for creating a test case:
{
  "title": "Login Test",
  "description": "Test the login functionality",
  "status": "Passed",
  "priority": "High"
}
