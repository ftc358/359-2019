package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class AutoTestAAAAAAAA extends RobotMainAAAAAAAA {

    public void runOpMode() throws InterruptedException {

        initializeSettings();

        waitForStart();

        if (opModeIsActive()) {

            forward(1, 5);
            strafeRight(1, 9);
            turnLeft(1, 5);

        }
    }
}
