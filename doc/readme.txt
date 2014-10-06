Usage: startTests.bat <module> <browser>
Couple of examples: 
	startTests.bat 				=> will start all tests in chrome
	startTests.bat Login chrome		=> will start only Login related tests in chrome
	startTests.bat ActivityStream firefox	=> will start only ActivityStream related tests in firefox
	startTests.bat * firefox		=> will start all tests in firefox

NOTE: case sensitive
NOTE: results are not cleared between runs, but only same tests results are overwriten

Results are opened in browser when test run is finished.
