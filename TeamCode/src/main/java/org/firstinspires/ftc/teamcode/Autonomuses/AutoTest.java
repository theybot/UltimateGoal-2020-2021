package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.RobotCustomade;


@Autonomous(name = "AutoTest", group = "teamcode")
public class AutoTest extends RobotCustomade {
    String TargetZone = "A";
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        waitForStart();
    }
}

