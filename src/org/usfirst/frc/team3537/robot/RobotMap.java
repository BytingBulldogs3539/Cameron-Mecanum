package org.usfirst.frc.team3537.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Controllers
	public static int controllerOnePort = 0;
	public static int controllerTwoPort = 1;
	
	//Buttons
	public static int buttonA = 1;
	public static int buttonB = 2;
	public static int buttonX = 3;
	public static int buttonY = 4;
	public static int buttonStart = 8;
	public static int buttonLS = 9;
	public static int buttonRS = 10;
	//Axis
	public static int X_AxisL = 0;
	public static int Y_AxisL = 1;

	public static int X_AxisR = 4;
	public static int Y_AxisR = 5;

	//Triggers
	public static int RIGHT_TRIGGER = 3;
	public static int LEFT_TRIGGER = 2;
	//Bumpers
	public static int bumperl = 5;
	public static int bumperr = 6;
	
	//Talons
	public static int FRtalon = 1;
	public static int BRtalon = 2;
	public static int FLtalon = 3;
	public static int BLtalon = 4;
	
}
