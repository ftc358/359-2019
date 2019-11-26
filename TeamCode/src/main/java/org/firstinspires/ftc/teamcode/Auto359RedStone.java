package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class Auto359RedStone extends RobotMain359 {

    int detected = 0;
    state state359;

    public void runOpMode() throws InterruptedException {

        state359 = state.DETECT;
        waitForStart();

        while (opModeIsActive()) {
            switch (state359) {
                case DETECT:
                    forward(0.25, 1800);

                    detected = lookForwardAndCheck();
                    telemetry.addData("position of the skystone", detected);
                    telemetry.update();

                    state359 = state.STOP;

                case DRIVE:
                    if (detected == 1) {

                    }
                    if (detected == 2) {

                    }
                    if (detected == 3) {

                    }
                    state359 = state.PARK;

                case PARK:
                    if (detected == 1) {

                    }
                    if (detected == 2) {

                    }
                    if (detected == 3) {

                    }
                    state359 = state.STOP;

                case STOP:
                    motor1.setPower(0);
                    motor2.setPower(0);
                    motor3.setPower(0);
                    motor4.setPower(0);
                    break;
            }
            break;
        }
    }

    enum state {

        DETECT, DRIVE, PARK, STOP

    }
}