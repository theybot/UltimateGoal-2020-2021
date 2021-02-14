package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotCustomade;

@Autonomous(name = "TahelAutoTest", group = "teamcode")
public class TahelAutoTest extends RobotCustomade {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        telemetry.addData("LF:",LF.getCurrentPosition());
        telemetry.addData("LB:",LB.getCurrentPosition());
        telemetry.addData("RF:",RF.getCurrentPosition());
        telemetry.addData("RB:",RB.getCurrentPosition());
        telemetry.update();
        MyDriveTrain.encoderDrive(0.5,25,-25,-25,25,1);
        MyDriveTrain.encoderDrive(0.5,-100,-100,-100,-100,2);
        MyDriveTrain.encoderDrive(0.5,-100,-100,-100,-100,2);
    }


}
