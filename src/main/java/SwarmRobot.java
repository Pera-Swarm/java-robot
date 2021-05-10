
import swarm.configs.MQTTSettings;
import swarm.robot.Robot;
import swarm.robot.VirtualRobot;
import swarm.robot.exception.RGBColorException;
import swarm.robot.types.RGBColorType;

import robotImplementations.ColorRippleRobot;
import robotImplementations.DiscoverColorRobot;
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
            // If it isn't there, please make one, as given sample in the 'sample_mqtt.properties' file

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

            obstacleAvoidingExperiment();
            // colorRippleExperiment();
            // discoverColorExperiment();

        } catch (FileNotFoundException ex) {
            // file does not exist
            System.out.println("File Not Found !!!");

        } catch (IOException ex) {
            // I/O error
            System.out.println("IO Error !!!");
        }
    }

    private static void obstacleAvoidingExperiment() {

        int[] robotList = {0, 1, 2, 3, 4};
        Robot[] vr = new VirtualRobot[robotList.length];

        for (int i = 0; i < robotList.length; i++) {
            vr[i] = new ObstacleAvoidRobot(robotList[i], -50 + 25 * i, 0, 90);
            new Thread(vr[i]).start();
        }

    }

    private static void colorRippleExperiment() {
        // Note: 0,1,2,6 and 7 are ignored; hardware robots
        int[] robotList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        circularFormation(robotList, 0, 0, 0, 60, 0, 36);
    }

    private static void discoverColorExperiment() {

        Robot[] vr = new VirtualRobot[10];
        RGBColorType obstacleColor = null;
        try {
            obstacleColor = new RGBColorType(255, 0, 0);
        } catch (RGBColorException e) {
            e.printStackTrace();
        }

        vr[0] = new DiscoverColorRobot(10, -52, 32, 45, obstacleColor);
        vr[1] = new DiscoverColorRobot(11, -32, -12, -20, obstacleColor);
        vr[2] = new DiscoverColorRobot(12, 49, -23, 3, obstacleColor);
        vr[3] = new DiscoverColorRobot(13, 54, 65, -70, obstacleColor);
        vr[4] = new DiscoverColorRobot(14, 0, -40, 105, obstacleColor);

        vr[5] = new DiscoverColorRobot(0, 80, 20, 90, obstacleColor);
        vr[6] = new DiscoverColorRobot(1, 40, -80, -90, obstacleColor);
        vr[7] = new DiscoverColorRobot(2, -40, -70, 45, obstacleColor);
        vr[8] = new DiscoverColorRobot(6, -50, -50, -90, obstacleColor);
        vr[9] = new DiscoverColorRobot(7, -70, 0, -45, obstacleColor);

        //robots start to move
        for (Robot robot : vr) {
            new Thread(robot).start();
        }
    }

    private static void lineFormation(int[] robotList, int startX, int startY, int heading, int incX, int incY) {
        Robot[] vr = new VirtualRobot[robotList.length];

        for (int i = 0; i < robotList.length; i++) {
            vr[i] = new ObstacleAvoidRobot(robotList[i], startX + incX * i, startY + incY * i, heading);
            new Thread(vr[i]).start();
        }
    }

    private static void circularFormation(int[] robotList, int centerX, int centerY, int headingOffset, int radius, int startAngle, int deltaAngle) {
        Robot[] vr = new VirtualRobot[robotList.length];

        int x, y, robotHeading;

        for (int i = 0; i < robotList.length; i++) {
            double a = (startAngle + i * deltaAngle);
            x = (int) (radius * Math.cos(a * Math.PI / 180));
            y = (int) (radius * Math.sin(a * Math.PI / 180));
            robotHeading = (int) (a + headingOffset);

            if (i == 0 || i == 1 || i == 2 || i == 6 | i == 7) {
                System.out.println(i + "> x:" + x + " y:" + y + " heading:" + robotHeading);
            } else {
                vr[i] = new ColorRippleRobot(robotList[i], x, y, robotHeading);
                // vr[i] = new ObstacleAvoidRobot(robotList[i], x, y, robotHeading);
                new Thread(vr[i]).start();
            }

        }
    }

    private static void spiralFormation(int[] robotList, int centerX, int centerY, int headingOffset, int startRadius, int deltaRadius, int startAngle, int deltaAngle) {
        Robot[] vr = new VirtualRobot[robotList.length];

        int x, y, robotHeading;

        for (int i = 0; i < robotList.length; i++) {
            double a = (startAngle + i * deltaAngle);
            double r = startRadius + (deltaRadius * i);
            x = (int) (r * Math.cos(a * Math.PI / 180));
            y = (int) (r * Math.sin(a * Math.PI / 180));
            robotHeading = (int) (a + headingOffset);

            vr[i] = new ColorRippleRobot(robotList[i], x, y, robotHeading);
            new Thread(vr[i]).start();
        }
    }
}
