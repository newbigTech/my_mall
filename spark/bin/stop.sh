#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`

if [ -f "conf/conf/spark.properties" ]; then
	APP_NAME=`sed '/spark.app.name/!d;s/.*=//' conf/conf/spark.properties | tr -d '\r'`
fi

if [ -z "$APP_NAME" ]; then
	APP_NAME=`sed '/app.name/!d;s/.*=//' conf/conf/app.properties | tr -d '\r'`
fi

MAIN_JAR="$DEPLOY_DIR/$APP_NAME.jar"
PIDS=`ps -ef | grep java | grep spark | grep "$MAIN_JAR" |awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The spark does not started!"
    exit 1
fi

echo -e "Stopping the spark ...\c"
for PID in $PIDS ; do
    kill $PID > /dev/null 2>&1
done

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in $PIDS ; do
        PID_EXIST=`ps -f -p $PID | grep java`
        if [ -n "$PID_EXIST" ]; then
            COUNT=0
            break
        fi
    done
done

rm -f $DEPLOY_DIR/spark.pid

echo "OK!"
echo "PID: $PID"
