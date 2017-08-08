package org.usfirst.frc.team3537.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3537.robot.Robot;
import org.usfirst.frc.team3537.robot.RobotMap;

/**
 *
 */
public class DriveTrainCom extends Command 
{
	public DriveTrainCom() 
	{
		super("DriveTrainCom");
		requires(Robot.driveTrainSub);
	}

	@Override
	protected void initialize() 
	{
		Robot.driveTrainSub.setDrivePercentVBus();
	}
	
	@Override
	protected void execute()
	{
		Robot.driveTrainSub.driveMecanum(Robot.oi.driver.getRawAxis(RobotMap.X_AxisR), -Robot.oi.driver.getRawAxis(RobotMap.X_AxisL) ,Robot.oi.driver.getRawAxis(RobotMap.Y_AxisL));
	}
	
	@Override
	protected boolean isFinished() 
	{
		return false;
	}

	@Override
	protected void end() 
	{
		Robot.driveTrainSub.stop();
	}

	@Override
	protected void interrupted() 
	{
		end();
	}
}
