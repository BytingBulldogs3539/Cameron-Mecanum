package org.usfirst.frc.team3537.robot.commands;

import org.usfirst.frc.team3537.robot.Robot;


import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class drivein extends PIDCommand {


    private int inches;
	private PIDController anglePID;
    private org.usfirst.frc.team3537.robot.utilities.BulldogPIDOutput pidOutput = new org.usfirst.frc.team3537.robot.utilities.BulldogPIDOutput();
	private double output ;
	private final PIDSource angle_output_source = new PIDSource()
	{
		public void setPIDSourceType(PIDSourceType pidSource)
		{
		}

		public PIDSourceType getPIDSourceType()
		{
			return PIDSourceType.kDisplacement;
		}

		public double pidGet()
		{
			return Robot.driveTrainSub.getAngle();
		}
	};
    
	public drivein(double in) 
    {
		super(SmartDashboard.getDouble("DriveP"), SmartDashboard.getDouble("DriveI"), SmartDashboard.getDouble("DriveD"));
		this.getPIDController().setPID(SmartDashboard.getDouble("DriveP"), SmartDashboard.getDouble("DriveI"), SmartDashboard.getDouble("DriveD"));
		this.getPIDController().setSetpoint(in);
	}
	public double returnAnglePIDInput()
	{
		return Robot.driveTrainSub.getAngle();
	}

	// Called just before this Command runs the first time
    protected void initialize() 
    {
    	System.out.println("Hello");
    	Robot.driveTrainSub.resetEncoders();
    	this.getPIDController().setAbsoluteTolerance(.2);
    	this.getPIDController().enable();
    	
		anglePID = new PIDController(org.usfirst.frc.team3537.robot.RobotMap.TurnP, org.usfirst.frc.team3537.robot.RobotMap.TurnI, org.usfirst.frc.team3537.robot.RobotMap.TurnD, angle_output_source,
				pidOutput);
		anglePID.setSetpoint(0);

		anglePID.setAbsoluteTolerance(1);
		anglePID.setToleranceBuffer(15);
		pidOutput.Reset();
		anglePID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.driveTrainSub.driveMecanum(0, pidOutput.Get() ,output);
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

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	System.out.println("interupt");
    	end();
    }

	@Override
	protected double returnPIDInput() 
	{
		return Robot.driveTrainSub.BLEncoderPos()*12.566;
	}

	@Override
	protected void usePIDOutput(double output) 
	{
		this.output = output;
		
	}
}
