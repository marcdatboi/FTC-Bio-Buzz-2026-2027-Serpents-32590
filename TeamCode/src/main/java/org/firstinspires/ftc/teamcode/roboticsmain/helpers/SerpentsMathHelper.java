



package org.firstinspires.ftc.teamcode.roboticsmain.helpers;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

public class SerpentsMathHelper {
    // NOTE: ALL MEASUREMENTS ARE IN MILLIMETERS!!

    // INITIALIZE
    private static final PoseFactory poseFactory = PoseFactory.degrees();


    // --- GEOMETRY ---
    public static final double WHEEL_RADIUS = 104.0;
    public static final double WHEEL_DIAMETER = WHEEL_RADIUS * 2.0;
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    public static final double GEAR_RATIO = 19.2; // 19.2 : 1
    public static final double ROBOT_LENGTH = inchToMillimeter(16.9);
    public static final double ROBOT_WIDTH = inchToMillimeter(16.4);
    public static final double ROBOT_HEIGHT = inchToMillimeter(11.0);
    public static final double MAX_VEL = rpmToVelocity(312) * 1000; // in mm (1.524m/s)
    public static final double TRACK_WIDTH = inchToMillimeter(16.61);
    public static final double LATERAL_DISTANCE = inchToMillimeter(6.5); // distance between both dead-wheels
    public static final double X_OFFSET_DISTANCE = inchToMillimeter(3.5); // y pos of parallel encoder
    public static final double Y_OFFSET_DISTANCE = inchToMillimeter(3.9); // x pos of perpendicular encoder


    // --- HARDWARE ---
    public static final double TICKS_PER_REV = 537.7;
    public static final double WHEEL_BASE = inchToMillimeter(9.7); // Basically track width but the perpendicular way
    public static final double TICKS_PER_INCH = (TICKS_PER_REV * GEAR_RATIO) / (WHEEL_DIAMETER * Math.PI);
    public static final double INCHES_PER_TICK = (WHEEL_CIRCUMFERENCE / TICKS_PER_REV); // still in millimeters


    // --- CONVERSIONS ---
    public static double millimeterToInch(double millimeters) {
        return millimeters / 25.4;
    }

    public static double inchToMillimeter(double inches) {
        return inches * 25.4;
    }

    public static double millimeterToTick(double millimeters) {
        double a = TICKS_PER_REV * GEAR_RATIO;
        return millimeters * (a / WHEEL_CIRCUMFERENCE);
    }

    public static double rpmToVelocity(double maxRpm) {
        return (maxRpm * (2.0 * Math.PI) * WHEEL_RADIUS) / 60;
    }


    // --- STARTING POSITIONS ---

    // RED ALLIANCE
    public static final Pose redStartingPositionNearGarden = poseFactory.of(8.6, 82.9, 0.0);
    public static final Pose redStartingPositionNearFlower = poseFactory.of(8.5, 60, 0.0);
    public static final Pose redShootingPositionNorth = poseFactory.of(58.5, 126.0, 270.0);
    public static final Pose redShootingPositionSouth = poseFactory.of(58.87, 9.48, 90.0);

    // BLUE ALLIANCE
    public static final Pose blueStartingPositionNearGarden = poseFactory.of(132, 58.237, 180);
    public static final Pose blueStartingPositionNearFlower = poseFactory.of(131.96, 80.74, 180);
    public static final Pose blueShootingPositionNorth = poseFactory.of(83.6, 128.2, 270);
    public static final Pose blueShootingPositionSouth = poseFactory.of(83.0, 14.28, 90);
}
