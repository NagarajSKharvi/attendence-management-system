#! /bin/bash
#title           :ams.sh
#description     :This script will make a header for a bash script.
#date            :22/02/2022
#version         :0.1
#usage		     :./ams.sh 2 in LINUX & sh ams.sh 2 in MAC OS
#notes           :Install Vim
echo "Git Pull"
#git pull
echo "Git pull successful"
#### JAR name start with
jarName="ams-0.1"
jarDirectory="target/"
### Get Running/Previous version of JAR name
perviousVersionOfJar=$(ls $jarDirectory*.jar)
echo "Previous version of jar " $perviousVersionOfJar
###Start build the new jar
echo "Start/build jar using mavenw"
./mvnw clean install
appName=$jarName".jar"
#echo "Killing existing instances" $appName
#pkill -f $appName
##Re-name the jar with current date and time
newJarName=$jarName$(date '+%Y_%m_%d_%H_%M_%S')".jar"
mv $jarDirectory$appName $jarDirectory$newJarName
##Start new Jar
echo "Starting AMS application from jar"
count=1
if [ $# -gt 0 ] 
then
    count=$1
fi
echo "Total number of instance to start" $count
for i in $(seq 1 $count);
do
 echo "Starting instance " $i " of " $count;
 nohup java -jar $jarDirectory$newJarName &
done
### Sleep for some time to start current version
echo "Start sleep for 10 Seconds"
sleep 10s
### Stop and Delete the previous version of JAR
echo "Stoping "$perviousVersionOfJar
pkill -f ${perviousVersionOfJar#*build/libs/}
echo "Removing previous version of JAR: " $perviousVersionOfJar
rm $perviousVersionOfJar
#### 
echo "Everything is done"
