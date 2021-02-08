package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotCustomade;

@Disabled
@Autonomous(name = "RejAutoTest", group = "teamcode")
public class RejAutoTest extends RobotCustomade {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        MyDriveTrain.encoderDrive(0.6,-30,-30,-30,-30,2);
        telemetry.addData("LB", LB.getCurrentPosition());
        telemetry.addData("LF", LF.getCurrentPosition());
        telemetry.addData("RB", RB.getCurrentPosition());
        telemetry.addData("RF", RF.getCurrentPosition());
        telemetry.update();
        sleep(10000);
//        MyDriveTrain.RotateP(0,0.1,10,0.008);
//        telemetry.addData("getAngel", MyDriveTrain.getAngle());
//        telemetry.update();
    }
}

