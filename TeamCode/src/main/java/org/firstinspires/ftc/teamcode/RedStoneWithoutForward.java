package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class RedStoneWithoutForward extends RobotMain359 {

    int detected = 0;
    state state359;
    boolean done = false;

    public void runOpMode() throws InterruptedException {

        initializeSettings();
        state359 = state.DETECT;
        skystoneMove.setPosition(1.);

        waitForStart();

        while (opModeIsActive() && !done) {
            switch (state359) {
                case DETECT:
                    telemetry.addData("Going to Vuforia", "yay!");
                    telemetry.update();
                    detected = lookForwardAndCheckRed();

                    telemetry.addData("position of the skystone", detected);
                    telemetry.addData("Going to DRIVE", "yay!");
                    telemetry.update();
                    state359 = state.DRIVE;
                    break;

                case DRIVE:
                    switch (detected) {
                        case 1:
                            turn(0.5,-85);
                            forward(0.5,17);
                            strafe(0.25,12);
                            sleep(500);
                            skystoneMove.setPosition(.4);
                            sleep(1000);
                            strafe(0.5,-25);
                            forward(0.5,-54);
                            skystoneMove.setPosition(1.);
                            forward(0.5,24);
                            break;
                        case 2:
                            turn(0.5,-85);
                            forward(0.5,10);
                            strafe(0.25,12);
                            sleep(500);
                            skystoneMove.setPosition(.4);
                            sleep(1000);
                            strafe(0.5,-25);
                            forward(0.5,-47);
                            skystoneMove.setPosition(1.);
                            forward(0.5,17);
                            break;
                        case 3:
                            turn(0.5,-85);
                            forward(0.5,3);
                            strafe(0.25,12);
                            sleep(500);
                            skystoneMove.setPosition(.4);
                            sleep(1000);
                            strafe(0.5,-25);
                            forward(0.5,-40);
                            skystoneMove.setPosition(1.);
                            forward(0.5,10);
                            break;
                    }
                    state359 = RedStoneWithoutForward.state.STOP;
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

    enum state {

        DETECT, DRIVE, STOP

    }
}