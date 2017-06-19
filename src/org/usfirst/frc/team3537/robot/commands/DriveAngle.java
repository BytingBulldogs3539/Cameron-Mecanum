package org.usfirst.frc.team3537.robot.commands;

import org.usfirst.frc.team3537.robot.Robot;
import org.usfirst.frc.team3537.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveAngle extends PIDCommand 
{

    private double angle;
	private double timex;
	private double time = 0;
	private double y;
	private double x;
	private double turn = 0;

	public DriveAngle(double angle, double time) 
    {
		super("DriveAngle", SmartDashboard.getDouble("TurnP"), SmartDashboard.getDouble("TurnI"), SmartDashboard.getDouble("TurnD"));
		requires(Robot.driveTrainSub);
    	this.angle = angle;
    	this.timex = time*50;
    }

    protected void initialize() 
    {
    	this.x = Math.cos(Robot.driveTrainSub.d2r(this.angle));
    	this.y = Math.sin(Robot.driveTrainSub.d2r(this.angle));

    }

    protected void execute() 
    {
    	this.time = this.time + 1;
    	Robot.driveTrainSub.driveMecanum(this.x, this.y, this.turn);
    	    	
    }
    protected boolean isFinished() 
    {
        return this.time>= this.timex;
    }

    protected void end()
    {
    	Robot.driveTrainSub.driveMecanum(0, 0, 0);
    }

    protected void interrupted()
    {
    	end();
    }

	@Override
	protected double returnPIDInput() 
	{
		return Robot.driveTrainSub.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) 
	{
		this.turn = output;
	}
}
