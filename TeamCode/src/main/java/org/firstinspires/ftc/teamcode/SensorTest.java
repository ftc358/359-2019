package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class SensorTest extends LinearOpMode {

    public DistanceSensor my_Distancesensor;
    public TouchSensor my_Touchsensor;

    public void runOpMode() throws InterruptedException {

        my_Distancesensor = hardwareMap.get(DistanceSensor.class, "ds");
        my_Touchsensor = hardwareMap.get(TouchSensor.class, "ts");

        waitForStart();
        while (opModeIsActive()) {

            telemetry.addData("distance", my_Distancesensor.getDistance(DistanceUnit.INCH));
            telemetry.addData("touch?", my_Touchsensor.isPressed());
            telemetry.addData("power", my_Touchsensor.getValue());
            telemetry.update();

        }
    }
}
