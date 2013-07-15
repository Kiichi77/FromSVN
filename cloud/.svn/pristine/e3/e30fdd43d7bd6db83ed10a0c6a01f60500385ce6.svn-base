#!/bin/sh
proStatus=`ps -ef|grep java|grep ipmi_task|grep -v grep`
echo " "
echo "Process           Status  ID"
echo "======================================"
if [ -n "$proStatus" ]
then
        proID=`echo $proStatus|awk '{print $2}'`
        echo "ipmi_task   Up      $proID"
        echo " "
        exit 1
else
        echo "ipmi_task   Down    null"
        echo " "
        exit 0
fi
