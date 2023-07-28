package robotImplementations;

import swarm.robot.VirtualRobot;

public class MyTestRobot extends VirtualRobot {

    public MyTestRobot(int id, double x, double y, double heading) {
        super(id, x, y, heading);
    }

    public void setup() {
        System.out.println("My Test Robot Started");
        super.setup();
    }

    public void loop() throws Exception {
        super.loop();

        if (state == robotState.RUN) {
            System.out.println("Test");
            delay(1000);
        }
    }

    @Override
    public void communicationInterrupt(String msg) {
        System.out.println("communicationInterrupt on " + id + " with msg:" + msg);
    }

}
