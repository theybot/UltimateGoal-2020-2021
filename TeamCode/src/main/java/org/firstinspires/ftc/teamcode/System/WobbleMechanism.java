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

        // Stop all motion;
        WobbleArmMotor.setPower(0);

        // Turn off RUN_TO_POSITION
        WobbleArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void WobbleUp(double speed) {
      WobbleEncoder(speed, 0);
    }

    public void WobbleDown(double speed) {
        WobbleEncoder(speed, -1300);
    }

    public void WobbleOpen() {
        WobbleCloseServo.setPosition(0.7);
    }

    public void WobbleClose() {
        WobbleCloseServo.setPosition(0.2);
    }
}
