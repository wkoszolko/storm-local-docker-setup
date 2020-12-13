# storm-local-development
Example how to develop storm locally without installing storm on your computer.

## Quick Start
You have to build the project:
```shell script
mvn clean package
```
then you need to run docker image:
```
docker-compose up
```
Alternatively you can force to recreate the containers (useful when there is some state stored in the container):
```shell script
docker-compose up --build --force-recreate
```
If something doesn't work, you can remove images and try again:
```shell script
docker-compose rm -f
```
- open Storm UI: http://localhost:8080

## Notes
- This project uses the [wait-for script](https://github.com/eficode/wait-for)
- The docker-compose setup uses
	- [official storm images](https://hub.docker.com/_/storm)
	- [official zookeeper image](https://hub.docker.com/r/_/zookeeper/)

