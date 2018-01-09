package org.usfirst.frc.team3537.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnAuto extends CommandGroup {

    public TurnAuto() {
    	addSequential(new turn(90));
    }
}
