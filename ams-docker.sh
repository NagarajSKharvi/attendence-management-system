#! /bin/bash
#title           :ams-docker.sh
#description     :This script will make a header for a bash script.
#date            :22/02/2022
#version         :0.1
#usage		     :./ams-docker.sh 2 in LINUX & sh ams-docker.sh 2 in MAC OS
#notes           :Install Vim
echo "Git Pull"
git pull
echo "Git pull successful"
#### JAR name start with
jarName="ams-0.1"
jarDirectory="target/"
###Start build the new jar
echo "Start/build jar using mavenw"
./mvnw clean install
echo "jar generated"
appName=$jarName".jar"
##Start new Jar
echo "Build docker image"
docker build -t ams .
echo "Run generated docker image as Container"
docker run -itd --network database_default --name ams -p 9999:9999 ams:latest
#### 
echo "Everything is done"
