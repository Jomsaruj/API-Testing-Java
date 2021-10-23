# API-Testing-Java

<img width="696" alt="ๅ" src="https://user-images.githubusercontent.com/59832457/138566349-c415733c-0747-4783-b67e-82a868de27f0.png">
Test Summary generate by JaCoCo Test Report

## Prerequisites

```
Java 8 JDK
```

To install Java, download the **JDK** installer from: 
  http://www.oracle.com/technetwork/java/javase/downloads/index.html

For those who not have `gradle` install in your computer. Please do so

Installing with a package manager

SDKMAN! is a tool for managing parallel versions of multiple Software Development Kits on most Unix-like systems (macOS, Linux, Cygwin, Solaris and FreeBSD). 

```
sdk install gradle
```
`Homebrew` is "the missing package manager for macOS".

```
brew install gradle
```

## Build test

It is necessary to run all test file before generate coverage test report. Here is how to build `CoffeeMakerTest.java` and `RecipeTest.java` in one command

```
gradle test
```

## Generate test report

After build all test file, you are now be able to generate JaCoCo test report.

```
gradle jacocoTestReport
```

you can now see the coverage report at `index.html` in build directory

## About

Here are some test cases to test Suchon’s Site web application API. This API is a JSON-based API. All requests are made to endpoints beginning:   
 

```
https://suchonsite-server.herokuapp.com/
```    

All requests must be secure, i.e. `https`, not `http`.

| Status code	| Msg| Description   |
|---|---|---|
| 404 | Not Found |  occur when get extra param after /all |
| 200 | OK |  occur when get successfully |

## API Documentation

**Pattern 1: retrieve the information of all appointment** Return full list of people's information that have vaccination appointment

The API endpoint exposes this list of people's information as a collection.A request to fetch a list of people's information looks like this:    

```
GET https://suchonsite-server.herokuapp.com/people/all
```

Example response:
```
[
    {
        "_id": "string",
        "date": "20-10-2021",
        "people": [
            {
                "reservation_id": 1,
                "register_timestamp": "2021-10-20T15:19:56.609000",
                "name": "string",
                "surname": "string",
                "birth_date": "string",
                "citizen_id": "string",
                "occupation": "string",
                "address": "string",
                "priority": "string"
            }
        ],
        "__v": 0
    },
    {
        "_id": "string",
        "date": "21-10-2021",
        "people": [
            {
                "reservation_id": 1,
                "register_timestamp": "2021-10-21T15:19:56.609000",
                "name": "string",
                "surname": "string",
                "birth_date": "string",
                "citizen_id": "string",
                "occupation": "string",
                "address": "string",
                "priority": "string"
            }
        ],
        "__v": 0
    }
]
```

**Pattern 2: retrieve the information of peoples appointment by date**
Return full list of people's information that have vaccination appointment in that day    

The API endpoint exposes this list of people's information as a collection.A request to fetch a list of people's information looks like this:    

```
GET https://suchonsite-server.herokuapp.com/people/by_date/dd-mm-yyyy
```

Example response:
```
{
    "_id": "617042a299a86369e7ba81f5",
    "date": "20-10-2021",
    "people": [
        {
            "reservation_id": 1,
            "register_timestamp": "string",
            "name": "string",
            "surname": "string",
            "birth_date": "string",
            "citizen_id": "string",
            "occupation": "string",
            "address": "string",
            "priority": "string"
        }
    ],
    "__v": 0
}
```
| Status code	| Msg| Description   |
|---|---|---|
| 404 | Not Found |  occur when get invalid param such as /people/by_date/10-July-2020 |
| 202 | no date included |  occur when not get any date /people/by_date/ |
| 200 | OK |  occur when get successfully |
