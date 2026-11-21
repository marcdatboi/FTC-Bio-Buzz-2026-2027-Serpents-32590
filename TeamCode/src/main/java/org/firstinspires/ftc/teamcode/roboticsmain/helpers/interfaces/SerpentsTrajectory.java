



package org.firstinspires.ftc.teamcode.roboticsmain.helpers.interfaces;

import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;

public interface SerpentsTrajectory
{
    public Path toScore();
    public Path toParking();
    public Command autoRoutine();
}
