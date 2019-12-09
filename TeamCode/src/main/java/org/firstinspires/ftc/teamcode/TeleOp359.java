package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOp359 extends RobotMain359 {

    public void runOpMode() throws InterruptedException {
        initializeSettings();
        skystoneMove.setPosition(1.);

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
            if (gamepad2.y){
                corehexmotorleft.setPower(0.5);
                corehexmotorright.setPower(0.5);
                frontintakeleft.setPower(0.5);
                frontintakeright.setPower(0.5);
            }
            else if (gamepad2.right_bumper){
                corehexmotorleft.setPower(-0.5);
                corehexmotorright.setPower(-0.5);
                frontintakeleft.setPower(-0.5);
                frontintakeright.setPower(-0.5);
            }
            else{
                corehexmotorleft.setPower(0);
                corehexmotorright.setPower(0);
                frontintakeleft.setPower(0);
                frontintakeright.setPower(0);
            }
//
            /**
             *Foundation mechanism
             */
            if (gamepad2.a) {
                foundation.setPower(1);
            } else if (gamepad2.b) {
                foundation.setPower(-1);
            } else {
                foundation.setPower(0);
            }

            /**
             *Skystone Move just in case there're problems during auto
             */

            if (gamepad1.a) {
                skystoneMove.setPosition(1.);          //up
            }
            else if (gamepad1.b){
                skystoneMove.setPosition(0.4);          //down
            }
        }
    }
}