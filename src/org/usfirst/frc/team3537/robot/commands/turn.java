package org.usfirst.frc.team3537.robot.commands;

import org.usfirst.frc.team3537.robot.Robot;
import org.usfirst.frc.team3537.robot.utilities.BulldogPIDOutput;
import org.usfirst.frc.team3537.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class turn extends PIDCommand {


	public turn(double angle ) 
    {
		
		super(SmartDashboard.getDouble("TurnP"), SmartDashboard.getDouble("TurnI"), SmartDashboard.getDouble("TurnD"));
		requires(Robot.driveTrainSub);
		this.getPIDController().setPID(SmartDashboard.getDouble("TurnP"), SmartDashboard.getDouble("TurnI"), SmartDashboard.getDouble("TurnD"));
		this.getPIDController().setSetpoint(angle);
		Robot.driveTrainSub.resetAngle();
		
	}

	// Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    	this.getPIDController().setPID(SmartDashboard.getDouble("TurnP"), SmartDashboard.getDouble("TurnI"), SmartDashboard.getDouble("TurnD"));
    	Robot.driveTrainSub.resetEncoders();
    	this.getPIDController().setAbsoluteTolerance(2);
    	this.getPIDController().enable();

    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return this.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	System.out.println("end");
    	this.getPIDController().disable();
    	Robot.driveTrainSub.driveMecanum(0, 0 ,0);
    }

    protected void interrupted() 
    {
    	System.out.println("interupt");
    	end();
    }

	@Override
	protected double returnPIDInput() 
	{
		System.out.println(Robot.driveTrainSub.getAngle());
		return Robot.driveTrainSub.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) 
	{
		
		Robot.driveTrainSub.driveMecanum(0, -output ,0);
	}
}
