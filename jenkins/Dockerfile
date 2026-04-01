FROM docker.io/jenkins/jenkins:lts

USER root

# Buildah 설치
RUN apt-get update && \
    apt-get install -y buildah && \
    apt-get clean

USER jenkins