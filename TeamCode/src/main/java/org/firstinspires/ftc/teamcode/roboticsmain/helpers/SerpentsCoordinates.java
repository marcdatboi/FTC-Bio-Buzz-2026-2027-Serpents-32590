



package org.firstinspires.ftc.teamcode.roboticsmain.helpers;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

/**
 * Centralized pose definitions for the FTC field (141.5" × 141.5").
 *
 * All poses are in inches and degrees, designed for use with the
 * {@code com.pedropathing} pathing library. The field is divided into two
 * alliance-color namespaces — {@link RedAlliance} and {@link BlueAlliance} —
 * each containing identical structure.
 *
 * ==============================
 *  USAGE & PATH-CHAIN RULES
 * ==============================
 *
 * 1. Multi-step paths (e.g. Starting → Scoring → Flower)
 *    return the robot to the alliance starting position between each step.
 *    For example, pathToScore → pathToFlower will:
 *       a. Move from the starting position to the scoring pose.
 *       b. Return to the starting position.
 *       c. Move from the starting position to the flower pose.
 *
 * 2. If you need the robot to visit multiple locations in sequence without
 *    returning to starting between each, chain the control points manually
 *    using a single {@code path.moveTo(List.of(…))} call.
 *
 * ==============================
 *  SWAPPING ALLIANCE SIDES
 * ==============================
 *
 * To cross the field and perform a routine on the opposite alliance side:
 *
 *   a. Path to the center of the field using the {@code ToCenter} control
 *      points from your own alliance's {@code Starting} class.
 *   b. From the center, path to the opposing alliance's starting position
 *      using that class with {@code .reverse()} applied to the pose chain.
 *
 * Example — Blue robot scores on the red side, visits the rightmost flower,
 * then returns to the blue starting position:
 *
 *   pathToScore        → score on red side
 *   toParking          → return to red starting position
 *   toRightFlower      → go to rightmost flower on red side
 *   toParking          → return to red starting position
 *   toCenter           → path to center via ToCenter control points
 *   RedAlliance.Starting.*.reverse() → path to blue starting position
 *
 * ==============================
 *  COORDINATE SYSTEM
 * ==============================
 *
 * - X axis: 0" (left) → 141.5" (right)
 * - Y axis: 0" (bottom / red scoring side) → 141.5" (top / blue scoring side)
 * - Heading: 0° = facing toward positive Y (blue wall), increasing counter-clockwise
 *
 * The BlueAlliance poses are the red-side poses mirrored across the field
 * center: X → 141.5 − X, heading → (heading + 180) mod 360.
 */
public class SerpentsCoordinates
{
    private static final PoseFactory pf = PoseFactory.degrees();

    public static class RedAlliance
    {
        public static class Starting {
            public static final Pose rightOfParking = pf.of(8.6, 82.9, 0.0);
            public static final Pose leftOfFlower = pf.of(8.6, 60, 0.0);


            // --- Control Points ---
            public static class ToScoring {

                // South
                public static final Pose southScoring1 = pf.of(67.01, 74.07, 0);
                public static final Pose southScoring2 = pf.of(0, 0, 0);
                public static final Pose southScoring3 = pf.of(34.28, 33.99, 0);
                public static final Pose southScoring4 = pf.of(58.77, 10.5, 0);

                // North
                public static final Pose northScoring1 = pf.of(64.43, 57.15, 0);
                public static final Pose northScoring2 = pf.of(0.0, 141.5, 0);
                public static final Pose northScoring3 = pf.of(44.36, 90.75, 0);
                public static final Pose northScoring4 = pf.of(64.08, 129.89, 0);
            }

            public static class ToParking { // Use the Parking class' poses
                // Upper Parking
                public static final Pose upperParking1 = pf.of(43.1, 90.85, 0);
                public static final Pose upperParking2 = pf.of(39.26, 122.91, 0);

                // Lower Parking
                public static final Pose lowerParking1 = pf.of(55.11, 78.14, 0);
            }

            public static class ToFlower {

                // Left Flower
                public static final Pose left = pf.of(8.5, 82.9, 0.0);

                // Top Flower
                public static final Pose top1 = pf.of(51.55, 47.82, 0.0);
                public static final Pose top2 = pf.of(12.2, 87.18, 0.0);

                // Bottom Flower
                public static final Pose bottom1 = pf.of(38.34, 58.35, 0.0);
                public static final Pose bottom = pf.of(29.43, 10.84, 0.0);

                // Right Flower
                public static final Pose right1 = pf.of(68.46, 28.62, 0.0);
                public static final Pose right2 = pf.of(0.17, 141.5, 0.0);
                public static final Pose right3 = pf.of(30.43, 111.25, 0.0);
                public static final Pose right4 = pf.of(111.18, 120.87, 0.0);
            }

            public static class ToCenter {

                // Starting LEFT of the flower
                public static final Pose leftOfFlowerStartingPoint1 = pf.of(36.09, 32.51, 0.0);
                public static final Pose leftOfFlowerStartingPoint2 = pf.of(30.0, 141.5, 0.0);
                public static final Pose leftOfFlowerStartingPoint3 = pf.of(30.0, 141.5, 0.0);
                public static final Pose leftOfFlowerStartingPoint4 = pf.of(64.27, 112.91, 0.0);

                public static final Pose rightOfParkingStartingPoint1 = pf.of(36.09, 108.99, 0.0);
                public static final Pose rightOfParkingStartingPoint2 = pf.of(30.0, 0.0, 0.0);
                public static final Pose rightOfParkingStartingPoint3 = pf.of(30.0, 0.0, 0.0);
                public static final Pose rightOfParkingStartingPoint4 = pf.of(64.27, 28.59, 0.0);
            }

        }

        public static class Scoring {
            public static final Pose scoringNorth = pf.of(58.5, 126.0, 270.0);
            public static final Pose scoringSouth = pf.of(58.87, 9.48, 90.0);
        }

        public static class Parking {
            public static final Pose upperParking = pf.of(9.86, 116.67, 0);
            public static final Pose lowerParking = pf.of(9.86, 96.3, 0);
        }

        public static class Flower {
            public static final Pose left = pf.of(13.52, 46.81, 180.0);
            public static final Pose top = pf.of(46.8, 127.5, 90.0);
            public static final Pose bottom = pf.of(94.55, 15.1, 270.0);
            public static final Pose right = pf.of(128.01, 94.56, 0.0);
        }
    }

    public static class BlueAlliance
    {
        public static class Starting {
            public static final Pose rightOfParking = pf.of(132.9, 60, 180.0) ;
            public static final Pose leftOfFlower = pf.of(132.9, 82.9, 180.0);


            // --- Control Points ---
            public static class ToScoring {

                // South
                public static final Pose southScoring1 = pf.of(74.49, 74.07, 180);
                public static final Pose southScoring2 = pf.of(141.5, 0, 180);
                public static final Pose southScoring3 = pf.of(107.22, 33.99, 180);
                public static final Pose southScoring4 = pf.of(82.73, 10.5, 180);

                // North
                public static final Pose northScoring1 = pf.of(77.07, 57.15, 180);
                public static final Pose northScoring2 = pf.of(141.5, 141.5, 180);
                public static final Pose northScoring3 = pf.of(97.14, 90.75, 180);
                public static final Pose northScoring4 = pf.of(77.42, 129.89, 180);
            }

            public static class ToParking { // Use the Parking class' poses
                // Upper Parking
                public static final Pose upperParking1 = pf.of(83.41, 87.36, 180);
                public static final Pose upperParking2 = pf.of(114.44, 14.92, 180);

                // Lower Parking
                public static final Pose lowerParking1 = pf.of(86.39, 78.14, 180);
            }

            public static class ToFlower {

                // Left Flower
                public static final Pose left = pf.of(133.0, 82.9, 180.0);

                // Top Flower
                public static final Pose top1 = pf.of(90.0, 47.82, 180.0);
                public static final Pose top2 = pf.of(129.3, 87.18, 180.0);

                // Bottom Flower
                public static final Pose bottom1 = pf.of(103.16, 58.35, 180.0);
                public static final Pose bottom = pf.of(112.07, 10.84, 180.0);

                // Right Flower
                public static final Pose right1 = pf.of(73.04, 28.62, 180.0);
                public static final Pose right2 = pf.of(141.33, 141.5, 180.0);
                public static final Pose right3 = pf.of(111.07, 111.25, 180.0);
                public static final Pose right4 = pf.of(30.32, 120.87, 180.0);
            }

            public static class ToCenter {

                // Starting LEFT of the flower
                public static final Pose leftOfFlowerStartingPoint1 = pf.of(105.41, 32.51, 0.0);
                public static final Pose leftOfFlowerStartingPoint2 = pf.of(111.51, 141.5, 0.0);
                public static final Pose leftOfFlowerStartingPoint3 = pf.of(111.51, 141.5, 0.0);
                public static final Pose leftOfFlowerStartingPoint4 = pf.of(77.23, 112.91, 0.0);

                public static final Pose rightOfParkingStartingPoint1 = pf.of(105.41, 108.99, 0.0);
                public static final Pose rightOfParkingStartingPoint2 = pf.of(111.51, 0.0, 0.0);
                public static final Pose rightOfParkingStartingPoint3 = pf.of(111.51, 0.0, 0.0);
                public static final Pose rightOfParkingStartingPoint4 = pf.of(77.23, 28.59, 0.0);
            }
        }

        public static class Scoring {
            public static final Pose scoringNorth = pf.of(83.0, 126.0, 270.0);
            public static final Pose scoringSouth = pf.of(82.63, 12.09, 90.0);
        }

        public static class Parking {
            public static final Pose lowerParking = pf.of(131.64, 24.83, 180);
            public static final Pose upperParking = pf.of(131.64, 45, 180);
        }

        public static class Flower {
            public static final Pose left = pf.of(127.98, 46.81, 180.0);
            public static final Pose top = pf.of(94.7, 127.5, 90.0);
            public static final Pose bottom = pf.of(46.95, 15.1, 270.0);
            public static final Pose right = pf.of(13.45, 94.56, 0.0);
        }
    }
}
