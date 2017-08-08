package org.usfirst.frc.team3537.robot.subsystems;

import org.usfirst.frc.team3537.robot.Robot;
import org.usfirst.frc.team3537.robot.commands.DriveTrainCom;
import org.usfirst.frc.team3537.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveTrainSub extends Subsystem 
{
	private RobotDrive drive;
	private PDPJNI pdp; 
	private ADXRS450_Gyro gyro;
	private CANTalon FRTalon, BRTalon, FLTalon, BLTalon;
	public DriveTrainSub()
	{
		super("DriveTrainSub");
		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		FRTalon = new CANTalon(RobotMap.FRtalon);
		FLTalon = new CANTalon(RobotMap.FLtalon);
		BRTalon = new CANTalon(RobotMap.BRtalon);
		BLTalon = new CANTalon(RobotMap.BLtalon);
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
	public void driveMecanum(double xIn, double yIn, double rotation)
	{
		FLTalon.set( -xIn + yIn + rotation);
	    FRTalon.set( -xIn + yIn - rotation);
	    BLTalon.set( xIn + yIn + rotation);
	    BRTalon.set( xIn + yIn - rotation);
	}
	public void driveTank(double xspeed, double yspeed)
	{
		drive.arcadeDrive(yspeed, xspeed);
	}
	public double d2r(double degrees)
	{
		return degrees*(Math.PI/180);
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
	public void resetEncoders()
	{
		BLTalon.setPosition(0);
		FRTalon.setPosition(0);
	}
	public double FREncoderPos()
	{
		return FRTalon.getPosition();
	}
	public double FLEncoderPos()
	{
		return FLTalon.getPosition();
	}
	public double BREncoderPos()
	{
		return BRTalon.getPosition();
	}
	public double BLEncoderPos()
	{
		return BLTalon.getPosition();
	}
	public void setPositionalMode()
	{
		FRTalon.changeControlMode(TalonControlMode.Position);
		FLTalon.changeControlMode(TalonControlMode.Position);
		BRTalon.changeControlMode(TalonControlMode.Position);
		BLTalon.changeControlMode(TalonControlMode.Position);
	}
	@SuppressWarnings({ "deprecation", "static-access" })
	public void update()
	{
		//Temp
		SmartDashboard.putDouble("Front Right Talon Temp", FRTalon.getTemperature());
		SmartDashboard.putDouble("Front Left Talon Temp", FLTalon.getTemperature());
		SmartDashboard.putDouble("Back Right Talon Temp", BRTalon.getTemperature());
		SmartDashboard.putDouble("Back Left Talon Temp", BLTalon.getTemperature());
		//Temp Color
		
		//Encoder Pos
		SmartDashboard.putDouble("Front Right Talon ENC", FRTalon.getPosition());
		SmartDashboard.putDouble("Front Left Talon ENC", FLTalon.getPosition());
		SmartDashboard.putDouble("Back Right Talon ENC", BRTalon.getPosition());
		SmartDashboard.putDouble("Back Left Talon ENC", BLTalon.getPosition());
		//FaultUnderVolt
		//System.out.println(FRTalon.getStickyFaultUnderVoltage());
		
	}
	public void SmartInit()
	{
		SmartDashboard.putDouble("TurnP", RobotMap.TurnP);
		SmartDashboard.putDouble("TurnI", RobotMap.TurnI);
		SmartDashboard.putDouble("TurnD", RobotMap.TurnD);
		
		
		SmartDashboard.putDouble("DriveP", RobotMap.DriveP);
		SmartDashboard.putDouble("DriveI", RobotMap.DriveI);
		SmartDashboard.putDouble("DriveD", RobotMap.DriveD);
	}

}
