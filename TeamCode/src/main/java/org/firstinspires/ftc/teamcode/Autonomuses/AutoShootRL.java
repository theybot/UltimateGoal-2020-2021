package org.firstinspires.ftc.teamcode.Autonomuses;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.RobotCustomade;

import static org.firstinspires.ftc.teamcode.BasicAuto.SkystoneDeterminationPipeline.RingPosition.NONE;

@Autonomous(name = "AutoShootRL", group = "teamcode")
@Disabled
public class AutoShootRL extends RobotCustomade {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        waitForStart();
        MyDriveTrain.encoderDrive(0.5, -170, -170, -170, -170, 2);
        sleep(500);
        MyDriveTrain.encoderDrive(0.5, 30, -30, -30, 30, 1);
        MyDriveTrain.RotateP(0,0.4,10,0.005);
        MyShootingSystem.ShootingOn(1);
        MyShootingSystem.CartridgeUp();
        sleep(500);
        MyShootingSystem.CartridgeOn();
        sleep(2000);
        MyShootingSystem.CartridgeOff();
        MyShootingSystem.ShootingOff();
        if(!CartridgeTouch.getState()){
            MyShootingSystem.CartridgeOn();
            sleep(200);
            MyShootingSystem.CartridgeOff();
        }
        MyShootingSystem.CartridgeDown();

        MyDriveTrain.RotateP(180,0.4,10,0.05);
        sleep(500);
        MyDriveTrain.encoderDrive(0.5, -10, -10, -10, -10, 2);
        MyIntakeSystem.IntakeOn(1);
        MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
        sleep(1500);
        MyIntakeSystem.IntakeOff();
        MyDriveTrain.RotateP(180,0.4,10,0.05);
        MyDriveTrain.encoderDrive(0.5, -10, -10, -10, -10, 2);
        MyShootingSystem.ShootingOn(1);
        MyShootingSystem.CartridgeUp();
        sleep(500);
        MyShootingSystem.CartridgeOn();
        sleep(2000);
        MyShootingSystem.CartridgeOff();
        MyShootingSystem.ShootingOff();
        if(!CartridgeTouch.getState()){
            MyShootingSystem.CartridgeOn();
            sleep(200);
            MyShootingSystem.CartridgeOff();
        }
        MyShootingSystem.CartridgeDown();







    }
}
