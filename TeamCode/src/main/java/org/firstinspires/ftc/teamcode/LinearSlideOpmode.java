package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Linear Slide OpMode")
//@Disabled
public class LinearSlideOpmode extends OpMode {

    static double GEAR_DIAMETER_MM = 38.2; //MM
    static double GEAR_CIRC_MM = GEAR_DIAMETER_MM * Math.PI;
    static double GEAR_CIRC_IN = DistanceUnit.INCH.fromMm(GEAR_CIRC_MM);
    static double ENCODER_TICKS = 103.8;

    public DcMotor SlideMotor;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        SlideMotor = hardwareMap.get(DcMotor.class, "ls");
        SlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        SlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SlideMotor.setTargetPosition(0);
        telemetry.addData("", "Motor initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits START
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {
        for (int i = 0; i < 10; i++)
        {
            SlideMotor.setTargetPosition(i);
            while(SlideMotor.isBusy());
        }
    }

    public long distanceToTicks(double distanceIN)
    {
        return Math.round((distanceIN/GEAR_CIRC_IN) * ENCODER_TICKS);
    }
}