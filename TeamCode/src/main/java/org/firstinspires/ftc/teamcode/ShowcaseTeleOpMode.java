/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Showcase OpMode")
public class ShowcaseTeleOpMode extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotorEx backLeftDrive = null;
    private DcMotorEx backRightDrive = null;
    private DcMotorEx frontLeftDrive = null;
    private DcMotorEx frontRightDrive = null;
    private MecanumDrive drive = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        backLeftDrive  = hardwareMap.get(DcMotorEx.class, "back_left_drive");
        backRightDrive = hardwareMap.get(DcMotorEx.class, "back_right_drive");
        frontLeftDrive  = hardwareMap.get(DcMotorEx.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotorEx.class, "front_right_drive");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        backLeftDrive.setDirection(DcMotorEx.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorEx.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotorEx.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotorEx.Direction.FORWARD);

        drive = new MecanumDrive(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double forward = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn  =  gamepad1.right_stick_x;

            drive.Drive(forward, strafe, turn);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight (%.2f)", frontLeftDrive.getPower(), frontRightDrive.getPower(), backLeftDrive.getPower(), backRightDrive.getPower());
            telemetry.update();
        }
    }
}
