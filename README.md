# Modern Developer Environment

This repository is designed to showcase how to configure and use a modern polyglot development environment with Docker.

## Getting Started

Requirements

- Docker
- Docker Compose

To start the sample you need to use docker compose to build the containers and run them. By running

```bash
docker-compose up
```

Once everything is running you can see the apps running by going to the following URLs

- http://localhost:8001 - dotnetcore
- http://localhost:8002 - java
- http://localhost:8003 - python
- http://localhost:8004 - node

To make cross service calls you simply append the route of the service you want to call. For example if you wanted to use python to call node you would use

http://localhost:8003/node

## Running this

To get started with just running the apps you can

```
docker-compose up
```

this will build and start all of the apps for you ready to interact with

To go to the next level so that you could develop on these apps you need to combine some docker-compose files together like

```bash
docker-compose -f docker-compose.yml -f docker-compose.volumes.yml up --build
```

This will use the same file as before but will also add in some extra config from the .volumes.yml file that will allow you to mount the local code into the running containers and the changes will be immediately reflected in the container.
