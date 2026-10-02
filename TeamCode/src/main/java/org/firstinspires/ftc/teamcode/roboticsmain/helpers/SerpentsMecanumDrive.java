



package org.firstinspires.ftc.teamcode.roboticsmain.helpers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SerpentsMecanumDrive
{
    private DcMotor[] allMotors;
    private Telemetry telemetry;


    // === Constructor ===
    public SerpentsMecanumDrive(Telemetry telemetry, DcMotor[] driveTrainMotors)
    {
        this.allMotors = driveTrainMotors;
        this.telemetry = telemetry;
    }

    /**
     * Calculates mecanum drive motor powers and returns them as an array.
     *
     * @param drive  Forward/backward motion (-1.0 to 1.0)
     * @param strafe Left/right motion (-1.0 to 1.0)
     * @param turn   Clockwise/counter-clockwise rotation (-1.0 to 1.0)
     * @return double array containing [frontRight, backRight, frontLeft, backLeft]
     */
    public double[] calculateMecanumPowers(double drive, double strafe, double turn) {
        // Kinematic equations for Mecanum drive
        double frontLeft  = drive + strafe + turn;
        double backLeft   = drive - strafe + turn;
        double frontRight = drive - strafe - turn;
        double backRight  = drive + strafe - turn;

        // Normalize power values so no motor exceeds 1.0
        double max = Math.max(
                Math.abs(frontLeft),
                Math.max(Math.abs(backLeft), Math.max(Math.abs(frontRight), Math.abs(backRight)))
        );

        if (max > 1.0) {
            frontLeft  /= max;
            backLeft   /= max;
            frontRight /= max;
            backRight  /= max;
        }

        telemetry.addData("FL", frontLeft);
        telemetry.addData("BL", backLeft);
        telemetry.addData("FR", frontRight);
        telemetry.addData("BR", backRight);

        // Returned in the requested order: frontRight, backRight, frontLeft, backLeft
        return new double[] { frontRight, backRight, frontLeft, backLeft };
    }
}