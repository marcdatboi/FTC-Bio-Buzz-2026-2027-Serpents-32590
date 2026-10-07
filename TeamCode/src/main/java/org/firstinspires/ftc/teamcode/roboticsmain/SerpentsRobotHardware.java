



package org.firstinspires.ftc.teamcode.roboticsmain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SerpentsRobotHardware {
    /**
     * Contains all of the robot's hardware objects and methods
     */
    private boolean isInitialized = false;
    private DcMotor frontRight, backRight, frontLeft, backLeft;
    private DcMotor[] allMotors;

    private DcMotor intakeMotor;

    private HardwareMap hwMap;
    private Telemetry telemetry;


    // === Constructor ===
    public SerpentsRobotHardware(HardwareMap inputHwMap, Telemetry inputTelemetry) {
        this.hwMap = inputHwMap;
        this.telemetry = inputTelemetry;
    }

    public void initialize() {
        /**
         * Initializes all hardware objects
         *
         * Returns
         * void
         *
         * Throws
         * None
         */

        // Define Motors
        this.frontRight = this.hwMap.get(DcMotor.class, "frontRight");
        this.backRight = this.hwMap.get(DcMotor.class, "backRight");
        this.frontLeft = this.hwMap.get(DcMotor.class, "frontLeft");
        this.backLeft = this.hwMap.get(DcMotor.class, "backLeft");

        this.allMotors = new DcMotor[]{this.frontRight, backRight, frontLeft, backLeft};

        this.intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");

        // Set zero power behavior
        for (DcMotor motor : allMotors) motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Mecanum Drive
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        this.isInitialized = true;
        telemetry.addLine("Hardware Initialized ^w^");
        telemetry.update();
    }

    public void setMotorPowers(double[] powers) {
        /**
         * Sets the power of all 4 motors simultaneously and
         * is mostly for mecanum drive and autonomous.
         *
         * Returns
         * void
         *
         * Throws NullPointerException
         */
        this.isInitialized();

        for (int i = 0; i < allMotors.length; i++) {
            allMotors[i].setPower(powers[i]);

            // Logs
            telemetry.addData("Motor Power" + (i + 1) + ": ", allMotors[i].getPower());
        }
    }

    public void isInitialized() throws NullPointerException
    {
        /**
         * Checks whether the hardware is initialized or not
         * and throws an exception if yes.
         *
         * Returns
         * void
         *
         * Throws
         * NullPointerException
         */
        if (!this.isInitialized)
        {
            throw new NullPointerException("isInitialized() >> Robot Hardware is NOT initialized!");
        }
    }

    public  DcMotor[] getAllMotors() throws NullPointerException
    {
        /**
         * Returns all motor objects
         *
         * Returns
         * DcMotor[]
         *
         * Throws
         * NullPointerException
         *
         */
        telemetry.addLine("getAllMotors() >> Attempting to retrieve motor objects...");
        this.isInitialized();
        return allMotors;
    }

    public double[] getAllMotorPowers() throws NullPointerException
    {
        /**
         * Returns all motor powers in this order:
         * FR, BR, FL, BL
         *
         * Returns
         * double[4]
         *
         * Throws
         * NullPointerException
         */
        telemetry.addLine("getAllMotorPowers() >> Attempting to retrieve all motor powers...");
        this.isInitialized();

        double[] resultPowers = new double[4];
        for (int i = 0; i < 4; i++)
        {
            resultPowers[i] = allMotors[i].getPower();
        }
        return resultPowers;
    }

    public void testMotors() throws NullPointerException
    {
        /**
         * Tests the motors for around 5 seconds by putting 1.0 for all values
         *
         * Returns
         * void
         *
         * Throws
         * NullPointerException
         */
        telemetry.addLine("testMotors() >> Attempting to test the motors...");
        this.isInitialized();

        ElapsedTime time = new ElapsedTime();
        time.reset();

        while (true)
        {
            for (DcMotor motor : this.allMotors)
            {
                motor.setPower(1.0);
            }
            if (time.seconds() >= 5.0) return;
        }



    }

    public void setIntakePower(double power)
    {
        this.isInitialized();
        this.intakeMotor.setPower(power);
    }
}