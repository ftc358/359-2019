package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleOp359 extends RobotMain359 {

    public void runOpMode() throws InterruptedException {
        initialize();

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

//            /**
//             *Intake Mechanism
//             */
//            if (gamepad2.left_bumper){
//                corehexmotorleft.setPower(0.5);
//                corehexmotorright.setPower(0.5);
//                frontintakeleft.setPower(0.5);
//                frontintakeright.setPower(0.5);
//            }
//            else if (gamepad2.right_bumper){
//                corehexmotorleft.setPower(-0.5);
//                corehexmotorright.setPower(-0.5);
//                frontintakeleft.setPower(-0.5);
//                frontintakeright.setPower(-0.5);
//            }
//            else{
//                corehexmotorleft.setPower(0);
//                corehexmotorright.setPower(0);
//                frontintakeleft.setPower(0);
//                frontintakeright.setPower(0);
//            }
//
            /**
             *Foundation mechanism
             */
            if (gamepad1.dpad_up){
                foundation.setPower(0.5);
            }
            else if (gamepad1.dpad_down){
                foundation.setPower(-0.5);
            }
            else{
                foundation.setPower(0);
            }

//            /**
//             *Skystone Move just in case there're problems during auto
//             */
//            if (gamepad1.a) {
//                skystoneMove.setPower(.5);
//            }
//            else if (gamepad1.b){
//                skystoneMove.setPower(-.5);
//            }
//            else{
//                skystoneMove.setPower(0);
//            }
        }
    }
}