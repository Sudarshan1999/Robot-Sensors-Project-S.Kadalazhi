package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ReportAbsoluteEncoder;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AbsoluteEncoder extends Subsystem
{
	 static AnalogPotentiometer analogPotentiometer = new AnalogPotentiometer(RobotMap.ABSOLUTE_ENCODER_PORT,360,30);
	 
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new ReportAbsoluteEncoder());
	}
	
	public  void report()
	{
		SmartDashboard.putNumber("Absolute Encoder Value",analogPotentiometer.get());
	}
}
