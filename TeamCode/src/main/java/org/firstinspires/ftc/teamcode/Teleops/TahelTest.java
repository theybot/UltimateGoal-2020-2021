package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.RobotCustomade;
//@Disabled
@TeleOp(name = "TahelTest" , group = "teleops")
public class TahelTest extends RobotCustomade {

    private double leftStickX = 0;
    private double leftStickY = 0;
    private double rightStickX = 0;
    private String Mode = "Oriented";

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad2.b) {
                MyWobbleMechanism.WobbleDown(0.6);
            } else if (gamepad2.left_trigger > 0) {
                MyWobbleMechanism.WobbleUp(1);
            }
            else if (gamepad2.x) {
            MyWobbleMechanism.WobbleClose();
                telemetry.addData("l", WobbleArmMotor.getCurrentPosition());
                telemetry.update();

        }
    }
 }
}
