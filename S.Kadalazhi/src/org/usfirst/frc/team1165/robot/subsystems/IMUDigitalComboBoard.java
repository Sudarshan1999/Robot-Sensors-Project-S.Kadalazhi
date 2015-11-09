package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.Sensors.ADXL345_I2C;
import org.usfirst.frc.team1165.robot.commands.CalibrateImuGyro;
import org.usfirst.frc.team1165.robot.commands.Reporter;
import org.usfirst.frc.team1165.robot.commands.ResetImuGyro;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.ITG3200;
import edu.wpi.first.wpilibj.ITG3200.GyroAxis;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;


/**
 *
 */
public class IMUDigitalComboBoard extends ReportableSubsystem
{
	private ADXL345_I2C accelerometer;
	private ITG3200 gyro;
	
	private GyroAxis xGyro;
	private GyroAxis yGyro;
	private GyroAxis zGyro;

	public IMUDigitalComboBoard(I2C.Port port, DigitalInput interrupt)
	{
		accelerometer = new ADXL345_I2C(port , Accelerometer.Range.k16G, true);
		gyro = new ITG3200(port, interrupt, false);
		
		xGyro = gyro.getXGyro();
		yGyro = gyro.getYGyro();
		zGyro = gyro.getZGyro();
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new Reporter(this));
		SmartDashboard.putData("Calibrate IMU Gyro",new CalibrateImuGyro(this));
		SmartDashboard.putData("Reset IMU Gyro", new ResetImuGyro(this));
	}

	@Override
	public void report()
	{
		if(null != accelerometer)
		{
			SmartDashboard.putNumber("IMU_Board_Accelerometer_X" , accelerometer.getX());
			SmartDashboard.putNumber("IMU_Board_Accelerometer_Y" , accelerometer.getY());
			SmartDashboard.putNumber("IMU_Board_Accelerometer_Z" , accelerometer.getZ());
		}
		if(null != gyro)
		{
			SmartDashboard.putNumber("IMU_Board_Gyroscope", gyro.getMeasuredSampleRate());
		}
	}
	public void resetGyro()
	{
		gyro.reset();
	}
	public void calibrateGyro()
	{
		gyro.calibrate(5);
	}
}
