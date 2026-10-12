



package org.firstinspires.ftc.teamcode.roboticsmain;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.PinpointLocalizer;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.CoconutHelper;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.SerpentsMecanumDrive;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.SerpentsMathHelper;

@Autonomous(name = "SerpentsAutonomous", group = "Autonomous")
public class SerpentsAutonomous extends LinearOpMode
{
    SerpentsRobotHardware robotHardware;
    SerpentsMecanumDrive mecanumDrive;
    PinpointLocalizer localizer;


    // --- Init Hardware ---
    public void runOpMode()
    {
        // Initialize Robot Hardware
        robotHardware = new SerpentsRobotHardware(hardwareMap, telemetry);
        robotHardware.initialize();

        mecanumDrive = new SerpentsMecanumDrive(telemetry, robotHardware.getAllMotors()); // TEMPORARY
        localizer = new PinpointLocalizer(hardwareMap, SerpentsMathHelper.INCHES_PER_TICK, new Pose2d(0, 0, 0));
        telemetry.update();





        // ---=== Op Loop ===---
        waitForStart();
        while (!isStopRequested() && opModeIsActive())
        {
            CoconutHelper.gay(telemetry);
            double[] staticMotorPowers = {0.5, 0.5, 0.5};
            robotHardware.setMotorPowers(mecanumDrive.calculateMecanumPowers(
                    staticMotorPowers[0],
                    staticMotorPowers[1],
                    staticMotorPowers[2]
            ));

            telemetry.addLine("Sleeping for 2 seconds...");
            sleep(2000);

            telemetry.update();
            requestOpModeStop();
            return;
        }
    }
}
