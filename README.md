# storm-local-development-setup

Example of developing Apache Storm topologies locally without installing storm on your computer.

This repository covers simple Storm 2 topology (WordCount) and docker-compose file which is responsible for running Storm Cluster, Storm UI, and container for deploying topology.

## Quick Start
You have to build the project (WordkCount topology):
```shell script
mvn clean package
```
then you need to run docker images:
```
docker-compose up
```
Docker-compose starts Storm Cluster, Storm UI and deploys WordCount topology to Storm Cluster.

Storm UI is aviable under http://localhost:8080

Alternatively you can force to recreate the containers (useful when there is some state stored in the container):
```shell script
docker-compose up --build --force-recreate
```
If something doesn't work, you can remove images and try again:
```shell script
docker-compose rm -f
docker-compose up --build --force-recreate
```

## Notes
- This project uses the [wait-for script](https://github.com/eficode/wait-for)
- The docker-compose setup uses
	- [official storm images](https://hub.docker.com/_/storm)
	- [official zookeeper image](https://hub.docker.com/r/_/zookeeper/)

