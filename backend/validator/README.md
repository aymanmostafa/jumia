# Customers' phone number validator API

API to detect customers' countries using their phone numbers and validate those phone numbers.
  
---

## Getting Started

This application was made by Java v8 using Spring boot framework v2.4.5 and the following are applied:

* SOLID principles.
* Restful best practices.
* Unit tests using jUnit.
* Swagger.
* Lombok.
* SonarList analysis.

---
 
### Run

* mvn spring-boot:run
* open http://localhost:8080
   	
---

### Swagger

* http://localhost:8080/swagger-ui.html
   	
---

### Docker 

* mvn clean install
* docker build -t validatorAPI-image
* docker run -d -p 8080:8080 --name validatorAPI-container validatorAPI-image:latest
