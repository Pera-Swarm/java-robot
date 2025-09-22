package Robots.samples;

import swarm.robot.VirtualRobot;

public class ColorRippleRobot extends VirtualRobot {

  private int currentHopId;
  private boolean colorUpdated;

  public ColorRippleRobot(int id, double x, double y, double heading) {
    super(id, x, y, heading);
  }

  public void setup() {
    super.setup();

    neoPixel.changeColor(0, 0, 0);
    colorUpdated = false;
    currentHopId = -1;
    coordinates.publishCoordinate();
  }

  @Override
  public void loop() throws Exception {
    super.loop();
    // Anything specially check in continuously
  }

  @Override
  public void communicationInterrupt(String msg) {
    System.out.println("communicationInterrupt on " + id + " with msg:" + msg);

    String[] s = msg.split(" ");

    if (s.length == 4) {
      int hopId = Integer.parseInt(s[0]);

      if (colorUpdated) {
        // a returning message, don't forward
        neoPixel.changeColor(0, 0, 0);

      } else if (hopId > currentHopId) {
        int hopR = Integer.parseInt(s[1]);
        int hopG = Integer.parseInt(s[2]);
        int hopB = Integer.parseInt(s[3]);
        neoPixel.changeColor(hopR, hopG, hopB);
        currentHopId = hopId;
        colorUpdated = true;

        delay(2000);

        // Send it to the next robot
        simpleComm.sendMessage((hopId + 1) + " " + hopR + " " + hopG + " " + hopB);
        neoPixel.changeColor(0, 0, 0);
      }
    } else {
      System.out.println("Invalid msg received");
    }
  }

  public void start() {
    super.start();

    // Things to do when start action
    neoPixel.changeColor(0, 0, 0);
    colorUpdated = false;
    currentHopId = -1;
  }

}