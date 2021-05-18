package org.firstinspires.ftc.teamcode.System;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSystem {

    /* Fileds */

    public DcMotor IntakeMotor = null;
    public Servo LeftRingDropperServo = null;
    public Servo RightRingDropperServo = null;

    public double DropperUp = 0;
    public double DropperDowm = 1;

    /* Constructor */
    public IntakeSystem(DcMotor IntakeMotor) {
        this.IntakeMotor = IntakeMotor;
        this.LeftRingDropperServo = LeftRingDropperServo;
        this.RightRingDropperServo = RightRingDropperServo;
    }

    public void IntakeOn(double power) {
        IntakeMotor.setPower(power);
    }
    public void IntakeOff() {
        IntakeMotor.setPower(0);
    }
    public void RingDropperDowm() {
        LeftRingDropperServo.setPosition(DropperDowm);
        RightRingDropperServo.setPosition(DropperUp);
    }
    public void RingDropperUp() {
        LeftRingDropperServo.setPosition(DropperUp);
        RightRingDropperServo.setPosition(DropperDowm);
    }


    }
