package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.util.Led;
import org.usfirst.frc.team1165.util.PidLeds;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * A PID subsystem demonstrator that controls two LEDs to
 * instruct the user how to turn the absolute encoder shaft
 * to reach the set point.
 * 
 * Flashing green LED indicates turn the shaft clockwise.
 * Flashing red LED indicates turn the shaft counterclockwise.
 * Both LEDs on solid indicates set point reached.
 */
public class PidDemo extends PIDSubsystem
{
	private final static double kP = 0.00;
	private final static double kI = 0.0;
	private final static double kD = 0;
	
	private final static double minPosition = 0.0;
	private final static double maxPosition = 360.0;
	
	private final static double minFrequency = -0.8;
	private final static double maxFrequency = 0.8;
	
	private final static double tolerance = 3;
	
	private PidLeds leds = new PidLeds(new Led(RobotMap.RED_LED_CHANNEL), new Led(RobotMap.GREEN_LED_CHANNEL));
	
	// Initialize your subsystem here
	public PidDemo()
	{
		super(kP, kI, kD);
		
        setAbsoluteTolerance(tolerance);
        setInputRange(minPosition, maxPosition);
        setOutputRange(minFrequency, maxFrequency);
        
        getPIDController().setContinuous();
        
		// Let's show everything on the LiveWindow
        LiveWindow.addActuator("Demo", "PID", getPIDController());
        LiveWindow.addSensor("Demo", "Pot", Robot.absoluteEncoder.getPot());
        LiveWindow.addSensor("Demo", "LEDs", leds);
	}

	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
	}

	@Override
	protected double returnPIDInput()
	{
		return Robot.absoluteEncoder.pidGet();
	}
	
	@Override
	protected void usePIDOutput(double output)
	{
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		leds.pidWrite(output);
	}
}
