@ECHO OFF
SETLOCAL ENABLEDELAYEDEXPANSION
set testDir=%0\..\..\tests\
set progDir=%0\..\..\bin\

set inputTest=%*.testInput
set outputTest=%*.testOutput

echo.

if [%1]==[] (
	echo No argument specified!
	goto exit
)
if not exist "%testDir%!inputTest!" (
	echo !inputTest! doesn't exist!
	goto exit
)
if not exist "%testDir%!outputTest!" (
	echo !outputTest! doesn't exist!
	goto exit
)

echo --- %* ---
cd /d %testDir%
java -Duser.language=en -cp %progDir% tau.asteroidgame.App %inputTest% > tmp.txt
java -Duser.language=en -cp %progDir% tau.asteroidgame.Util.TestChecker %outputTest% tmp.txt

:exit
echo.
pause