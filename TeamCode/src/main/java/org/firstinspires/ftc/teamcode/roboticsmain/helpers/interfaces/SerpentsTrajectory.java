



package org.firstinspires.ftc.teamcode.roboticsmain.helpers.interfaces;

import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;

public interface SerpentsTrajectory
{
    Path toScore();
    Path toParking();
    Command autoRoutine();
}
