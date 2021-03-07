package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.BasicAuto;
import org.firstinspires.ftc.teamcode.RobotCustomade;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "TahelAutoTest", group = "teamcode")
public class TahelAutoTest extends BasicAuto {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        while (!opModeIsActive())
        {
            telemetry.addData("Analysis", UltimatePipline.getAnalysis());
            telemetry.addData("Position", UltimatePipline.position);
            telemetry.update();


            sleep(50);
        }
        waitForStart();
//        MyDriveTrain.encoderDrive(0.5,25,-25,-25,25,1);
        MyDriveTrain.encoderDrive(0.5,-100,-100,-100,-100,2);
//        MyDriveTrain.encoderDrive(0.5,-100,-100,-100,-100,2);
        telemetry.addData("LF:",LF.getCurrentPosition());
        telemetry.addData("LB:",LB.getCurrentPosition());
        telemetry.addData("RF:",RF.getCurrentPosition());
        telemetry.addData("RB:",RB.getCurrentPosition());
        telemetry.update();
        sleep(2000);
    }
}
