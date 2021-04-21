package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.BasicAuto;
import org.firstinspires.ftc.teamcode.RobotCustomade;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "TahelAutoTest", group = "teamcode")
@Disabled
public class TahelAutoTest extends BasicAuto {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        int count = 0;
//        while (!opModeIsActive()) {
//            telemetry.addData("Analysis", UltimatePipline.getAnalysis());
//            telemetry.addData("Position", UltimatePipline.position);
//            telemetry.update();
//
//
//            sleep(50);
//        }
        waitForStart();
        count = 0;
        while (count < 2) {
            MyShootingSystem.CartridgeOn();
            if (CartridgeTouch.getState()) {
                count++;
            }
        }
    }
}
