
import swarm.configs.MQTTSettings;
import swarm.robot.Robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import Robots.samples.MazeFollowingRobot;
import Robots.samples.ObstacleAvoidRobot;
import Robots.samples.SampleRobot;

public class App {

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

            // Start a single robot
            Robot robot = new MazeFollowingRobot(10, 9, 9, 90);
            new Thread(robot).start();

            // // Start a swarm of robots
            // int[] robotList = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

            // int startX = 0;
            // int startY = 0;
            // int startHeading = 90;

            // Robot[] vr = new VirtualRobot[robotList.length];

            // for (int i = 0; i < robotList.length; i++) {
            // vr[i] = new SampleRobot(robotList[i], startX + 40 * i, startY + 50 * i,
            // startHeading + 10 * i);
            // new Thread(vr[i]).start();
            // }

        } catch (FileNotFoundException ex) {
            // file does not exist
            System.out.println("File Not Found !!!");

        } catch (IOException ex) {
            // I/O error
            System.out.println("IO Error !!!");
        }
    }

}
