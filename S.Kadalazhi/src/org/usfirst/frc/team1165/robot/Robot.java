package org.usfirst.frc.team1165.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team1165.robot.subsystems.RoboRioAccelerometer;
import org.usfirst.frc.team1165.robot.subsystems.MaxBotixRangeFinder;
import org.usfirst.frc.team1165.robot.subsystems.MaxBotixRangeFinder.SensorType;
import org.usfirst.frc.team1165.robot.subsystems.AbsoluteEncoder;
import com.ni.vision.NIVision.Image;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{

	public static final RoboRioAccelerometer roboRioAccelerometer = new RoboRioAccelerometer();
	//public static final Camera camera = new Camera(CameraMode.THREAD);
	public static final AbsoluteEncoder absoluteEncoder = new AbsoluteEncoder();
	//public static final VexUltrasonicSensor vexUtrasonicSensor= new VexUltrasonicSensor();
	public static MaxBotixRangeFinder maxBotixRangeFinder = new MaxBotixRangeFinder(SensorType.mb1200,RobotMap.MaxBotix_Serial_Port,RobotMap.MaxBotix_Analog_Port);
	public static OI oi;
	//For camera
	public static int session;
	static Image frame;
	
	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		oi = new OI();
	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	public void autonomousInit()
	{
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit()
	{

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
