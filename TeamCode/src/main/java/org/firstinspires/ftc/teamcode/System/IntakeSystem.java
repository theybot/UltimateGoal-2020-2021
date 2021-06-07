package org.firstinspires.ftc.teamcode.System;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSystem {

    /* Fileds */

    public DcMotor IntakeMotor = null;
    public Servo LeftRingDropperServo = null;
    public Servo RightRingDropperServo = null;


    /* Constructor*/
    public IntakeSystem(DcMotor IntakeMotor, Servo LeftRingDropperServo, Servo RightRingDropperServo) {
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
    public void RingDropperUp() {
        LeftRingDropperServo.setPosition(0.6);
        RightRingDropperServo.setPosition(0.8);
    }
    public void RingDropperDown() {
        LeftRingDropperServo.setPosition(0.95);
        RightRingDropperServo.setPosition(0.35);
    }
    public void RingDropperStart() {
        LeftRingDropperServo.setPosition(0.4);
        RightRingDropperServo.setPosition(1);
    }


    }
