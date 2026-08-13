package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Linear Slide OpMode")
//@Disabled
public class LinearSlideOpmode extends OpMode {

    static double GEAR_DIAMETER_MM = 38.2; //MM
    static double GEAR_CIRC_MM = GEAR_DIAMETER_MM * Math.PI;
    static double GEAR_CIRC_IN = DistanceUnit.INCH.fromMm(GEAR_CIRC_MM);
    static double ENCODER_TICKS = 103.8;

    public DcMotorEx SlideMotor;

    public ElapsedTime timer;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        timer = new ElapsedTime();
        SlideMotor = hardwareMap.get(DcMotorEx.class, "ls");
        SlideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        SlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
     */
    @Override
    public void init_loop() {
        telemetry.addData("Initialized", timer.time());
    }

    /*
     * Code to run ONCE when the driver hits START
     */
    @Override
    public void start() {
        SlideMotor.setTargetPosition(0);
        SlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SlideMotor.setPower(0.25);
        telemetry.addData("Initialization", "Motor initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Initialized", timer.time());
        telemetry.addData("CurrentTicks", SlideMotor.getCurrentPosition());
        telemetry.addData("RequestedTicks", SlideMotor.getTargetPosition());
        telemetry.addData("Power", SlideMotor.getPower());
        telemetry.addData("Current", SlideMotor.getCurrent(CurrentUnit.MILLIAMPS));
        telemetry.addData("RequestedDistance", Constants.DISTANCE);
        SlideMotor.setTargetPosition(distanceToTicks(Constants.DISTANCE));


//        for (int i = 0; i < 10; i++)
//        {
//            telemetry.addData("Distance", i);
//            SlideMotor.setTargetPosition(distanceToTicks(i));
//            while(SlideMotor.isBusy());
//        }
    }

    public int distanceToTicks(double distanceIN)
    {
        return (int) Math.round((distanceIN/GEAR_CIRC_IN) * ENCODER_TICKS);
    }
}