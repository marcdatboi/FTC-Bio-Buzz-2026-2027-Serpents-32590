



package org.firstinspires.ftc.teamcode.roboticsmain.helpers;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.Paths;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import org.firstinspires.ftc.teamcode.roboticsmain.SerpentsRobotHardware;
import org.firstinspires.ftc.teamcode.roboticsmain.helpers.interfaces.SerpentsTrajectory;

public class SerpentsTrajectoryLibrary
{
    Follower follower;
    SerpentsRobotHardware hardware;
    TelemetryLogger logger;


    // === Constructor ===
    public SerpentsTrajectoryLibrary(Follower follower, SerpentsRobotHardware hardware, TelemetryLogger logger)
    {
        this.follower = follower;
        this.hardware = hardware;
        this.logger = logger;

        if (
            this.follower == null ||
            this.hardware == null ||
            this.logger == null
        ) {
            throw new NullPointerException("SerpentsAutonomousHelper() >> One or more parameters are null!");
        }
    }


    // === Red Alliance ===
    class Red
    {
        // NOTE: All classes here represent starting points on the field.
        class Flower implements SerpentsTrajectory
        {
            @Override
            public Path toScore() {
                return Paths.curve(
                        SerpentsMathHelper.redStartingPositionNearFlower,
                        SerpentsMathHelper.redControlPoint3,
                        SerpentsMathHelper.redControlPoint4,
                        SerpentsMathHelper.redShootingPositionSouth
                ).linear(
                        SerpentsMathHelper.redStartingPositionNearFlower,
                        SerpentsMathHelper.redShootingPositionSouth
                );
            }

            @Override
            public Path toParking()
            {
                return Paths.curve(
                        SerpentsMathHelper.redShootingPositionSouth,
                        SerpentsMathHelper.redControlPoint5,
                        SerpentsMathHelper.redControlPoint6,
                        SerpentsMathHelper.redParkingPosition
                ).linear(
                        SerpentsMathHelper.redShootingPositionSouth,
                        SerpentsMathHelper.redParkingPosition
                );
            }

            @Override
            public Command autoRoutine()
            {
                return sequential(
                        follow(follower, toScore()),
                        instant(() -> hardware.startOuttakeSequence(0.8, 0.0)), // Ramp outtake
//                      instant(() -> hardware.setGapServoPosition(0.0)),
                        waitMs(3000),
                        instant(() -> hardware.startOuttakeSequence(0.7, -1.0)), // Shoot pollen
                        waitMs(3000),
                        instant(() -> hardware.stopOuttakeSequence()),
                        follow(follower, toParking())
                );
            }
        }

        class Parking implements SerpentsTrajectory
        {
            @Override
            public Path toScore() {
                return curve(
                        SerpentsMathHelper.redStartingPositionNearParking,
                        SerpentsMathHelper.redControlPoint1,
                        SerpentsMathHelper.redControlPoint2,
                        SerpentsMathHelper.redShootingPositionSouth
                ).linear(
                        SerpentsMathHelper.redStartingPositionNearParking,
                        SerpentsMathHelper.redShootingPositionSouth
                );
            }

            @Override
            public Path toParking()
            {
                return Paths.curve(
                        SerpentsMathHelper.redShootingPositionSouth,
                        SerpentsMathHelper.redControlPoint5,
                        SerpentsMathHelper.redControlPoint6,
                        SerpentsMathHelper.redParkingPosition
                ).linear(
                        SerpentsMathHelper.redShootingPositionSouth,
                        SerpentsMathHelper.redParkingPosition
                );
            }

            @Override
            public Command autoRoutine()
            {
                return sequential(
                        follow(follower, toScore()),
                        instant(() -> hardware.startOuttakeSequence(0.8, 0.0)), // Ramp outtake
//                      instant(() -> hardware.setGapServoPosition(0.0)),
                        waitMs(3000),
                        instant(() -> hardware.startOuttakeSequence(0.7, -1.0)), // Shoot pollen
                        waitMs(3000),
                        instant(() -> hardware.stopOuttakeSequence()),
                        follow(follower, toParking())
                );
            }
        }
    }


    // === Blue Alliance ===
    class Blue
    {
        class Flower implements SerpentsTrajectory
        {
            @Override
            public Path toScore()
            {
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

            @Override
            public Path toParking()
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

            @Override
            public Command autoRoutine()
            {
                return sequential
                (
                        follow(follower, toScore()),
                        instant(() -> hardware.startOuttakeSequence(0.8, 0.0)), // Ramp outtake 4 pollen
//                      instant(() -> hardware.setGapServoPosition(0.0)),
                        waitMs(3000),
                        instant(() -> hardware.startOuttakeSequence(0.7, -1.0)), // Shoot pollen
                        waitMs(3000),
                        instant(() -> hardware.stopOuttakeSequence()),
                        follow(follower, toParking())
                );
            }
        }

        class Parking implements SerpentsTrajectory
        {
            @Override
            public Path toScore()
            {
                return Paths.curve(
                        SerpentsMathHelper.blueStartingPositionNearParking,
                        SerpentsMathHelper.blueControlPoint1,
                        SerpentsMathHelper.blueControlPoint2,
                        SerpentsMathHelper.blueShootingPositionNorth
                ).linear(
                        SerpentsMathHelper.blueStartingPositionNearParking,
                        SerpentsMathHelper.blueShootingPositionNorth
                );
            }

            @Override
            public Path toParking()
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

            @Override
            public Command autoRoutine()
            {
                return sequential(
                        follow(follower, toScore()),
                        instant(() -> hardware.startOuttakeSequence(0.8, 0.0)), // Ramp outtake
//                      instant(() -> hardware.setGapServoPosition(0.0)), // Adjust for pollen
                        waitMs(3000),
                        instant(() -> hardware.startOuttakeSequence(0.7, -1.0)), // Shoot pollen
                        waitMs(3000),
                        instant(() -> hardware.stopOuttakeSequence()),
                        follow(follower, toParking())
                );
            }
        }
    }
}
