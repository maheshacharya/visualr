Install locally
===============
Install this project into your local maven repository
In order to use this project as a dependency in your own hippo *enterprise* projects,
run the following maven command.

 $ mvn clean install

Running Locally
===============
This project cannot be run itself, rather you will include this project in your own Hippo DX project.
Follow directions in this page below  to create, build and run a new Hippo Project
https://www.onehippo.org/trails/getting-started/hippo-essentials-getting-started.html
Please note: you will require credentials to Hippo Enterprise maven repository.

Add dependency to site module
==============================
Add following to depedencies in your site pom.xml

 <dependency>
   <groupId>org.macharya</groupId>
   <artifactId>visualr-site</artifactId>
   <version>0.1.0-SNAPSHOT</version>
 </dependency>
  
  
Add dependency to cms module
============================
Add following to depedencies in your cms pom.xml

 <dependency>
   <groupId>org.macharya</groupId>
   <artifactId>visualr-cms</artifactId>
   <version>0.1.0-SNAPSHOT</version>
 </dependency>
