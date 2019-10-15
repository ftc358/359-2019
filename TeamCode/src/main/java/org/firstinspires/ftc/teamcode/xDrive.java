
package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
public class xDrive {
    @TeleOp
    public static class xDrive359 extends LinearOpMode {
        DcMotor motor1, motor2, motor3, motor4, motor1i, motor2i;
        Servo servoNeck, servoGrip;

        public void runOpMode() throws InterruptedException {

            motor1 = hardwareMap.dcMotor.get("motor1");
            motor2 = hardwareMap.dcMotor.get("motor2");
            motor3 = hardwareMap.dcMotor.get("motor3");
            motor4 = hardwareMap.dcMotor.get("motor4");
            motor1i = hardwareMap.dcMotor.get("motor1i");
            motor2i = hardwareMap.dcMotor.get("motor2i");
            servoNeck = hardwareMap.servo.get("servoNeck");
            servoGrip= hardwareMap.servo.get("servoGrip");

            waitForStart();
            while (opModeIsActive()) {
                telemetry.addData("opModeIsActive", opModeIsActive());
                telemetry.update();


                // mecanum wheels
                double leftX = gamepad1.left_stick_x;

                final double v1 = gamepad1.right_stick_y - gamepad1.right_stick_x -leftX;
                final double v2 = gamepad1.right_stick_y + gamepad1.right_stick_x +leftX;
                final double v2 = gamepad1.right_stick_y + gamepad1.right_stick_x +leftX;
                final double v3 = gamepad1.right_stick_y - gamepad1.right_stick_x +leftX;
                final double v4 = gamepad1.right_stick_y + gamepad1.right_stick_x -leftX;

                motor1.setPower(0.5*v1);
                motor2.setPower(0.5*v2);
                motor3.setPower(0.5*v3);
                motor4.setPower(0.5*v4);

                telemetry.addData("motor1", v1);
                telemetry.addData("motor2", v2);
                telemetry.addData("motor3", v3);
                telemetry.addData("motor4", v4);
                telemetry.update();


                // intake mechanism
                boolean leftBump = gamepad1.left_bumper;
                boolean rightBump = gamepad1.right_bumper;

                motor1i.setPower(0);
                motor2i.setPower(0);

                if (leftBump) {
                    motor1i.setPower(-0.5);
                    motor2i.setPower(0.5);
                }
                else if (rightBump) {
                    motor1i.setPower(0.5);
                    motor2i.setPower(-0.5);
                }


                // Placement mechanism -- neck
                float neckleft=gamepad1.left_trigger;
                float neckright=gamepad1.right_trigger;

                if (neckleft>0.1) {
                    servoNeck.setPosition(servoNeck.getPosition()-0.1);
                }
                if (neckright>0.1) {
                    servoNeck.setPosition(servoNeck.getPosition()+0.1);
                }


                // Placement mechanism -- Grip
                boolean a =gamepad1.a;
                boolean up=true;

                if (a && up) {
                    servoNeck.setPosition(0.25);
                }
                else if (a && !up) {
                    servoNeck.setPosition(0);
                }




                // Linear slide



            }
        }
    }
}

//                double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
//                double robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
//                double leftX = gamepad1.right_stick_x;
//                //double leftX = 0;
//                final double v2 = r * Math.cos(robotAngle) + leftX;
//                final double v1 = -r * Math.sin(robotAngle) - leftX;
//                final double v3 = r * Math.sin(robotAngle) + leftX;
//                final double v4 = -r * Math.cos(robotAngle) - leftX;


//if (gamepad1.right_bumper && button) {
//        servoTest.setPosition(-0.2);
//        button = false;
//        } else if (gamepad1.left_bumper && !button) {
//        servoTest.setPosition(0.35);
//        button = true;
//        }
//        telemetry.addData("opModeIsActive", opModeIsActive());
//
//        telemetry.addData("Servo Position", servoTest.getPosition());
//        telemetry.addData("Status", "Running");