package org.usfirst.frc.team1165.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	public  final static int ABSOLUTE_ENCODER_PORT = 0;
	public  final static int VEX_ULTRASONIC_IN_CHANNEL = 0;
	public  final static int VEX_ULTRASONIC_OUT_CHANNEL = 1;
	public  final static int BAUD_RATE = 9600;
	public  final static Port MAXBOTIX_SENSOR_PORT_NUMBER = SerialPort.Port.kOnboard;
	public  final static int MAXBOTIX_ANALOG_PORT_NUMBER = 3;
	public  final static SerialPort MaxBotix_Serial_Port = new SerialPort(BAUD_RATE,MAXBOTIX_SENSOR_PORT_NUMBER);
	public  final static AnalogInput MaxBotix_Analog_Port = new AnalogInput(MAXBOTIX_ANALOG_PORT_NUMBER);
}