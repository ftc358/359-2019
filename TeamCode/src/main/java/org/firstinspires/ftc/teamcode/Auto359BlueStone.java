package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class Auto359BlueStone extends RobotMain359 {

    int detected = 0;
    state state359;

    public void runOpMode() throws InterruptedException {

        initializeSettings();
        state359 = state.DETECT;
        waitForStart();

        if (opModeIsActive()) {
            switch (state359) {
                case DETECT:
                    skystoneMove.setPosition(90);
//                    forward(0.5, 20);

//                    detected = lookForwardAndCheckBlue();
                    telemetry.addData("position of the skystone", detected);
                    telemetry.update();
                    state359 = state.DRIVE;

                case DRIVE:
                    if (detected == 1) {
                        turn(0.5,-85);
                        forward(0.5,10);
                        strafe(0.5,11);
                        skystoneMove.setPosition(90);
                        strafe(0.5,-25);
                        forward(0.5,45);
                        forward(0.5,-20);

                    }
                    if (detected == 2) {
                        turn(0.5,-85);
                        forward(0.5,1.5);
                        strafe(0.5,11);
                        skystoneMove.setPosition(90);
                        strafe(0.5,-25);
                        forward(0.5,50);
                        forward(0.5,-15);

                    }
                    if (detected == 3) {
                        turn(0.5,-85);
                        forward(0.5,-7);
                        strafe(0.5,11);
                        skystoneMove.setPosition(90);
                        strafe(0.5,-25);
                        forward(0.5,59);
                        forward(0.5,-20);

                    }
                    state359 = state.STOP;

                case STOP:
                    motor1.setPower(0);
                    motor2.setPower(0);
                    motor3.setPower(0);
                    motor4.setPower(0);
                    break;
            }
        }
    }

    enum state {

        DETECT, DRIVE, STOP

    }
}