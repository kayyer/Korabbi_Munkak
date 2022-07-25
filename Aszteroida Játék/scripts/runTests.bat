@ECHO OFF
SETLOCAL ENABLEDELAYEDEXPANSION
set testDir=%0\..\..\tests\
set progDir=%0\..\..\bin\

echo.
cd /d %testDir%
FOR %%G IN (*.testInput) DO (
	set test=%%G
	echo --- !test:~0,-10! ---
	java -Duser.language=en -cp %progDir% tau.asteroidgame.App !test! > tmp.txt
	set test=!test:~0,-10!.testOutput
	java -Duser.language=en -cp %progDir% tau.asteroidgame.Util.TestChecker !test! tmp.txt
	echo.	
)

pause
