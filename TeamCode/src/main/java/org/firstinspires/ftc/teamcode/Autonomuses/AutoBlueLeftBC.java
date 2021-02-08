package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.RobotCustomade;


@Autonomous(name = "AutoBlueLeftBC", group = "teamcode")
public class AutoBlueLeftBC extends RobotCustomade {
    String TargetZone = "C";
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        waitForStart();
        MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
        MyDriveTrain.encoderDrive(0.5, -25, 25, 25, -25, 1);
        MyDriveTrain.encoderDrive(0.5, -45, -45, -45, -45, 2);
        sleep(500);
        LeftShootingMotor.setPower(1);
        RightShootingMotor.setPower(1);
        MyDriveTrain.RotateP(-15,0.5,10,0.04);
        sleep(500);
        CartridgeServo.setPosition(1);
        sleep(500);
        CartridgeServo.setPosition(0.5);
        MyDriveTrain.RotateP(-20,0.5,10,0.04);
        sleep(500);
        CartridgeServo.setPosition(1);
        sleep(500);
        CartridgeServo.setPosition(0.5);
        MyDriveTrain.RotateP(-25,0.5,10,0.04);
        CartridgeServo.setPosition(1);
        sleep(500);
        CartridgeServo.setPosition(0.5);
        LeftShootingMotor.setPower(0);
        RightShootingMotor.setPower(0);
        MyDriveTrain.RotateP(0,0.4,10,0.04);
        sleep(500);
        sleep(500);
        if (TargetZone == "A") {
            MyDriveTrain.encoderDrive(0.5, -90, -90, -90, -90, 2);
            MyDriveTrain.RotateP(90,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -10, -10, -10, -10, 2);
//            ArmWobbleServo.setPosition(0.5);
            sleep(500);
            WobbleCloseServo.setPosition(0.5);
            sleep(500);
            WobbleCloseServo.setPosition(0);
//            ArmWobbleServo.setPosition(0);
        }
        else {
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
            MyDriveTrain.encoderDrive(0.5, 15, -15, -15, 15, 1);
            MyDriveTrain.encoderDrive(0.5, -135, -135, -135, -135, 2);
//            ArmWobbleServo.setPosition(0.5);
            sleep(500);
            WobbleCloseServo.setPosition(0.5);
            sleep(500);
            WobbleCloseServo.setPosition(0);
//            ArmWobbleServo.setPosition(0);
            sleep(500);
            MyDriveTrain.encoderDrive(0.5, 45, 45, 45, 45, 2);
        }
    }
}

