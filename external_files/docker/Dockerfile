# 베이스 이미지 설정
FROM ubuntu:22.04

# 기본 패키지 설치
RUN apt-get -y update
RUN apt-get -y upgrade

# 시간대 설정 (Asia/Seoul 시간대로)
RUN DEBIAN_FRONTEND=noninteractive TZ=Asia/Seoul apt-get -y install tzdata
RUN ln -fs /usr/share/zoneinfo/Asia/Seoul /etc/localtime && dpkg-reconfigure -f noninteractive tzdata

# OpenJDK 설치 !!!필요한 JDK 버전에 맞는 설치!!!
RUN apt install -y openjdk-21-jdk

COPY springboot_spring_admin-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-jar", "/app/springboot_spring_admin-0.0.1-SNAPSHOT.jar"]