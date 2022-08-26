# Store-Mariela-Backend
Backend de proyecto web para tienda virtual de útiles y productos escolares

# Order para encender los microservicios

> El primer microservicio en arrancar es "config-service"
> El segundo microservicio en arrancar es "eureka-service"
> Luego cualquier otro microservicio que se desee utilizar
> El último debe ser la puerta de enlace "gateway-service"


# Base de datos que usan los microservicios

## Mongo DB
> Auth-Service

## MySQL
> Product-Service
> Category-Service
> Brand-Service
> Order-Service
> Order-Detail-Service

## PosgreSQL
> Stock-Control-Service
> Historical-Purchase-Service

### Recursos utilizados
1. Spring Cloud [Spring Cloud](https://spring.io/projects/spring-cloud)