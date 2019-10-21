package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Tick extends LinearOpMode {
    DcMotor motor1, motor2, motor3, motor4;
    public void runOpMode() throws InterruptedException {

        // Comments are cool.
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");


        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor1.setTargetPosition(motor1.getCurrentPosition() + 100);
        motor2.setTargetPosition(motor2.getCurrentPosition() + 100);
        motor3.setTargetPosition(motor3.getCurrentPosition() + 100);
        motor4.setTargetPosition(motor4.getCurrentPosition() + 100);

        motor1.setPower(0.5);
        motor2.setPower(0.5);
        motor3.setPower(0.5);
        motor4.setPower(0.5);


        while (motor1.isBusy()) {

//            telemetry.addData("motor 1 current", motor1.getCurrentPosition());
//            telemetry.addData("motor 1 target", motor1.getTargetPosition());
//            telemetry.addData("motor 2 current", motor2.getCurrentPosition());
//            telemetry.addData("motor 2 target", motor2.getTargetPosition());
//            telemetry.update();
        }


        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }
}