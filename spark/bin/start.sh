#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
LIB_DIR=$DEPLOY_DIR/lib
LOG_DIR=~/logs/all

if [ -z "$SPARK_HOME" ]; then
	echo 'please set SPARK_HOME value ...'
	exit 1
fi

if [ -f "conf/conf/spark.properties" ]; then
	APP_NAME=`sed '/spark.app.name/!d;s/.*=//' conf/conf/spark.properties | tr -d '\r'`
	SPARK_MAIN=`sed '/spark.main/!d;s/.*=//' conf/conf/spark.properties | tr -d '\r'`
	MASTER_URL=`sed '/spark.master.url/!d;s/.*=//' conf/conf/spark.properties | tr -d '\r'`
fi

if [ -z "$APP_NAME" ]; then
	APP_NAME=`sed '/app.name/!d;s/.*=//' conf/conf/app.properties | tr -d '\r'`
	SPARK_MAIN=`sed '/spark.main/!d;s/.*=//' conf/conf/app.properties | tr -d '\r'`
	MASTER_URL=`sed '/spark.master.url/!d;s/.*=//' conf/conf/app.properties | tr -d '\r'`
fi

MAIN_JAR="$DEPLOY_DIR/$APP_NAME.jar"

mkdir -p $LOG_DIR

SPARK_OPTS=" --conf spark.driver.userClassPathFirst=true --driver-memory 2G "

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi
JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi
# JAVA_MEM_OPTS=" -server -Xms256m -Xmx1g -Xmn128m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
JAVA_MEM_OPTS=" -server -XX:SurvivorRatio=2 -XX:+UseParallelGC "

JAVA_OPTS="$JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS"

PIDFILE=$DEPLOY_DIR/spark.pid
if [ -f "$PIDFILE" ] && kill -0 $(cat "$PIDFILE"); then
   echo 'spark job is already running'
   exit 1
fi

echo -e "Starting the spark ...\c"

if [ -n "$SPARK_MAIN" ]; then
	$SPARK_MAIN="--class $SPARK_MAIN"
fi
  
nohup $SPARK_HOME/bin/spark-submit --master $MASTER_URL $SPARK_MAIN $SPARK_OPTS --driver-java-options "$JAVA_OPTS" $@ $MAIN_JAR > $LOG_DIR/$APP_NAME.log 2>&1 < /dev/null &
echo $! > $PIDFILE

while [ -z "$PIDS" ]; do    
    echo -e ".\c"
    sleep 1 
    PIDS=`ps -ef | grep spark | grep "$MAIN_JAR" | awk '{print $2}' `
done

echo "OK!"
echo "PID: $PIDS"

