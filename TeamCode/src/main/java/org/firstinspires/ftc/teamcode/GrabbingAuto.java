package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous
public class GrabbingAuto extends LinearOpMode {

    DcMotor motor1, motor2, motor3, motor4;


    @Override
    public void runOpMode() {
        initialMotor();
        waitForStart();
        setModeRunToPosition();

        int OriginalPositions[] = new int[4];

        //get the original position
        OriginalPositions[0] = motor1.getCurrentPosition();
        OriginalPositions[1] = motor2.getCurrentPosition();
        OriginalPositions[2] = motor3.getCurrentPosition();
        OriginalPositions[3] = motor4.getCurrentPosition();


        //Go to target position

        while(motor1.isBusy()){
            telemetry.addLine("Running at speed:");
            telemetry.update();
        }

        stopRunning();

        //Grab the base

        //Go back to original position
        runToPosition(OriginalPositions, 0.5);

        while(motor1.isBusy()){
            telemetry.addLine("Going back at speed:");
            telemetry.update();
        }

        stopRunning();

    }

    public void initialMotor(){
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        motor4.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setModeRunToPosition(){
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void runToPosition(int position, double power){
        motor1.setTargetPosition(motor1.getCurrentPosition() + position);
        motor2.setTargetPosition(motor2.getCurrentPosition() + position);
        motor3.setTargetPosition(motor3.getCurrentPosition() + position);
        motor4.setTargetPosition(motor4.getCurrentPosition() + position);

        startRunning(power);
    }

    public void runToPosition(int[] position, double power){
        motor1.setTargetPosition(position[0]);
        motor2.setTargetPosition(position[1]);
        motor3.setTargetPosition(position[2]);
        motor4.setTargetPosition(position[3]);

        startRunning(power);

    }

    public void stopRunning(){
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void startRunning(double power){
        motor1.setPower(-power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(-power);
    }

}


