package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ZZZMotorTest extends LinearOpMode {
    public Servo servo1;
    public void runOpMode() throws InterruptedException{

        servo1 = hardwareMap.servo.get("servo");
        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.left_bumper) {
                servo1.setPosition(0.8);
            }
            else if (gamepad1.right_bumper) {
                servo1.setPosition(0.3);
            }
        }
    }
}
