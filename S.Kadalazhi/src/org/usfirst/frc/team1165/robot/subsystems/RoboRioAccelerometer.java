
package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.commands.Reporter;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RoboRioAccelerometer extends ReportableSubsystem 
{
	BuiltInAccelerometer accel = new BuiltInAccelerometer();
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() 
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new Reporter(this));
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
	public void report()
	{
		SmartDashboard.putNumber("Accelerometer X",  accel.getX());
		SmartDashboard.putNumber("Accelerometer Y",  accel.getY());
		SmartDashboard.putNumber("Accelerometer Z",  accel.getZ());
	}
}

