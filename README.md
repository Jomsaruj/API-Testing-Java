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
