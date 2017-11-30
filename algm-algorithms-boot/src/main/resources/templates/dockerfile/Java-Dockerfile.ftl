#使用的基础镜像
FROM intelligentpathways/java8
#作者信息
MAINTAINER  beyond "beyond@foxmail.com"
#VOLUME /tmp创建/tmp目录并持久化到Docker数据文件夹，因为Spring Boot使用的内嵌Tomcat容器默认使用/tmp作为工作目录
VOLUME /tmp
# 创建目录并添加jar包
RUN mkdir -p /work/server
ADD algmarket-publish-0.0.1-SNAPSHOT.jar /work/server/app.jar

#ENTRYPOINT表示容器运行后默认执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/work/server/app.jar"]

#暴露8080端口
#EXPOSE 8080
EXPOSE 80