package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class servoTest extends OpMode {

    Servo skystoneMove;

    public void init() {
        skystoneMove = hardwareMap.servo.get("skystoneMove");
    }

    public void loop() {
        skystoneMove.setPosition(Math.abs(gamepad1.left_stick_x));
        telemetry.addData("Current Position", skystoneMove.getPosition());
        telemetry.update();
    }
}
