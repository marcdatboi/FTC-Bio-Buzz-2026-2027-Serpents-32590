















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
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.SerpentsMathHelper;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.TelemetryLogger;

@Autonomous(name = "BlueAutonomousNearFlower")
public class SerpentsBlueAutonomousNearFlower extends LinearOpMode
{
    private Follower follower;
    private PoseFactory p;
    private TelemetryLogger logger;

    @Override
    public void runOpMode()
    {
        Scheduler.reset();
        this.p = PoseFactory.degrees();
        this.follower = Constants.create(hardwareMap);
        this.logger = new TelemetryLogger(this.follower, telemetry);
        this.follower.setPose(SerpentsMathHelper.redStartingPositionNearGarden);

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
        sleep(2000);

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
        return sequential(
                follow(follower, toScore())
        );
    }


    // --- PATHING ---
    private Path toScore() {
        return Paths.curve(
                SerpentsMathHelper.blueStartingPositionNearFlower,
                SerpentsMathHelper.getBlueControlPointNorth,
                SerpentsMathHelper.blueShootingPositionNorth
        ).linear(
                SerpentsMathHelper.blueStartingPositionNearFlower,
                SerpentsMathHelper.blueShootingPositionNorth
        );
    }
}
