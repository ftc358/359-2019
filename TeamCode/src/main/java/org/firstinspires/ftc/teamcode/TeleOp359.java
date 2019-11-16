
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOp359 extends LinearOpMode {

    DcMotor motor1, motor2, motor3, motor4;
    DcMotor frontintakeleft, frontintakeright;
    DcMotor corehexmotorleft, corehexmotorright;
    CRServo foundation;

    public void runOpMode() throws InterruptedException {

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        corehexmotorleft = hardwareMap.dcMotor.get("chleft");
        corehexmotorright = hardwareMap.dcMotor.get("chright");
        frontintakeleft = hardwareMap.dcMotor.get("frontleft");
        frontintakeright = hardwareMap.dcMotor.get("frontright");
        foundation = hardwareMap.crservo.get("foundation");

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontintakeright.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {

            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            /**
             * Mechanum
             */
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

            telemetry.addData("position1", motor1.getCurrentPosition());
            telemetry.addData("position2", motor2.getCurrentPosition());
            telemetry.addData("position3", motor3.getCurrentPosition());
            telemetry.addData("position4", motor4.getCurrentPosition());
            telemetry.update();

            /**
             *Intake Mechanism
             */
            if (gamepad1.left_bumper){
                corehexmotorleft.setPower(0.5);
                corehexmotorright.setPower(0.5);
//                frontintakeleft.setPower(1);
//                frontintakeright.setPower(1);
            }
            else if (gamepad1.right_bumper){
                corehexmotorleft.setPower(-0.5);
                corehexmotorright.setPower(-0.5);
//                frontintakeleft.setPower(-1);
//                frontintakeright.setPower(-1);
            }
            else{
                corehexmotorleft.setPower(0);
                corehexmotorright.setPower(0);
//                frontintakeleft.setPower(0);
//                frontintakeright.setPower(0);
            }
        }
    }
}