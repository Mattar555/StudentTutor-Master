# Overview

This is a container based application leveraging Docker where each service performs a specific role. A broker (RabbitMQ) facilitates communication between the backend services. A highly available pair of SQL servers houses the student tutor allocations. Read calls are load balanced whereas write calls are replicated using a master-slave configuration. Updates made to the database pair are only performed by the master to ensure strong write consistency. The web application is served by an Nginx server. Queries are made to the database and the data is displayed in an intuitive tabular format.

The frontend is written in React, whereas the backend is written in Java utilizing the Spring framework. A sample Dockerfile is provided in this repository.

