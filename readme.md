Rest Prototype

A sample rest api writeen using the restEasy jax-rs implementation.

This project is just a prototype to help me learn to use restEasy to build a rest api. The goal of this project is to get enough experience with the framework that I can quickly begin building the main project that is my summer job in 2014.

The code for the main project will not be distributed here publically but will be available to potential employers on request.

------------------------------------------------------------------------------

This project uses maven as a build tool. Download maven at maven.apache.org

This project also requires that you have java EE installed. Download this from oracle.com

All additional requirements will be downloaded through maven, the main one being the jetty servlet container that will run the war file produced by maven.

------------------------------------------------------------------------------
To build and run the project cd to the Proto directory that contains pom.xml
Compile with: mvn clean install
Run with mvn jetty:run
