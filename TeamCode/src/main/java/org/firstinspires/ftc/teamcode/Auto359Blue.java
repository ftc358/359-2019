package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Auto359Blue extends LinearOpMode {

    DcMotor motor1, motor2, motor3, motor4;
    DcMotor motor1i, motor2i;

    public static void Forward(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, long timer) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);

        Thread.sleep(timer);

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public static void Turn(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, long timer) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(-power);
        motor3.setPower(-power);
        motor4.setPower(power);

        Thread.sleep(timer);

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public static void Slide(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, long timer) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(-power);
        motor3.setPower(power);
        motor4.setPower(-power);

        Thread.sleep(timer);

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void runOpMode() throws InterruptedException{

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);

        motor1i = hardwareMap.dcMotor.get("motor1i");
        motor2i = hardwareMap.dcMotor.get("motor2i");

        waitForStart();

        if (opModeIsActive()){

            Forward(motor1,motor2,motor3,motor4,0.5,2500);
            Slide(motor1,motor2,motor3,motor4,-0.5,1000);
            Forward(motor1,motor2,motor3,motor4,-0.5,1500);
            Slide(motor1,motor2,motor3,motor4,-0.5,500);
            Forward(motor1,motor2,motor3,motor4,-0.5,500);
            Slide(motor1,motor2,motor3,motor4,0.5,2600);
            Forward(motor1,motor2,motor3,motor4,0.5,500);
            Slide(motor1,motor2,motor3,motor4,-0.5,3250);
            Forward(motor1,motor2,motor3,motor4,-0.5,700);
            Slide(motor1,motor2,motor3,motor4,0.5,3000);

        }
    }
}
