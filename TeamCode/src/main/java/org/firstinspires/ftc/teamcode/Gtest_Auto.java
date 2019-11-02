

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Disabled
@Autonomous
public class Gtest_Auto extends LinearOpMode {

    DcMotor motor1, motor2;

    Integer cubePosition;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AXzW9CD/////AAAAGTPAtr9HRUXZmowtd9p0AUwuXiBVONS/c5x1q8OvjMrQ8/XJGxEp0TP9Kl8PvqSzeXOWIvVa3AeB6MyAQboyW/Pgd/c4a4U/VBs1ouUsVBkEdbaq1iY7RR0cjYr3eLwEt6tmI37Ugbwrd5gmxYvOBQkGqzpbg2U2bVLycc5PkOixu7PqPqaINGZYSlvUzEMAenLOCxZFpsayuCPRbWz6Z9UJfLeAbfAPmmDYoKNXRFll8/jp5Ie7iAhSQgfFggWwyiqMRCFA3GPTsOJS4H1tSiGlMjVzbJnkusPKXfJ0dK3OH9u7ox9ESpi91T0MemXw3nn+/6QRvjGtgFH+wMDuQX7ta89+yW+wqdXX9ZQu8BzY";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() throws InterruptedException {

        // Comments are cool.
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");

        motor1.setDirection(DcMotorSimple.Direction.REVERSE);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        waitForStart();

        if (tfod != null) {
            tfod.activate();
        }
//
//      cubePosition = vuforiaRecognize();
        cubePosition = 1;

        if (tfod != null) {
            tfod.shutdown();
        }


        motor1.setTargetPosition(motor1.getCurrentPosition() + 2000);
        motor2.setTargetPosition(motor2.getCurrentPosition() + 2000);

        motor1.setPower(0.75);
        motor2.setPower(0.5);


        while (motor1.isBusy() && motor2.isBusy()) {
            telemetry.addData("Running", "segment 1");
            telemetry.addData("motor 1 current", motor1.getCurrentPosition());
            telemetry.addData("motor 1 target", motor1.getTargetPosition());
            telemetry.addData("motor 2 current", motor2.getCurrentPosition());
            telemetry.addData("motor 2 target", motor2.getTargetPosition());
            telemetry.update();
        }


        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
//            telemetry.addData("this is message number 2", motor1.getCurrentPosition());
//            telemetry.update();

        if (cubePosition == 0) {
            motor1.setTargetPosition(motor1.getCurrentPosition() + 360);
            motor1.setPower(0.75);

            while (motor1.isBusy()) {
                telemetry.addData("Running", "Segment 2");
                telemetry.addData("motor 1 current", motor1.getCurrentPosition());
                telemetry.addData("motor 1 target", motor1.getTargetPosition());
                telemetry.update();
            }

            motor1.setPower(0);
        } else if (cubePosition == 1) {
            motor2.setTargetPosition(motor2.getCurrentPosition() + 360);
            motor2.setPower(0.5);

            while (motor2.isBusy()) {
                telemetry.addData("Running", "Segment 2");
                telemetry.addData("motor 1 current", motor2.getCurrentPosition());
                telemetry.addData("motor 1 target", motor2.getTargetPosition());
                telemetry.update();
            }

            motor1.setPower(0);
        }


        motor1.setTargetPosition(motor1.getCurrentPosition() + 2000);
        motor2.setTargetPosition(motor2.getCurrentPosition() + 2000);
        motor1.setPower(0.75);
        motor2.setPower(0.5);
        // hurray for magic numbers. comment this.


        while (motor1.isBusy() && motor2.isBusy()) {
            telemetry.addData("Running", "Segment 2");
            telemetry.addData("motor 1 current", motor1.getCurrentPosition());
            telemetry.addData("motor 1 target", motor1.getTargetPosition());
            telemetry.addData("motor 2 current", motor2.getCurrentPosition());
            telemetry.addData("motor 2 target", motor2.getTargetPosition());
            telemetry.update();
        }

        motor1.setPower(0);
        motor2.setPower(0);


    }

    public Integer vuforiaRecognize() {

        Integer cubePosition = -1;

        while (cubePosition == -1) {
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    telemetry.addData("# Object Detected", updatedRecognitions.size());
                    if (updatedRecognitions.size() == 2) {
                        int goldMineralX = -1;
                        int silverMineralX = -1;
                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                goldMineralX = (int) recognition.getLeft();
                            } else {
                                silverMineralX = (int) recognition.getLeft();
                            }
                        }
                        if (goldMineralX != -1 && silverMineralX != -1) {
                            if (goldMineralX < silverMineralX) {
                                cubePosition = 0;
                                //telemetry.addData("Gold Mineral Position", "Left");
                            } else if (goldMineralX > silverMineralX) {
                                cubePosition = 1;
                                //telemetry.addData("Gold Mineral Position", "Right");
                            }
                        }
                    }
                    telemetry.update();
                }
            }
        }

        return cubePosition;
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}


