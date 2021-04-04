package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.RobotCustomade;

@TeleOp(name = "TeleopPreGame" , group = "teleops")
public class TeleopPreGame extends RobotCustomade {

    private double leftStickX = 0;
    private double leftStickY = 0;
    private double rightStickX = 0;
    private int count = 0;
    private String Mode = "Oriented";

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();

        LeftShootingMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightShootingMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (!isStarted()) {
            runtime.reset();
        }
        waitForStart();
        while (opModeIsActive()) {

            angles = IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
            double heading = angles.firstAngle;
            if (gamepad1.b) {
                Mode = "arcade";
            } else if (gamepad1.x) {
                Mode = "Oriented";
            }

            if (Mode == "arcade") {
                MyDriveTrain.arcade(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_trigger - gamepad1.left_trigger);
            } else {
                MyDriveTrain.fieldOriented(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_trigger - gamepad1.left_trigger, heading);
            }


            if (gamepad2.right_trigger > 0) {
                LeftShootingMotor.setPower(1);
                RightShootingMotor.setPower(1);
            } else {
                LeftShootingMotor.setPower(0);
                RightShootingMotor.setPower(0);
            }

            if (gamepad2.left_bumper) {
                if (count < 3) {
                    if (!CartridgeTouch.getState()) {
                        count++;
                        telemetry.addLine("ok");
                        telemetry.update();
                    }
                    MyShootingSystem.CartridgeOn();
                }
            } else if (gamepad2.right_bumper) {
                CartridgeServo.setPosition(1);
            } else {
                MyShootingSystem.CartridgeOff();
                count = 0;
            }
            telemetry.addData("t", CartridgeTouch.getState());
            telemetry.update();

            if (gamepad2.dpad_up) {
                MyShootingSystem.CartridgeUp();
            } else if (gamepad2.dpad_down) {
                MyShootingSystem.CartridgeDown();
            }


            if (gamepad1.y) {
                MyWobbleMechanism.WobbleClose();
            } else if (gamepad1.a) {
                MyWobbleMechanism.WobbleOpen();
            }
            if (gamepad2.y) {
                MyIntakeSystem.IntakeOn(0.9);
            } else if (gamepad2.a) {
                MyIntakeSystem.IntakeOn(-0.9);
            } else {
                MyIntakeSystem.IntakeOff();
            }
            if (gamepad2.b) {
                MyWobbleMechanism.WobbleDown(0.7);
                MyWobbleMechanism.WobbleOpen();
            } else if (gamepad2.x) {
                time = runtime.seconds();
                MyWobbleMechanism.WobbleDown(0.7);
                MyWobbleMechanism.WobbleClose();
                telemetry.addData("r",runtime.seconds());
                telemetry.addData("t",time);
                telemetry.update();
                if (runtime.seconds() - time > 2) {
                    MyWobbleMechanism.WobbleUp(0.7);
                    telemetry.addLine(":)");
                    telemetry.update();
                }
            }

        }
    }
}