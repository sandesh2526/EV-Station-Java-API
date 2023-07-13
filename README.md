# EV-Station-Java-API

The API can add ev stations to the database and retrieve the list of APIs as a JSON object. 

This API is created using the Spring Boot, JPA and MySQL.

You can also use the AWS RDS service as a database by simply making changes to the application properties.

## Operations 

We can perform all the basic CRUD operations such as fetching the list of the current stations in database using the GET request to the API, updating the existing station and deleting specific station.

The number of stations returned by the request can be managed by adding the query parameter ```?limit=1``` which will show the last modified station from the list.
