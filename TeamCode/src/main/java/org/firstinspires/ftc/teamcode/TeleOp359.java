
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOp359 extends LinearOpMode {

    DcMotor motor1, motor2, motor3, motor4;
    DcMotor motor1i, motor2i;
//    Servo servoNeck, servoGrip;

    public void runOpMode() throws InterruptedException {

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

//        servoNeck = hardwareMap.servo.get("servoN");
//        servoGrip = hardwareMap.servo.get("servoG");


        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();


            // mecanum wheels
            double RightX = gamepad1.right_stick_x;
            double LeftY= -gamepad1.left_stick_y;
            double LeftX= gamepad1.left_stick_x;

            final double v1 = LeftY - LeftX - RightX;
            final double v2 = LeftY + LeftX + RightX;
            final double v3 = LeftY - LeftX + RightX;
            final double v4 = LeftY + LeftX - RightX;

            motor1.setPower(v1);
            motor2.setPower(-v2);
            motor3.setPower(-v3);
            motor4.setPower(v4);

            telemetry.addData("motor1", motor1.getPower());
            telemetry.addData("motor2", motor2.getPower());
            telemetry.addData("motor3", motor3.getPower());
            telemetry.addData("motor4", motor4.getPower());
            telemetry.update();

            // intake mechanism
            boolean leftBump = gamepad1.left_bumper;
            boolean rightBump = gamepad1.right_bumper;

            motor1i.setPower(0);
            motor2i.setPower(0);

            if (leftBump) {
                motor1i.setPower(-1);
                motor2i.setPower(1);
            }
            else if (rightBump) {
                motor1i.setPower(1);
                motor2i.setPower(-1);
            }


//            // Placement mechanism -- neck
//            float neckleft=gamepad1.left_trigger;
//            float neckright=gamepad1.right_trigger;
//
//            if (neckleft>0.1) {
//                servoNeck.setPosition(servoNeck.getPosition()-0.1);
//            }
//            if (neckright>0.1) {
//                servoNeck.setPosition(servoNeck.getPosition()+0.1);
//            }
//
//
//            // Placement mechanism -- Grip
//            boolean a =gamepad1.a;
//            boolean up=true;
//
//            if (a && up) {
//                servoGrip.setPosition(0.25);
//            }
//            else if (a && !up) {
//                servoGrip.setPosition(0);
//            }

        }
    }

}