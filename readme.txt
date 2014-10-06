Pre-requisites:
1. Java (please download from http://java.com and follow embedded instructions on how to install)
2. Create new environment variable called JAVA_HOME with full path to installed java (e.g.c:\Program Files\Java\jdk1.7.0_51). You can follow nice picture instructions from here: http://www.wikihow.com/Set-Java-Home or here: https://confluence.atlassian.com/display/DOC/Setting+the+JAVA_HOME+Variable+in+Windows or simply run from command line: setx JAVA_HOME=<your path to java>

Usage: startTests.bat <module> <browser>
Couple of examples: 
	startTests.bat 				=> will start all tests in chrome
	startTests.bat Login chrome		=> will start only Login related tests in chrome
	startTests.bat ActivityStream firefox	=> will start only ActivityStream related tests in firefox
	startTests.bat * firefox		=> will start all tests in firefox

NOTE: very first run will take much more time because maven will download all dependencies
NOTE: all parameters are case sensitive
NOTE: results are not cleared between runs, but only same tests results are overwriten


Results are opened automatically when test run finishes. 
To clean results you can run clean.bat.