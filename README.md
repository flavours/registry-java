# Flavours Java Registry

![](https://github.com/flavours/documentation/workflows/CI/badge.svg)

<p align="center">
  <img src="https://utils.flavours.dev/presskit/spring_boot_flavour_glossy.svg" width="100">
</p>

A [Flavours](https://www.flavours.dev) registry for automatically resolving Java Artefacts


## Quick start

### Clone the repository

```
git clone git@github.com:flavours/registry-java.git
```

### Build the project

```
cd registry-java
docker-compose build
```

The project includes a ``web`` service, running the Java code.
See the ``docker-compose.yml`` file for details.

### Run the project

```
docker-compose up
````

Containers for the registry will be launched. The project can be reached at http://localhost:8000.

Hot-reloading is enabled (i.e. changes to the Java code in the project will cause the application to restart so that they 
can be used.)


## How to

### Run the local project on a different port

The container runs a Tomcat server listening on port 8080. The ``docker-compose.yml`` file is set up to
expose this port to the Docker host at port 8000, but you are free to change it as you wish - edit the ``ports`` directive:

```
services:
  web:
    [...]
    ports: 
      - 8000:8080
```

### Contribute to the project

See the [contribution guide](https://github.com/flavours/getting-started-with-spring-boot/blob/master/CONTRIBUTING.md).

