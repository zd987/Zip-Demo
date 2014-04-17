Zip-Demo
========

A simple zip command tool implemented by Java.

Design Notes
----
Should I re-implement the zip algorithm, or re-design a zip tool to support various usage?

I would like to start with the latter option, as Java has already equipment with java.util.zip package.

Start with using Info-Zip command tool (Default installed on Ubuntu 12.04) to analyse how many ways can we use zip. There can be a lot of options for zip tool. Due to limited spare time, I only implemented the simplest function.

It is often the case that you should specify a lot of arguments including the classpath and other parameters to correctly launch the java program. Here I use appassembler-maven-plugin to make the java commond line tool start easier.

I should move the common utility method into separate helper class. I plan to implement the wildcard (*) support, and recursive flag support.

Seems I do not do well on the design patters here. Maybe the below design patterns are metioned in my project:
* Command
* Interpreter

Instruction to launch and execute:
--
Clone the project into local workspace:
```sh
git.exe clone --progress -v "https://github.com/zd987/Zip-Demo" "E:\2014apr\workspace\Zip-Demo"
```
You should correctly set up Java and Maven envrionment. (Make sure the JAVA_HOME\bin and MAVEN_HOME\bin are in your system PATH environment variable.)

Suppose the project folder is: E:\2014apr\workspace\zip

####build the project:####
```sh
E:\2014apr\workspace\Zip-Demo> mvn clean install
```
You can run the windows batch file to launch the tool: (default to print the help)
```sh
E:\2014apr\workspace\Zip-Demo> target\appassembler\bin\zip
usage: zip [-options] [zipfile [file ...]]
 -help   print this message
 -r      recurse into directories
```
The tool can support compress multiple file entries(including directories), and support wildcard *.

You can verify the zip output files by several other existing tools(WinRAR, Info-Zip, etc)

Currently the zip tool will override the existing zip file specified in the argument.

####Here are some example:####
1. compress the single file:
```sh
E:\2014apr\workspace\Zip-Demo> target\appassembler\bin\zip x.zip a.txt
adding: a.txt
Task Complete.
```
2. compress the file list:
```sh
E:\2014apr\workspace\Zip-Demo> target\appassembler\bin\zip x.zip a.txt b.txt
adding: a.txt
adding: b.txt
Task Complete.
```
3. compress the directory(not recursively):
```sh
E:\2014apr\workspace\Zip-Demo> target\appassembler\bin\zip x.zip folder1
adding: folder1/
Task Complete.
```
4. compress the directory(recursively):
```sh
E:\2014apr\workspace\Zip-Demo> target\appassembler\bin\zip -r x.zip folder1
adding: folder1\c.txt
adding: folder1\folder2\d.txt
Task Complete.
```
5. compress the files specified by wildcard * :
```sh
E:\2014apr\workspace\Zip-Demo> target\appassembler\bin\zip -r x.zip *.txt *.xml
adding: a.txt
adding: b.txt
adding: pom.xml
Task Complete.
```
