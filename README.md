# Flavours Java Registry

![](https://github.com/flavours/documentation/workflows/CI/badge.svg)

A [Flavours](https://www.flavours.dev) registry for automatically resolving Java Artefacts. No datastorage is used in this project and all requests are handled stateless. 


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

### Run manual tests

You can run these quick tests to make sure the service works fine.

```
curl -XPOST http://localhost:8000/addonversions/resolve/ -d 'query=java/com.amazon/aws-s3:1.2.3'
curl http://localhost:8000/addonversions/amF2YS9jb20uYW1hem9uL2F3cy1zMzoxLjIuMw/
curl http://localhost:8000/addons/amF2YS9jb20uYW1hem9uL2F3cy1zMzoxLjIuMw/
curl http://localhost:8000/stacks/77bde934-5d73-4d25-9222-e74adb48ef3e/
curl http://localhost:8000/namespaces/380ca58e-32dc-4a90-831d-b63a57a8f621/
```

Of use the flavour CLI:

```
flavour check --verbose java/com.amazon/aws-s3:1.2.3 --registry=http://localhost:8000/
```


### Configure response URLs
The app uses the environment variables `SCHEME`, `DOMAIN` and `PORT` to create URLs in responses. For local development
the values specified in .env_local are used.  

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

### How to prepare a new release of the java registry project
To create a new release of this project, you have to updated the default welcome screen with the latest version.

```
docker run --rm --volume "`pwd`:/data" --user `id -u`:`id -g` pandoc/core:2.9.2 -s --css https://utils.flavours.dev/baseproject/1.0/style.css -o /data/src/main/resources/templates/index.html /data/README.md
```

Please also update the changelog accordingly and tag a new release in github.
