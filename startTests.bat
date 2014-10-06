rem Usage: startTests.bat <module> <browser>
rem Couple of examples: 
rem 	startTests.bat 							=> will start all tests in chrome
rem		startTests.bat Login chrome				=> will start only Login related tests in chrome
rem		startTests.bat ActivityStream firefox	=> will start only ActivityStream related tests in firefox
rem		startTests.bat * firefox				=> will start all tests in firefox

rem 	NOTE: case sensitive
rem		NOTE: results are not cleared between runs, but only same tests results are overwriten
rem 	threadCount for now is hardcoded to 1. //TODO: investigate if reasonable to run in multi threads
rem	
SET MODULE=%1
SET BROWSER=%2

IF [%1]==[] SET MODULE=*
IF [%2]==[] SET BROWSER=chrome

rem call mvn clean
cls
call mvn verify -Dtestname=**/%MODULE%Test.java -Dwebdriver.driver=%BROWSER% -DthreadCount=1

call mvn thucydides:aggregate
start target\site\thucydides\index.html
pause