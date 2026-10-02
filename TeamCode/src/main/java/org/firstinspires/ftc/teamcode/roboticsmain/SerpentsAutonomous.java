



package org.firstinspires.ftc.teamcode.roboticsmain;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.CoconutHelper;

import org.firstinspires.ftc.teamcode.roboticsmain.helpers.SerpentsMecanumDrive;

@Autonomous(name = "SerpentsThomas", group = "Autonomous")
public class SerpentsAutonomous extends LinearOpMode
{
    SerpentsRobotHardware robotHardware;
    SerpentsMecanumDrive mecanumDrive;


    // --- Init Hardware ---
    public void runOpMode()
    {
        robotHardware = new SerpentsRobotHardware(hardwareMap, telemetry);
        robotHardware.initialize();

        mecanumDrive = new SerpentsMecanumDrive(telemetry, robotHardware.getAllMotors());
        CoconutHelper.gay(telemetry);
        telemetry.update();





        // ---=== Op Loop ===---
        waitForStart();
        while (!isStopRequested() && opModeIsActive())
        {
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
