package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ReportAbsoluteEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AbsoluteEncoder extends Subsystem
{
	 AnalogInput analogInput =new AnalogInput(RobotMap.ABSOLUTE_ENCODER_PORT);
	 AnalogPotentiometer analogPotentiometer = new AnalogPotentiometer(analogInput,360,0);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new ReportAbsoluteEncoder());
	}
	
	public  void report()
	{
		SmartDashboard.putNumber("Voltage Value", analogInput.getVoltage());
		SmartDashboard.putNumber("ADC Value", analogInput.getValue());
		SmartDashboard.putNumber("Absolute Encoder Value",analogPotentiometer.get());
	}
}
