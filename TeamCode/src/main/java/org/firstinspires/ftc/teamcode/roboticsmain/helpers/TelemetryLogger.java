



package org.firstinspires.ftc.teamcode.roboticsmain.helpers;
import com.pedropathing.follower.Follower;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryLogger
{
    Follower follower;
    Telemetry telemetry;

    public TelemetryLogger(Follower _follower, Telemetry _telemetry)
    {
        this.follower = _follower;
        this.telemetry = _telemetry;
    }

    public void displayCurrentPosition() throws NullPointerException
    {
        // NOTE: use this in the opmode loop only!
        this.telemetry.addLine("--- CURRENT ROBOT POS ---");
        this.telemetry.addData("X: ", this.follower.pose().x());
        this.telemetry.addData("Y: ", this.follower.pose().y());
        this.telemetry.addData("θ: ", this.follower.pose().heading());
    }
}
