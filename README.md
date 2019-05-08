## Setup

### Handy commands
docker kill $(docker ps -q)

## Dockerize a java app and run it 
```
docker build . -t fill-heap:0.1 -f Dockerfile-java8-basic
docker run -d -m 250MB  fill-heap:0.1
docker run -d -m 250MB  -e JAVA_OPTS='-XX:+UnlockExperimentalVMOptions  -XX:+UseCGroupMemoryLimitForHeap  -XshowSettings:vm' fill-heap:0.1
```

## Starting tests

### What happens on Java 8u121?
`docker run -m 250MB openjdk:8u131 java -XshowSettings:vm -version`

### Moving to Java 8u131

JDK 8u131 has backported the ability of JVM to detect how much memory is available when running inside a Docker container from JDK 9

```
docker run -m 250MB openjdk:8u131 java \
  -XX:+UnlockExperimentalVMOptions \
  -XX:+UseCGroupMemoryLimitForHeap \
  -XshowSettings:vm -version
```

Even for JDK 9 we need to use the same options.

If we want to juice out even more of the available memory then use -XX:MaxRAMFraction=<1,2,3...> (where 1 will utilize 100% of avalilable memory and 2 will utilize 50%)
```
docker run -m 250MB openjdk:8u131 java \
  -XX:+UnlockExperimentalVMOptions \
  -XX:+UseCGroupMemoryLimitForHeap \
  -XX:MaxRAMFraction=1 \
  -XshowSettings:vm -version
```

### Moving to Java 10 & 11

No need to specify any experimental features, by default the JVM understands the container memory allocation by accessing the cgroups

```
docker run -m 250MB openjdk:10 java -XshowSettings:vm -version
docker run -m 250MB openjdk:11 java -XshowSettings:vm -version
```

### Why does all this matter?
If apps are written in Java and we want to utilize container orchestrators. Orchestration solutions like Kubernetes will try to efficiently “pack” containers on each nodes based on "memory" and "cpu" usage of the individual containers. 
