package org.usfirst.frc.team1165.util;

import edu.wpi.first.wpilibj.Timer;

/**
 * A class for pulsing Pulsee's.
 * 
 * @author jkoehring
 *
 */
public class Pulser
{
	private double period;
	private Thread thread;
	boolean isOn = false;
	
	public Pulser(Pulsee pulsee)
	{
		thread = new Thread(new Runnable()
		{
			public void run()
			{
				while (true)
				{
					if (0 == period)
					{
						pulsee.idle();
						isOn = false;
					}
					else
					{
						isOn = !isOn;
						if (isOn)
						{
							pulsee.on();
						}
						else
						{
							pulsee.off();
						}
					}
					
					// Wait for half of period or a short time if idle:
					Timer.delay(0 == period ? 0.050 : period / 2.0);
				}
			}
		});
		thread.setName("Pulser");
		thread.start();
	}
	
	/**
	 * Sets the frequency (in Hz) of the pulsing. The
	 * pulsee is turned on for half the period and off
	 * for half the period.
	 * 
	 * A frequency of 0 sets the pulsee idle.
	 */
	public void setFrequency(double frequency)
	{
		setPeriod(0 == frequency ? 0 : 1 / frequency);
	}
	
	/**
	 * Sets the period (in sec) of the pulsing. The
	 * pulsee is turned on for half the period and off
	 * for half the period.
	 * 
	 * A period of 0 sets the pulsee idle.
	 */
	public void setPeriod(double period)
	{
		this.period = period;
	}
}
