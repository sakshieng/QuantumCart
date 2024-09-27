# Spring Boot Microservices
This repository contains the latest source code of the spring-boot-microservices tutorial

You can watch the tutorial on Youtube [here](https://youtu.be/yn_stY3HCr8?si=EjrBEUl0P-bzSWRG)

## Services Overview

1. Inventory Service
- Provides APIs to check the stock of products.
- Updates stock on order confirmation.
  
2. Order Service
- Accepts order requests.
- Manages the order lifecycle from placement to fulfillment.
  
3. Notification Service
- Listens to Kafka topics for events such as `order_placed` or `stock_updated`.
- Sends notifications based on these events (e.g., Email or SMS notifications).

4. Product Service
  -Adds, updates, and deletes product data.
  - Interacts with the Inventory Service to maintain accurate stock information.
    
## Tech Stack

The technologies used in this project are:

- Spring Boot
- Mongo DB
- MySQL
- Kafka
- Keycloak
- Grafana Stack (Prometheus, Grafana, Loki and Tempo)
- API Gateway using Spring Cloud Gateway MVC architecture


## Application Architecture
![image](https://github.com/user-attachments/assets/d4ef38bd-8ae5-4cc7-9ac5-7a8e5ec3c969)

## How to run the frontend application

Make sure you have the following installed on your machine:

- Node.js
- NPM

Run the following commands to start the frontend application

```shell
cd frontend
npm install
npm run start
```
## How to build the backend services

Run the following command to build and package the backend services into a docker container

```shell
mvn spring-boot:build-image -DdockerPassword=<your-docker-account-password>
```

The above command will build and package the services into a docker container and push it to your docker hub account.

## How to run the backend services

Make sure you have the following installed on your machine:

- Java 21
- Docker
- Kind Cluster - https://kind.sigs.k8s.io/docs/user/quick-start/#installation

### Start Kind Cluster
    
Run the k8s/kind/create-kind-cluster.sh script to create the kind of Kubernetes cluster

``` shell
./k8s/kind/create-kind-cluster.sh
```
This will create a kind cluster and pre-load all the required Docker images into it. This will save you time downloading the images when you deploy the application.

### Deploy the infrastructure

Run the k8s/manisfests/infrastructure.yaml file to deploy the infrastructure

``` shell
kubectl apply -f k8s/manifests/infrastructure.yaml
```

### Deploy the services

Run the k8s/manifests/applications.yaml file to deploy the services

```shell
kubectl apply -f k8s/manifests/applications.yaml
```

### Access the API Gateway

To access the API Gateway, you need to port-forward the gateway service to your local machine

```shell
kubectl port-forward svc/gateway-service 9000:9000
```

### Access the Keycloak Admin Console
To access the Keycloak admin console, you need to port-forward the Keycloak service to your local machine

```shell
kubectl port-forward svc/keycloak 8080:8080
```

### Access the Grafana Dashboards
To access the Grafana dashboards, you need to port-forward the grafana service to your local machine

```shell
kubectl port-forward svc/grafana 3000:3000
```
