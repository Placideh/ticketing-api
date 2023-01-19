# ONLINE BUS TICKET RESERVATION

##  E-Ticket-App reservation that help automate online bus ticket reservation rather than the manual procedures booking for any traveler.

## Project Layout

The project uses the following layout:
```
├── .idea                       
├── .mvn                          
│    
├──────├── src             application file bootstrap 
│      ├── main 
│      │     ├──java 
│      │       │       ├──ticketapi
│      │       │           ├──config
│      │       │           ├──controller
│      │       │           ├──Dto
│      │       │           ├──entity
│      │       │           ├──exception
│      │       │           ├──filter
│      │       │           ├──mails
│      │       │           ├──repository
│      │       │           ├──service
│      │       │           ├──utils
│      │       │           ├── TicketingApplication.java
│      │       │                
│      │       ├──resources
│      │                         
│      │                         
│      │                                     
│      ├── target              
│      ├── .evn                
│      ├── .env.example
│            ├── .gitignore
│            ├── docker-compose.yml
│            ├── Dockerfile
│            ├── HELP.md
│            ├── mvnw
│            ├── mvnw.cmd
│            └── README.md                  
                          

```

## Common Development Tasks

This section describes some common development tasks.

## Get The Project
This section describes how to get the project on your computer.
1. Download the project here [Project](https://github.com/Placideh/ticketing-api.git).
2. Unzip the file.

3. To go to the project Directory. Open terminal and type
 ```ps 
 cd e-ticketing-api 

 ls  #to view all directories and files contains the project 
  
```

## Run The Project
This section describes how to get the project running.

### Prerequisite
1. **Java Jdk 11:** You can find more information about the installation on the [Oracle website](https://www.oracle.com/java/technologies/downloads/)
2. **Intellij Idea:** You can find more information about the installation on the [Intellij IDEA](https://www.jetbrains.com/idea/) 
3. **Docker and Docker compose:** You can find more information about the installation on the [Docker](https://docs.docker.com/compose/install/)

To run the app make sure you have :
- application properties or .env file with all data
- make sure to download all dependencies in pom.xml file
-  ```ps
   - to generate the jar file so as to build the docker image open terminal
   
   make sure you are on the root level
   
   - mvn clean install   #here will get a jar in folder named target   
   
   - docker-compose up --build ticket_api doctor    #run the app in the container
    
   - Or run the project in your IDEA
    ```
    you can access the API documentation by this link: http://localhost:8081/swagger-ui/index.html 
## STRATEGIES USED TO GET THE PROJECT DONE
* Understand project scope
* Gather possible Requirements
* Project Setup
* Define Database Schemas 
* Implement Authentication --> on account creation you will receive an email to confirm your account
* Implement Business Logic
* Test Implemented Feature
* Push Changes to remote repository for better version and tracking

For help getting started with Spring boot development, view the 
[online documentation](https://spring.io/projects/spring-boot), which offers tutorials,
samples, guidance on mobile development, and a full API reference.
