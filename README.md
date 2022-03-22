# Implementation of Saga-pattern

![N|Solid](	https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)

## Features

- An example of orchestration of saga-pattern 


![Screenshot_20220321_214458](https://user-images.githubusercontent.com/1341627/159385653-710bcd64-e949-49a3-9c67-e5810984f678.png)

## Tech

- Spring boot 2.6
- Axon Framework 4.5
- Axon Server 4.5
- Java 17
- Maven

## Docker

To run the axon server in docker container just execute the command bellow:

```sh
docker run -d \
  --name axonserver \
  -p 8024:8024 \
  -p 8124:8124 \
  axoniq/axonserver
```
