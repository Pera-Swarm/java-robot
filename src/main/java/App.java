
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import Robots.samples.ColorRippleRobot;
import swarm.configs.MQTTSettings;
import swarm.robot.Robot;
import swarm.robot.VirtualRobot;

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

            // // Start a swarm of robots
            int[] robotList = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            Robot[] vr = new VirtualRobot[robotList.length];

            int x, y, robotHeading;
            int startAngle = 0;
            int deltaAngle = 360 / 10;
            int radius = 50;
            int headingOffset = 0;

            for (int i = 0; i < robotList.length; i++) {
                double a = (startAngle + i * deltaAngle);
                x = (int) (radius * Math.cos(a * Math.PI / 180));
                y = (int) (radius * Math.sin(a * Math.PI / 180));
                robotHeading = (int) (a + headingOffset);

                if (i == 0 || i == 1 || i == 2 || i == 3 | i == 4) {
                    // These are physical robots
                    System.out.println(i + "> x:" + x + " y:" + y + " heading:" + robotHeading);
                } else {
                    // These are virtual robots
                    vr[i] = new ColorRippleRobot(robotList[i], x, y, robotHeading);
                    new Thread(vr[i]).start();
                }
            }

        } catch (FileNotFoundException ex) {
            // file does not exist
            System.out.println("File Not Found !!!");

        } catch (IOException ex) {
            // I/O error
            System.out.println("IO Error !!!");
        }
    }

}
