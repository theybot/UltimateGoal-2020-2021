package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.RobotCustomade;


@Autonomous(name = "AutoBlueRight", group = "teamcode")
@Disabled
public class AutoBlueRight extends RobotCustomade {
    String TargetZone = "A";
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        waitForStart();
        MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
        MyDriveTrain.encoderDrive(0.5, 30, -30, -30, 30, 1);
        MyDriveTrain.encoderDrive(0.5, -60, -60, -60, -60, 2);
        sleep(500);
        LeftShootingMotor.setPower(1);
        RightShootingMotor.setPower(1);
        MyDriveTrain.RotateP(-15,0.4,10,0.05);
        sleep(500);
        CartridgeServo.setPosition(1);
        sleep(500);
        CartridgeServo.setPosition(0.5);
        MyDriveTrain.RotateP(-17,0.4,10,0.06);
        sleep(500);
        CartridgeServo.setPosition(1);
        sleep(500);
        CartridgeServo.setPosition(0.5);
        MyDriveTrain.RotateP(-20,0.4,10,0.06);
        CartridgeServo.setPosition(1);
        sleep(500);
        CartridgeServo.setPosition(0.5);
        LeftShootingMotor.setPower(0);
        RightShootingMotor.setPower(0);
        MyDriveTrain.RotateP(0,0.4,10,0.04);
        sleep(500);
        if (TargetZone == "B") {
            MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
            LeftShootingMotor.setPower(1);
            RightShootingMotor.setPower(1);
            sleep(500);
            IntakeMotor.setPower(0.7);
            CartridgeServo.setPosition(1);
            sleep(500);
            LeftShootingMotor.setPower(0);
            RightShootingMotor.setPower(0);
            IntakeMotor.setPower(0);
            CartridgeServo.setPosition(0.5);
            MyDriveTrain.encoderDrive(0.5, -150, -150, -150, -150, 2);
//            MyWobbleMechanism.WobbleDown(0.5);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleUp(0.5);
            MyDriveTrain.encoderDrive(0.5, 20, 20, 20, 20, 2);
        }
        else if(TargetZone == "C") {
            MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
            LeftShootingMotor.setPower(1);
            RightShootingMotor.setPower(1);
            sleep(500);
            IntakeMotor.setPower(0.7);
            CartridgeServo.setPosition(1);
            sleep(500);
            LeftShootingMotor.setPower(0);
            RightShootingMotor.setPower(0);
            IntakeMotor.setPower(0);
            CartridgeServo.setPosition(0.5);
            sleep(500);
            MyDriveTrain.encoderDrive(0.5, -190, -190,-190, -190, 2);
            MyDriveTrain.RotateP(45,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -25, -25, -25, -25, 2);
//            MyWobbleMechanism.WobbleDown(0.5);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleUp(0.5);
            sleep(500);
            MyDriveTrain.encoderDrive(0.5, 10, 10, 10, 10, 2);
            MyDriveTrain.RotateP(0,0.4,10,0.04);
            sleep(500);
            MyDriveTrain.encoderDrive(0.5, 70, 70, 70, 70, 2);
        }
        else {
            MyDriveTrain.encoderDrive(0.5, -120, -120, -120, -120, 2);
            MyDriveTrain.RotateP(90,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -10, -10, -10, -10, 2);
//            MyWobbleMechanism.WobbleDown(0.5);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleUp(0.5);
        }


    }
}

