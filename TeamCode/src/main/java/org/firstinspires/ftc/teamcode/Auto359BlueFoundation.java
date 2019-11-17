package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

@Autonomous
public class Auto359BlueFoundation extends LinearOpMode {

    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";
    //    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final String VUFORIA_KEY = "ARk+AQb/////AAABmV0RDGTiBEXluSpswNWIs+oLdAjW3AE6onoU0iyNfIiXnU0gt0DHT4m9FEzlJ+IoRun4NQglstqKn8rCzNvE7D+SS6FI2jWjhfD9UzfaedCHHCR+4VfLVFqAkUSIys2kX58N0D2E5GsxvFW0TdXI44RWZ1neUt8lbmK2uDTZfo+NtOSgqvSJEsrG0J6nLv9Cr+CAB6/X71URFpH2WtCJRH/F+6Y1Usy4b6uDdMoSKocv4B4j0DO3EuQuV1p/PCk3naRGYuKCdamnkcHMK/kK1yOoXtvRjFh374/3YtHkzFMCl7q3eHvh5h7X6kVCGXYheQurpk7JXScxZttBfiCi3GJQWnN6Ia6bIWx9aKe5WuPN";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    List<Recognition> updatedRecognitions;

    int detected = 0;
    state state359;

    DcMotor motor1, motor2, motor3, motor4;
    CRServo foundation;

    public void runOpMode() throws InterruptedException {

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        foundation = hardwareMap.crservo.get("foundation");

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);

        state359 = state.DETECT;
        waitForStart();

        while (opModeIsActive()) {
            switch (state359) {
                case DETECT:
                    foundation.setPower(1);
                    Thread.sleep(1000);

                    Encoders359.Forward(motor1, motor2, motor3, motor4, 0.25, -3000);

                    foundation.setPower(-1);
                    Thread.sleep(1000);

                    Encoders359.Forward(motor1,motor1,motor3,motor4,0.25,3000);
                    Encoders359.Turn(motor1,motor2,motor3,motor4,0.25,1500);
                    Encoders359.Forward(motor1,motor2,motor3,motor4,0.25,4000);
//                    detected = lookForwardAndCheck();
//                    telemetry.addData("position of the skystone", detected);
//                    telemetry.update();
                    state359 = state.STOP;

//                case DRIVE:
//                    if (detected == 1) {
//                        Encoders359.Forward(motor1,motor2,motor3,motor4,0.25,2000);
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, 2500);
////                        Encoders359.Forward(motor1, motor2, motor3, motor4, 0.25, 3000);
//
////                        Encoders359.Forward(motor1, motor2, motor3, motor4, 0.25, -3000);
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, -1000);   //Turn around the stone
////                        Encoders359.Forward(motor1, motor2, motor3, motor4, 0.25, -1000);
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, 5000);   //Push the stone over the line
//                    }
//                    if (detected == 2) {
//                        Encoders359.Forward(motor1,motor2,motor3,motor4,0.25,2000);
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, 2500);
////                        Encoders359.Forward(motor1, motor2, motor3, motor4, 0.25, 3000);
//
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, -2000);
////                        Encoders359.Forward(motor1, motor2, motor3, motor4, 0.25, -2500);
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, 1000);    //Turn around the stone
////                        Encoders359.Forward(motor1, motor2, motor3, motor4, 0.25, -1000);
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, 4000);    //Push the stone over the line
//                    }
//                    if (detected == 3) {
//                        Encoders359.Forward(motor1,motor2,motor3,motor4,0.25,2000);
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, 2500);
////                        Encoders359.Forward(motor1, motor2, motor3, motor4, 0.25, 3000);
//
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, -2000);
////                        Encoders359.Forward(motor1, motor2, motor3, motor4, 0.25, -2500);
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, 1000);    //Turn around the stone
////                        Encoders359.Forward(motor1, motor2, motor3, motor4, 0.25, -1000);
////                        Encoders359.Drift(motor1, motor2, motor3, motor4, 0.25, 3500);    //Push the stone over the line
//                    }
//                    state359 = state.PARK;
//
//                case PARK:
//                    if (detected == 2) {
//                        Encoders359.Forward(motor1,motor2,motor3,motor4,0.5,-500);
//                    }
//                    if (detected == 2) {
//                        Encoders359.Forward(motor1,motor2,motor3,motor4,0.5,-500);
//                    }
//                    if (detected == 3) {
//                        Encoders359.Forward(motor1,motor2,motor3,motor4,0.5,-500);
//                    }
//                    state359 = state.STOP;

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

    private void initTfod () {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);

        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

    private void initVuforiaThingy () {
        /**
         * Webcam Initialization
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
//        parameters.cameraDirection = CAMERA_CHOICE;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
    }

    private int lookForwardAndCheck () {
        int position = 0;
        initVuforiaThingy();

        /**
         * position = 1 is Skystone left, 2 is middle, and 3 is right
         */

        if (tfod != null) {
            tfod.activate();
        } else {
            return 0;
        }

        // getUpdatedRecognitions() will return null if no new information is available since
        // the last time that call was made.
        while (position == 0) {
            updatedRecognitions = tfod.getUpdatedRecognitions();
            int maxSize = 0;
            for (int i = 0; i < 50; i++) {
                List<Recognition> newRecognitions = tfod.getUpdatedRecognitions();
                if (newRecognitions != null && newRecognitions.size() > maxSize) {
                    updatedRecognitions = newRecognitions;
                    maxSize = newRecognitions.size();
                }
                sleep(10);
            }

            if (updatedRecognitions != null) {
                if (updatedRecognitions.size() == 2) {
                    if (updatedRecognitions.get(0).getLabel() == LABEL_SECOND_ELEMENT || updatedRecognitions.get(1).getLabel() == LABEL_SECOND_ELEMENT) {
                        int THRESHOLD = 200;
                        int skystonePosition;
                        int stonePosition;
                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel() == LABEL_SECOND_ELEMENT) {
                                skystonePosition = (int) recognition.getLeft();

                                if (skystonePosition < THRESHOLD) {
                                    position = 1;
                                } else if (skystonePosition > THRESHOLD) {
                                    position = 2;
                                }

                            } else if (recognition.getLabel() == LABEL_FIRST_ELEMENT) {
                                stonePosition = (int) recognition.getLeft();
                                if (stonePosition < THRESHOLD) {
                                    position = 2;
                                } else if (stonePosition > THRESHOLD) {
                                    position = 1;
                                }
                            }
                        }
                    } else {
                        //This means that we have not detected a Skystone, so the Skystone is
                        // probably at position 3
                        position = 3;
                    }
                }
            }
        }
        return position;
    }

    enum state {

        DETECT, DRIVE, PARK, STOP

    }
}