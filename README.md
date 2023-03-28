# jednoducha-aplikace

# Introduction

Example of a classical REST API application in the Java programming language using the Spring Boot framework and MySQL database.

## Authorization

`api/v1/auth`

To access certain endpoints, it is necessary to register and obtain an access token.

```http
POST /registration

Request body: 
{
    "name": "string",
    "surname": "string",
    "email": "string",
    "login": "string",
    "password": "string"
}
```

```http
POST /login

Request body: 
{
    "login": "string",
    "password": "string"
}
```

## Users

`api/v1/user`

To access certain endpoints, it is necessary to register and obtain an access token.

```http
DELETE /delete?uid=

Request param: 
{
    "uid": "string",
}
```

```http
GET /getByUid?uid=

Request param: 
{
    "uid": "string",
}
```

```http
GET /getByToken?token=

Request param: 
{
    "token": "string",
}
```

```http
GET /getAll
```

## Books

`api/v1/book`

To access certain endpoints, it is necessary to register and obtain an access token.

```http
POST /add

Request body: 
{
    "name": "string",
    "isbn": "string",
    "token": "string"
}
```

```http
DELETE /delete?uid=&token=

Request param: 
{
    "bookId": "integer",
    "token": "string"
}
```

```http
POST /edit?bookId=

Request param: 
{
    "bookId": "integer",
}

Request body: 
{
    "name": "string",
    "isbn": "string",
    "token": "string"
}
```

```http
GET /getByName?name=&token=

Request param: 
{
    "name": "string",
    "token": "string"
}
```

```http
GET /getAllByUid?token=

Request param: 
{
    "token": "string"
}
```

```http
GET /getAll
```

## Responses

The response always comes in the form of:

```javascript
{
  "message" : string,
  "code" : integer,
  "payload"    : array | object
}
```

The `message` attribute contains a message commonly used to indicate errors or, in the case of deleting a resource, success that the resource was properly deleted.

The `code` HTTP status codes.

The `payload` contains an object or array of objects, the result of an endpoint.

## Status Codes

Gophish returns the following status codes in its API:

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 201 | `CREATED` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |


