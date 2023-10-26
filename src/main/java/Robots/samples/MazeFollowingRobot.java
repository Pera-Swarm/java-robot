// This robot will move in a maze environment freely
// Written by Imesh Isuranga

package Robots.samples;

import swarm.robot.VirtualRobot;
import swarm.robot.types.ProximityReadingType;

import java.util.Objects;


public class MazeFollowingRobot extends VirtualRobot {

    //minimum distance between grid wall and robot
    private final double GRID_SPACE = 18.000;

    // The default movement speed
    private final int defaultMoveSpeed = 100;

    // The default rotate speed
    private final int defaultRotateSpeed = 100;



    // Proximity Sensor options
    //angles for left,front and right side rotating
    private int[] proximityAngles = { -90, 0, 90 };

    //index to get left proximity angle
    public static int PROXIMITY_LEFT = 0;

    //index to get front proximity angle
    public static int PROXIMITY_FRONT = 1;

    //index to get right proximity angle
    public static int PROXIMITY_RIGHT = 2;





    public MazeFollowingRobot(int id, double x, double y, double heading) {
        super(id, x, y, heading);
    }

    public void setup() {
        System.out.println("My Test Robot Started");

        super.setup();

        // Setup proximity sensor with 3 angles
        proximitySensor.setAngles(proximityAngles);

        // Start immediately after the setup
        state = robotState.RUN;

    }

    @Override
    public void loop() throws Exception {
        super.loop();


        if (state == robotState.RUN) {
            //creating ProximityReadingType object from proximitySensor
            ProximityReadingType p = proximitySensor.getProximity();

            //get present distances from robot's left,front and right
            int[] d = proximitySensor.getProximity().distances();


            //robot rotating way :- if distance from (any side +6) > GRID_SPACE then robot will rotate that side.

            if (d[PROXIMITY_RIGHT] + 6 > GRID_SPACE) {
                // Right
                motion.rotateDegree(defaultRotateSpeed, 90);

            } else if (d[PROXIMITY_FRONT] + 6 > GRID_SPACE) {
                // Front

            } else if (d[PROXIMITY_LEFT] + 6 > GRID_SPACE) {
                // Turn Left
                motion.rotateDegree(defaultRotateSpeed, -90);

            } else {
                //if robot can't go left,right and front then robot will rotate to back.
                motion.rotateDegree(defaultRotateSpeed, 180);
            }

            //robot move
            motion.moveDistance(defaultMoveSpeed, GRID_SPACE);
            delay(1000);

        }
}}
