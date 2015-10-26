package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.Reporter;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AbsoluteEncoder extends ReportableSubsystem
{
	 AnalogInput analogInput =new AnalogInput(RobotMap.ABSOLUTE_ENCODER_PORT);
	 AnalogPotentiometer analogPotentiometer = new AnalogPotentiometer(analogInput,360,0);

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new Reporter(this));
	}
	
	public  void report()
	{
		SmartDashboard.putNumber("Voltage Value", analogInput.getVoltage());
		SmartDashboard.putNumber("ADC Value", analogInput.getValue());
		SmartDashboard.putNumber("Absolute Encoder Value",analogPotentiometer.get());
		SmartDashboard.putNumber("5 Volt Rail", ControllerPower.getVoltage5V());
	}
}
