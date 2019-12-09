package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class AutonomousParkToRight extends RobotMain359 {

    boolean done = false;

    @Override
    public void runOpMode() throws InterruptedException{
        initializeSettings();
        skystoneMove.setPosition(1.);
        waitForStart();

        while (opModeIsActive() && !done) {
            sleep(25000);
            forward(0.5,2);
            strafe(0.5,15);
            skystoneMove.setPosition(.4);

            done = true;
        }
    }
}
