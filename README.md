[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

# Kingfisher backend interview test
 
Quick start for Spring Boot and Gradle.

[![Build Status](https://travis-ci.org/cristiangreco/spring-boot-boilerplate.svg?branch=master)](https://travis-ci.org/cristiangreco/spring-boot-boilerplate)

## How to build

Build with Gradle wrapper:

```sh
$ ./gradlew clean build
```

## How to run

Run with Gradle wrapper:

```sh
$ ./gradlew bootRun
```

Or run it as an executable jar:

```sh
$ java -jar build/libs/spring-boot-boilerplate-0.1.0.jar
```

## Testing with Curl

```sh
$ curl http://localhost:8080/product
{"product_number":null,"product_name":"name","product_description":null}
```


##The task

Develop a simple web service that allows clients to save and retrieve product details over HTTP.

It should also be possible to retrieve the aisle location of a product in a specific store. Static json containing aisle locations of products in specific stores is included in the codebase, as part of `AisleLocationManager.getAisleLocations()`

The client requirements for the API are outlined below.

###Save Product Details

To create a product, the client makes a request that includes:
```
Product number
Product name
Product description
```

The service then saves the product details to the file system, and returns the full product details in the response

###Retrieve Product Details

To retrieve product details, the client makes a request that includes:
```
Product number
```

Provided a product with that number exists (has been saved previously), the web service should return:
```
Product number
Product name
Product description
```


###Retrieve Product Details with Aisle Location

To retrieve aisle location along with product details, the client makes a request that includes:
```
Product number
Store ID
```

Provided:
 - a product with that number exists (has been saved previously)
 - an aisle location exists in our static json for that product number and store ID combination

the web service should return:
```
Product number
Product name
Product description
Store ID
Aisle number
``` 

