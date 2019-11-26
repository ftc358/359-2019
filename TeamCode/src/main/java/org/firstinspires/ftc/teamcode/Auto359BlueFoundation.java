package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.List;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@Disabled
@Autonomous
public class Auto359BlueFoundation extends RobotMain359 {

    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeIsActive()) {
            foundation.setPower(1);
            Thread.sleep(1000);

            forward(0.25, -3000);

            foundation.setPower(-1);
            Thread.sleep(1000);

            forward( 0.25, 3000);
            turn(0.25, 1500);
            forward(0.25, 4000);

            break;
        }
    }
}