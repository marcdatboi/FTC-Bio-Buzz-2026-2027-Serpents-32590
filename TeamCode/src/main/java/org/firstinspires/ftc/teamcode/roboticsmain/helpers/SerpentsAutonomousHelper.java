



package org.firstinspires.ftc.teamcode.roboticsmain.helpers;

import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.roboticsmain.SerpentsRobotHardware;

public class SerpentsAutonomousHelper
{
    Follower follower;
    SerpentsRobotHardware hardware;
    TelemetryLogger logger;


    // === Constructor ===
    public SerpentsAutonomousHelper(Follower follower, SerpentsRobotHardware hardware, TelemetryLogger logger)
    {
        this.follower = follower;
        this.hardware = hardware;
        this.logger = logger;

        if (
            this.follower == null ||
            this.hardware == null ||
            this.logger == null
        ) {
            throw new NullPointerException("SerpentsAutonomousHelper() >> One or more parameters are null!");
        }
    }
}
