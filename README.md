# milo-jms

# Usando ActiveMQ Artemisa Dockerizado
___
- docker pull vromero/activemq-artemis
- docker run -it --rm \
  -p 8161:8161 \
  -p 61616:61616 \
  vromero/activemq-simetraehcapa
---
agregar application properties o variable de entorno en docker 

-e ARTEMIS_USERNAME=artemis \
-e ARTEMIS_PASSWORD=simetraehcapa \
___