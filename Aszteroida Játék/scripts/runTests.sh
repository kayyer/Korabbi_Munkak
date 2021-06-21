#!/bin/bash
testDir=../tests/
progDir=../bin/

for f in ./$testDir/*.testInput ; do
	test=$f
	echo --- $f ---
	java -Duser.language=en -cp $progDir tau.asteroidgame.App $test > tmp.txt
	test=$(echo $f | sed -e 's/testInput/testOutput/')
	java -Duser.language=en -cp $progDir tau.asteroidgame.Util.TestChecker $test tmp.txt
done
