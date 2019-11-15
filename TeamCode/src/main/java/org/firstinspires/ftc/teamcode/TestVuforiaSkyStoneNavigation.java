package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@TeleOp
public class TestVuforiaSkyStoneNavigation extends LinearOpMode {

    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false  ;
    private static final String VUFORIA_KEY = "ARk+AQb/////AAABmV0RDGTiBEXluSpswNWIs+oLdAjW3AE6onoU0iyNfIiXnU0gt0DHT4m9FEzlJ+IoRun4NQglstqKn8rCzNvE7D+SS6FI2jWjhfD9UzfaedCHHCR+4VfLVFqAkUSIys2kX58N0D2E5GsxvFW0TdXI44RWZ1neUt8lbmK2uDTZfo+NtOSgqvSJEsrG0J6nLv9Cr+CAB6/X71URFpH2WtCJRH/F+6Y1Usy4b6uDdMoSKocv4B4j0DO3EuQuV1p/PCk3naRGYuKCdamnkcHMK/kK1yOoXtvRjFh374/3YtHkzFMCl7q3eHvh5h7X6kVCGXYheQurpk7JXScxZttBfiCi3GJQWnN6Ia6bIWx9aKe5WuPN";
    private VuforiaLocalizer vuforia;
    private boolean targetVisible = false;

    @Override public void runOpMode() {

        initVuforia();

        waitForStart();

        while (opModeIsActive()){

            Vuforia359();

        }
    }
    public void initVuforia() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
//
//        VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");
//        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
//        stoneTarget.setName("Stone Target");
//
//        List<VuforiaTrackable> allTrackables = new ArrayList<>();
//        allTrackables.addAll(targetsSkyStone);
    }

    public void Vuforia359(){
        initVuforia();
//
//        /**Note: To use the remote camera preview:
//           AFTER you hit Init on the Driver Station, use the "options menu" to select "Camera Stream"
//           Tap the preview window to receive a fresh image.
//         */
//        targetsSkyStone.activate();
//        while (!isStopRequested()) {
//            targetVisible = false;
//            for (VuforiaTrackable trackable : allTrackables) {
//                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
//                    telemetry.addData("Visible Target", trackable.getName());
//                    targetVisible = true;
//                }
//                break;
//            }
//        }
//
//        if (targetVisible) {
//
//        } else {
//
//        }
//        targetsSkyStone.deactivate();
    }
}