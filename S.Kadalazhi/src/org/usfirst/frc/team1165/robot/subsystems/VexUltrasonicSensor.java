package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.Reporter;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

enum MEASURMENT_SYSTEM {INCHES,MM};
public class VexUltrasonicSensor extends ReportableSubsystem
{

	Ultrasonic ultrasonic = new Ultrasonic(RobotMap.VEX_ULTRASONIC_OUT_CHANNEL,RobotMap.VEX_ULTRASONIC_IN_CHANNEL);

	public void initDefaultCommand()
	{
		setDefaultCommand(new Reporter(this));
	}
	
	public double getDistance(MEASURMENT_SYSTEM system)
	{
		if(system==MEASURMENT_SYSTEM.INCHES)
			return ultrasonic.getRangeInches();
		else
			return ultrasonic.getRangeMM();
		
	}
	public void report()
	{
		SmartDashboard.putNumber("Distance to nearest object", ultrasonic.getRangeMM());
	}
}
