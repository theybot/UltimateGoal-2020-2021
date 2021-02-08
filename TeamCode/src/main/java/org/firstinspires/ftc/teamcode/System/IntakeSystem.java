package org.firstinspires.ftc.teamcode.System;

import com.qualcomm.robotcore.hardware.DcMotor;

public class IntakeSystem {

    /* Fileds */

    public DcMotor IntakeMotor = null;

    /* Constructor */
    public IntakeSystem(DcMotor IntakeMotor) {
        this.IntakeMotor = IntakeMotor;
    }

    public void IntakeOn(double power) {
        IntakeMotor.setPower(power);
    }
    public void IntakeOff() {
        IntakeMotor.setPower(0);
    }
}
