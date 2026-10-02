



package org.firstinspires.ftc.teamcode.roboticsmain.helpers;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.*;

public class CoconutHelper
{
    public static void gay(Telemetry telemetry) throws NullPointerException
    {
        String nutPath = "org\\firstinspires\\ftc\\teamcode\\roboticsmain\\testicleSmasher.png";
        File file = new File(nutPath);

        if (!file.exists()) {
            telemetry.addLine(" >> No coconut png detected. <<");
        }
//        if (!file.exists()) {
//            for (int i = 0; i < 1000000; i++) {
//                telemetry.addLine("YOUR TESTICLES WILL EXPLODE");
//            }
//
//            throw new NullPointerException("Hey twink, you forgot the coconut image. Add it now or your testicles will detonate :)");
//        }
    }
}
