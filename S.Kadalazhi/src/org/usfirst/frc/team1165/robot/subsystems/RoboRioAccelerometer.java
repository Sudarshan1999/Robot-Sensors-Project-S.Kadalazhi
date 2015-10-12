
package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.commands.ReportAccelerometer;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RoboRioAccelerometer extends Subsystem 
{
	BuiltInAccelerometer accel = new BuiltInAccelerometer();
	private static double x;
	private static double y;
	private static double z;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() 
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new ReportAccelerometer());
	}
	public double getX()
	{
		return accel.getX();

	}
	
	public double getY()
	{
		return accel.getX();

	}
	
	public double getZ()
	{
		return accel.getX();

	}
}

