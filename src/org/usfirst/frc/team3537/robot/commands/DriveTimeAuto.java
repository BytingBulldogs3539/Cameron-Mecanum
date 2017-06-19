package org.usfirst.frc.team3537.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveTimeAuto extends CommandGroup {

    public DriveTimeAuto() {
    	addSequential(new DriveAngle(30, 1));
    }
}
