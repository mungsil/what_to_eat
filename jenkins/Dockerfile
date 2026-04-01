FROM docker.io/jenkins/jenkins:lts

USER root

# Buildah 설치
RUN apt-get update && \
    apt-get install -y buildah && \
    apt-get clean

RUN groupmod -g 1001 jenkins && \
    usermod -u 1001 -g 1001 jenkins

RUN echo "jenkins:100000:65536" >> /etc/subuid && \
    echo "jenkins:100000:65536" >> /etc/subgid

USER jenkins