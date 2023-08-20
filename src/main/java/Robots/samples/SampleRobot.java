package Robots.samples;

import swarm.robot.VirtualRobot;

public class SampleRobot extends VirtualRobot {

    public SampleRobot(int id, double x, double y, double heading) {
        super(id, x, y, heading);
    }

    public void setup() {
        System.out.println("My Test Robot");
        super.setup();
    }

    public void loop() throws Exception {
        super.loop();

        if (state == robotState.RUN) {
            System.out.println("Run");

        } else if (state == robotState.WAIT) {
            System.out.println("Waiting");

        } else if (state == robotState.BEGIN) {
            System.out.println("Begin");

        }
    }

    @Override
    public void communicationInterrupt(String msg) {
        System.out.println("communicationInterrupt on " + id + " with msg:" + msg);
    }

}