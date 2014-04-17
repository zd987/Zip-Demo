Zip-Demo
========

A simple zip command tool implemented by Java.

Overview
----
Should I re-implement the zip algorithm, or re-design a zip tool to support various usage?

I would like to start with the latter option, as Java has already equipment with java.util.zip package.

Start with using Info-Zip command tool (Default installed on Ubuntu 12.04) to analyse how many ways can we use zip. There can be a lot of options for zip tool. Due to limited spare time, I only implemented the simplest function.

It is often the case that you should specify a lot of arguments including the classpath and other parameters to correctly launch the java program. Here I use appassembler-maven-plugin to make the java commond line tool start easier.

I should move the common utility method into separate helper class. I plan to implement the wildcard (*) support, and recursive flag support.

Instruction to launch and execute:
--
Suppose the project folder is: E:\2014apr\workspace\zip
