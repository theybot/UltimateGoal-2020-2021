package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.RobotCustomade;

@Disabled
@Autonomous(name = "TahelAutoTest", group = "teamcode")
public class TahelAutoTest extends RobotCustomade {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        waitForStart();
        MyDriveTrain.encoderDrive(0.5,25,-25,-25,25,1);
        MyDriveTrain.encoderDrive(0.5,-100,-100,-100,-100,2);
        MyDriveTrain.encoderDrive(0.5,-100,-100,-100,-100,2);
    }


}
