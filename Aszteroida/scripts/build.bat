@ECHO OFF
cd /d %0\..\..\src\main\java
javac -d %0\..\..\bin -J-Duser.language=en tau\asteroidgame\*.java
javac -d %0\..\..\bin -J-Duser.language=en tau\asteroidgame\Util\*.java
javac -d %0\..\..\bin -J-Duser.language=en tau\asteroidgame\UserInterface\*.java
xcopy /Y %0\..\..\res\META-INF %0\..\..\bin\META-INF\
xcopy /Y/E %0\..\..\src\main\resources\* %0\..\..\bin
cd %0\..\..\bin
jar -J-Duser.language=en -cmvf META-INF\MANIFEST.MF %0\..\..\bin\asteroidGame.jar .

echo.
pause