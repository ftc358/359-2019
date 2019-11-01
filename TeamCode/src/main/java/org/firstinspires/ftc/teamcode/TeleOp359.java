
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOp359 extends OpMode {

    DcMotor motor1, motor2, motor3, motor4;
    DcMotor motor1i, motor2i;
//    Servo servoNeck, servoGrip;

    public void init() {

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

//        servoNeck = hardwareMap.servo.get("servoN");
//        servoGrip = hardwareMap.servo.get("servoG");
    }

    public void loop() {

        // mecanum wheels
        double leftX = gamepad1.left_stick_x;
        double rightY= -gamepad1.right_stick_y;
        double rightX= gamepad1.right_stick_x;

        final double v1 = rightY - rightX - leftX;
        final double v2 = rightY + rightX + leftX;
        final double v3 = rightY - rightX + leftX;
        final double v4 = rightY + rightX - leftX;

        motor1.setPower(0.7 * v1);
        motor2.setPower(0.7 * v2);
        motor3.setPower(0.7 * v3);
        motor4.setPower(0.7 * v4);

        telemetry.addData("motor1", motor1.getPower());
        telemetry.addData("motor2", motor2.getPower());
        telemetry.addData("motor3", motor3.getPower());
        telemetry.addData("motor4", motor4.getPower());
        telemetry.update();

        // intake mechanism
        boolean leftBump = gamepad1.left_bumper;
        boolean rightBump = gamepad1.right_bumper;

        if (leftBump == true) {
            motor1i.setPower(-1);
            motor2i.setPower(1);
        }
        else if (rightBump == true) {
            motor1i.setPower(1);
            motor2i.setPower(-1);
        }
        else{
            motor1i.setPower(0);
            motor2i.setPower(0);
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