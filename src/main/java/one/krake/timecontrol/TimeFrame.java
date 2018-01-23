package one.krake.timecontrol;

import one.krake.timecontrol.TimeFrame;

public class TimeFrame {
    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;

    public TimeFrame(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw   = yaw;
        this.pitch = pitch;
    }
}
