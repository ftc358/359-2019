package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Written to test RobotMainAAAAAAAA class for 359 faulty chassis
 */

@Disabled
@Autonomous
public class AutoTestAAAAAAAA extends RobotMainAAAAAAAA {

    public void runOpMode() throws InterruptedException {

        initializeSettings();

        waitForStart();

        if (opModeIsActive()) {

            forward(1, 24);
            strafeRight(1, 24);
            turnLeft(1, 360);

        }
    }
}
