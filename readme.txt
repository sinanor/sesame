System requirements: Java 8 JDK, Maven, Docker

To build the application go to the root directory and execute commands as below, 

$mvn clean install
$docker-compose up

The first command will build a spring boot application.
The second command is for the create and run docker images for the application.

After executing the commands above the application is ready to accept rest calls.

http://localhost:8087/api/    is the root of the rest service

To create an appointment

http://localhost:8087/api/appointments/

Request: POST

Request Body:

{"appointmentDate":"2018-12-19T20:16:12.780268","appointmentDuration":120,"nameOfDoctor":"Micheal Wright","status":"BOOKED","price":4999}

Response:

{
    "id": "1a5c070a-52b9-4aa0-ac7c-6c016488f712",
    "createdAt": "2019-02-02T16:57:23.229",
    "updatedAt": null,
    "appointmentDate": "2018-12-19T20:16:12.780268",
    "appointmentDuration": 120,
    "nameOfDoctor": "Micheal Wright",
    "status": "BOOKED",
    "price": 4999
}

To get a specific appointment

Request: GET

Request url with path param

http://localhost:8087/api/appointments/1a5c070a-52b9-4aa0-ac7c-6c016488f712

Response:

{
    "id": "1a5c070a-52b9-4aa0-ac7c-6c016488f712",
    "createdAt": "2019-02-02T16:57:23",
    "updatedAt": null,
    "appointmentDate": "2018-12-19T20:16:13",
    "appointmentDuration": 120,
    "nameOfDoctor": "Micheal Wright",
    "status": "BOOKED",
    "price": 4999
}

To Update the status of an appointment

http://localhost:8080/api/appointments/892133bf-55cb-4732-99b8-feeb16e377fd/AVAILABLE

Request: PATCH

Response:
{
    "id": "892133bf-55cb-4732-99b8-feeb16e377fd",
    "createdAt": "2019-02-01T12:03:35",
    "updatedAt": "2019-02-01T17:12:37.167285",
    "appointmentDate": "2018-12-19T20:16:13",
    "appointmentDuration": 120,
    "nameOfDoctor": "Micheal Wright",
    "status": "AVAILABLE",
    "price": 4999
}

To get all the appointments between dates

http://localhost:8080/api/appointments/2018-02-01T11:03:36/2020-02-01T12:03:35

Request: GET

Response:

[
    {
        "id": "98b44760-2304-4f7c-9978-415c1533f26b",
        "createdAt": "2019-02-01T03:18:07",
        "updatedAt": null,
        "appointmentDate": "2018-12-19T20:16:13",
        "appointmentDuration": 120,
        "nameOfDoctor": "Micheal Wright",
        "status": "BOOKED",
        "price": 4999
    },
    {
        "id": "892133bf-55cb-4732-99b8-feeb16e377fd",
        "createdAt": "2019-02-01T12:03:35",
        "updatedAt": "2019-02-01T17:51:52",
        "appointmentDate": "2018-12-19T20:16:13",
        "appointmentDuration": 120,
        "nameOfDoctor": "Micheal Wright",
        "status": "BOOKED",
        "price": 4999
    }
]
