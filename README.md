# healthcare-enrollee-management-service
HealthCare Enrollee Management service is a Java Spring boot Microservice to manage enrollees into health care program

## About the Service

HealthCare Enrollee Management application is simple REST service using Spring boot framework and Java 8 and Maven. It uses MongoDB (NoSQL) database to connect , store, retrieve, modify and delete Enrollees and manage respective Dependents over several REST endpoints. It uses Spring Actuator to provide app metrics, info, healthcheck etc. It supports JSON request and responses. All the APIs are self documented by Swagger2 using annotations
This service addtionally provides Dockerfile and docker-compose.yml for easy setup and running on docker platform

## Requirements
 Java -1.8
 
 Maven - 3.*
 
 MongoDB 
 
 Docker 
 
 GIT 

## Steps to Setup and Run Application in Docker Environment
 1. git clone https://github.com/srikanthrm11/healthcare-enrollee-management-service.git
 
 2. build the jar file using maven command "mvn clean install"
 
 3. build docker image from Dockerfile using following command
			" docker build -t enrollee-app:1.0 . "

 4. start mongodb and application containers using docker-compose 
		" docker-compose up"
	This will start application and it was able to connectec to mongodb properly

## SWAGGER documentation and Operational endpoints
	you can view swagger by accessing following endpoints in browser
	http://localhost:8080/swagger-ui.html
	plain JSON swagger can get from here 
	http://localhost:8080/v2/api-docs
  Springboot actuator provide following endpoints to see /metrics, /appinfo, /heapdump

  http://localhost:8080/actuator/

## POSTMAN Collection for Testing is available here
	https://github.com/srikanthrm11/postman-col-health-care-enrollee-service.git
	
## Enrollee Management Related REST endpoints

### Add Enrollee 
	
POST /api/add/enrolle HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accepts : application/json

Request payload:
{
	"activationStatus": false,
	"phoneNumber": "515555555",
	"dependents": [{
		"id": "d1",
		"name": "dependent1",
		"birthDate": "today"
	}, {
		"id": "d2",
		"name": "dependent2",
		"birthDate": "today2"
	}],
	"id": "1",
	"name": "user1",
    "birthDate":"10-15-1982"
}

### GET Enrollee by Id
GET /api/get/enrolle/{id} HTTP/1.1
Host: localhost:8080
Accepts : application/json

Response: HttpStatus.OK
{
    "id": "1",
    "name": "user1",
    "birthDate": "10-15-1982",
    "activationStatus": false,
    "phoneNumber": "515555555",
    "dependents": [
        {
            "id": "d1",
            "name": "dependent1",
            "birthDate": "today"
        },
        {
            "id": "d2",
            "name": "dependent2",
            "birthDate": "today2"
        }
    ]
}

### PUT Modify Enrollee/Dependents
PUT /api/update/enrolle HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accepts : application/json

RequestBody:
{
    "id": "1",
	"modifyEmployee" : true ;
	"updateDependents" : "ADD";
    "name": "user1Updated",
    "birthDate": "10-15-1972",
    "activationStatus": true,
    "phoneNumber": "515555555",
    "dependents": [
        {
            "id": "d1",
            "name": "dependent1",
            "birthDate": "today"
        },
        {
            "id": "d2",
            "name": "dependent2",
            "birthDate": "today2"
        }
    ]
}


	





 
		
 
 

