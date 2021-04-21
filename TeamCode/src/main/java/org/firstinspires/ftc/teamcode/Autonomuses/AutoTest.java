package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.RobotCustomade;


@Autonomous(name = "AutoTest", group = "teamcode")
//@Disabled
public class AutoTest extends RobotCustomade {
    String TargetZone = "A";
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        waitForStart();
        LF.setPower(1);
        sleep(1000);
        LF.setPower(0);
        LB.setPower(1);
        sleep(1000);
        LB.setPower(0);
        RF.setPower(1);
        sleep(1000);
        RF.setPower(0);
        RB.setPower(1);
        sleep(1000);
        RB.setPower(0);
        telemetry.addData("RF", RF.getCurrentPosition());
        telemetry.addData("LF", LF.getCurrentPosition());
        telemetry.addData("RB", RB.getCurrentPosition());
        telemetry.addData("LB", LB.getCurrentPosition());
        telemetry.update();
        sleep(10000);
    }
}

