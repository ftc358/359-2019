package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public abstract class RobotMain359 extends LinearOpMode {

    //Vuforia and Drive Train
    public static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    public static final String VUFORIA_KEY =
            "ARk+AQb/////AAABmV0RDGTiBEXluSpswNWIs+oLdAjW3AE6onoU0iyNfIiXnU0gt0DHT4m9FEzlJ+IoRun4NQglstqKn8rCzNvE7D+SS6FI2jWjhfD9UzfaedCHHCR+4VfLVFqAkUSIys2kX58N0D2E5GsxvFW0TdXI44RWZ1neUt8lbmK2uDTZfo+NtOSgqvSJEsrG0J6nLv9Cr+CAB6/X71URFpH2WtCJRH/F+6Y1Usy4b6uDdMoSKocv4B4j0DO3EuQuV1p/PCk3naRGYuKCdamnkcHMK/kK1yOoXtvRjFh374/3YtHkzFMCl7q3eHvh5h7X6kVCGXYheQurpk7JXScxZttBfiCi3GJQWnN6Ia6bIWx9aKe5WuPN";
    public VuforiaLocalizer vuforia;
    public TFObjectDetector tfod;
    public RobotPosition359 STARTING_POSITION;
    public RobotPosition359 CURRENT_POSITION;
    public static final double FORWARD_ADJUST = 98.04;
    public static final double TURN_ADJUST = 10.0 * (90.0 / 47.0) * (720.0 / 702.0);
    public static final double STRAFE_ADJUST = 98.04;
    //Motors and sensors
    protected DcMotor motor1, motor2, motor3, motor4;
    protected DcMotor frontintakeleft, frontintakeright;
    protected DcMotor corehexmotorleft, corehexmotorright;
    protected Servo skystoneMove, foundation;
    protected DistanceSensor leftDistanceSensor;
    protected DistanceSensor rightDistanceSensor;
    protected BNO055IMU my_imu;
    state state359;
    List<Recognition> updatedRecognitions;

    /**
     * Initializing settings
     */
    public void initializeSettings() throws InterruptedException {

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        corehexmotorleft = hardwareMap.dcMotor.get("chleft");
        corehexmotorright = hardwareMap.dcMotor.get("chright");
        frontintakeleft = hardwareMap.dcMotor.get("frontleft");
        frontintakeright = hardwareMap.dcMotor.get("frontright");
        foundation = hardwareMap.servo.get("foundation");
        skystoneMove = hardwareMap.servo.get("skystoneMove");
        leftDistanceSensor = hardwareMap.get(DistanceSensor.class, "lds");
        rightDistanceSensor = hardwareMap.get(DistanceSensor.class, "rds");

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);
        frontintakeright.setDirection(DcMotorSimple.Direction.REVERSE);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        my_imu = hardwareMap.get(BNO055IMU.class, "imu");
        my_imu.initialize(parameters);

//        my_Distancesensor = hardwareMap.get(DistanceSensor.class, "ds");

        skystoneMove.setPosition(1.);
        foundation.setPosition(.85);
        state359 = state.DETECT;
    }

    /**
     * Encoders settings
     */
    public void forward(double power, double inches) {

        final double FORWARD_DISTANCE = inches * FORWARD_ADJUST;

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motor1.setTargetPosition(motor1.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor2.setTargetPosition(motor2.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor3.setTargetPosition(motor3.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor4.setTargetPosition(motor4.getCurrentPosition() + (int) FORWARD_DISTANCE);

        //Set Drive Power
        motor1.setPower(-power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(-power);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void forwardWithOneMotor(DcMotor motorBob, double power, int rotations) {
//        motorBob.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBob.setTargetPosition(motorBob.getCurrentPosition() + rotations * 1000);
        motorBob.setPower(power);
        motorBob.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motorBob.isBusy()) {
            telemetry.addData("position", motorBob.getCurrentPosition());
            telemetry.addData("target", motorBob.getTargetPosition());
            telemetry.update();

        }
        motorBob.setPower(0);
    }

    public void turn(double power, int degrees) {

        final double TURN_DISTANCE = degrees * TURN_ADJUST;

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motor1.setTargetPosition(motor1.getCurrentPosition() + (int) TURN_DISTANCE);
        motor2.setTargetPosition(motor2.getCurrentPosition() + (int) TURN_DISTANCE);
        motor3.setTargetPosition(motor3.getCurrentPosition() + (int) TURN_DISTANCE);
        motor4.setTargetPosition(motor4.getCurrentPosition() + (int) TURN_DISTANCE);

        //Set Drive Power
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void strafe(double power, double inches) {

        final double STRAFE_DISTANCE = inches * STRAFE_ADJUST;

        //positive distance is going forward

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motor1.setTargetPosition(motor1.getCurrentPosition() + (int) STRAFE_DISTANCE);
        motor2.setTargetPosition(motor2.getCurrentPosition() + (int) STRAFE_DISTANCE);
        motor3.setTargetPosition(motor3.getCurrentPosition() + (int) STRAFE_DISTANCE);
        motor4.setTargetPosition(motor4.getCurrentPosition() + (int) STRAFE_DISTANCE);

        //Set Drive Power
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void strafeWithLeftDistanceSensor(double distanceLimitInches, double power, int inches) {

        telemetry.addData("leftdistance", leftDistanceSensor.getDistance(DistanceUnit.INCH));
        telemetry.addData("rightdistance", rightDistanceSensor.getDistance(DistanceUnit.INCH));
        telemetry.update();

        final double FORWARD_DISTANCE = inches * FORWARD_ADJUST;

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motor1.setTargetPosition(motor1.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor2.setTargetPosition(motor2.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor3.setTargetPosition(motor3.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor4.setTargetPosition(motor4.getCurrentPosition() + (int) FORWARD_DISTANCE);

        //Set Drive Power
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
            if (leftDistanceSensor.getDistance(DistanceUnit.INCH) < distanceLimitInches) {
                state359 = state.STOP;
                break;
            } else {

            }
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void strafeWithrightDistanceSensor(double distanceLimitInches, double power, int inches) {

        telemetry.addData("leftdistance", leftDistanceSensor.getDistance(DistanceUnit.INCH));
        telemetry.addData("rightdistance", rightDistanceSensor.getDistance(DistanceUnit.INCH));
        telemetry.update();

        final double FORWARD_DISTANCE = inches * FORWARD_ADJUST;

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motor1.setTargetPosition(motor1.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor2.setTargetPosition(motor2.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor3.setTargetPosition(motor3.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor4.setTargetPosition(motor4.getCurrentPosition() + (int) FORWARD_DISTANCE);

        //Set Drive Power
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
            if (rightDistanceSensor.getDistance(DistanceUnit.INCH) < distanceLimitInches) {
                state359 = state.STOP;
                break;
            } else {

            }
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void strafeWithLeftDistanceSensorFoundation(double distanceLimitInches, double power, int inches) {

        boolean done = false;

        telemetry.addData("leftdistance", leftDistanceSensor.getDistance(DistanceUnit.INCH));
        telemetry.addData("rightdistance", rightDistanceSensor.getDistance(DistanceUnit.INCH));
        telemetry.update();

        final double FORWARD_DISTANCE = inches * FORWARD_ADJUST;

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motor1.setTargetPosition(motor1.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor2.setTargetPosition(motor2.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor3.setTargetPosition(motor3.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor4.setTargetPosition(motor4.getCurrentPosition() + (int) FORWARD_DISTANCE);

        //Set Drive Power
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy() && !done) {
            if (leftDistanceSensor.getDistance(DistanceUnit.INCH) != DistanceSensor.distanceOutOfRange){
                if (leftDistanceSensor.getDistance(DistanceUnit.INCH) < distanceLimitInches) {
                    state359 = state.DRIVE;
                    done = true;
                }
            }
        }

        if (!done) {
            state359 = state.REST;
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void strafeWithRightDistanceSensorFoundation(double distanceLimitInches, double power, int inches) {

        boolean done = false;

        final double FORWARD_DISTANCE = inches * FORWARD_ADJUST;

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motor1.setTargetPosition(motor1.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor2.setTargetPosition(motor2.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor3.setTargetPosition(motor3.getCurrentPosition() + (int) FORWARD_DISTANCE);
        motor4.setTargetPosition(motor4.getCurrentPosition() + (int) FORWARD_DISTANCE);

        //Set Drive Power
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy() && !done) {
            if (rightDistanceSensor.getDistance(DistanceUnit.INCH) != DistanceSensor.distanceOutOfRange){
                if (rightDistanceSensor.getDistance(DistanceUnit.INCH) < distanceLimitInches) {
                    state359 = state.DRIVE;
                    done = true;
                }
            }
        }

        if (!done) {
            state359 = state.REST;
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    /**
     * Vuforia settings
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(
                tfodMonitorViewId);

        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, "Stone", "Skystone");
    }

    private void initVuforiaThingy() {
        /**
         * Webcam Initialization
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
    }

    public int lookForwardAndCheckBlue() {
        int position = 0;
        initVuforiaThingy();

        sleep(500);

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
                    if (updatedRecognitions.get(0).getLabel() == "Skystone"
                            || updatedRecognitions.get(1).getLabel() == "Skystone") {
                        int THRESHOLD = 200;
                        int skystonePosition;
                        int stonePosition;
                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel() == "Skystone") {
                                skystonePosition = (int) recognition.getLeft();

                                if (skystonePosition < THRESHOLD) {
                                    position = 1;
                                } else if (skystonePosition > THRESHOLD) {
                                    position = 2;
                                }

                            } else if (recognition.getLabel() == "Stone") {
                                stonePosition = (int) recognition.getLeft();
                                if (stonePosition < THRESHOLD) {
                                    position = 2;
                                } else if (stonePosition > THRESHOLD) {
                                    position = 1;
                                }
                            } else {
                                position = 3;
                            }
                        }
                    } else if (updatedRecognitions.get(0).getLabel() == "Skystone" && updatedRecognitions.get(1).getLabel() == "Skystone") {
                        position = 3;
                    } else {
                        position = 3;
                    }
                } else {
                    //This means that we have not detected a Skystone, so the Skystone is
                    // probably at position 3
                    position = 3;
                }
            }
        }
        return position;
    }

    public int lookForwardAndCheckRed() {
        int position = 0;
        initVuforiaThingy();

        sleep(500);

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
                    if (updatedRecognitions.get(0).getLabel() == "Skystone"
                            || updatedRecognitions.get(1).getLabel() == "Skystone") {
                        int THRESHOLD = 200;
                        int skystonePosition;
                        int stonePosition;
                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel() == "Skystone") {
                                skystonePosition = (int) recognition.getLeft();

                                if (skystonePosition < THRESHOLD) {
                                    position = 2;
                                } else if (skystonePosition > THRESHOLD) {
                                    position = 3;
                                }

                            } else if (recognition.getLabel() == "Stone") {
                                stonePosition = (int) recognition.getLeft();
                                if (stonePosition < THRESHOLD) {
                                    position = 3;
                                } else if (stonePosition > THRESHOLD) {
                                    position = 2;
                                }
                            } else {
                                position = 1;
                            }
                        }
                    } else if (updatedRecognitions.get(0).getLabel() == "Skystone" && updatedRecognitions.get(1).getLabel() == "Skystone") {
                        position = 1;
                    } else {
                        position = 1;
                    }
                } else {
                    //This means that we have not detected a Skystone, so the Skystone is
                    // probably at position 3
                    position = 1;
                }
            }
        }
        return position;
    }

    /**
     * imu settings
     */
    //TODO: Think of a way to make this initialize function work in an actual Opmode
    public void initialize(RobotPosition359 STARTING_POSITION) throws InterruptedException {
        initializeSettings();
        this.STARTING_POSITION = STARTING_POSITION;
        CURRENT_POSITION = STARTING_POSITION;
    }

    public double getAbsoluteCurrentHeading() {
        Orientation angles = my_imu.getAngularOrientation().toAxesReference(
                AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).toAngleUnit(AngleUnit.DEGREES);
        double absoluteHeading;
        if (angles.firstAngle <= 0) {
            absoluteHeading = -angles.firstAngle;
        } else {
            absoluteHeading = 360 - angles.firstAngle;
        }
        return absoluteHeading + STARTING_POSITION.getHeading();
    }

    enum state {

        DETECT, DRIVE, STOP, REST

    }
}
