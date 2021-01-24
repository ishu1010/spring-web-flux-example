
# spring-web-flux-example
The reactive-stack web framework, Spring WebFlux, has been added Spring 5.0. It is fully non-blocking, supports reactive streams back pressure.
that promotes an asynchronous, non-blocking, event-driven approach to data processing. Reactive programming involves modeling data and events as observable data streams and implementing data processing routines to react to the changes in those streams.

# Non-blocking request processing
In non-blocking or asynchronous request processing, no thread is in waiting state. There is generally only one request thread receiving the request.

All incoming requests come with a event handler and call back information. Request thread delegates the incoming requests to a thread pool (generally small number of threads) which delegate the request to it’s handler function and immediately start processing other incoming requests from request thread.

When the handler function is complete, one of thread from pool collect the response and pass it to the call back function.

if want to know more on this please visit https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/

# Mongo database setup steps :
1. download community server and install it
https://www.mongodb.com/download-center/community

2. create direcoty structure "C:\data\db", if already there then can ignore this.

3. now set environment variable mongodb.
For me i have added variable name:MongoDB, variable value: C:\Program Files\MongoDB\Server\4.2\bin and added the same at path variable like this %MongoDB%.

4. now open command promt  and type command mongod
C:\Users\**>mongod

5. now open one more command promt  and type command mongo
C:\Users\ishu1010>mongo
do not close both CMD windows.
Now mongo database is ready to hanle all the request.


#  lombok setup
To run this application on your machine you need to install Lombok jar for that follow below points
1. check .m2 folder on your machine follow the below path
 .m2\repository\org\projectlombok\lombok\1.18.12
 2. double click on lombok jar
 3. popup will apper. it will specify location of eclipse.exe / sts.exe automatically
 4. click on install/update
 5. click Quit installer 
 6. close and re-open sts/eclipse
 
# Jacoco code coverage 
Pom has configuration for code coverage plugin Jacoco.

#junit test case
Demo controller class test cases using Mockmvc.


try checkout this application on you machine and run and share feedback.
