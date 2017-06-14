package org.usfirst.frc.team3537.robot.subsystems;

import org.usfirst.frc.team3537.robot.Robot;
import org.usfirst.frc.team3537.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class JoySub extends Subsystem 
{
	//Oper
	public double operRXAxis()
	{
		return Robot.oi.oper.getRawAxis(RobotMap.X_AxisR);
	}
	
	public double operRYAxis()
	{
		return Robot.oi.oper.getRawAxis(RobotMap.Y_AxisR);
	}
	
	public double operLXAxis()
	{
		return Robot.oi.oper.getRawAxis(RobotMap.X_AxisL);
	}
	
	public double operLYAxis()
	{
		return Robot.oi.oper.getRawAxis(RobotMap.Y_AxisL);
	}
	// Driver
	public double driverRXAxis()
	{
		return Robot.oi.driver.getRawAxis(RobotMap.X_AxisR);
	}
	
	public double driverRYAxis()
	{
		return Robot.oi.driver.getRawAxis(RobotMap.Y_AxisR);
	}
	
	public double driverLXAxis()
	{
		return Robot.oi.driver.getRawAxis(RobotMap.X_AxisL);
	}
	
	public double driverLYAxis()
	{
		return Robot.oi.driver.getRawAxis(RobotMap.Y_AxisL);
	}

    public void initDefaultCommand() 
    {

    }
}

