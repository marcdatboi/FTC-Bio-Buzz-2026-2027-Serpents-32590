



package org.firstinspires.ftc.teamcode.roboticsmain.autonomous;
import static com.pedropathing.api.Paths.line;

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

@Autonomous(name = "RedAutonomousNearParking")
public class SerpentsRedAutonomusNearParking extends LinearOpMode
{
    private Follower follower;
    private PoseFactory p;

    @Override
    public void runOpMode()
    {
        Scheduler.reset();
        this.p = PoseFactory.degrees();
        this.follower = Constants.create(hardwareMap);
        this.follower.setPose(SerpentsMathHelper.redStartingPositionNearGarden);

        while (!isStarted() && !isStopRequested())
        {
            this.follower.update();
            telemetry.addLine("Autonomous initializing, please wait at least 3 seconds :3");
            telemetry.addData("Follower X:", follower.pose().x());
            telemetry.addData("Follower Y:", follower.pose().y());
            telemetry.addData("Follower HEADING:", follower.pose().heading());
            telemetry.update();
        }

        // Scheduling Routines
        schedule(autoRoutine());


//      --- Wait For Start ---
        waitForStart();

        while (opModeIsActive() && !isStopRequested())
        {
            follower.update();
            Scheduler.execute();
            telemetry.update();
        }
    }

    // --- Routines ---
    private Command autoRoutine()
    {
        return sequential(
                follow(follower, toScore())
        );
    }


    // --- PATHING ---
    private Path toScore() {
        return line(SerpentsMathHelper.redStartingPositionNearGarden, SerpentsMathHelper.redShootingPositionNorth).linear(
                SerpentsMathHelper.redStartingPositionNearGarden, SerpentsMathHelper.redShootingPositionNorth);
    }
}
