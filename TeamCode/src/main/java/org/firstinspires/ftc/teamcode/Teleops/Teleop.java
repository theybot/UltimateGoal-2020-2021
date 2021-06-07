package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.RobotCustomade;

@TeleOp(name = "Teleop" , group = "teleops")
public class Teleop extends RobotCustomade {

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
                MyDriveTrain.arcade(gamepad1.left_stick_y * 0.7, gamepad1.left_stick_x * 0.7, (gamepad1.right_trigger - gamepad1.left_trigger) * 0.7);
            } else {
                MyDriveTrain.fieldOriented(gamepad1.left_stick_y * 0.7, gamepad1.left_stick_x * 0.7, (gamepad1.right_trigger - gamepad1.left_trigger) * 0.7, heading);
            }

            if (gamepad1.y) {
                BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
                parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
                parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
                parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
                parameters.loggingEnabled = true;
                parameters.loggingTag = "IMU";
                parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
                IMU.initialize(parameters);
                telemetry.addLine("imu is calibrated");
                telemetry.update();
            }
//            if (!isStopRequested() && !IMU.isGyroCalibrated()) {
//                idle();
//                telemetry.addLine("imu isnt calibrated");
//            }
            if (gamepad2.dpad_up){
                MyIntakeSystem.RingDropperUp();
            }
            else if (gamepad2.dpad_down){
                MyIntakeSystem.RingDropperDown();
            }

            if (gamepad2.right_trigger > 0) {
                MyIntakeSystem.IntakeOn(-0.9);
            }
            telemetry.addData("t", CartridgeTouch.getState());
            telemetry.update();

            }
            if (gamepad2.y) {
                MyIntakeSystem.IntakeOn(0.9);
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
            }else if (gamepad2.left_trigger > 0){
                MyWobbleMechanism.WobbleUp(0.7);
            }
            if(gamepad2.right_stick_button){
                MyWobbleMechanism.WobbleOpen();
            }
            if(gamepad2.a){
                runtime.reset();
                MyIntakeSystem.RingDropperUp();
                MyShootingSystem.ShootingOn(1);
                if(runtime.seconds() > 0.5){
                    MyShootingSystem.CartridgeUp();
                    runtime.reset();
                }
                if(runtime.seconds() > 1 && IntakeMotor.getCurrentPosition() < - 11000){
                    telemetry.addLine("ok");
                    telemetry.update();
                    MyShootingSystem.CartridgeOn();
                }
                MyShootingSystem.ShootingOff();
                MyShootingSystem.CartridgeDown();
        }
    }
}