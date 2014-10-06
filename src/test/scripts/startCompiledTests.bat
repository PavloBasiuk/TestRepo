rem Usage: startCompiledTests.bat <module> <browser>
rem Couple of examples: 
rem 	startCompiledTests.bat 							=> will start all tests in chrome
rem		startCompiledTests.bat Login chrome				=> will start only Login related tests in chrome
rem		startCompiledTests.bat ActivityStream firefox	=> will start only ActivityStream related tests in firefox
rem		startCompiledTests.bat * firefox				=> will start all tests in firefox

rem 	NOTE: case sensitive
rem		NOTE: results are not cleared between runs, but only same tests results are overwriten
rem 	threadCount for now is hardcoded to 1. //TODO: investigate if reasonable to run in multi threads
rem	
SET MODULE=%1
SET BROWSER=%2

rem Default values: all tests for chrome driver
IF [%1]==[] SET MODULE=*
IF [%2]==[] SET BROWSER=chrome
IF [%3]==[] SET TEST=*

SET WBU=-Dwebdriver.base.url=https://nextpodio.com
SET OPT1=-Dmaven.test.failure.ignore=true
SET OPT2=-DthreadCount=1
SET OPT3=-Dwebdriver.driver=%BROWSER% 
SET OPT4=-Dbrowser.session.reuse=false

SET mvn=3rdParty\maven\bin\mvn.bat
call %mvn% install:install-file -DgroupId=automation_project -DartifactId=automation_project -Dversion=0.1-SNAPSHOT -Dpackaging=jar -Dfile=automation_project.jar
call %mvn% dependency:unpack
call %mvn% failsafe:integration-test %OPT1% %OPT2% %OPT3% %OPT4% -Dit.test=%MODULE%#%TEST%
call %mvn% thucydides:aggregate
start target\site\thucydides\index.html
pause
