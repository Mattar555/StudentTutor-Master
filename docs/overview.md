# Overview

This is a container based application leveraging Docker where each service performs a specific role. A broker (RabbitMQ) facilitates communication between the backend services. A highly available pair of SQL servers houses the student tutor allocations. Read calls are load balanced whereas write calls are replicated using a master-slave configuration. Updates made to the database pair are only performed by the master to ensure strong write consistency. The web application is served by an Nginx server. Queries are made to the database and the data is displayed in an intuitive tabular format.

The frontend is written in React, whereas the backend is written in Java utilizing the Spring framework. A sample Dockerfile is provided in this repository. The application consists of the following services:

1) Student-Tutor-Master. This service can be regarded as the coordinator of the whole operation. It queries the database daily to determine whom among the volunteers have not responded yet (At the beginning of each weekly cycle, a notification is sent out to all volunteers as none of them would have received a notification yet). The tutors' email addresses are collated into a list and pushed one by one into a specific queue as seperate messages to be consumed by "Student-Tutor-Writer (discussed below). In addition, it pulls messages from another queue containing the response sent by the volunteers and updates the database accordingly.
2) Student-Tutor-Writer. This service is responsible for one single function. That is, pulling messages from the writer queue where each message contains the email address of the volunteer. An email notification is then sent out given that address. Google's SMTP server is used to carry out the push notifications. A 500 email daily limit is imposed by Google. By default, this worker can process 5 distinct messages concurrently. A dead letter queue, which ought to be included for robust error handling, is not included here.
3) Student-Tutor-Reader. This service polls the email inbox (used to send notifications) on a daily basis using an IMAP client. Search filters by email subject and time are included as part of the request to only retrieve the relevant emails. A payload comprising the email address and the contents of the email are pushed to a dedicated "reader queue" where, as mentioned in point 1 above, are pulled and subsequently parsed by the Master node prior to updating the relevant entries in the database.
4) RabbitMQ: As described above, communication between the Master, Writer and Reader is achieved via a messaging broker. RabbitMQ was the broker of choice. The entire setup consists of an exchange object, two routing keys and two dedicated queues, one for each key. The "writer-queue" enables communication between the Master and the Writer, whereas the "reader-queue" enables communication between the Master and the Reader. The Writer is "Reader agnostic" (and vice versa, the Reader is "Writer agnostic") due to mutual exclusivity of roles.
5) SQL: A pair of SQL servers in a master slave configuration of set up to ensure redundancy. Read requests are load balanced and write requests are replicated. A check is performed every minute to verify whether or not any disabled databases (due to connectivity issues) can be re-enabled. Synchronization is subsequently performed aysnchronously prior to rejoining the cluster.
6) Student-Tutor-Web: This is a React application hosted on an Nginx server with basic user authentication enabled. A HTTP call is performed to an endpoint on the Master node on every page load/refresh. This endpoint retrieves the contents of the SQL table and renders it in a tabular fashion for the user to view.

The diagram given below effectively summarizes the above in a graphical manner.

# Architecture Diagram


![image](https://user-images.githubusercontent.com/21075687/118350978-b7510d00-b59c-11eb-9b8b-a037cc0ab930.png)

