# Modern Developer Environment

This repository is designed to showcase how to configure and use a modern polyglot development environment using containers. It shows how we can use modern tools to create an environment that is

- Fast and simple setup
- Fully automated
- Real time editable
- Debug-able

## Getting Started

The simplicity of this environment is that the only real requirement is to have docker installed (the latest version even comes with docker-compose as part of the docker CLI).

Once you have docker the only other things you need is your favorite editor or IDE. In this example we have setup with VSCode but the same setup can be achieved with one or more other editors.

To start our environment we use docker compose to build and run our containers.

```bash
docker compose up
```

Once everything is built and started you will have 4 different apps running. You can see the results of each by going to the following URLs

- http://localhost:8001 - dotnetcore
- http://localhost:8002 - java
- http://localhost:8003 - python
- http://localhost:8004 - node

These applications are functionally identical and written in 4 different languages. They all have 2 different functions

- the root path `/` will return the results of a random timer and the results of a random request to https://httpbin.org/delay/.
- You can also append the name of another service to make a call to it. For example http://localhost:8001/java will use dotnetcore to make a request to java.

## The next level

Thats all great and gets the apps running. What about when i want to make changes? I need to stop and re-build them all again?

To go to the next level we can add in some extra docker compose configuration that allows us to

- mount the local code into the container
- allow the container to recognise changes and re-compile if needed
- allow us to attach debuggers to the services

To run this environment we can call multiple docker compose files like this

```bash
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up --build
```

If we were not in a demo these would likely be in a single file.
