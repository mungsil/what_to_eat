FROM docker.io/jenkins/jenkins:lts

USER root

# Buildah 설치
RUN apt-get update && \
    apt-get install -y buildah && \
    apt-get clean

# rootless 실행을 위한 subuid/subgid 설정
RUN echo "jenkins:100000:65536" >> /etc/subuid && \
    echo "jenkins:100000:65536" >> /etc/subgid

USER jenkins