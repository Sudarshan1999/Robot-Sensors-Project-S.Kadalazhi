
package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.commands.ReportAccelerometer;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Accelerometer extends Subsystem 
{

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() 
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new ReportAccelerometer());
	}
}

