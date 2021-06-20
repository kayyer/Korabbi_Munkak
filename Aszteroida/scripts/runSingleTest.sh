#!/bin/bash
testDir="../tests/"
progDir="../bin/"

inputTest="$1.testInput"
outputTest="$1.testOutput"

if [[ "$#" -ne 1 ]] ;then
	echo "No argument specified!"
    exit
fi
if [[ $(cat "$testDir""$inputTest" | wc -l) -eq 0 ]]; then
	cat "$testDir""$inputTest" | wc -l
	echo "$testDir$inputTest doesn't exist or empty!"
    exit
fi
if [[ $(cat "$testDir""$outputTest" | wc -l) -eq 0 ]] ; then
	echo "$outputTest doesn't exist or empty!"
	exit
fi

echo --- $1 ---
cd $testDir
java -Duser.language=en -cp $progDir tau.asteroidgame.App $inputTest > tmp.txt
java -Duser.language=en -cp $progDir tau.asteroidgame.Util.TestChecker $outputTest tmp.txt
