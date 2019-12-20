package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class Auto359BlueFoundation extends RobotMain359 {

    public void runOpMode() throws InterruptedException {
        initializeSettings();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("state", state359);
            telemetry.update();
            switch (state359) {
                case DETECT:

                    state359 = state.STOP;
                    break;

                case DRIVE:

                    state359 = state.STOP;
                    break;

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
}