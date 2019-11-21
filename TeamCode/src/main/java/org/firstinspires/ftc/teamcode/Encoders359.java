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
        motorb.setPower(0.4 * power);
        motorc.setPower(power);
        motord.setPower(-power);

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

    public static void Turn(DcMotor motora, DcMotor motorb, DcMotor motorc, DcMotor motord, double power, int distance) {

        //positive distance is turning left

        //Reset Encoders
        motora.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorc.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motord.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motora.setTargetPosition(distance);
        motorb.setTargetPosition(-distance);
        motorc.setTargetPosition(-distance);
        motord.setTargetPosition(distance);

        //Set Drive Power
        motora.setPower(-power);
        motorb.setPower(0.4 * power);
        motorc.setPower(power);
        motord.setPower(-power);

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

    public static void Strafe(DcMotor motora, DcMotor motorb, DcMotor motorc, DcMotor motord, double power, int distance) {

        //positive distance is turning left

        //Reset Encoders
        motora.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorc.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motord.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motora.setTargetPosition(distance);
        motorb.setTargetPosition(-distance);
        motorc.setTargetPosition(distance);
        motord.setTargetPosition(-distance);

        //Set Drive Power
        motora.setPower(-power);
        motorb.setPower(0.4 * power);
        motorc.setPower(power);
        motord.setPower(-power);

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
}
