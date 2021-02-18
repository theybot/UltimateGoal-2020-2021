package org.firstinspires.ftc.teamcode.System;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.util.Arrays;

public class DriveTrain {

/* Fileds */

public DcMotor LeftBack = null;
public DcMotor LeftFront = null;
public DcMotor RightFront = null;
public DcMotor RightBack = null;

public double lastAngles = 0;
Orientation angle = new Orientation();
double globalAngle = 0, power = .30, correction;

public double dx = 0;
public double dy = 0;


protected BNO055IMU IMU = null;

public String Mode = "Oriented";

public String telemetry[];
public double Angle = 0;
private ElapsedTime runtime = new ElapsedTime();
private HardwareMap hardwareMap;
private VuforiaLocalizer vuforia;


/* Constructor */
public DriveTrain(DcMotor LB, DcMotor LF, DcMotor RF, DcMotor RB, BNO055IMU imu ) {
    this.LeftBack = LB;
    this.LeftFront = LF;
    this.RightFront = RF;
    this.RightBack = RB;
    IMU = imu;
}

public void SetPower (double LB, double RB, double LF, double RF){
    LeftFront.setPower(LF);
    LeftBack.setPower(LB);
    RightFront.setPower(RF);
    RightBack.setPower(RB);
}

/* Methodes */

public void arcade(double y, double x, double c) {
    double leftFrontVal = y - x - c;
    double rightFrontVal = y + x + c;
    double leftBackVal = y + x - c;
    double rightBackVal = y - x + c;

    //Move range to between 0 and +1, if not already
    double[] wheelPowers = {rightFrontVal, leftFrontVal, leftBackVal, rightBackVal};
    Arrays.sort(wheelPowers);
    if (wheelPowers[3] > 1) {
        leftFrontVal /= wheelPowers[3];
        rightFrontVal /= wheelPowers[3];
        leftBackVal /= wheelPowers[3];
        rightBackVal /= wheelPowers[3];
    }
//    SetPower(leftBackVal,rightBackVal,leftFrontVal,rightFrontVal);
    LeftFront.setPower(leftFrontVal);
    RightFront.setPower(rightFrontVal);
    RightBack.setPower(rightBackVal);
    LeftBack.setPower(leftBackVal);
}

public void fieldOriented(double y, double x, double c, double gyroheading) {
    double cosA = Math.cos(gyroheading);
    double sinA = Math.sin(gyroheading);
    double xOut = x * cosA - y * sinA;
    double yOut = x * sinA + y * cosA;
    arcade(yOut, xOut, c);
}

public String getMode() {
    return Mode;
}

public void setMode(String mode) {
    Mode = mode;
}


public void encoderDrive(double speed, double LeftFrontCM, double LeftBackCM, double RightFrontCM, double RightBackCM , int State) {
    int newLeftFrontTarget = 0;
    int newLeftBackTarget = 0;
    int newRightFrontTarget = 0;
    int newRightBackTarget = 0;
    double Pnumber = 1;
    int COUNTS_PER_CM = 0;

    // Ensure that the opmode is still active

    if (State == 1) COUNTS_PER_CM =  20 ; /* 1 for Strafe*/
    else COUNTS_PER_CM = 17 ; /* for Strate */

    // Determine new target position, and pass to motor controller
    newLeftFrontTarget = LeftFront.getCurrentPosition() + (int) (LeftFrontCM * COUNTS_PER_CM);
    newLeftBackTarget = LeftBack.getCurrentPosition() + (int) (LeftBackCM * COUNTS_PER_CM);
    newRightFrontTarget = RightFront.getCurrentPosition() + (int) (RightFrontCM * COUNTS_PER_CM);
    newRightBackTarget = RightBack.getCurrentPosition() + (int) (RightBackCM * COUNTS_PER_CM);

    LeftFront.setTargetPosition(newLeftFrontTarget);
    LeftBack.setTargetPosition(newLeftBackTarget);
    RightFront.setTargetPosition(newRightFrontTarget);
    RightBack.setTargetPosition(newRightBackTarget);

    // Turn On RUN_TO_POSITION
    LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    // reset the timeout time and start motion.
    LeftFront.setPower(Math.abs(speed));
    LeftBack.setPower(Math.abs(speed));
    RightFront.setPower(Math.abs(speed));
    RightBack.setPower(Math.abs(speed));

    while ((LeftFront.isBusy() && LeftBack.isBusy() && RightFront.isBusy()) && RightBack.isBusy() /*&& runtime.seconds() < Math.abs(LeftFrontCM / 35)*/) {

    }

    // Stop all motion;
    LeftFront.setPower(0);
    LeftBack.setPower(0);
    RightFront.setPower(0);
    RightBack.setPower(0);

    // Turn off RUN_TO_POSITION
    LeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    LeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    RightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    RightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

}

    public void encoderDriveP(double speed, double LeftFrontCM, double LeftBackCM, double RightFrontCM, double RightBackCM , int State) {
        runtime.reset();
        int newLeftFrontTarget = 0;
        int newLeftBackTarget = 0;
        int newRightFrontTarget = 0;
        int newRightBackTarget = 0;
        double PnumberL = 1;
        double PnumberR = 1;
        int COUNTS_PER_CM = 0;

        // Ensure that the opmode is still active

        if (State == 1) COUNTS_PER_CM =  20 ; /* 1 for Strafe*/
        else COUNTS_PER_CM = 17 ; /* for Strate */

        // Determine new target position, and pass to motor controller
        newLeftFrontTarget = LeftFront.getCurrentPosition() + (int) (LeftFrontCM * COUNTS_PER_CM);
        newLeftBackTarget = LeftBack.getCurrentPosition() + (int) (LeftBackCM * COUNTS_PER_CM);
        newRightFrontTarget = RightFront.getCurrentPosition() + (int) (RightFrontCM * COUNTS_PER_CM);
        newRightBackTarget = RightBack.getCurrentPosition() + (int) (RightBackCM * COUNTS_PER_CM);

        LeftFront.setTargetPosition(newLeftFrontTarget);
        LeftBack.setTargetPosition(newLeftBackTarget);
        RightFront.setTargetPosition(newRightFrontTarget);
        RightBack.setTargetPosition(newRightBackTarget);

        // Turn On RUN_TO_POSITION
        LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        LeftFront.setPower(Math.abs(speed) * PnumberL);
        LeftBack.setPower(Math.abs(speed) * PnumberL);
        RightFront.setPower(Math.abs(speed) * PnumberR);
        RightBack.setPower(Math.abs(speed) * PnumberR);

        while ((LeftFront.isBusy() && LeftBack.isBusy() && RightFront.isBusy()) && RightBack.isBusy() /*&& runtime.seconds() < Math.abs(LeftFrontCM / 35)*/) {
            if(getAngle() < 0){
                PnumberL = 0.8;
            }
            else if (getAngle() > 0){
                PnumberR = 0.8;
            }
        }

        // Stop all motion;
        LeftFront.setPower(0);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        RightBack.setPower(0);

        // Turn off RUN_TO_POSITION
        LeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

private void resetAngle() {
    angle = IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

    globalAngle = 0;
}


public double getAngle() {
    // We experimentally determined the Z axis is the axis we want to use for heading angle.
    // We have to process the angle because the imu works in euler angles so the Z axis is
    // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
    // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

    angle = IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
    double angle = this.angle.firstAngle;
    double deltaAngle = Math.toDegrees(angle - lastAngles);

    if (deltaAngle < -180)
        deltaAngle += 360;
    else if (deltaAngle > 180)
        deltaAngle -= 360;

    globalAngle += deltaAngle;

    lastAngles = angle;

    return globalAngle;
}


public void Rotate(int degrees, double power, double timeoutR) {
    runtime.reset();

    double PNumber = 1;
    double INumber = 0;
    double SumErrors = 0;
    double oldSumErors = 0;
    double NewAngleTarget = 0;

//        degrees =  degrees-LastDegrees;
//        resetAngle();
    // restart imu movement tracking.

//        NewAngleTarget = getAngle() + degrees;

    Angle = getAngle();
    if (getAngle() < degrees) {
        while (getAngle() < degrees && runtime.seconds() < timeoutR) {
//                SumErrors = SumErrors + (getAngle() + degrees);
            LeftFront.setPower(power /* * (((getAngle() + degrees) * PNumber) + SumErrors * INumber)*/);
            LeftBack.setPower(power /* * (((getAngle() + degrees) * PNumber) + SumErrors * INumber)*/);
            RightFront.setPower(-power /* * (((getAngle() + degrees) * PNumber) + SumErrors * INumber)*/);
            RightBack.setPower(-power /* * (((getAngle() + degrees) * PNumber) + SumErrors * INumber)*/);


        }
    } else if (getAngle() > degrees) {
        while (getAngle() > degrees && runtime.seconds() < timeoutR) {
            LeftFront.setPower(-power /* * (((getAngle() - degrees) * PNumber) + SumErrors * INumber)*/);
            LeftBack.setPower(-power /* * (((getAngle() - degrees) * PNumber) + SumErrors * INumber)*/);
            RightFront.setPower(power /* * (((getAngle() - degrees) * PNumber) + SumErrors * INumber)*/);
            RightBack.setPower(power /* * (((getAngle() - degrees) * PNumber) + SumErrors * INumber)*/);

        }
    } else return;
    // turn the motors off.
    LeftFront.setPower(0);
    LeftBack.setPower(0);
    RightFront.setPower(0);
    RightBack.setPower(0);

//        LastDegrees = degrees;

    // reset -angle tracking on new heading
}
public void RotateP(int degrees, double power, double timeoutR,double KP) {

    double PNumber = 0.0108;
    double INumber = 0;
    double SumErrors = 0;
    double oldSumErors = 0;
    double NewAngleTarget = 0;

//        degrees =  degrees-LastDegrees;
//        resetAngle();
    // restart imu movement tracking.

//        NewAngleTarget = getAngle() + degrees;

    Angle = getAngle();
    if (getAngle() < degrees) {
        while (getAngle() < degrees) {
//                SumErrors = SumErrors + (getAngle() + degrees);
            double error = degrees - getAngle();
            LeftFront.setPower(power  * error * KP);
            LeftBack.setPower(power  * error * KP);
            RightFront.setPower(-power  * error * KP);
            RightBack.setPower(-power  * error * KP);
        }
    } else if (getAngle() > degrees) {
        while (getAngle() > degrees) {
            double error = getAngle()-degrees;
            LeftFront.setPower(-power  * error * KP);
            LeftBack.setPower(-power * error * KP);
            RightFront.setPower(power * error * KP);
            RightBack.setPower(power  * error * KP);

        }
    } else return;
    // turn the motors off.
    LeftFront.setPower(0);
    LeftBack.setPower(0);
    RightFront.setPower(0);
    RightBack.setPower(0);

//        LastDegrees = degrees;

    // reset -angle tracking on new heading
}

    public void stop(){
        LeftFront.setPower(0);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        RightBack.setPower(0);
        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}












