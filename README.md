# Suchon site API-Testing-Java by Saruj

See original repository [Suchon site](https://github.com/SuchonSite/Server)

## Prerequisites

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

## Complete API Documentation

https://suchonsite-server.herokuapp.com/api-docs/#/

## What I have tested so far

Now I have tested only 2 API path

<img width="629" alt="1" src="https://user-images.githubusercontent.com/59832457/143680993-3ea070be-bb03-4094-869b-7d0471e99f64.png">

For GET people/all

| Status code	| Msg| Description   |
|---|---|---|
| 404 | Not Found |  occur when get invalid param such as /people/by_date/10-July-2020 |
| 200 | OK |  occur when get successfully |

For GET by_date/{dd-mm-yyyy}

| Status code	| Msg| Description   |
|---|---|---|
| 404 | Not Found |  occur when get invalid param such as /people/by_date/10-July-2020 |
| 204 | No Content |  No people in this date |
| 406 | No date param included in request |  No date param included in request |
| 200 | OK |  occur when get successfully |

and 1 internal path - /getDataFormGov
