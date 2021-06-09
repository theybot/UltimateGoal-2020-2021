package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.BasicAuto;
import org.firstinspires.ftc.teamcode.RobotCustomade;

import static org.firstinspires.ftc.teamcode.BasicAuto.SkystoneDeterminationPipeline.RingPosition.FOUR;
import static org.firstinspires.ftc.teamcode.BasicAuto.SkystoneDeterminationPipeline.RingPosition.NONE;
import static org.firstinspires.ftc.teamcode.BasicAuto.SkystoneDeterminationPipeline.RingPosition.ONE;


@Autonomous(name = "T_AutoRedRight", group = "teamcode")
public class T_AutoRedRight extends BasicAuto {
    BasicAuto.SkystoneDeterminationPipeline.RingPosition TargetZone = ONE;

    @Override
    public void runOpMode() throws InterruptedException {
        sideX = 0;
        super.runOpMode();
        LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int count = 0;
        while (!opModeIsActive())
        {
            TargetZone = UltimatePipline.position;
            telemetry.addData("Position", UltimatePipline.position);
            telemetry.update();

            sleep(50);
        }
        waitForStart();
        MyIntakeSystem.RingDropperUp();
        MyWobbleMechanism.WobbleClose();
        sleep(100);
        MyDriveTrain.encoderDrive(0.5, -140,-140, -140, -140, 2);
        MyDriveTrain.RotateP(0, 0.8, 10, 0.15);
        MyDriveTrain.encoderDrive(0.7, 35, -35, -35, 35, 1);
        MyDriveTrain.RotateP(0, 0.8, 10, 0.15);
//        MyWobbleMechanism.WobbleMidle(0.6);
        MyShootingSystem.ShootingOn(1);
        MyShootingSystem.CartridgeUp();
        sleep(3000);
//        while (IntakeMotor.getCurrentPosition() > - 11000) {
//            MyShootingSystem.CartridgeOn();
//        }
        MyShootingSystem.CartridgeOn();
        sleep(1500);
        MyShootingSystem.CartridgeOff();
        MyShootingSystem.ShootingOff();
        MyShootingSystem.CartridgeDown();
            sleep(500);
            if (TargetZone == ONE) {
                MyDriveTrain.RotateP(180, 0.4, 10, 0.15);
                MyIntakeSystem.IntakeOn(1);
                MyDriveTrain.encoderDrive(0.5, -45, -45, -45, -45, 2);
                sleep(2000);
                MyIntakeSystem.IntakeOff();
//                MyWobbleMechanism.WobbleDown(0.7);
                MyShootingSystem.CartridgeUp();
                MyShootingSystem.ShootingOn(1);
                MyDriveTrain.encoderDrive(0.5, 60, 60, 60, 60, 2);
                MyDriveTrain.RotateP(0, 0.3, 10, 0.15);
                sleep(500);
                count = 0;
                MyShootingSystem.CartridgeOn();
                sleep(1000);
                MyShootingSystem.CartridgeOff();
                MyShootingSystem.ShootingOff();
                MyShootingSystem.CartridgeDown();
                MyDriveTrain.RotateP(180, 0.4, 10, 0.07);
                MyDriveTrain.encoderDrive(0.5, 60, 60, 60, 60, 2);
//                MyWobbleMechanism.WobbleDown(0.7);
//                sleep(500);
//                MyWobbleMechanism.WobbleOpen();
//                sleep(500);
//                MyWobbleMechanism.WobbleUp(0.7);
                MyDriveTrain.encoderDrive(0.5, -20, -20, -20, -20, 2);
            } else if (TargetZone == FOUR) {
                MyDriveTrain.RotateP(180, 0.4, 10, 0.15);
                MyIntakeSystem.IntakeOn(1);
                MyDriveTrain.encoderDrive(0.5, -45, -45, -45, -45, 2);
                sleep(2000);
                MyIntakeSystem.IntakeOff();
//                MyWobbleMechanism.WobbleDown(0.7);
                MyShootingSystem.CartridgeUp();
                MyShootingSystem.ShootingOn(1);
                MyDriveTrain.encoderDrive(0.5, 60, 60, 60, 60, 2);
                MyDriveTrain.RotateP(0, 0.3, 10, 0.15);
                sleep(500);
                count = 0;
                MyShootingSystem.CartridgeOn();
                sleep(1000);
                MyShootingSystem.CartridgeOff();
                MyShootingSystem.ShootingOff();
                MyShootingSystem.CartridgeDown();
                MyDriveTrain.RotateP(180, 0.4, 10, 0.07);
                MyDriveTrain.encoderDrive(0.5, -130, -130, -130, -130, 2);
                MyDriveTrain.RotateP(-45,0.4,10,0.04);
                MyDriveTrain.encoderDrive(0.5, -25, -25, -25, -25, 2);
//                MyWobbleMechanism.WobbleDown(0.7);
//                sleep(500);
//                MyWobbleMechanism.WobbleOpen();
//                sleep(500);
//                MyWobbleMechanism.WobbleClose();
//                MyWobbleMechanism.WobbleUp(0.7);
//                sleep(500);
                MyDriveTrain.encoderDrive(0.5, 25, 25, 25, 25, 2);
                MyDriveTrain.RotateP(0,0.4,10,0.04);
                MyDriveTrain.encoderDrive(0.5, 50, 50, 50, 50, 2);
            } else {
                MyDriveTrain.encoderDrive(0.5, -50, -50, -50, -50, 2);
                MyDriveTrain.RotateP(90, 0.4, 10, 0.07);
                MyDriveTrain.encoderDrive(0.5, 40, 40, 40, 40, 2);
//                MyWobbleMechanism.WobbleDown(0.7);
//                sleep(500);
//                MyWobbleMechanism.WobbleOpen();
//                sleep(500);
//                MyWobbleMechanism.WobbleClose();
//                MyWobbleMechanism.WobbleUp(0.5);
                MyDriveTrain.encoderDrive(0.5, -30, -30, -30, -30, 2);
            }


        }
    }



