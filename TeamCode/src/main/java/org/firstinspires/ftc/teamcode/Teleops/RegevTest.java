package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.RobotCustomade;

@TeleOp(name = "RegevTest" , group = "teleops")
public class RegevTest extends RobotCustomade {

    private double leftStickX = 0;
    private double leftStickY = 0;
    private double rightStickX = 0;
    private String Mode = "Oriented";

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();

        LeftShootingMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightShootingMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        while (opModeIsActive()) {

            angles = IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
            double heading = angles.firstAngle;
            if(gamepad1.b) {
                Mode = "arcade";
            } else if(gamepad1.x){
                Mode = "Oriented";
            }

            if(Mode == "arcade"){
                MyDriveTrain.arcade(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_trigger - gamepad1.left_trigger);
            } else {
                MyDriveTrain.fieldOriented(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_trigger - gamepad1.left_trigger, heading);
            }


            if (gamepad2.right_bumper) {
                LeftShootingMotor.setPower(1);
                RightShootingMotor.setPower(1);
            } else {
                LeftShootingMotor.setPower(0);
                RightShootingMotor.setPower(0);
            }

            if (gamepad2.left_bumper) {
                CartridgeServo.setPosition(1);
            } else {
                CartridgeServo.setPosition(0.5);
            }

            if (gamepad2.dpad_up){
                UpDownServo.setPosition(ShootingUp);
            }
            else if (gamepad2.dpad_down){
                UpDownServo.setPosition(ShootingDown);
            }

            if(gamepad2.y){
                MyWobbleMechanism.WobbleUp(0.3);
            }else if (gamepad2.a){
                MyWobbleMechanism.WobbleDown(0.3);
            }
            if(gamepad1.y){
                MyIntakeSystem.IntakeOn(0.9);
            } else if (gamepad1.a) {
                MyIntakeSystem.IntakeOn(-0.9);
            }else {
                MyIntakeSystem.IntakeOff();
            }
            if(gamepad2.b){
                MyWobbleMechanism.WobbleOpen();
            }else if (gamepad2.x){
                MyWobbleMechanism.WobbleClose();
            }

        }
    }
}