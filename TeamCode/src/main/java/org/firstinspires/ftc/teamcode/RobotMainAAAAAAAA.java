package org.firstinspires.ftc.teamcode;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Main class for robot drive train (simple version)
 * without any heading or odometry
 *
 * written for faulty 359 chassis
 */

abstract class RobotMainAAAAAAAA extends LinearOpMode {
    //Robot & drive train measurements
    private static final double WHEEL_DIAMETER = (double) 4; //inches
    private static final double DRIVE_TRAIN_LENGTH = 9.375; //inches
    private static final double DRIVE_TRAIN_WIDTH = 15.5; //inches
    private static final double DRIVE_TRAIN_DIAGONAL = sqrt(
            DRIVE_TRAIN_WIDTH * DRIVE_TRAIN_WIDTH + DRIVE_TRAIN_LENGTH * DRIVE_TRAIN_LENGTH);
    private static final double GEAR_RATIO = (double) 1 / 1; //motor / final (drive)

    //Constants to adjust driving (based on testing, multiplied to drive distances)
    private static final double DRIVE_ADJUST = 1;
    private static final double STRAFE_ADJUST = 1;
    private static final double ROTATE_ADJUST = 1;

    //Encoder tick conversion constants:
    private final int TICK_BLUE = 1440; //Torquenado motors
    private final int TICK_REV = 1120; //Rev motors
    
    //Declare motors
    private DcMotor motor1, motor2, motor3, motor4;


    void initializeSettings() throws InterruptedException {
        //Get motors from configuration
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

        //Set zero power behavior
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Reverse motors
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Drive forward with set power and set inches
     */
    void forward(double power, double inches) {
        double dist = inches * DRIVE_ADJUST;
        driveTrain(motor1, motor2, motor3, motor4,
                dist, dist, dist, dist,
                power, -power, power, -power);
    }

    /**
     * Strafe right with set power and set inches
     */
    void strafeRight(double power, double inches) {
        double dist = inches * STRAFE_ADJUST;
        driveTrain(motor1, motor2, motor3, motor4,
                dist, dist, dist, dist,
                power, power, power, power);
    }

    /**
     * Turn left with set power and set degrees
     */
    void turnLeft(double power, double degrees) {

        double dist = (degrees / 360) * (DRIVE_TRAIN_DIAGONAL * PI) * ROTATE_ADJUST;

        driveTrain(motor1, motor2, motor3, motor4,
                dist, dist, dist, dist,
                power, power, -power, -power);
    }

    /**
     * Drive train main method
     */
    private void driveTrain(DcMotor m1, DcMotor m2, DcMotor m3, DcMotor m4,
            double m1dist, double m2dist, double m3dist, double m4dist,
            double m1power, double m2power, double m3power, double m4power) {

        //Distance to ticks constant helps calculate ticks from given distance
        final double DTT_CONST = TICK_REV / (WHEEL_DIAMETER * Math.PI * GEAR_RATIO);

        //positive distance is going forward

        //Reset Encoders
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        m1.setTargetPosition((int) (m1dist * DTT_CONST + 0.5));
        m2.setTargetPosition((int) (m2dist * DTT_CONST + 0.5));
        m3.setTargetPosition((int) (m3dist * DTT_CONST + 0.5));
        m4.setTargetPosition((int) (m4dist * DTT_CONST + 0.5));

        //Set Drive Power
        m1.setPower(m1power);
        m2.setPower(m2power);
        m3.setPower(m3power);
        m4.setPower(m4power);

        //Set to RUN_TO_POSITION mode
        m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (m1.isBusy() && m2.isBusy() && m3.isBusy() && m4.isBusy()) {
            //Wait Until Target Position is Reached
            //Update encoder data during driving
            telemetry.addData("position1", m1.getCurrentPosition());
            telemetry.addData("target1", m1.getTargetPosition());
            telemetry.addData("position2", m2.getCurrentPosition());
            telemetry.addData("target2", m2.getTargetPosition());
            telemetry.addData("position3", m3.getCurrentPosition());
            telemetry.addData("target3", m3.getTargetPosition());
            telemetry.addData("position4", m4.getCurrentPosition());
            telemetry.addData("target4", m4.getTargetPosition());
            telemetry.update();
        }

        //Stop and Change Mode back to Normal
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);

    }

//    void forward(double power, int rotations) {
//
//        //Reset Encoders
//        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        //Set Target Position
//        motor1.setTargetPosition(motor1.getCurrentPosition() + rotations * TICK_BLUE);
//        motor2.setTargetPosition(motor2.getCurrentPosition() + rotations * TICK_BLUE);
//        motor3.setTargetPosition(motor3.getCurrentPosition() + rotations * TICK_REV);
//        motor4.setTargetPosition(motor4.getCurrentPosition() + rotations * TICK_REV);
//
//        //Set Drive Power
//        motor1.setPower(power);
//        motor2.setPower(-power);
//        motor3.setPower(power);
//        motor4.setPower(-power);
//
//        //Set to RUN_TO_POSITION mode
//        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
//            //Wait Until Target Position is Reached
//            telemetry.addData("position1", motor1.getCurrentPosition());
//            telemetry.addData("target1", motor1.getTargetPosition());
//            telemetry.addData("position2", motor2.getCurrentPosition());
//            telemetry.addData("target2", motor2.getTargetPosition());
//            telemetry.addData("position3", motor3.getCurrentPosition());
//            telemetry.addData("target3", motor3.getTargetPosition());
//            telemetry.addData("position4", motor4.getCurrentPosition());
//            telemetry.addData("target4", motor4.getTargetPosition());
//            telemetry.update();
//        }
//
//        //Stop and Change Mode back to Normal
//        motor1.setPower(0);
//        motor2.setPower(0);
//        motor3.setPower(0);
//        motor4.setPower(0);
//    }

//    void strafeRight(double power, int rotation) {
//
//        power = -power;
//        //positive distance is going forward
//
//        //Reset Encoders
//        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        //Set Target Position
//        motor1.setTargetPosition(rotation * TICK_BLUE);
//        motor2.setTargetPosition(rotation * TICK_BLUE);
//        motor3.setTargetPosition(rotation * TICK_REV);
//        motor4.setTargetPosition(rotation * TICK_REV);
//
//        //Set Drive Power
//        motor1.setPower(power);
//        motor2.setPower(power);
//        motor3.setPower(power);
//        motor4.setPower(power);
//
//        //Set to RUN_TO_POSITION mode
//        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
//            //Wait Until Target Position is Reached
//        }
//
//        //Stop and Change Mode back to Normal
//        motor1.setPower(0);
//        motor2.setPower(0);
//        motor3.setPower(0);
//        motor4.setPower(0);
//    }

//    void turnLeft(double power, int rotations) {
//
//        //positive distance is going forward
//
//        //Reset Encoders
//        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        //Set Target Position
//        motor1.setTargetPosition(rotations * TICK_BLUE);
//        motor2.setTargetPosition(rotations * TICK_BLUE);
//        motor3.setTargetPosition(rotations * TICK_REV);
//        motor4.setTargetPosition(rotations * TICK_REV);
//
//        //Set Drive Power
//        motor1.setPower(power);
//        motor2.setPower(power);
//        motor3.setPower(-power);
//        motor4.setPower(-power);
//
//        //Set to RUN_TO_POSITION mode
//        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
//            //Wait Until Target Position is Reached
//        }
//
//        //Stop and Change Mode back to Normal
//        motor1.setPower(0);
//        motor2.setPower(0);
//        motor3.setPower(0);
//        motor4.setPower(0);
//    }

}
