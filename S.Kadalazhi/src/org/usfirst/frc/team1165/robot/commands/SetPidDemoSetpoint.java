package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetPidDemoSetpoint extends Command
{
	private double setPoint;
	private String setPointKey;
	public SetPidDemoSetpoint(double setPoint)
	{
		this.setPoint = setPoint;
	}
	public SetPidDemoSetpoint(String setPointkey)
	{
		this.setPointKey = setPointkey;
	}
	// Called just before this Command runs the first time
	protected void initialize()
	{
		if(null != setPointKey)
		{
			setPoint = SmartDashboard.getNumber(setPointKey);
		}
		requires(Robot.pidDemo);
		Robot.pidDemo.disable();
		Robot.pidDemo.setSetpoint(setPoint);
		Robot.pidDemo.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		if (Robot.absoluteEncoder.pidGet() - 5 > setPoint || Robot.absoluteEncoder.pidGet() + 5 < setPoint)
		{
			Robot.pidDemo.onTarget();
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
