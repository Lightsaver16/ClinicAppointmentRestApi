
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
Getting all the appointments will return a DTO:


```http
  GET /api/appointments
```

| Parameter | Default Value     | Description                |
| :-------- | :------- | :------------------------- |
| `pageNo` | `1` | **Not Required**. Pagination number. |
| `pageSize` | `10` | **Not Required**. Number of resources per page. |
| `sortBy` | `appointmentId` | **Not Required**. The column(field) that the sorting will be  based upon. |
| `order` | `ascending` | **Not Required**. Order of the sorting - can be ascending or descending. |
