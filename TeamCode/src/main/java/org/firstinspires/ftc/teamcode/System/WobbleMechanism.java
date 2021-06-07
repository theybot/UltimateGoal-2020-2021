package org.firstinspires.ftc.teamcode.System;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleMechanism {

    /* Fileds */

    public DcMotor WobbleArmMotor = null;
    public Servo WobbleCloseServo = null;

    /* Constructor */
    public WobbleMechanism (DcMotor WobbleArmMotor, Servo WobbleCloseServo) {
        this.WobbleArmMotor = WobbleArmMotor;
        this.WobbleCloseServo = WobbleCloseServo;
    }

    public void WobbleEncoder(double speed, int WobbleEncoder) {
        WobbleArmMotor.setTargetPosition(WobbleEncoder);

        // Turn On RUN_TO_POSITION
        WobbleArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        WobbleArmMotor.setPower(Math.abs(speed));
        while ((WobbleArmMotor.isBusy())) {

        }
        WobbleArmMotor.setPower(0);
        WobbleArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void WobbleUp(double speed) {
      WobbleEncoder(speed, 0);
    }

    public void WobbleDown(double speed) {
        WobbleEncoder(speed, -65);
    }
    public void WobbleMidle(double speed) {
        WobbleEncoder(speed, -10);
    }

    public void WobbleOpen() {
        WobbleCloseServo.setPosition(0.5);
    }

    public void WobbleClose() {
        WobbleCloseServo.setPosition(0);
    }
}
