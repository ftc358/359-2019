package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Auto359GuessCode extends LinearOpMode {

    DcMotor motor1, motor2, motor3, motor4;
    DcMotor motor1i, motor2i;

    public void runOpMode() throws InterruptedException{

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor1i = hardwareMap.dcMotor.get("motor1i");
        motor2i = hardwareMap.dcMotor.get("motor2i");

        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()){

            //Those are just random numbers I put, don't actually run it seriously...
            Encoders359.Forward(motor1, motor2, motor3, motor4, 0.7,1000);
            Encoders359.Turn(motor1, motor2, motor3, motor4, 0.7,1000);
            Encoders359.Drift(motor1, motor2, motor3, motor4, 0.7,1000);
            break;
        }
    }
}
