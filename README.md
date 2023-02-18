
# Clinic Appointment REST API

A simple REST API as a personal project for my journey in self-learning Java and Spring Boot.




## Entities for basic CRUD functionalities

- Patient
- Doctor
- Appointment



## Design Architecture

<h3>General Architecture of the Project</h3>

<img
  src="General Architecture.png"
  alt="General Architecture of the REST API"
  style="display: inline-block; margin: 0 auto; max-width: 300px">

This is the general design architecture of clinic appointment REST API.

Starting from the Admin (client), they can directly create, read, update and delete the three entities - Patient, Doctors, and Appointment.

Currently learning Spring Security to add authentication process for admin privileges (e.g. Secretary and Doctors). 

Both doctor and patient entities have basic crud functionalities. For creating a new appointment, it requires a doctor id and patient id.

<h4>Entities</h4>

<img
  src="Entity Architecture.png"
  alt="Entity architecture"
  style="display: inline-block; margin: 0 auto; max-width: 300px">

This is the general design pattern for each entities. Everytime a client sends an HTTP Request, the controller layer will first handle the request - identifying the HTTP Method and sending the appropriate response from the server.

The service layer acts a separate layer that will handle all the business logic.

The repository layer, as the name implies, will handle all the transaction to and from the database. For this case, the database used is MySQL.



## 🔗 My Linkedin

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/joshua-sumagang-7208b8236/)



## Tools and Dependencies used

**Framework:** Spring Boot

**Dependencies:** Spring MVC, Spring Data JPA, DevTools, Project Lombok, MySQL Driver, Hibernate Validator

**Database:** MySQL

**Testing API:** Postman

