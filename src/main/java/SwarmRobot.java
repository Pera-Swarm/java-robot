
import swarm.configs.MQTTSettings;
import swarm.robot.Robot;
import swarm.robot.VirtualRobot;
import robotImplementations.MyTestRobot;
import robotImplementations.ObstacleAvoidRobot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SwarmRobot {

    public static void main(String[] args) {

        try {
            // COMPLETE THIS BEFORE RUN
            // Read config properties from the file, src/resources/config/mqtt.properties
            // If it isn't there, please make one, as given sample in the
            // 'sample_mqtt.properties' file

            File configFile = new File("src/resources/config/mqtt.properties");
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            MQTTSettings.server = props.getProperty("server");
            MQTTSettings.port = Integer.parseInt(props.getProperty("port", "1883"));
            MQTTSettings.userName = props.getProperty("username");
            MQTTSettings.password = props.getProperty("password");
            MQTTSettings.channel = props.getProperty("channel", "v1");
            reader.close();

            Robot robot = new MyTestRobot(10, -52, 32, 45);
            new Thread(robot).start();

        } catch (FileNotFoundException ex) {
            // file does not exist
            System.out.println("File Not Found !!!");

        } catch (IOException ex) {
            // I/O error
            System.out.println("IO Error !!!");
        }
    }

}
