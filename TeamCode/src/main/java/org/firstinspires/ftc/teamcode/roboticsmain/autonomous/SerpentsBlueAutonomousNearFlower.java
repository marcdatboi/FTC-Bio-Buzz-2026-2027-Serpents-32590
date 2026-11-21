















package org.firstinspires.ftc.teamcode.roboticsmain.autonomous;
import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.Paths;
import com.pedropathing.paths.Path;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.pedropathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.pedro.Tuning;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.util.ElapsedTime;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import org.firstinspires.ftc.teamcode.roboticsmain.SerpentsRobotHardware;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.SerpentsMathHelper;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.TelemetryLogger;

import java.nio.channels.spi.SelectorProvider;

@Autonomous(name = "BlueAutonomousNearFlower")
public class SerpentsBlueAutonomousNearFlower extends LinearOpMode
{
    private Follower follower;
    private PoseFactory p;
    private TelemetryLogger logger;
    private SerpentsRobotHardware hardware;
    private ElapsedTime outtakeTimer;

    @Override
    public void runOpMode()
    {
        Scheduler.reset();
        this.p = PoseFactory.degrees();
        this.follower = Constants.create(hardwareMap);
        this.logger = new TelemetryLogger(this.follower, telemetry);
        this.hardware = new SerpentsRobotHardware(hardwareMap, telemetry);
        this.hardware.initialize();
        this.outtakeTimer = new ElapsedTime();
        this.follower.setPose(SerpentsMathHelper.blueStartingPositionNearFlower);

        while (!isStarted() && !isStopRequested())
        {
            this.follower.update();
            telemetry.addLine("Autonomous initializing, please wait at least a few seconds :3");
            this.logger.displayCurrentPosition();
            telemetry.update();
        }

        // Scheduling Routines
        schedule(this.autoRoutine());


//      --- Wait For Start ---
        waitForStart();
        hardware.setGapServoPosition(0.0); // Reset Position
        sleep(5000);

        while (opModeIsActive() && !isStopRequested())
        {
            follower.update();
            Scheduler.execute();

            // Telemetry Stuff
            this.logger.displayCurrentPosition();
            telemetry.update();
        }
    }


    // --- Routines ---
    private Command autoRoutine() // use in the 'schedule()' function
    {
        return sequential
        (
                follow(follower, toScore()),
                instant(() -> hardware.startOuttakeSequence(0.8, 0.0)), // Ramp outtake 4 pollen
//                instant(() -> hardware.setGapServoPosition(0.0)),
                waitMs(3000),
                instant(() -> hardware.startOuttakeSequence(0.7, -1.0)), // Shoot pollen
                waitMs(3000),
                instant(() -> hardware.stopOuttakeSequence()),
                follow(follower, toParking())
        );
    }


    // --- PATHING ---
    private Path toScore() {
        return Paths.curve(
                SerpentsMathHelper.blueStartingPositionNearFlower,
                SerpentsMathHelper.blueControlPoint3,
                SerpentsMathHelper.blueControlPoint4,
                SerpentsMathHelper.blueShootingPositionNorth
        ).linear(
                SerpentsMathHelper.blueStartingPositionNearFlower,
                SerpentsMathHelper.blueShootingPositionNorth
        );
    }

    private Path toParking()
    {
        return Paths.curve(
                SerpentsMathHelper.blueShootingPositionNorth,
                SerpentsMathHelper.blueControlPoint5,
                SerpentsMathHelper.blueControlPoint6,
                SerpentsMathHelper.blueParkingPosition
        ).linear(
                SerpentsMathHelper.blueShootingPositionNorth,
                SerpentsMathHelper.blueParkingPosition
        );
    }
}
