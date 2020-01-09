package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
public class Auto359BlueFoundation extends RobotMain359 {

    public void runOpMode() throws InterruptedException {

        boolean done = false;

        initializeSettings();
        waitForStart();

        while (opModeIsActive() && !done) {
            telemetry.addData("state", state359);
            telemetry.addData("leftdistance", leftDistanceSensor.getDistance(DistanceUnit.INCH));
            telemetry.addData("rightdistance", rightDistanceSensor.getDistance(DistanceUnit.INCH));
            telemetry.update();
            switch (state359) {
                case DETECT:
                    forward(0.5, -28);
                    strafe(0.5, 18);
                    sleep(500);
                    forward(0.25, -2);
                    foundation.setPosition(.25);
                    forward(0.15, 30);
                    foundation.setPosition(.85);
                    sleep(500);
                    strafeWithLeftDistanceSensorFoundation(10, 0.3, -55);
                    break;

                case DRIVE:
                    forward(0.75, -23);
                    strafe(0.75, -15);
                    state359 = state.STOP;
                    break;

                case REST:
                    state359 = state.STOP;
                    break;

                case STOP:
                    motor1.setPower(0);
                    motor2.setPower(0);
                    motor3.setPower(0);
                    motor4.setPower(0);
                    done = true;
                    break;
            }
        }
    }
}