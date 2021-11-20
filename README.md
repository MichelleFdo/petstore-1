# PetStore Application

## Introduction

MicroProfile Starter has generated this MicroProfile application for you.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Packaging and running the application

If you want to build an _??ber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petstore-runner.jar

The application can be also packaged using simple:

    ./gradlew build

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it is not an _??ber-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

    ./gradlew quarkusDev

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Creating a native executable

Mind having GRAALVM_HOME set to your Mandrel or GraalVM installation.

You can create a native executable using:

    ./gradlew build -Dquarkus.package.type=native

Or, if you don't have [Mandrel](https://github.com/graalvm/mandrel/releases/) or
[GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases) installed, you can run the native executable
build in a container using:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true

Or to use Mandrel distribution:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:20.3-java11

You can then execute your native executable with:

    ./build/petstore-runner

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Executing the Test Suite

To run the test suite, execute the following command:

    ./build/petstore-runner
    
## Testing the API using curl

### Handling Pets

Get all pets

    curl --location --request GET 'http://localhost:8080/v1/pets' \
    --data-raw ''
    
Get Pet details by ID
    
    curl --location --request GET 'http://localhost:8080/v1/pets/2' \
    --data-raw ''

Add new pet

    curl --location --request POST 'http://localhost:8080/v1/pets/addpet' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "petId": 2,
    "petAge": 3,
    "petName": "Clio",
    "petType": "Dog"
    }'

Update pet

    curl --location --request PUT 'http://localhost:8080/v1/pets/update' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "petId": 2,
    "petAge": 3,
    "petName": "Cliopatra",
    "petType": "Dog"
    }'
    
Delete Pet

    curl --location --request DELETE 'http://localhost:8080/v1/pets/delete' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "petAge": 3,
        "petId": 2,
        "petName": "Cliopatra",
        "petType": "Dog"
    }'
    
### Handling Pet Types
    
Get all pet types

    curl --location --request GET 'http://localhost:8080/v1/pettypes'
    
Add new pet type

    curl --location --request POST 'http://localhost:8080/v1/pettypes/addtype' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "petTypeId": 1,
    "petTypeName": "Dog"
    }'
    
Get pet type details by pet type Id

    curl --location --request GET 'http://localhost:8080/v1/pettypes/1'
    
Update pet type

    curl --location --request PUT 'http://localhost:8080/v1/pettypes/updatetype' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "petTypeId": 1,
    "petTypeName": "Parrot"
    }'
    
Delete Pet type

    curl --location --request DELETE 'http://localhost:8080/v1/pettypes/deletetype' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "petTypeId": 1,
    "petTypeName": "Dog"
    }'
