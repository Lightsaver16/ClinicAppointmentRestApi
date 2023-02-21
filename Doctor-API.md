
## API Reference

<h3>Doctors</h3>

#### Create new doctor record. 
Availability of the doctor will automatically be set to AVAILABLE.

```http
  POST /api/doctors/create
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `firstName` | `String` | **Required**. Doctor's First Name. |
| `lastName` | `String` | **Required**. Doctor's Last Name. |

Example URL:
```
  http://localhost:8080/api/doctors/create
```

Sample payload:
```
{
    "firstName": "Juan",
    "lastName": "Dela Cruz"
}
```
Response:
```
{
    "doctorId": 4,
    "firstName": "Juan",
    "lastName": "Dela Cruz",
    "appointments": null,
    "availability": "AVAILABLE"
}
```

#### Get all doctor records.

```http
  GET /api/doctors
```

Example URL:
```
  http://localhost:8080/api/doctors
```

Example Response:

```
[
    {
        "doctorId": 1,
        "firstName": "Troy",
        "lastName": "Permejo",
        "appointments": [
            {
                "appointmentId": 3,
                "appointmentDate": "2023-02-10",
                "appointmentTime": "11:00 AM",
                "appointmentStatus": "BOOKED"
            }
        ],
        "availability": "AVAILABLE"
    },
    {
        "doctorId": 2,
        "firstName": "Francis",
        "lastName": "Funandog",
        "appointments": [
            {
                "appointmentId": 2,
                "appointmentDate": "2023-01-27",
                "appointmentTime": "11:00 AM",
                "appointmentStatus": "CANCELLED"
            }
        ],
        "availability": "UNAVAILABLE"
    },
    {
        "doctorId": 3,
        "firstName": "Elijah",
        "lastName": "Garpia",
        "appointments": [
            {
                "appointmentId": 5,
                "appointmentDate": "2023-02-05",
                "appointmentTime": "3:00 PM",
                "appointmentStatus": "CANCELLED"
            }
        ],
        "availability": "AVAILABLE"
    },
    {
        "doctorId": 4,
        "firstName": "Juan",
        "lastName": "Dela Cruz",
        "appointments": [],
        "availability": "AVAILABLE"
    }
]
```

#### Get doctor record by doctor id.

```http
  GET /api/doctors/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. Doctor id. |

Example URL:
```
  http://localhost:8080/api/doctors/1
```
Example Response:
```
{
    "doctorId": 1,
    "firstName": "Troy",
    "lastName": "Permejo",
    "appointments": [
        {
            "appointmentId": 3,
            "appointmentDate": "2023-02-10",
            "appointmentTime": "11:00 AM",
            "appointmentStatus": "BOOKED"
        }
    ],
    "availability": "AVAILABLE"
}
```
#### Find doctor by name.

```http
  GET /api/doctors/query
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `firstName` | `String` | **Not Required**. Doctor's First Name. |
| `lastName` | `String` | **Not Required**. Doctor's Last Name. |

Example URL:
```
  http://localhost:8080/api/doctors/query?firstName=Troy&lastName=Permejo
```
Example Response:
```
{
    "doctorId": 1,
    "firstName": "Troy",
    "lastName": "Permejo",
    "appointments": [
        {
            "appointmentId": 3,
            "appointmentDate": "2023-02-10",
            "appointmentTime": "11:00 AM",
            "appointmentStatus": "BOOKED"
        }
    ],
    "availability": "AVAILABLE"
}
```
#### Update a doctor record.

```http
  PUT /api/doctors/{id}/update
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. Doctor Id. |
| `firstName` | `String` | **Not Required**. New Doctor's First Name. |
| `lastName` | `String` | **Not Required**. New Doctor's Last Name. |

Example URL:
```
  http://localhost:8080/api/doctors/1/update
```
Example Payload:
```
{
    "firstName": "Jericho"
}
```
Example Response:
```
{
    "doctorId": 1,
    "firstName": "Jericho",
    "lastName": "Permejo",
    "appointments": [
        {
            "appointmentId": 3,
            "appointmentDate": "2023-02-10",
            "appointmentTime": "11:00 AM",
            "appointmentStatus": "BOOKED"
        }
    ],
    "availability": "AVAILABLE"
}
```

#### Delete patient record.

```http
  DELETE /api/doctors/{id}/delete
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | Long | **Required**. Doctor Id. |

Example URL:
```
  http://localhost:8080/api/doctors/1/delete
```
Example Response:
```
  Doctor record successfully deleted.
```

#### Make the doctor available by querying the doctor name.

```http
  PUT /api/doctors/unavailable/query
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `firstName` | `String` | **Required**. Doctor's First Name. |
| `lastName` | `String` | **Required**. Doctor's Last Name. |

Example URL:
```
  http://localhost:8080/api/doctors/unavailable/query?firstName=Elijah&lastName=Garpia
```
Example Response:
```
  Dr. Elijah Garpia is now unavailable.
```

#### Make the doctor available by querying the doctor name.

```http
  PUT /api/doctors/available/query
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `firstName` | `String` | **Required**. Doctor's First Name. |
| `lastName` | `String` | **Required**. Doctor's Last Name. |

Example URL:
```
  http://localhost:8080/api/doctors/unavailable/query?firstName=Elijah&lastName=Garpia
```
Example Response:
```
  Dr. Elijah Garpia is now available.
```
