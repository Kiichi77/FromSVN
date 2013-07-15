./status.sh
if [ $? -eq 0 ]
then
       echo "ipmi_task gateway is NOT running."
        echo " "
else
        proID=`ps -ef|grep java|grep ipmi_task|grep -v grep|awk '{print $2}'`
        echo "ipmi_task callLog gateway..."
        kill -9 $proID
        sleep 1
        ./status.sh
fi
