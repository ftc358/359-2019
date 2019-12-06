package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class Auto359RedStone extends RobotMain359 {

    int detected = 0;
    state state359;

    public void runOpMode() throws InterruptedException {

        initializeSettings();
        state359 = state.DETECT;
        waitForStart();

        if (opModeIsActive()) {
            switch (state359) {
                case DETECT:
                    motor1.setPower(1);
                    motor2.setPower(1);
                    motor3.setPower(1);
                    motor4.setPower(1);
//                    forward(1, 5);

//                    detected = lookForwardAndCheck();
//                    telemetry.addData("position of the skystone", detected);
//                    telemetry.update();
//                    state359 = state.DRIVE;
                    break;
//                case DRIVE:
//                    if (detected == 1) {
//
//                    }
//                    if (detected == 2) {
//
//                    }
//                    if (detected == 3) {
//
//                    }
//                    state359 = state.STOP;
//
//                case STOP:
//                    motor1.setPower(0);
//                    motor2.setPower(0);
//                    motor3.setPower(0);
//                    motor4.setPower(0);
//                    break;
            }
//            break;
        }
    }

    enum state {

        DETECT, DRIVE, STOP

    }
}