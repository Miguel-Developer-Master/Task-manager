# The project

This is a basic project i made alone, for learning and to expand my real-code experiences.
It allows you to organize your ideas, creating a task, putting it a name, a description and a priority level. 
You can: create, see, edit and delete a task.
It uses Spring boot framework for Java.

# Runnning

First, you need the requirements, they are:
- Java 21+
- Maven 3.x.x+ (preferencially 3.8.7)

## Windows

If you're using Windows, you'll have do to these steps:

### Java

```
java --version
```
On cmd.
If it returns something like "java is not recognized as a command", or even: "openjdk 17.x.x" java is not installed, or it' very old. Do that (If you have java 21+, go to next step):

First, go to [this link](https://www.oracle.com/br/java/technologies/downloads/), put the version you want, put Windows as OS(Operational System) and download x64 installer.
Once it get installed, run the .exe file, and install it.

### Maven

Now, see you have maven installed. Run:

```
mvn --version
```

If returns something like "mvn is not recognized as a command", or even if your maven is older than version 3, don't skip this step.
If you're on Windows or 10, i recommend [this video](https://youtu.be/z4V94gyNABM) to you learn how to install it.

## Linux

If you're using linux, things get much easier. Do this:

### Java

First, run:

```
java --version
```

If something like "openjdk xx.x.x" appear and "xx" is 21 or bigger, skip this step, else, do not.

Now, run:

```
sudo apt update
```

and:

```
sudo apt install openjdk-21-jdk
```

### Maven

Don't close the terminal yet. Before, run:


```
mvn --version
```

if nothing like "Apache Maven 3.x.x" appeared, run:


```
sudo apt install maven
```

## Finally, running (Windows/Linux)

Now, go to the the folder project with the terminal, and run:

```
cd springbackend
```

then:

```
mvn clean package
```

now:

```
mvn clen compile
```

and, finally:

```
mvn spring-boot:run
```

Since you do that, you can acces [localhost:8080](http://localhost:8080) to see the project.
