package Robots.samples;

import swarm.Interfaces.IRobotState;
import swarm.robot.VirtualRobot;

public class MazeFollowingRobot extends VirtualRobot {

    private int distanceThreshold = 15;
    private int nearThreshold = 10;

    // The default movement speed
    private int defaultMoveSpeed = 100;

    public MazeFollowingRobot(int id, double x, double y, double heading) {
        super(id, x, y, heading);
    }

    public void setup() {
        super.setup();
    }

    @Override
    public void loop() throws Exception {
        state = IRobotState.robotState.RUN;
        super.loop();


        if(state == IRobotState.robotState.RUN){
            double dist = distSensor.getDistance();


            if(dist<=nearThreshold){
                if(dist<=1){
                    motion.rotateDegree((int) (defaultMoveSpeed * 0.75), 91);
                    if(distSensor.getDistance()<=1){
                        motion.rotateDegree((int) (defaultMoveSpeed * 0.75), -91);
                        motion.rotateDegree((int) (defaultMoveSpeed * 0.75), -91);
                        if(distSensor.getDistance()<=1){
                            motion.rotateDegree((int) (defaultMoveSpeed * 0.75), -91);
                        }
                    }
                }else{
                    motion.moveDistance(defaultMoveSpeed,1);
                    //motion.move(defaultMoveSpeed, defaultMoveSpeed, 100);
                }
            }else{
                motion.moveDistance(defaultMoveSpeed,10);
               // motion.move(defaultMoveSpeed, defaultMoveSpeed, 1000);
            }

        }
    }
}
