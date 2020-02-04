package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class Auto359BlueStone extends RobotMain359 {

    int detected = 0;
    boolean done = false;

    public void runOpMode() throws InterruptedException {

        initializeSettings();

        waitForStart();

        while (opModeIsActive() && !done) {
            switch (state359) {
                case DETECT:
                    telemetry.addData("Going forward", "yay!");
                    telemetry.update();
                    forward(0.5, 20);

                    telemetry.addData("Going to Vuforia", "yay!");
                    telemetry.update();
                    detected = lookForwardAndCheckBlue();

                    telemetry.addData("position of the skystone", detected);
                    telemetry.addData("Going to DRIVE", "yay!");
                    telemetry.update();
                    state359 = state.DRIVE;
                    break;

                case DRIVE:
                    switch (detected) {
                        case 1:
                            turn(0.5,-85);
                            forward(0.5,10);
                            strafe(0.25,12);
                            sleep(500);
                            skystoneMove.setPosition(.4);
                            sleep(1000);
                            strafe(0.5,-13);
                            forward(0.5,45);
                            skystoneMove.setPosition(1.);
                            forward(0.5,-20);
                            strafe(0.5,5);
                            break;
                        case 2:
                            turn(0.5,-85);
                            forward(0.5,1.5);
                            strafe(0.25,12);
                            sleep(500);
                            skystoneMove.setPosition(.4);
                            sleep(1000);
                            strafe(0.5,-13);
                            forward(0.5,50);
                            skystoneMove.setPosition(1.);
                            forward(0.5,-15);
                            strafe(0.5,5);
                            break;
                        case 3:
                            turn(0.5,-85);
                            forward(0.5,-7);
                            strafe(0.25,12);
                            sleep(500);
                            skystoneMove.setPosition(.4);
                            sleep(1000);
                            strafe(0.5,-13);
                            forward(0.5,59);
                            skystoneMove.setPosition(1.);
                            forward(0.5,-20);
                            strafe(0.5,5);
                            break;
                    }
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