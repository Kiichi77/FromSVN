#!/bin/bash
svn update
statpath="/root/statsvn"
echo "statpath=$statpath"
mkdir $statpath 2>/dev/null
rm -rf $statpath/*
echo ${PWD##*/}
svn log -v --xml > /root/${PWD##*/}/logfile.log
java -jar statsvn.jar logfile.log /root/${PWD##*/} -output-dir $statpath
