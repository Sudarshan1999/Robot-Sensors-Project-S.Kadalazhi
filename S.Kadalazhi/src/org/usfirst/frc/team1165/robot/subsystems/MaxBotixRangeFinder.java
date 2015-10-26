package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.Reporter;
import org.usfirst.frc.team1165.util.SampleRate;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MaxBotixRangeFinder extends ReportableSubsystem implements Runnable
{
	public enum SensorType {mb1200,mb1013}
	
	SerialPort maxBotixSensor = new SerialPort(RobotMap.BAUD_RATE,RobotMap.MAXBOTIX_SENSOR_PORT_NUMBER );
	private SensorType sensor;
	private SerialPort serialPort;
	private double serialRangeCm = -1;
	private double serialRangeFactorCm;
	private SampleRate serialSampleRate;
	
	private final double cmPerInch = 2.54;
	private AnalogPotentiometer analogPotentiometer;

	public MaxBotixRangeFinder(SensorType sensor, SerialPort serialPort,AnalogInput analogInput)
	{
		this.sensor=sensor;
		this.serialPort = serialPort;
		if (null != serialPort)
		{
			serialSampleRate = new SampleRate();
			serialSampleRate.start();
			serialRangeFactorCm = (SensorType.mb1013 == sensor)
				? 10		// 10 mm/cm
				: 1;		// 1 cm/cm
			new Thread(this).start();
		}
		
		if (null != analogInput)
		{
			// Construct analog potentiometer to measure full range of sensor in cm:
			double analogRangeCm = (SensorType.mb1013 == sensor)
				? 512		// 5120 mm = 512 cm
				: 1024;		// 1024 cm
			analogPotentiometer = new AnalogPotentiometer(analogInput, analogRangeCm, 0.0);
		}
			
	}
	
	public void initDefaultCommand()
	{
		setDefaultCommand(new Reporter(this));
	}

	public void report()
	{
		if (null != serialPort)
		{
			SmartDashboard.putNumber(sensor + " Serial (cm)", getSerialRangeCm());
			SmartDashboard.putNumber(sensor + " Serial (inch)", getSerialRangeInches());
			SmartDashboard.putNumber(sensor + " Serial Rate", serialSampleRate.getSampleRate());
		}
		
		if (null != analogPotentiometer)
		{
			SmartDashboard.putNumber(sensor + " Analog (cm)", getAnalogRangeCm());
			SmartDashboard.putNumber(sensor + " Analog (inch)", getAnalogRangeInches());
		}
	}
	public double getAnalogRangeCm()
	{
		return analogPotentiometer == null ? -1 : analogPotentiometer.get();
	}

	/**
	 * Reads and returns the current range (in inches) from the analog port.
	 * -1 is returned if range is not being obtained form the analog port.
	 */
	public double getAnalogRangeInches()
	{
		return analogPotentiometer == null ? -1 : analogPotentiometer.get() / cmPerInch;
	}

	/**
	 * Returns the current range (in cm) from the serial port.
	 * -1 is returned if range is not being obtained form the serial port.
	 */
	public double getSerialRangeCm()
	{
		return serialRangeCm;
	}
	
	/**
	 * Returns the current range (in inches) from the serial port.
	 * -1 is returned if range is not being obtained form the serial port.
	 */
	public double getSerialRangeInches()
	{
		return serialRangeCm == -1 ? -1 : serialRangeCm / cmPerInch;
	}

	@Override
	public void run()
	{
		serialPort.reset();
		
		// Start with an empty character buffer:
		byte[] data = new byte[0];
		int index = 0;
		
		int value = 0;
		boolean startCharacterFound = false;
		
		// Loop forever reading and processing characters from the serial port.
		while (true)
		{
			// If all previously read characters have been processed, read more characters:
			if (index >= data.length)
			{
				data = serialPort.read(serialPort.getBytesReceived());
				if (0 == data.length)
				{
					continue;
				}
				index = 0;
			}
			
			// Process a single character:
			byte c = data[index++];
			if (startCharacterFound)
			{
				// If we have seen a 'R', look for a CR or digit:
				if ('\r' == c)
				{
					serialRangeCm = value / serialRangeFactorCm;
					value = 0;
					startCharacterFound = false;
					serialSampleRate.addSample();
				}
				else
				{
					// Add current digit to value being accumulated:
					value = value * 10 + (c - '0');
				}
			}
			else
			{
				// See if character is the 'R' indicating the start of a value:
				startCharacterFound = 'R' == c;
			}	
		}
	}
}