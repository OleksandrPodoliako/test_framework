Test_framwork
-------------

### MAIN GOAL:

- Education

### PREPARATION:

1. install JDK (v. 1.8.0_161)
     - 1.1 download jdk (v. 1.8.0_161)
     - 1.2 set jdk path to 'JAVA_HOME' environment variable
     - 1.3 add %JAVA_HOME%\bin to 'Path' environment variable
2. install Gradle 
     - 2.1 download gradle
     - 2.2 unzip to c:\Gradle\
     - 2.3 set gradle path to 'GRADLE_HOME' environment variable
     - 2.4 add %Gradle_HOME%\bin to 'Path' environment variable

### HOW TO USE:

##### - All tests :

1. open cmd
2. goto folder path to project/test_framework
3. execute command: gradle clean testAll

##### - UI tests :

1. open cmd
2. goto folder path to project/test_framework
3. execute command: gradle clean testUI

##### - Api tests :

1. open cmd
2. goto folder path to project/test_framework
3. execute command: gradle clean testAPI

### Report

1. execute command: gradle allureReport
2. execute command: gradle allureServe
