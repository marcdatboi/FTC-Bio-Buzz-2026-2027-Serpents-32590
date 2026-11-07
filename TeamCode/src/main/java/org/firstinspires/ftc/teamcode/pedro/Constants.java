package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Localizer;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("frontLeft");
        c.frontRightName.set("frontRight");
        c.backLeftName.set("backLeft");
        c.backRightName.set("backRight");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
//        c.xPodOffset.set(-4.721558338075172); old
        c.yPodOffset.set(-4.65738461712214);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);

        // New configs
        c.xPodOffset.set(-3.17);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.2419380551375949);
                Controller secondaryTranslationalForward = Controller.proportional(0.08938964666944588);
                Controller primaryTranslationalLateral = Controller.proportional(1.4384925724016568);
                Controller secondaryTranslationalLateral = Controller.proportional(0.531484568272969);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01763270318504324));
                c.brake.set(Controller.proportionalFeedforward(0.014987797707286754));

                c.headingFeedback.set(Controller.proportional(3.8831225001646925));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04496065519620883, 0.0039164277621744886));

                c.linearBrakeCoefficients.set(Matrix.diag(0.06629831588717833, 0.04682788283512004));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0019223357232628735, 0.0017485480855145993));

                c.maxAchievableForwardVelocity.set(57.73143190393364);
                c.maxAchievableStrafeVelocity.set(46.96162727279446);
                c.naturalForwardDeceleration.set(43.77104374410941);
                c.naturalStrafeDeceleration.set(69.26411771248712);

                c.parametricTConstraint.set(0.025);
                c.velocityConstraint.set(0.1);
                c.translationalConstraint.set(0.1);
                c.headingConstraint.set(Math.toRadians(0.4));
                c.timeoutConstraint.set(100.0);

                c.brakeAggression.set(1.0);
                c.maxBrakingPower.set(0.2);
                c.maxAccelerationConstraint.set(ForesightConfig.Constraint.NONE);
                c.maxVelocityConstraint.set(ForesightConfig.Constraint.NONE);
                c.maxDecelerationConstraint.set(ForesightConfig.Constraint.NONE);
                c.coastDownToVelocity.set(0.0);

                c.brakeAtEnd.set(true);
                c.translationalDeviationTolerance.set(2.5);
                c.headingDeviationTolerance.set(Math.toRadians(11.25));
            }
    );
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}