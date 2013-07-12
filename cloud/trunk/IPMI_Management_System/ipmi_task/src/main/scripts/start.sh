#!/bin/sh
if [ $? -eq 0 ]
then
       lib=../conf/:../lib/activation-1.1.jar:../lib/antlr-2.7.6.jar:../lib/asm-1.5.3.jar:../lib/asm-attrs-1.5.3.jar:../lib/cglib-2.1_3.jar:../lib/commons-beanutils-1.7.0.jar:../lib/commons-beanutils-core-1.8.0.jar:../lib/commons-collections-3.2.jar:../lib/commons-configuration-1.6.jar:../lib/commons-dbcp-1.2.1.jar:../lib/commons-digester-1.8.jar:../lib/commons-lang-2.4.jar:../lib/commons-logging-1.1.1.jar:../lib/commons-pool-1.2.jar:../lib/dom4j-1.6.1.jar:../lib/ehcache-1.2.3.jar:../lib/ganymed-ssh2-build210-hudson-1.jar:../lib/hibernate-3.2.1.ga.jar:../lib/hibernate-annotations-3.3.0.ga.jar:../lib/hibernate-commons-annotations-3.0.0.ga.jar:../lib/hibernate-memcached-1.2.2.jar:../lib/ipmi_sdk-1.0-SNAPSHOT.jar:../lib/ipmi_share-1.0-SNAPSHOT.jar:../lib/ipmi_task-1.0-SNAPSHOT.jar:../lib/jta-1.1.jar:../lib/log4j-1.2.14.jar:../lib/mail-1.4.jar:../lib/memcached-2.3.1.jar:../lib/memcached-2.6.6.jar:../lib/mysql-connector-java-5.0.5.jar:../lib/persistence-api-1.0.jar:../lib/quartz-1.8.0.jar:../lib/quartz-oracle-1.8.0.jar:../lib/slf4j-api-1.5.10.jar:../lib/slf4j-log4j12-1.5.10.jar:../lib/spring-2.5.5.jar:../lib/xercesImpl-2.0.2.jar:../lib/xml-apis-1.0.b2.jar    
        nohup java -cp $lib -Xms256m -Xmx512m cn.com.kxcomm.task.MainService  &
	./status.sh
else
        echo "Task is already running."
        echo " "
fi
