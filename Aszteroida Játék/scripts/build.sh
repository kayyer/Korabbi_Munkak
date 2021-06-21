#!/bin/bash
find ../src -iname "*.java" | xargs javac -d ../bin/
cp -r ../res/META-INF ../bin
cp -r ../src/main/resources/* ../bin
CWD=$(pwd)
cd ../bin
jar -cmvf META-INF/MANIFEST.MF "$CWD""/asteroidGame.jar" .
cd "$CWD"
