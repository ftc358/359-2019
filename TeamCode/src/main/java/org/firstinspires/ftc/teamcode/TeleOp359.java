
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOp359 extends LinearOpMode {

    DcMotor motor1, motor2, motor3, motor4;
//    DcMotor frontintakeleft, frontintakeright;

    boolean leftBump = gamepad1.left_bumper;
    boolean rightBump = gamepad1.right_bumper;

    public void runOpMode() throws InterruptedException {

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

//        frontintakeleft = hardwareMap.dcMotor.get("motor1i");
//        frontintakeright = hardwareMap.dcMotor.get("motor2i");
//
//        frontintakeright.setDirection(DcMotorSimple.Direction.REVERSE);
//

        waitForStart();
        while (opModeIsActive()) {

            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            // mecanum wheels
            double RightX = gamepad1.right_stick_x;
            double LeftY = -gamepad1.left_stick_y;
            double LeftX = gamepad1.left_stick_x;

            double v1 = LeftY - LeftX - RightX;
            double v2 = LeftY + LeftX + RightX;
            double v3 = LeftY - LeftX + RightX;
            double v4 = LeftY + LeftX - RightX;

            motor1.setPower(v1);
            motor2.setPower(v2);
            motor3.setPower(v3);
            motor4.setPower(v4);

            telemetry.addData("motor1", motor1.getPower());
            telemetry.addData("motor2", motor2.getPower());
            telemetry.addData("motor3", motor3.getPower());
            telemetry.addData("motor4", motor4.getPower());
            telemetry.update();

//            frontintakeleft.setPower(0);
//            frontintakeright.setPower(0);
//
//            if (leftBump) {
//                frontintakeleft.setPower(-1);
//                frontintakeright.setPower(-1);
//            }
//            else if (rightBump) {
//                frontintakeleft.setPower(1);
//                frontintakeright.setPower(1);
//            }
        }
    }
}