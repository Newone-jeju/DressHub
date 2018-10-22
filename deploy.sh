#!/bin/bash
REPOSITORY=/home/ubuntu/app/git
cd $REPOSITORY/DressHub/
echo "> Git Pull"
git pull
echo "> 프로젝트 Build Start"
./gradlew build
echo "> Build file 복사"
cp ./DressHub/build/libs/*jar $REPOSITORY/
echo "> 현재 구동중인 Application pid check"
CURRENT_PID=$(pgrep -f DressHub)
echo "$CURRENT_PID"
if [ -z $CURRENT_PID ]; then
	echo "> 최초 실행입니다. 종료하지 않습니다."
else
	echo "> kill -2 $CURRENT_PID"
	kill -9 $CURRENT_PID
	sleep 5
fi
echo "> New Application Deploy"
JAR_NAME=$(ls $REPOSITORY/ |grep 'DressHub' | tail -n 1)
echo "> JAR Name: $JAR_NAME"
nohup java -jar $REPOSITORY/$JAR_NAME &
