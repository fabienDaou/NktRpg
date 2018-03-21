# NktRpg
Help track history of decisions and major events.

## How to install the server

### Install MySql
```
choco install mysql
```

### Create database
Run src/main/NktRpgDatabase/CreateNktRpgSchema.sql

## Api description
expects content-type application/json

### Create a session
path
```
POST [basePath]/session
```
body
```json
{
  "title": "Title of the session"
}
```
returns
```json
{
  "sessionId": 7
}
```

### Create an event
path
```
POST [basePath]/event
```
body
```json
{
  "sessionId": 6,
  "location": "Test",
  "description": ""
}
```
returns
```json
{
  "eventId": 7
}
```

### Get all sessions
path
```
GET [basePath]/sessions
```
returns
```json
[
  {
    "id": 1,
    "title": "Montroc",
    "date": 1521586164000
  },
  {
    "id": 2,
    "title": "Ombre",
    "date": 1521626932000
  }
]
```

### Get all events of a specific session
path
```
GET [basePath]/events/session/{sessionId}
```
returns
```json
[
    {
        "id": 1,
        "sessionId": 1,
        "location": "Ombre",
        "description": "",
        "date": 1521586164000
    },
    {
        "id": 2,
        "sessionId": 1,
        "location": "Montroc",
        "description": "",
        "date": 1521586164000
    },
    {
        "id": 3,
        "sessionId": 1,
        "location": "Noumea",
        "description": "",
        "date": 1521586164000
    }
]
```