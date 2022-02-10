package org.firstinspires.ftc.teamcode.drive.tempOpModeAndHardware.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.tempOpModeAndHardware.Hardware.mecanumHardware;

import java.lang.*;


@TeleOp(name="mecanumDriveTester", group="Iterative Opmode")

public class mecanumDriveTester extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    mecanumHardware mecanumHardware = new mecanumHardware();
    boolean mechanisms = true;
    double deadzone = .3;
    double maxSpeed = .8;
    double x = 0;
    double y = 0;
    double rx = 0;



    @Override
    public void init() {
        mecanumHardware.init(hardwareMap, mechanisms, false);
        telemetry.addData("Status", "Initialized");
        mecanumHardware.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        mecanumHardware.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        mecanumHardware.leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        mecanumHardware.rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }


    @Override
    public void init_loop() {
    }


    @Override
    public void start() {
    }


    @Override
    public void loop() {

        double frontLeftPower;
        double backLeftPower;
        double frontRightPower;
        double backRightPower;

        if (Math.abs(-gamepad1.left_stick_y) > deadzone) {
            x = -gamepad1.left_stick_y * .7;
        } else {
            x = 0;
        }
        if (Math.abs(gamepad1.left_stick_x) > deadzone) {
            y = gamepad1.left_stick_x * .65;
        } else {
            y = 0;
        }
        if (Math.abs(gamepad1.right_stick_x) > deadzone) {
            rx = gamepad1.right_stick_x * .85;
        } else {
            rx = 0;
        }

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        if (mechanisms) {
            frontLeftPower = (y + x + rx) / denominator;
            backLeftPower = (y - x - rx) / denominator;
            frontRightPower = (y - x + rx) / denominator;
            backRightPower = (y + x - rx) / denominator;
        } else {
            frontLeftPower = (y + x - rx) / denominator;
            backLeftPower = (y - x + rx) / denominator;
            frontRightPower = (y - x - rx) / denominator;
            backRightPower = (y + x - rx) / denominator;
        }

        mecanumHardware.leftFront.setPower(frontLeftPower * maxSpeed);
        mecanumHardware.leftRear.setPower(backLeftPower * maxSpeed);
        mecanumHardware.rightFront.setPower(frontRightPower * maxSpeed);
        mecanumHardware.rightRear.setPower(backRightPower * maxSpeed);
    }

    @Override
    public void stop() {
    }

}
