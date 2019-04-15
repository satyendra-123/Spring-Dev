API: product-inventory
description: It is an api which exposes rest services to 
			 1) add a product
			 2) get the products
			 3) place order for available products

1) Once order is placed it generates an invoice for the object and send message by producer over kafka server through kafkaTemplate

To configure kafka server in windows follow below steps:

Prequisites
a) install JDK and JRE in system. set JDK_HOME and Path in env variables.
b) Download and install 7-zip from http://www.7-zip.org/download.html
c) Download and extract Zookeeper using 7-zip from http://zookeeper.apache.org/releases.html
   - Go to your Zookeeper config directory. For me its C:\Users\<mydir>\zookeeper\zookeeper-3.4.14\conf 
   - Rename file “zoo_sample.cfg” to “zoo.cfg”
   - Open zoo.cfg and edit dataDir=/tmp/zookeeper to C:\Users\<mydir>\zookeeper\zookeeper-3.4.14\data
   - Add ZOOKEEPER_HOME = C:\Users\<mydir>zookeeper\zookeeper-3.4.14 to the System / Account Variables. For me i added in account variables. Update the path.
   - You can change the default Zookeeper port in zoo.cfg file (Default port 2181)
   - run in cmd zkserver

#Zookeeper is up and running on port 2181!
----------------------------------------------------------------------------------
d) Download and extract Kafka using 7-zip from http://kafka.apache.org/downloads.html
   - Go to your Kafka config directory. For me its C:\Users\<mydir>\kafka\kafka_2.12-2.2.0\config
   - Edit the file “server.properties.”
   - Find and edit the line log.dirs=/tmp/kafka-logs” to “log.dir= C:\Users\<mydir>\kafka\kafka_2.12-2.2.0\kafka-logs
   - If your Zookeeper is running on some other machine or cluster you can edit “zookeeper.connect:2181” to your custom IP and port.

e) Run the Kafka Server
   - Go to your Kafka installation directory: C:\Users\<mydir>\kafka\kafka_2.12-2.2.0
   - run ".\bin\windows\kafka-server-start.bat .\config\server.properties" in cmd here.
   - Now your Kafka Server is up and running, you can create topics to store messages. Also, we can produce or consume data from Java or Scala code or directly from the command prompt.
f) create a sample topic
   - Open a new command prompt in the location C:\Users\<mydir>\kafka\kafka_2.12-2.2.0\bin\windows
   - run below command to create topic
   $ kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
g) produce the message
   - Open a new command prompt in the location C:\Users\<mydir>\kafka\kafka_2.12-2.2.0\bin\windows
   - run below command for producer
   $ kafka-console-producer.bat --broker-list localhost:9092 --topic test
h) consume the message
   - Open a new command prompt in the location C:\Users\<mydir>\kafka\kafka_2.12-2.2.0\bin\windows
   - run below command for producer
   $ kafka-console-consumer.bat --bootstrap-server localhost:2181 --topic test

   some helpful commands:
   Read messaegs from begining
   $ kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning
   List Topics: 
   $kafka-topics.bat --list --zookeeper localhost:2181
   Describe Topic: 
   $ kafka-topics.bat --describe --zookeeper localhost:2181 --topic test
   Delete Topic: 
   $ kafka-run-class.bat kafka.admin.TopicCommand --delete --topic test --zookeeper localhost:2181

-----------------------------------------------------------------------------------

Once the kafka server setup is completed need to configure the properties for spring kafka. Please check out application.yml file in project.

This api provides below features:
1) Documentation using swagger api.
2) Global Exception handler
3) Rest Service security
4) IO Operation
5) Spring boot with JPA + Mysql 8
6) Junit test cases
   Integration Test
   Unit Testing using MockBean 