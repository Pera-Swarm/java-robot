# Builds

This folder contains the pre-build jar files of the different robot setups for easily re-use.

## 1. Maze Following Robot

This robot is designed to navigate through a maze using sensors to detect walls and obstacles, in an infinite loop.

- Pera-Swarm Library Version: 1.0.3
- RobotId: 11
- Start Location: (9,9)
- Heading: North (90 degrees)
- Environment: Maze (Ch11)

Run the below command in terminal within the root directory of the project. (Make sure you have Java installed, and all the MQTT configs are set in `src/main/resources/mqtt.properties`)

```bash
java -jar builds/java-robot-maze-follow-v1.jar
```
