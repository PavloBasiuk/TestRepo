Description:
Small web UI automation tests project to only show how tests can be done.

There are 2 ways of using it:

1. As automation engineer - develop, run and debug tests.

2. As anybody else - just run tests and analyze results.

Pre-requisites:

1. Java (please download from http://java.com and follow embedded instructions on how to install)

2. Create new environment variable called JAVA_HOME with full path to installed java (e.g.c:\Program Files\Java\jdk1.7.0_51). YOu can follow nice picture instructions from here http://www.wikihow.com/Set-Java-Home or here https://confluence.atlassian.com/display/DOC/Setting+the+JAVA_HOME+Variable+in+Windows or simply run from command line: setx JAVA_HOME=<your path to java>


Additional pre-requisites for automation engineers:

1. Setup maven (http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

2. Setup IntelliJIdea (or any other IDE for Java)

3. Setup required webdrivers



Usage:

Unpack bin/automation_project-bin.zip to dedicated folder.


startCompiledTests.bat <module> <browser>


Couple of examples: 
	startCompiledTests.bat 						=> will start all tests in chrome
	startCompiledTests.bat Login chrome			=> will start only Login related tests in chrome
	startCompiledTests.bat ActivityStream firefox	=> will start only ActivityStream related tests in firefox
	startCompiledTests.bat * firefox			=> will start all tests in firefox


NOTE: very first run will take much more time because maven will download all dependencies
NOTE: sometimes it is not working from first time, try to execute same test again then
NOTE: all parameters are case sensitive
NOTE: results are not cleared between runs, but only same tests results are overwriten


Results are opened automatically when test run finishes. 
To clean results you can run clean.bat.