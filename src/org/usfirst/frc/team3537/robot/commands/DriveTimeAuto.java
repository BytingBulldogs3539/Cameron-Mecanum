package org.usfirst.frc.team3537.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTimeAuto extends CommandGroup {

    public DriveTimeAuto() {
    	addSequential(new drivein(100));
    }
}
