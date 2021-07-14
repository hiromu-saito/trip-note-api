FROM adoptopenjdk/openjdk11

RUN apt-get update && \
    apt-get install &&\
    apt-get install software-properties-common -y
RUN add-apt-repository ppa:cwchien/gradle -y &&\
  apt-get update &&\
  apt-get upgrade gradle -y