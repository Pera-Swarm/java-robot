// This robot will move freely, avoiding obstacles 
// Written by Nuwan Jaliyagoda 

package Robots.samples;

import swarm.robot.VirtualRobot;

public class ObstacleAvoidRobot extends VirtualRobot {

    // The minimum distance that robot tries to keep with the obstacles
    private int distanceThreshold = 15;

    // The default movement speed
    private int defaultMoveSpeed = 100;

    public ObstacleAvoidRobot(int id, double x, double y, double heading) {
        super(id, x, y, heading);
    }

    public void setup() {
        super.setup();
    }

    @Override
    public void loop() throws Exception {
        super.loop();

        if (state == robotState.RUN) {
            double dist = distSensor.getDistance();

            if (dist < distanceThreshold) {
                // Generate a random number in [-1000,1000] range
                // if even, rotate CW, otherwise rotate CCW an angle depends on the random
                // number
                int random = -1000 + ((int) ((Math.random() * 2000)));
                int sign = (random % 2 == 0) ? 1 : -1;

                System.out.println("\t Wall detected, go back and rotate " + ((sign > 0) ? "CW" : "CCW"));

                // Go back a little
                motion.move(-100, -100, 900);

                // rotate
                int loopCount = 0;
                while (distSensor.getDistance() < distanceThreshold && loopCount < 5) {
                    // Maximum 5 tries to rotate and find a obstale free path
                    motion.rotate((int) (defaultMoveSpeed * 0.75 * sign), 1000);
                    loopCount++;
                }

                // rotate a little more
                motion.rotate((int) (defaultMoveSpeed * 0.75 * sign), 500);

            } else {
                motion.move(defaultMoveSpeed, defaultMoveSpeed, 1000);
            }
        }

    }
}
