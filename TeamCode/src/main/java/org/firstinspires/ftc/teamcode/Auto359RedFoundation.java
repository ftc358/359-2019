package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous
public class Auto359RedFoundation extends RobotMain359 {

    public void runOpMode() throws InterruptedException {
        initializeSettings();
        foundation.setPosition(1.);
        skystoneMove.setPosition(1.);
        waitForStart();

        while (opModeIsActive()) {
            foundation.setPosition(1);
            forward(0.25, -3000);
            foundation.setPosition(-1);

            forward(0.25, 3000);
            turn(0.25, 1500);
            forward(0.25, 4000);

            break;
        }
    }
}