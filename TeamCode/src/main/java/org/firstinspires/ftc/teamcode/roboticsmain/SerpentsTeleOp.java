



package org.firstinspires.ftc.teamcode.roboticsmain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.roboticsmain.helpers.SerpentsMecanumDrive;

@TeleOp(name = "SerpentsTeleOp", group = "OpMode")
public class SerpentsTeleOp extends LinearOpMode
{
    SerpentsRobotHardware serpentsHardware;
    SerpentsMecanumDrive serpentsMecanumDrive;
    ElapsedTime timer;


    // === Initialize Hardware ===
    public void runOpMode()
    {
        timer = new ElapsedTime();
        serpentsHardware = new SerpentsRobotHardware(hardwareMap, telemetry);
        serpentsHardware.initialize();

        // Buttons Buffers
        boolean pressedA = false;
        boolean pressedB = false;
        boolean pressedX = false;
        boolean pressedY = false;

        boolean pressedRightBumper = false;
        boolean pressedLeftBumper = false;

        // Cooldown for bumpers
        ElapsedTime rightBumperTimer = new ElapsedTime();
        ElapsedTime leftBumperTimer = new ElapsedTime();
        boolean canPressRightBumper = true;
        boolean canPressLeftBumper = true;

        // Mecanum Drive
        serpentsMecanumDrive = new SerpentsMecanumDrive(telemetry, serpentsHardware.getAllMovementMotors());
        telemetry.update();

        // Wait until play button is pressed
        waitForStart();
        serpentsHardware.setGapServoPosition(0.0);





        // ---=== Opmode Loop ===---
        while (opModeIsActive() && !isStopRequested())
        {

            // --- Movement ---
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;


            // --- Button Function Mapping ---
            boolean a_button = gamepad1.a;
            boolean b_button = gamepad1.b;
            boolean x_button = gamepad1.x;
            boolean y_button = gamepad1.y;
            boolean increaseBumper = gamepad1.right_bumper;
            boolean decreaseBumper = gamepad1.left_bumper;

            // Gamepad Controls
            /**
             *     Y
             * B       A
             *     X
             */

            // A - Intake (Forward)
            if (a_button && !pressedA)
            {
                serpentsHardware.startIntakeSequence(-1.0, -1.0);
                pressedA = true;
            }

            // B - Outtake (Forward)
            if (b_button && !pressedB)
            {
                serpentsHardware.startOuttakeSequence(1.0, -1.0);
                pressedB = true;
            }

            // X - Outtake (Reverse)
            if (x_button && !pressedX)
            {
                serpentsHardware.startOuttakeSequence(-0.5, 1.0);
                pressedX = true;
            }

            // Y - Intake (Reverse)
            if (y_button && !pressedY) {
                serpentsHardware.startIntakeSequence(1.0, 1.0);
                pressedY = true;
            }


            // --- Bumpers ---
            if (increaseBumper && !pressedRightBumper && canPressRightBumper) { // Right Bumper - INCREASE DISTANCE
                // last config: 0.7
                serpentsHardware.setGapServoPosition(0.0);
                canPressRightBumper = false;
                pressedRightBumper = true;
                rightBumperTimer.reset();
            }

            if (decreaseBumper && !pressedLeftBumper && canPressLeftBumper) { // Left Bumper - DECREASE DISTANCE
                // last config: 1.0
                serpentsHardware.setGapServoPosition(0.27);
                canPressLeftBumper = false;
                pressedLeftBumper = true;
                leftBumperTimer.reset();
            }


            // --- Update Everything at the end of the Loop ---
            double[] mecanumPowers = serpentsMecanumDrive.calculateMecanumPowers(drive, strafe, turn);
            serpentsHardware.setMotorPowers(mecanumPowers); // Set mecanum powers
            serpentsHardware.stopIntakeSequence();
            serpentsHardware.stopOuttakeSequence();


            // --- Update Button States ---
            // Bumper Cooldowns
            if (leftBumperTimer.milliseconds() >= 500.0 && leftBumperTimer.milliseconds() <= 550.0) { // 0.5s
                canPressLeftBumper = true;
            }

            if (rightBumperTimer.milliseconds() >= 500.0 && rightBumperTimer.milliseconds() <= 550.0) { // 0.5s
                canPressRightBumper = true;
            }

            // Update Button States
            pressedB = false;
            pressedA = false;
            pressedY = false;
            pressedX = false;
            pressedLeftBumper = false;
            pressedRightBumper = false;
            telemetry.addLine(String.format("GAP SERVO POS: %.2f", serpentsHardware.getGapServo().getPosition()));
            telemetry.addLine(String.format("canPressLeftBumper = %b", canPressLeftBumper));
            telemetry.addLine(String.format("canPressRightBumper = %b", canPressRightBumper));
            telemetry.update(); // Update Telemetry
        }
    }
}
