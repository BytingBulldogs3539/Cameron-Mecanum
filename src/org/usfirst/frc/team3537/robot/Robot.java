
package org.usfirst.frc.team3537.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3537.robot.commands.DriveTrainCom;
import org.usfirst.frc.team3537.robot.subsystems.DriveTrainSub;
import org.usfirst.frc.team3537.robot.subsystems.JoySub;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{

	
	public static OI oi;
	public static final JoySub joySub = new JoySub();
	public static final DriveTrainSub driveTrainSub = new DriveTrainSub();
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	@Override
	public void robotInit() 
	{
		oi = new OI();
		chooser.addDefault("Default Auton", null);
		SmartDashboard.putData("Auto mode", chooser);
		SmartInit();
		Update();
	}

	@Override
	public void disabledInit() 
	{
		Update();
	}

	@Override
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
		Update();
	}

	@Override
	public void autonomousInit() 
	{
		autonomousCommand = chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
		Update();
	}

	@Override
	public void autonomousPeriodic() 
	{
		Scheduler.getInstance().run();
		Update();
	}

	@Override
	public void teleopInit() 
	{
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Update();
	}

	@Override
	public void teleopPeriodic() 
	{
		Scheduler.getInstance().run();
		Update();
	}

	@Override
	public void testPeriodic() 
	{
		LiveWindow.run();
		Update();
	}
	public void Update()
	{
		Robot.driveTrainSub.update();
	}
	public void SmartInit()
	{
		Robot.driveTrainSub.SmartInit();
	}
}
