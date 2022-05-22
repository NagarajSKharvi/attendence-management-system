# Updated as of Nov 21, 2020
# Install FROM JDK IMAGE
FROM openjdk:11

#Author of the Docker File
# MAINTAINER Nagaraj Note: MAINTAINER has been deprecated for LABEL, 
# LABEL is a key value pair 
LABEL "Maintainer"="Nagaraj"

# ADD a directory called auto start app inside the JDK IMAGE where you will be moving all of these files under this 
# DIRECTORY to
ADD . /usr/local/ams

# AFTER YOU HAVE MOVED ALL THE FILES GO AHEAD CD into the directory
RUN cd /usr/local/ams

#THE CMD COMMAND tells docker the command to run when the container is started up from the image. In this case we are
# making a REST Call.
CMD ["java", "-jar", "/usr/local/ams/target/ams-0.1.jar", "--DOCKER_HOSTNAME=container-postgresdb"]