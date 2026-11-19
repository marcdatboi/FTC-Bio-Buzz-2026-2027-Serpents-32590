



package org.firstinspires.ftc.teamcode.roboticsmain;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SerpentsRobotHardware {
    /**
     * Contains all of the robot's hardware objects and methods
     */
    private boolean isInitialized = false;
    private DcMotor frontRight, backRight, frontLeft, backLeft;
    private DcMotor outtakeMotor;
    private DcMotor conveyorMotor;
    private DcMotor turretMotor;
    private DcMotor intakeMotor;
    private DcMotor[] allMotors;

    // Servos
    private CRServo conveyorServo1, conveyorServo2;
    private CRServo[] conveyorServos;
    private Servo gapServo;

    // Misc
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


        // --- Motors ---
        // Define Movement Motors
        this.frontRight = this.hwMap.get(DcMotor.class, "frontRight"); // Port C0
        this.backRight = this.hwMap.get(DcMotor.class, "backRight"); // Port C1
        this.frontLeft = this.hwMap.get(DcMotor.class, "frontLeft"); // Port C2
        this.backLeft = this.hwMap.get(DcMotor.class, "backLeft"); // Port C3
        this.allMotors = new DcMotor[]{this.frontRight, backRight, frontLeft, backLeft};

        // --- Intake ---
        this.intakeMotor = hwMap.get(DcMotor.class, "intakeMotor"); // Port E0
        this.intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.conveyorMotor = hwMap.get(DcMotor.class, "conveyorMotor"); // Port E1
        this.conveyorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.conveyorMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        // --- Outtake ---
        this.outtakeMotor = this.hwMap.get(DcMotor.class, "outtakeMotor"); // Port E2
        this.outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.outtakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.turretMotor = this.hwMap.get(DcMotor.class, "turretMotor"); // Port E3
        this.turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // --- Movement Motors ---
        for (DcMotor motor : allMotors) motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Mecanum Drive
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        // --- Servos ---
        // NOTE: moving servo pos to '1.0' moves servo COUNTER-CLOCKWISE
        this.conveyorServo1 = hwMap.get(CRServo.class, "conveyorServoRight"); // Port E0
        this.conveyorServo2 = hwMap.get(CRServo.class, "conveyorServoLeft"); // Port E1
        this.conveyorServos = new CRServo[]{this.conveyorServo1, this.conveyorServo2};

        this.gapServo = hwMap.get(Servo.class, "gapServo"); // Port E2

        this.isInitialized = true;
        telemetry.addLine("Hardware Initialized ^w^");
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

    public DcMotor[] getAllMovementMotors() throws NullPointerException
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
        telemetry.addLine("getAllMovementMotors() >> Attempting to retrieve movement motor objects...");
        this.isInitialized();
        return allMotors;
    }

    public DcMotor getConveyorMotor() throws NullPointerException
    {
        telemetry.addLine("getConveyorMotor() >> Attempting to retrieve conveyor motor object...");
        this.isInitialized();
        return this.conveyorMotor;
    }

    public double[] getAllMovementMotorPowers() throws NullPointerException
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

    public double getConveyorMotorPower() throws NullPointerException
    {
        telemetry.addLine("getConveyorMotorPower() >> Trying to get current conveyor motor power...");
        this.isInitialized();
        return this.conveyorMotor.getPower();
    }


    // --- Set Motor Powers ---
    public void setIntakePower(double power) throws NullPointerException
    {
        this.isInitialized();
        this.intakeMotor.setPower(power);
    }

    public void setOuttakePower(double power) throws NullPointerException
    {
        this.isInitialized();
        this.outtakeMotor.setPower(power);
    }

    public void setMotorPowers(double[] powers) throws NullPointerException
    {
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

    public void setConveyorMotorPower(double power) throws NullPointerException
    {
        this.telemetry.addLine(String.format("CONVEYOR MOTOR POWER: %f", this.conveyorMotor.getPower()));

        this.isInitialized();
        this.conveyorMotor.setPower(power);
    }

    public void startIntakeSequence(double intakePower, double conveyorPower) throws NullPointerException
    {
        this.isInitialized();
        this.setIntakePower(intakePower);
        this.setConveyorMotorPower(conveyorPower);
    }

    public void stopIntakeSequence() throws NullPointerException
    {
        this.isInitialized();
        this.setIntakePower(0.0);
        this.setConveyorMotorPower(0.0);
    }

    public void startOuttakeSequence(double outtakePower, double servoPower) throws NullPointerException
    {
        this.isInitialized();
        this.setOuttakePower(outtakePower);
        this.setServoPowers(new double[]{servoPower, servoPower * -1.0});
    }

    public void stopOuttakeSequence() throws NullPointerException
    {
        this.isInitialized();
        this.setOuttakePower(0.0);
        this.setServoPowers(new double[]{0.0, 0.0});
    }


    // --- Servos ---
    public void setServoPowers(double[] servoPowers) throws NullPointerException // 1 = RIGHT | 2 = LEFT
    {
        this.isInitialized();
        telemetry.addLine(String.format("RIGHT CONVEYOR SERVO POWER: %f | LEFT CONVEYOR SERVO POWER: %f", this.conveyorServo1.getPower(), this.conveyorServo2.getPower()));
        this.conveyorServos[0].setPower(servoPowers[0]);
        this.conveyorServos[1].setPower(servoPowers[1]);
    }

    public void setGapServoPosition(double position)
    {
        isInitialized();
        this.gapServo.setPosition(position);
    }


    // --- Getters ---
    public CRServo[] getConveyorServos() throws NullPointerException
    {
        this.isInitialized();
        return this.conveyorServos;
    }

    public Servo getGapServo() { return this.gapServo; }


}