package org.usfirst.frc.team3537.robot.subsystems;

import org.usfirst.frc.team3537.robot.Robot;
import org.usfirst.frc.team3537.robot.commands.DriveTrainCom;
import org.usfirst.frc.team3537.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveTrainSub extends Subsystem 
{
	private RobotDrive drive;
	private ADXRS450_Gyro gyro;
	private CANTalon FRTalon, BRTalon, FLTalon, BLTalon;
	public DriveTrainSub()
	{
		super("DriveTrainSub");
		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		drive = new RobotDrive(FLTalon, BLTalon, FRTalon, BRTalon);
		setDrivePercentVBus();
		//Encoders
		FRTalon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		FLTalon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		BRTalon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		BLTalon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		//Voltage Limits
		FRTalon.configNominalOutputVoltage(0.0f, -0.0f);
		FRTalon.configPeakOutputVoltage(12.0f, -12.0f);
		FLTalon.configNominalOutputVoltage(0.0f, -0.0f);
		FLTalon.configPeakOutputVoltage(12.0f, -12.0f);
		BRTalon.configNominalOutputVoltage(0.0f, -0.0f);
		BRTalon.configPeakOutputVoltage(12.0f, -12.0f);
		BLTalon.configNominalOutputVoltage(0.0f, -0.0f);
		BLTalon.configPeakOutputVoltage(12.0f, -12.0f);
		FRTalon.configMaxOutputVoltage(12);
		FLTalon.configMaxOutputVoltage(12);
		BRTalon.configMaxOutputVoltage(12);
		BLTalon.configMaxOutputVoltage(12);
		//Current Limit
		FRTalon.setCurrentLimit(35);
		FLTalon.setCurrentLimit(35);
		BRTalon.setCurrentLimit(35);
		BLTalon.setCurrentLimit(35);
		FRTalon.EnableCurrentLimit(true);
		FLTalon.EnableCurrentLimit(true);
		BRTalon.EnableCurrentLimit(true);
		BLTalon.EnableCurrentLimit(true);
		//Brake Mode
		FRTalon.enableBrakeMode(true);
		FLTalon.enableBrakeMode(true);
		BRTalon.enableBrakeMode(true);
		BLTalon.enableBrakeMode(true);
		//Safety
		drive.setSafetyEnabled(false);

		FRTalon.setSafetyEnabled(false);
		FLTalon.setSafetyEnabled(false);
		BRTalon.setSafetyEnabled(false);
		BLTalon.setSafetyEnabled(false);

	}
	public void stop() 
	{
		setDrivePercentVBus();
		FRTalon.set(0);
		FLTalon.set(0);
		BRTalon.set(0);
		BLTalon.set(0);
		
	}
	public void resetAngle()
	{
		gyro.reset();
	}
	public void calibrateAngle()
	{
		gyro.calibrate();
	}
	public double getAngle()
	{
		return gyro.getAngle();
	}
	public void driveMecanum(double xspeed, double yspeed, double rotationspeed)
	{
		drive.mecanumDrive_Cartesian(xspeed, yspeed, rotationspeed, Robot.driveTrainSub.getAngle());
	}
	public void setDrivePercentVBus()
	{
		FRTalon.changeControlMode(TalonControlMode.PercentVbus);
		FLTalon.changeControlMode(TalonControlMode.PercentVbus);
		BRTalon.changeControlMode(TalonControlMode.PercentVbus);
		BLTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	public void initDefaultCommand() 
	{
		setDefaultCommand(new DriveTrainCom());
	}

}
