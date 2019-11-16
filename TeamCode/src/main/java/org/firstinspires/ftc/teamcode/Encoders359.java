package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class Encoders359 extends LinearOpMode{

    public static void Forward(DcMotor motora, DcMotor motorb, DcMotor motorc, DcMotor motord, double power, int distance) {

        //positive distance is going forward

        //Reset Encoders
        motora.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorc.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motord.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motora.setTargetPosition(distance);
        motorb.setTargetPosition(distance);
        motorc.setTargetPosition(distance);
        motord.setTargetPosition(distance);

        //Set Drive Power
        motora.setPower(-power);
        motorb.setPower(power);
        motorc.setPower(power);
        motord.setPower(-.5 * power);

        //Set to RUN_TO_POSITION mode
        motora.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorc.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motord.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motora.isBusy() && motorb.isBusy() && motorc.isBusy() && motord.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motora.setPower(0);
        motorb.setPower(0);
        motorc.setPower(0);
        motord.setPower(0);
    }

    public static void forwardWithOneMotor(DcMotor motor, double power, int distance) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(distance);
        motor.setPower(-power);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (motor.isBusy()) {

        }
        motor.setPower(0);
    }

    public static void Turn(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, int distance) {

        //positive distance is turning left

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motor1.setTargetPosition(distance);
        motor2.setTargetPosition(-distance);
        motor3.setTargetPosition(-distance);
        motor4.setTargetPosition(distance);

        //Set Drive Power
        motor1.setPower(-power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(-.5 * power);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public static void Drift(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, int distance) {

        //positive distance is drifting left

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motor1.setTargetPosition(distance);
        motor2.setTargetPosition(-distance);
        motor3.setTargetPosition(distance);
        motor4.setTargetPosition(-distance);

        //Set Drive Power
        motor1.setPower(-power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(-.5 * power);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }
}
