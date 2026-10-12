



package org.firstinspires.ftc.teamcode.roboticsmain.helpers;
import com.acmerobotics.roadrunner.Pose2d;

public class SerpentsMathHelper
{
    // NOTE: ALL MEASUREMENTS ARE IN Millimeters


    // --- GEOMETRY ---
    public static final double WHEEL_RADIUS = 104;
    public static final double WHEEL_DIAMETER = WHEEL_RADIUS * 2;
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    public static final double GEAR_RATIO = 19.2; // 19.2 : 1
    public static final double TRACK_WIDTH = inchToMillimeter(16);


    // --- HARDWARE ---
    public static final double TICKS_PER_REV = 537.7;
    public static final double TICKS_PER_INCH = (TICKS_PER_REV * GEAR_RATIO) / (WHEEL_DIAMETER * Math.PI);
    public static final double INCHES_PER_TICK = millimeterToInch((WHEEL_CIRCUMFERENCE / TICKS_PER_REV));


    // --- CONVERSIONS ---
    public static double millimeterToInch(double millimeters) { return millimeters / 25.4; }
    public static double inchToMillimeter(double inches) { return inches * 25.4; }


    // --- STARTING POSITION ---
}
