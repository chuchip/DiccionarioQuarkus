# Proyecto Diccionario

Para probar la aplicación, he dejado  en la carpeta build estan los ficheros preparados para ejecutar en un docker-compose.

Simplemente ejecutar el script 'deploy.bat' el cual creará dos dockers. Uno con una base de datos postgres y otra con la aplicación en Quarkus.

La aplicación en Quarkus esta escuchando en el puerto 8080 y Postgres en el 5432.

Para parar  la aplicación ejecutar: 

> docker-compose down

En esa misma carpeta teneis una colección de postman para importar y que permitirá realizar tests y teneis los endpoints definidos.


# En modo DEV.

Crear se debera tener lanzado un docker Postgres con estos parametros:

```
docker volume create postgres_diccionario
docker run -d --volume postgres_diccionario:/var/lib/postgresql/data --name postgres_dev_diccionario -ePOSTGRES_USER=postgres  -e POSTGRES_PASSWORD=pass -e POSTGRES_DB=diccionario -p 5432:5432 postgres
```

------

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/diccionario-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
