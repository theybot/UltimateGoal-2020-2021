package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.BasicAuto;
import org.firstinspires.ftc.teamcode.RobotCustomade;

import static org.firstinspires.ftc.teamcode.BasicAuto.SkystoneDeterminationPipeline.RingPosition.FOUR;
import static org.firstinspires.ftc.teamcode.BasicAuto.SkystoneDeterminationPipeline.RingPosition.NONE;
import static org.firstinspires.ftc.teamcode.BasicAuto.SkystoneDeterminationPipeline.RingPosition.ONE;


@Autonomous(name = "T_AutoRedRight", group = "teamcode")
public class T_AutoRedRight extends RobotCustomade {
    BasicAuto.SkystoneDeterminationPipeline.RingPosition TargetZone = ONE;
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        while (!opModeIsActive())
//        {
//            TargetZone = UltimatePipline.position;
//            telemetry.addData("Position", UltimatePipline.position);
//            telemetry.update();
//
//            sleep(50);
//        }
        waitForStart();
        MyDriveTrain.encoderDrive(0.5, -178, -178, -178, -178, 2);
        MyDriveTrain.RotateP(0,0.4,10,0.07);
        MyDriveTrain.encoderDrive(0.1, 33, -33, -33, 33, 1);
        sleep(500);
        telemetry.addData("a",MyDriveTrain.getAngle());
        telemetry.update();
        MyDriveTrain.RotateP(0,0.4,10,0.07);
        telemetry.addData("b",MyDriveTrain.getAngle());
        telemetry.update();
        MyShootingSystem.ShootingOn(1);
        MyShootingSystem.CartridgeUp();
        sleep(1000);
        MyShootingSystem.CartridgeOn();
        sleep(1700);
        MyShootingSystem.CartridgeOff();
        MyShootingSystem.ShootingOff();
        MyShootingSystem.CartridgeDown();
        sleep(500);
        if (TargetZone == ONE) {
            MyDriveTrain.RotateP(180,0.6,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -40, -40, -40, -40, 2);
            MyIntakeSystem.IntakeOn(1);
            sleep(1500);
            MyIntakeSystem.IntakeOff();
            MyShootingSystem.CartridgeUp();
            MyDriveTrain.encoderDrive(0.5, -10, -10, -10, -10, 2);
            MyShootingSystem.ShootingOn(1);
            MyDriveTrain.RotateP(0,0.6,10,0.04);
            sleep(500);
            MyShootingSystem.CartridgeOn();
            sleep(1700);
            MyShootingSystem.CartridgeOff();
            MyShootingSystem.ShootingOff();
            MyShootingSystem.CartridgeDown();
            MyDriveTrain.encoderDrive(0.5, -150, -150, -150, -150, 2);
////            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
//            MyWobbleMechanism.WobbleOpen();
            sleep(500);
//            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            MyDriveTrain.encoderDrive(0.5, 150, 150, 150, 150, 2);
            MyDriveTrain.RotateP(180,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, 30, -30, -30, 30, 1);
            MyDriveTrain.encoderDrive(0.5, -60, -60, -60, -60, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
//            MyWobbleMechanism.WobbleOpen();
            MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
            sleep(500);
//            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            MyDriveTrain.RotateP(0,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, 30, -30, -30, 30, 1);
            MyDriveTrain.encoderDrive(0.5, -210, -210, -210, -210, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
//            MyWobbleMechanism.WobbleOpen();
            sleep(500);
//            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            MyDriveTrain.encoderDrive(0.5, 20, 20, 20, 20, 2);
        }
        else if(TargetZone == FOUR) {
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
            MyDriveTrain.encoderDrive(0.5, -190, -190, -190, -190, 2);
            MyDriveTrain.RotateP(-45,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -25, -25, -25, -25, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            sleep(500);
            MyDriveTrain.encoderDrive(0.5, 25, 25, 25, 25, 2);
            MyDriveTrain.RotateP(0,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, 190, 190, 190, 190, 2);
            MyDriveTrain.RotateP(180,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -30, 30, 30, -30, 1);
            MyDriveTrain.encoderDrive(0.5, -60, -60, -60, -60, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            MyDriveTrain.RotateP(0,0.4,0,0.04);
            MyDriveTrain.encoderDrive(0.5, -270, -270, -270, -270, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            sleep(500);
            MyDriveTrain.encoderDrive(0.5, 70, 70, 70, 70, 2);
        }
        else {
            MyDriveTrain.encoderDrive(0.5, -120, -120, -120, -120, 2);
            MyDriveTrain.RotateP(-90,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -10, -10, -10, -10, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            MyDriveTrain.encoderDrive(0.5, 10, 10, 10, 10, 2);
            MyDriveTrain.RotateP(180,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, 120, 120, 120, 120, 2);
            MyDriveTrain.encoderDrive(0.5, 30, -30, -30, 30, 1);
            MyDriveTrain.encoderDrive(0.5, -60, -60, -60, -60, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            MyDriveTrain.RotateP(0,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, 30, -30, -30, 30, 1);
            MyDriveTrain.encoderDrive(0.5, -180, -180, -180, -180, 2);
            MyDriveTrain.RotateP(-90,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -10, -10, -10, -10, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
        }


    }
}

