# Suchon site API-Testing-Java by Saruj

<img width="696" alt="1" src="https://user-images.githubusercontent.com/59832457/138586807-db82db2b-3784-4e25-b7cc-154949c46002.png">


See original repository [Suchon site](https://github.com/SuchonSite/Server)

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

```
gradle test
```

After build all test file, you are now be able to generate JaCoCo test report.

```
gradle jacocoTestReport
```

you can now see the coverage report at `index.html` in build directory

## Testing Domain

Here are some test cases to test Suchon’s Site web application API. This API is a JSON-based API. All requests are made to endpoints beginning:   
 

```
https://suchonsite-server.herokuapp.com/
```    

All requests must be secure, i.e. `https`, not `http`.

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

| Status code	| Msg| Description   |
|---|---|---|
| 404 | Not Found |  occur when get extra param after /all |
| 200 | OK |  occur when get successfully |

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

**Pattern 3: retrieve the information from GOV and push to database**

This API end-point need to modify (may be chabge from POST to GET), but the objective of this route is to GET user information form GOV and then process it before store in our database.

```
POST https://suchonsite-server.herokuapp.com/getDataFromGov{{date}}
```
It is a POST request without body (that is the reason why we have to modify it)

| Status code	| Msg| Description   |
|---|---|---|
| 401 | already have data in this date |  occur when get duplicate data from GOV |
| 504 | gov status code != 200 | occur when unable to fetch information form GOV |
| 202 | no date included |  occur when not get any date /getDataFromGov |
| 200 | OK |  occur when get successfully |
