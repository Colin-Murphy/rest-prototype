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

------------------------------------------------------------------------------
Basic Information
1) All classes must be in the org.suffieldacademy.proto package, maven expects this and it is necessary for things to be packaged into the war file correctly. Domain and Services are just standard naming conventions, all the classes could be in a single directory with a different name if you wanted, just as long as they are in the package that maven expects.

Entry Point
Everything starts in the PrototypeApplicaion class, this is where services are registered. This class is what is used to build the war file, only classes that are registered here will make it into the final built product. PrototypeApplication extends application, this is how restEasy that this is the entry point. In the starting point the singletons and classes are declared and returned in the appropriate method. Singletons are web applications, so if you wanted a new application for users you would return it in that method. Classes are the providers, they can be additional marshallers or error handlers. The entry point also defines the application path which is the root for any applications you have in your web service.

Adding a new application
Adding a new application is fairly simple, create a new class, it doesn't need to extend anything, standard naming convention is to add Service to the end of the name. So for example an application for getting students may be called Student. You'll want to define a path using the @Path annotation, its not required but its a good idea. 

Responding to a request
When a url is entered restEasy tries to find a method that handles it. These methods can have any name to them, what matters is their annotations. You can annotate the path they respond to (if omitted they respond to the path of their containing class. You must provide an annotation for the http request type (@GET, @POST). And optionally you say what the method consumes (Content-Type http header) and returns (Accepts http header). Your path can define variables that you can pull out of the path within the method to provide accurate data, these can be path params (/example/UsefulText) query peramators (/example?text=useful) or any other type of url data, these can also be in the form of regular expressions to limit the type of data. Finally what you return from the method is also important, restEasy can handle a lot of stuff like strings and output streams, but for your own objects you need to be sure they support a marshaller. 

Adding marshaling support.
Marshalling support is surprisingly easy in restEasy, so far this application supports marshaling with jax b and jackson. Classes are annotated to describe their data members that the marshaller will look for. Both jax b and jackson use the same annotations, so supporting xml and json is only 1 task. When an object is returned that cannot be handled by the default restEasy marshallers (typically a custom object). Jax-b and jackson get their turn to handle the object (jax-b handles xml and jackson handles json). They look for the annotations and build the proper output for the object. The annotations are defined by javax.xml.bind.annotation. Demarshalling is also supported to take input streams from post data and turn them back into objects. One thing to note with jax-b and jackson is that you only define the getters for data, not the setters, this can limit method names for setters. If you define a getter for your data as getName() it expects a setter to be called setName(), if it can't find something that looks like an obvious setter it will throw an error. 