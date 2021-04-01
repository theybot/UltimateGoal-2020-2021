package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.System.DriveTrain;
import org.firstinspires.ftc.teamcode.System.IntakeSystem;
import org.firstinspires.ftc.teamcode.System.ShootingSystem;
import org.firstinspires.ftc.teamcode.System.WobbleMechanism;

public class RobotCustomade extends LinearOpMode {

    //Drive Train Motor
    public DcMotor LB = null;
    public DcMotor LF = null;
    public DcMotor RF = null;
    public DcMotor RB = null;

    //Systems Motor and Servo
    public DcMotor LeftShootingMotor = null;
    public DcMotor RightShootingMotor = null;
    public DcMotor IntakeMotor = null;
    public DcMotor WobbleArmMotor = null;
    public com.qualcomm.robotcore.hardware.Servo CartridgeServo = null;
    public com.qualcomm.robotcore.hardware.Servo UpDownServo = null;
    public com.qualcomm.robotcore.hardware.Servo WobbleCloseServo = null;
   // public com.qualcomm.robotcore.hardware.Servo ElevatorServo = null;


    /*IMU Fileds*/
    protected BNO055IMU IMU = null;
    protected Orientation angles = null;
    protected org.firstinspires.ftc.robotcore.external.navigation.Velocity Velocity;
    protected org.firstinspires.ftc.robotcore.external.navigation.Acceleration Acceleration;

    //digitalChannels

    /*Analog Sensor*/


    /*Mechanisms*/
    protected DriveTrain MyDriveTrain = null;
    protected WobbleMechanism MyWobbleMechanism = null;
    protected IntakeSystem MyIntakeSystem = null;
    protected ShootingSystem MyShootingSystem = null;
    /*LED*/

//    Recognition

    public double ShootingUp = 0;
    public double ShootingDown = 1;

    @Override
    public void runOpMode() throws InterruptedException {

//         Define and Initialize Motors Of Drive Train
        LB = hardwareMap.get(DcMotor.class, "LB");
        LF = hardwareMap.get(DcMotor.class, "LF");
        RF = hardwareMap.get(DcMotor.class, "RF");
        RB = hardwareMap.get(DcMotor.class, "RB");

        //Define and Initialize Systems Motors and Servo
        LeftShootingMotor = hardwareMap.get(DcMotor.class, "LeftShootingMotor");
        RightShootingMotor = hardwareMap.get(DcMotor.class, "RightShootingMotor");
        IntakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");
        WobbleArmMotor = hardwareMap.get(DcMotor.class, "WobbleArmMotor");

        CartridgeServo = hardwareMap.get(com.qualcomm.robotcore.hardware.Servo.class, "CartridgeServo");
        UpDownServo = hardwareMap.get(com.qualcomm.robotcore.hardware.Servo.class, "UpDownServo");
        WobbleCloseServo = hardwareMap.get(com.qualcomm.robotcore.hardware.Servo.class, "WobbleCloseServo");
        //ElevatorServo = hardwareMap.get(com.qualcomm.robotcore.hardware.Servo.class, "ElevatorServo");

        LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RF.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        RB.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        LF.setDirection(DcMotor.Direction.FORWARD);
        LB.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        LF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
        RF.setPower(0);

        LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//        Define and Initialize Of IMU

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode

        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        IMU = hardwareMap.get(BNO055IMU.class, "imu");
        IMU.initialize(parameters);

        // make sure the imu gyro is calibrated before continuing.
        while (!isStarted() && !isStopRequested() && !IMU.isGyroCalibrated()) {
            sleep(50);
            idle();
        }
        telemetry.addLine("IMU is Calibrated");
        telemetry.update();

        angles = IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        Velocity = IMU.getVelocity();
        Acceleration = IMU.getAcceleration();

        //Define Mechanisms:
        MyDriveTrain = new DriveTrain(LB, LF, RF, RB, IMU);
        MyWobbleMechanism = new WobbleMechanism(WobbleArmMotor, WobbleCloseServo);
        MyIntakeSystem = new IntakeSystem(IntakeMotor);
        MyShootingSystem = new ShootingSystem(LeftShootingMotor,RightShootingMotor,CartridgeServo,UpDownServo);

        // Define and initialize ALL installed servos.
    }
    public double IMUError(double TargetAngle, double turnSpeed){
        return turnSpeed* Range.clip((Math.toRadians(MyDriveTrain.getAngle()) - (Math.toRadians(TargetAngle)))/ Math.toRadians(30), -1, 1);
    }

    public void RotateP1(int degrees, double power, double timeoutR,double KP) {
        if (MyDriveTrain.getAngle() < degrees) {
            while (MyDriveTrain.getAngle() < degrees && opModeIsActive()) {
//                SumErrors = SumErrors + (getAngle() + degrees);
                double error = degrees-MyDriveTrain.getAngle();
                MyDriveTrain.LeftFront.setPower(power  * error * KP);
                MyDriveTrain.LeftBack.setPower(power  * error * KP);
                MyDriveTrain.RightFront.setPower(-power  * error * KP);
                MyDriveTrain.RightBack.setPower(-power  * error * KP);
            }
        } else if (MyDriveTrain.getAngle() > degrees) {
            while (MyDriveTrain.getAngle() > degrees && opModeIsActive()) {
                double error = MyDriveTrain.getAngle()-degrees;
                MyDriveTrain.LeftFront.setPower(-power  * error * KP);
                MyDriveTrain.LeftBack.setPower(-power * error * KP);
                MyDriveTrain.RightFront.setPower(power * error * KP);
                MyDriveTrain.RightBack.setPower(power  * error * KP);
            }
        } else {
            return;
        }
        // turn the motors off.
        MyDriveTrain.LeftFront.setPower(0);
        MyDriveTrain.LeftBack.setPower(0);
        MyDriveTrain.RightFront.setPower(0);
        MyDriveTrain.RightBack.setPower(0);

    }
}

