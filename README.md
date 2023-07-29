[![Java CI with Maven](https://github.com/Pera-Swarm/java-robot/actions/workflows/java-ci.yml/badge.svg)](https://github.com/Pera-Swarm/java-robot/actions/workflows/java-ci.yml)

# Java Robot 

This is a boilerplate template for java virtual robotst, *Pera-Swarm*

More details will be updated soon.

---

## Local Environment Setup

If you need to run this repository on your local environment,
please create a file named *'mqtt.properties'* in path, *'./src/resources/config/'*
as follows and provide your MQTT broker's configurations.

You can select any channel, as same as your simulation server runs on.

```
server=127.0.0.1
port=1883
username=user
password=pass
channel="v1"
```

## Install the Library

- You need to setup a GitHub Token with the scope of `read:packages` and save it along with your GitHub UserName as environment variables as follows:

```xml
<server>
    <username>{GITHUB_USERNAME}</username>
    <password>{GITHUB_TOKEN}</password>
</server>
```

- Run the following command to run the `mvn install`

```bash 
mvn -s ./settings.xml -B install --file pom.xml 
```