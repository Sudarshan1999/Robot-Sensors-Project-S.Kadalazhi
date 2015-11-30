package org.usfirst.frc.team1165.util;

import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * A class to emulate a PID controlled device (such as a motor) using two LEDs.
 * 
 * @author jkoehring
 */
public class PidLeds implements PIDOutput, LiveWindowSendable
{
	private Pulser redPulser;
	private Pulser greenPulser;
	private double frequency;
	
	/**
	 * Construct using LEDs.
	 */
	public PidLeds(Led redLed, Led greenLed)
	{
		redPulser = new Pulser(redLed);
		greenPulser = new Pulser(greenLed);
	}
	
	/**
	 * Construct using channel numbers.
	 */
	public PidLeds(int redChannel, int greenChannel)
	{
		this(new Led(redChannel), new Led(greenChannel));
	}

	/**
	 * Construct using analog outputs.
	 */
	public PidLeds(AnalogOutput redLed, AnalogOutput greenLed)
	{
		this(new Led(redLed), new Led(greenLed));
	}

	/**
	 * Sets the PID output value. Acceptable range is -1 to +1, inclusive.
	 * Positive values pulse green LED at a rate of (10 * output) Hz.
	 * Negative values pulse red LED at a rate of (-10 * output) Hz.
	 * Non-pulsing LEDs are set idle.
	 */
	@Override
	public void pidWrite(double output)
	{
		this.frequency = output;
		SmartDashboard.putNumber("Frequency", output);
		redPulser.setFrequency(output >= 0 ? 0 : 10.0 * Math.abs(output));
		greenPulser.setFrequency(output <= 0 ? 0 : 10.0 * output);
	}
	
	public double get()
	{
		return frequency;
	}

	// The following are required by the LiveWindowSendable interface.

	private ITable table;
	private ITableListener tableListener;

	@Override
	public void initTable(ITable subtable)
	{
		this.table = subtable;
		updateTable();
	}

	@Override
	public ITable getTable()
	{
		return table;
	}
	
	@Override
	public String getSmartDashboardType()
	{
		return "Speed Controller";
	}

	@Override
	public void updateTable()
	{
		if (null != table)
		{
			table.putNumber("Value", get());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startLiveWindowMode()
	{
		tableListener = new ITableListener()
		{
			public void valueChanged(ITable itable, String key, Object value, boolean bln)
			{
				pidWrite(((Double) value).doubleValue());
			}
		};
		table.addTableListener("Value", tableListener, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopLiveWindowMode()
	{
		// TODO: Broken, should only remove the listener from "Value" only.
		table.removeTableListener(tableListener);
	}
}
