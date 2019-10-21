
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleOp359 extends LinearOpMode {

    DcMotor motor1, motor2, motor3, motor4;
//        DcMotor motor1i, motor2i;
//        Servo servoNeck, servoGrip;

    public void runOpMode() throws InterruptedException {

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();


            // mecanum wheels
            double leftX = gamepad1.left_stick_x;

            final double v1 = gamepad1.right_stick_y - gamepad1.right_stick_x - leftX;
            final double v2 = gamepad1.right_stick_y + gamepad1.right_stick_x + leftX;
            final double v3 = gamepad1.right_stick_y - gamepad1.right_stick_x + leftX;
            final double v4 = gamepad1.right_stick_y + gamepad1.right_stick_x - leftX;

            motor1.setPower(-0.5 * v1);
            motor2.setPower(0.5 * v2);
            motor3.setPower(0.5 * v3);
            motor4.setPower(-0.5 * v4);

            telemetry.addData("motor1", v1);
            telemetry.addData("motor2", v2);
            telemetry.addData("motor3", v3);
            telemetry.addData("motor4", v4);
            telemetry.update();

        }
    }

}