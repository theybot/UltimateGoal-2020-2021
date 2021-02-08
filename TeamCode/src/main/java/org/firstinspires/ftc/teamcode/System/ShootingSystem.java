package org.firstinspires.ftc.teamcode.System;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ShootingSystem {

    /* Fileds */

    public DcMotor LeftShootingMotor = null;
    public DcMotor RightShootingMotor = null;
    public Servo CartridgeServo = null;
    public Servo UpDownServo = null;

    public ShootingSystem(DcMotor LeftShootingMotor, DcMotor RightShootingMotor, Servo CartridgeServo, Servo UpDownServo) {
        this.LeftShootingMotor = LeftShootingMotor;
        this.RightShootingMotor = RightShootingMotor;
        this.CartridgeServo = CartridgeServo;
        this.UpDownServo = UpDownServo;
    }

    public void ShootingOn(double power) {
        LeftShootingMotor.setPower(power);
        RightShootingMotor.setPower(power);
    }
    public void ShootingOff() {
        LeftShootingMotor.setPower(0);
        RightShootingMotor.setPower(0);
    }
    public void CartridgeOn() {
        CartridgeServo.setPosition(1);
    }
    public void CartridgeOff() {
        CartridgeServo.setPosition(0.5);
    }
}
