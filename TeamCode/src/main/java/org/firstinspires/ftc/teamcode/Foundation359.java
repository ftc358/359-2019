package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Foundation359 extends LinearOpMode {

    DcMotor motor1, motor2, motor3, motor4;
    DcMotor motor1i, motor2i;

    public static void Forward(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, long timer) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);
        Thread.sleep(timer);

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public static void Turn(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, long timer) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(-power);
        motor3.setPower(-power);
        motor4.setPower(power);
        Thread.sleep(timer);

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public static void Slide(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, long timer) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(-power);
        motor3.setPower(power);
        motor4.setPower(-power);
        Thread.sleep(timer);

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void runOpMode() throws InterruptedException{

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor1i = hardwareMap.dcMotor.get("motor1i");
        motor2i = hardwareMap.dcMotor.get("motor2i");

        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()){
            Thread.sleep(23000);
            Forward(motor1,motor2,motor3,motor4,0.5,3000);
            Slide(motor1,motor2,motor3,motor4,0.5,1500);
            Forward(motor1,motor2,motor3,motor4,-0.5,2000);
            break;
        }
    }
}
