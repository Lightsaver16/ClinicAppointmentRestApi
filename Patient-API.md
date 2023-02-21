
## API Reference

<h3>Patients</h3>

#### Create new patient record.

```http
  POST /api/patients/create
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `firstName` | `String` | **Required**. Patient's First Name. |
| `lastName` | `String` | **Required**. Patient's Last Name. |
| `contactNumber` | `String` | **Required**. Patient's Contact Number. |
| `address` | `String` | **Required**. Patient's address. |

```
    Sample payload:

    {
    "firstName": "Clark",
    "lastName": "Cabaron",
    "contactNumber": "123123123",
    "address": "Valenzuela"
    }
```



#### Get all patient records.

```http
  GET /api/patients
```

Example URL:
```
  http://localhost:8080/api/patients
```

Example Response:

```
[
    {
        "patientId": 1,
        "firstName": "Joshua",
        "lastName": "Sumagang",
        "contactNumber": "09158316447",
        "address": "Marilao",
        "appointments": [
            {
                "appointmentId": 1,
                "appointmentDate": "2023-02-26",
                "appointmentTime": "3:00 PM",
                "appointmentStatus": "BOOKED"
            },
            {
                "appointmentId": 4,
                "appointmentDate": "2023-02-10",
                "appointmentTime": "1:00 PM",
                "appointmentStatus": "BOOKED"
            },
            {
                "appointmentId": 6,
                "appointmentDate": "2023-02-10",
                "appointmentTime": "2:00 PM",
                "appointmentStatus": "BOOKED"
            },
            {
                "appointmentId": 7,
                "appointmentDate": "2023-05-10",
                "appointmentTime": "10:00 AM",
                "appointmentStatus": "BOOKED"
            },
            {
                "appointmentId": 8,
                "appointmentDate": "2023-05-12",
                "appointmentTime": "10:00 AM",
                "appointmentStatus": "BOOKED"
            },
            {
                "appointmentId": 9,
                "appointmentDate": "2023-05-13",
                "appointmentTime": "10:00 AM",
                "appointmentStatus": "BOOKED"
            },
            {
                "appointmentId": 10,
                "appointmentDate": "2023-05-14",
                "appointmentTime": "10:00 AM",
                "appointmentStatus": "BOOKED"
            },
            {
                "appointmentId": 11,
                "appointmentDate": "2023-05-15",
                "appointmentTime": "10:00 AM",
                "appointmentStatus": "BOOKED"
            },
            {
                "appointmentId": 12,
                "appointmentDate": "2023-05-16",
                "appointmentTime": "10:00 AM",
                "appointmentStatus": "BOOKED"
            },
            {
                "appointmentId": 13,
                "appointmentDate": "2023-05-21",
                "appointmentTime": "10:00 AM",
                "appointmentStatus": "BOOKED"
            }
        ]
    },
    {
        "patientId": 2,
        "firstName": "Clark",
        "lastName": "Cabaron",
        "contactNumber": "123123123",
        "address": "Valenzuela",
        "appointments": [
            {
                "appointmentId": 2,
                "appointmentDate": "2023-01-27",
                "appointmentTime": "11:00 AM",
                "appointmentStatus": "CANCELLED"
            },
            {
                "appointmentId": 3,
                "appointmentDate": "2023-02-10",
                "appointmentTime": "11:00 AM",
                "appointmentStatus": "BOOKED"
            }
        ]
    },
    {
        "patientId": 3,
        "firstName": "Roven",
        "lastName": "Gentolea",
        "contactNumber": "6969696969",
        "address": "Valenzuela",
        "appointments": [
            {
                "appointmentId": 5,
                "appointmentDate": "2023-02-05",
                "appointmentTime": "3:00 PM",
                "appointmentStatus": "CANCELLED"
            }
        ]
    },
    {
        "patientId": 4,
        "firstName": "Roven",
        "lastName": "Gentolea",
        "contactNumber": "6969696969",
        "address": "Valenzuela",
        "appointments": []
    }
]
```

#### Get all appointments by patient id.

```http
  GET /api/patients/{id}/appointments
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. Patient's Id. |

Example URL:
```
  http://localhost:8080/api/patients/2/appointments
```
Example Response:
```
[
    {
        "appointmentId": 2,
        "appointmentDate": "2023-01-27",
        "appointmentTime": "11:00 AM",
        "doctorFirstName": "Francis",
        "doctorLastName": "Funandog",
        "status": "CANCELLED"
    },
    {
        "appointmentId": 3,
        "appointmentDate": "2023-02-10",
        "appointmentTime": "11:00 AM",
        "doctorFirstName": "Troy",
        "doctorLastName": "Permejo",
        "status": "BOOKED"
    }
]
```

#### Get patient record by patient name.

```http
  GET /api/patients/query
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `firstName` | String | **Required**. Patient's First Name. |
| `lastName` | String | **Required**. Patient's Last Name. |

Example URL:
```
  http://localhost:8080/api/patients/query?firstName=Joshua&lastName=Sumagang
```
Example Response:
```
{
    "patientId": 2,
    "firstName": "Clark",
    "lastName": "Cabaron",
    "contactNumber": "123123123",
    "address": "Valenzuela",
    "appointments": [
        {
            "appointmentId": 2,
            "appointmentDate": "2023-01-27",
            "appointmentTime": "11:00 AM",
            "appointmentStatus": "CANCELLED"
        },
        {
            "appointmentId": 3,
            "appointmentDate": "2023-02-10",
            "appointmentTime": "11:00 AM",
            "appointmentStatus": "BOOKED"
        }
    ]
}
```
#### Update a patient record.

```http
  PUT /api/patients/{id}/update
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. Patient Id. |
| `firstName` | `String` | **Not Required**. New Patient's First Name. |
| `lastName` | `String` | **Not Required**. New Patient's Last Name. |
| `contactNumber` | `String` | **Not Required**. New Patient's Contact Number. |
| `address` | `String` | **Not Required**. New Patient's address. |

Example URL:
```
  http://localhost:8080/api/patients/1/update
```
Example Payload:
```
  {
    "firstName": "Josue"
}
```
Example Response:
```
  {
    "patientId": 1,
    "firstName": "Josue",
    "lastName": "Sumagang",
    "contactNumber": "09158316447",
    "address": "Marilao",
    "appointments": [
        {
            "appointmentId": 1,
            "appointmentDate": "2023-02-26",
            "appointmentTime": "3:00 PM",
            "appointmentStatus": "BOOKED"
        },
        {
            "appointmentId": 4,
            "appointmentDate": "2023-02-10",
            "appointmentTime": "1:00 PM",
            "appointmentStatus": "BOOKED"
        },
        {
            "appointmentId": 6,
            "appointmentDate": "2023-02-10",
            "appointmentTime": "2:00 PM",
            "appointmentStatus": "BOOKED"
        },
        {
            "appointmentId": 7,
            "appointmentDate": "2023-05-10",
            "appointmentTime": "10:00 AM",
            "appointmentStatus": "BOOKED"
        },
        {
            "appointmentId": 8,
            "appointmentDate": "2023-05-12",
            "appointmentTime": "10:00 AM",
            "appointmentStatus": "BOOKED"
        },
        {
            "appointmentId": 9,
            "appointmentDate": "2023-05-13",
            "appointmentTime": "10:00 AM",
            "appointmentStatus": "BOOKED"
        },
        {
            "appointmentId": 10,
            "appointmentDate": "2023-05-14",
            "appointmentTime": "10:00 AM",
            "appointmentStatus": "BOOKED"
        },
        {
            "appointmentId": 11,
            "appointmentDate": "2023-05-15",
            "appointmentTime": "10:00 AM",
            "appointmentStatus": "BOOKED"
        },
        {
            "appointmentId": 12,
            "appointmentDate": "2023-05-16",
            "appointmentTime": "10:00 AM",
            "appointmentStatus": "BOOKED"
        },
        {
            "appointmentId": 13,
            "appointmentDate": "2023-05-21",
            "appointmentTime": "10:00 AM",
            "appointmentStatus": "BOOKED"
        }
    ]
}
```

#### Delete patient record.

```http
  DELETE /api/patients/{id}/delete
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | Long | **Required**. Patient Id. |

Example URL:
```
  http://localhost:8080/api/patients/1/delete
```
Example Response:
```
  Patient record successfully deleted.
```
