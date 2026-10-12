



package org.firstinspires.ftc.teamcode.roboticsmain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roboticsmain.SerpentsRobotHardware;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.CoconutHelper;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.SerpentsMecanumDrive;

@TeleOp(name = "SerpentsTeleOp", group = "OpMode")
public class SerpentsTeleOp extends LinearOpMode
{
    SerpentsRobotHardware serpentsHardware;
    SerpentsMecanumDrive serpentsMecanumDrive;


    // === Initialize Hardware ===
    public void runOpMode()
    {
        serpentsHardware = new SerpentsRobotHardware(hardwareMap, telemetry);
        serpentsHardware.initialize();

        // Mecanum Drive
        serpentsMecanumDrive = new SerpentsMecanumDrive(telemetry, serpentsHardware.getAllMotors());
        telemetry.update();

        // Wait until play button is pressed
        waitForStart();





        // ---=== Opmode Loop ===---
        while (opModeIsActive() && !isStopRequested())
        {
            CoconutHelper.gay(telemetry); // Ignore this :3


            // Buttons
            boolean pressedX = false;


            // --- Movement ---
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;


            // --- Buttons ---
            boolean x_button = gamepad1.a;

            // Gamepad Controls
            if (x_button && !pressedX)
            {
                serpentsHardware.setIntakePower(-1.0);
                pressedX = true;
            }


            // --- Update Everything at the end of the Loop ---
            double[] mecanumPowers = serpentsMecanumDrive.calculateMecanumPowers(drive, strafe, turn);
            serpentsHardware.setMotorPowers(mecanumPowers);
            serpentsHardware.setIntakePower(0.0);
            telemetry.update();
        }
    }
}
