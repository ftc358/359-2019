package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class TeleOp359 extends RobotMain359 {

    public void runOpMode() throws InterruptedException {
        initializeSettings();

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

            /**
             *Intake Mechanism
             */
            if (gamepad1.left_bumper){
                intakeleft.setPower(0.4);
                intakeright.setPower(0.4);
            }
            else if (gamepad1.right_bumper){
                intakeleft.setPower(-0.4);
                intakeright.setPower(-0.4);
            }
            else{
                intakeleft.setPower(0);
                intakeright.setPower(0);
            }

            /**
             * Linear Slide
             */
            if (gamepad2.left_bumper) {
                slide.setPower(0.3);
            }
            else if (gamepad2.right_bumper) {
                slide.setPower(-0.3);
            }
            else {
                slide.setPower(0);
            }

            /**
             * Graber Mechanism
             */
            if (gamepad2.a){
                graber.setPosition(.8);
            }
            else if (gamepad2.b){
                graber.setPosition(.4);
            }

//            /**
//             *Foundation mechanism
//             */
//            if (gamepad2.dpad_up) {
//                foundation.setPosition(.80);
//            }
//            else if (gamepad2.dpad_down) {
//                foundation.setPosition(-.1);
//            }

//            /**
//             *Skystone Move just in case there're problems during auto
//             */
//            if (gamepad1.dpad_up) {
//                skystoneMove.setPosition(1.);
//            }
//            else if (gamepad1.dpad_down){
//                skystoneMove.setPosition(.4);
//            }
        }
    }
}