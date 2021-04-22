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

http://localhost:8001 - dotnetcore
http://localhost:8002 - java
http://localhost:8003 - python
http://localhost:8004 - node

To make cross service calls you simply append the route of the service you want to call. For example if you wanted to use python to call node you would use

http://localhost:8003/node
