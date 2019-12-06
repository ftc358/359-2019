package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
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
                    forward(0.25, 10000);

                    detected = lookForwardAndCheck();
                    telemetry.addData("position of the skystone", detected);
                    telemetry.update();

                    state359 = state.DRIVE;

                case DRIVE:
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
        }
    }

    enum state {

        DETECT, DRIVE, STOP

    }
}