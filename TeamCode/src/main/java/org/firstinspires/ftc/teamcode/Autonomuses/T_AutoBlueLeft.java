package org.firstinspires.ftc.teamcode.Autonomuses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.BasicAuto;
import org.firstinspires.ftc.teamcode.RobotCustomade;

import static org.firstinspires.ftc.teamcode.BasicAuto.SkystoneDeterminationPipeline.RingPosition.FOUR;
import static org.firstinspires.ftc.teamcode.BasicAuto.SkystoneDeterminationPipeline.RingPosition.NONE;
import static org.firstinspires.ftc.teamcode.BasicAuto.SkystoneDeterminationPipeline.RingPosition.ONE;


@Autonomous(name = "T_AutoBlueLeft", group = "teamcode")
@Disabled
public class T_AutoBlueLeft extends BasicAuto {
    BasicAuto.SkystoneDeterminationPipeline.RingPosition TargetZone = NONE;
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        while (!opModeIsActive())
        {
            TargetZone = UltimatePipline.position;
            telemetry.addData("Position", UltimatePipline.position);
            telemetry.update();

            sleep(50);
        }
        waitForStart();
        MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
        MyDriveTrain.encoderDrive(0.5, -30, 30, 30, -30, 1);
        MyDriveTrain.encoderDrive(0.5, -60, -60, -60, -60, 2);
        sleep(500);
        LeftShootingMotor.setPower(1);
        RightShootingMotor.setPower(1);
        CartridgeServo.setPosition(1);
        sleep(1000);
        LeftShootingMotor.setPower(0);
        RightShootingMotor.setPower(0);
        IntakeMotor.setPower(0);
        CartridgeServo.setPosition(0.5);
        MyDriveTrain.RotateP(0,0.4,10,0.05);
        sleep(500);
        if (TargetZone == ONE) {
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
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            MyDriveTrain.encoderDrive(0.5, 150, 150, 150, 150, 2);
            MyDriveTrain.RotateP(180,0.4,10,0.04);
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
            MyDriveTrain.encoderDrive(0.5, -210, -210, -210, -210, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
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
            MyDriveTrain.RotateP(45,0.4,10,0.04);
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
            MyDriveTrain.encoderDrive(0.5, 30, -30, -30, 30, 1);
            MyDriveTrain.encoderDrive(0.5, -60, -60, -60, -60, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            MyDriveTrain.RotateP(0,0.4,0,0.04);
            MyDriveTrain.encoderDrive(0.5, 30, -30, -30, 30, 1);
            MyDriveTrain.encoderDrive(0.5, -250, -250, -250, -250, 2);
            MyDriveTrain.RotateP(45,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -25, -25, -25, -25, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            sleep(500);
            MyDriveTrain.encoderDrive(0.5, 25, 25, 25, 25, 2);
            MyDriveTrain.encoderDrive(0.5, 70, 70, 70, 70, 2);
        }
        else {
            MyDriveTrain.encoderDrive(0.5, -120, -120, -120, -120, 2);
            MyDriveTrain.RotateP(90,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -10, -10, -10, -10, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            MyDriveTrain.encoderDrive(0.5, 10, 10, 10, 10, 2);
            MyDriveTrain.RotateP(180,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, -120, -120, -120, -120, 2);
            MyDriveTrain.encoderDrive(0.5, 30, -30, -30, 30, 1);
            MyDriveTrain.encoderDrive(0.5, -50, -50, -50, -50, 2);
//            MyWobbleMechanism.WobbleUp(0.1);
            sleep(500);
            MyWobbleMechanism.WobbleOpen();
            MyDriveTrain.encoderDrive(0.5, -5, -5, -5, -5, 2);
            sleep(500);
            MyWobbleMechanism.WobbleClose();
//            MyWobbleMechanism.WobbleDown(0.1);
            MyDriveTrain.RotateP(0,0.4,10,0.04);
            MyDriveTrain.encoderDrive(0.5, 30, -30, -30, 30, 1);
            MyDriveTrain.encoderDrive(0.5, -170, -170, -170, -170, 2);
            MyDriveTrain.RotateP(90,0.4,10,0.04);
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

