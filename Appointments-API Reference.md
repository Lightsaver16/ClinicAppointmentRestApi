
## API Reference

<h3>Appointments</h3>

#### Create new appointment (throws exception when doctor is not available)

```http
  POST /api/appointments/create
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `doctorId` | `Long` | **Required**. Primary key of a doctor. Must be available. |
| `patientId` | `Long` | **Required**. Primary key of a patient. |
| `appointmentDate` | `String` | **Required**. Date of appointment. Format - "YYYY-MM-DD" |
| `appointmentTime` | `String` | **Required**. Time of appointment. Format - "HH:MM" |

```
    Sample payload:

    {
        "doctorId": 1,
        "patientId": 1,
        "appointmentDate": "2023-02-25",
        "appointmentTime": "10:00"
    }
```



#### Get all appointments - comes with pagination and sorting

```http
  GET /api/appointments
```

| Parameter | Default Value     | Description                |
| :-------- | :------- | :------------------------- |
| `pageNo` | `1` | **Not Required**. Pagination number. |
| `pageSize` | `10` | **Not Required**. Number of resources per page. |
| `sortBy` | `appointmentId` | **Not Required**. The column(field) that the sorting will be  based upon. |
| `order` | `ascending` | **Not Required**. Order of the sorting - can be ascending or descending. |

Example URL:
```
  http://localhost:8080/api/appointments
```

This API endpoint will return an appointment DTO:

```
[
  {
        "appointmentId": 1,
        "appointmentDate": "2023-02-05",
        "appointmentTime": "4:00 PM",
        "patientFirstName": "Joshua",
        "patientLastName": "Sumagang",
        "doctorFirstName": "Elijah",
        "doctorLastName": "Garpia",
        "status": "BOOKED"
    },
    {
        "appointmentId": 2,
        "appointmentDate": "2023-01-27",
        "appointmentTime": "11:00 AM",
        "patientFirstName": "Clark",
        "patientLastName": "Cabaron",
        "doctorFirstName": "Francis",
        "doctorLastName": "Funandog",
        "status": "BOOKED"
    },
    {
        "appointmentId": 3,
        "appointmentDate": "2023-02-10",
        "appointmentTime": "11:00 AM",
        "patientFirstName": "Clark",
        "patientLastName": "Cabaron",
        "doctorFirstName": "Troy",
        "doctorLastName": "Permejo",
        "status": "BOOKED"
    },
    {
        "appointmentId": 4,
        "appointmentDate": "2023-02-10",
        "appointmentTime": "1:00 PM",
        "patientFirstName": "Joshua",
        "patientLastName": "Sumagang",
        "doctorFirstName": "Troy",
        "doctorLastName": "Permejo",
        "status": "BOOKED"
    }
 ]
```

#### Get all appointments by patient name.

```http
  GET /api/appointments/patient-name/
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `firstName` | String | **Required**. Patient's First Name. |
| `lastName` | String | **Required**. Patient's Last Name. |

Example URL:
```
  http://localhost:8080/api/appointments/patient-name?firstName=Joshua&lastName=Sumagang
```
Example Response:
```
  {
        "appointmentId": 1,
        "appointmentDate": "2023-02-05",
        "appointmentTime": "4:00 PM",
        "patientFirstName": "Joshua",
        "patientLastName": "Sumagang",
        "doctorFirstName": "Elijah",
        "doctorLastName": "Garpia",
        "status": "BOOKED"
    },
    {
        "appointmentId": 4,
        "appointmentDate": "2023-02-10",
        "appointmentTime": "1:00 PM",
        "patientFirstName": "Joshua",
        "patientLastName": "Sumagang",
        "doctorFirstName": "Troy",
        "doctorLastName": "Permejo",
        "status": "BOOKED"
    },
    {
        "appointmentId": 6,
        "appointmentDate": "2023-02-10",
        "appointmentTime": "2:00 PM",
        "patientFirstName": "Joshua",
        "patientLastName": "Sumagang",
        "doctorFirstName": "Troy",
        "doctorLastName": "Permejo",
        "status": "BOOKED"
    }
 }
```

#### Get all appointments by date.

```http
  GET /api/appointments/query
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `date` | String | **Required**. Appointment Date. |

Example URL:
```
  http://localhost:8080/api/appointments/query?date=2023-01-27
```
Example Response:
```
  [
    {
        "appointmentId": 2,
        "appointmentDate": "2023-01-27",
        "appointmentTime": "11:00 AM",
        "patientFirstName": "Clark",
        "patientLastName": "Cabaron",
        "doctorFirstName": "Francis",
        "doctorLastName": "Funandog",
        "status": "BOOKED"
    }
]
```
#### Cancel an appointment by id.

```http
  PUT /api/appointments/{id}/cancel
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | Long | **Required**. Appointment Id. |

Example URL:
```
  http://localhost:8080/api/appointments/2/cancel
```
Example Response:
```
  Appointment cancelled.
```

#### Change date and time of appointment by appointment id. Must have at least either a new date or a new time.

```http
  PUT /api/appointments/{id}/change-date-time
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `appointmentId` | Long | **Required**. Appointment Id. |
| `appointmentDate` | String | **Not Required**. New Appointment Date. |
| `appointmentTime` | String | **Not Required**. New Appointment Time. |

Example URL:
```
  http://localhost:8080/api/appointments/1/change-date-time
```
Example Payload:
```
  {
    "appointmentDate": "2023-02-26",
    "appointmentTime": "3:00 PM"
  }
```
#### Change appointment's doctor by appointment id and doctor id.

```http
  PUT /api/appointments/{appointmentId}/doctors/{doctorId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `appointmentId` | Long | **Required**. Appointment Id. |
| `doctorId` | Long | **Required**. New Doctor Id. |

Example URL:
```
  http://localhost:8080/api/appointments/1/doctors/2
```
Example Response:
```
    {
    "appointmentId": 1,
    "appointmentDate": "2023-02-26",
    "appointmentTime": "3:00 PM",
    "patientFirstName": "Joshua",
    "patientLastName": "Sumagang",
    "doctorFirstName": "Francis",
    "doctorLastName": "Funandog",
    "status": "BOOKED"
}
```
