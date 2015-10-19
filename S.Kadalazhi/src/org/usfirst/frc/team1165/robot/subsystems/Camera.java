package org.usfirst.frc.team1165.robot.subsystems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.usfirst.frc.team1165.robot.commands.Reporter;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera extends ReportableSubsystem implements Runnable
{
	public enum CameraMode {SUBSYSTEM,THREAD}
	
	private CameraMode cameraMode;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static int session;
	static Image frame;
	public boolean runMode;

	public Camera(CameraMode cameraMode)
	{
		this.cameraMode = cameraMode;
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		// the camera name (ex "cam0") can be found through the roborio web
		// interface
		session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);
		NIVision.IMAQdxStartAcquisition(session);

		if (cameraMode == CameraMode.THREAD)
			new Thread(this).start();
	}

	public void initDefaultCommand()
	{
		if (cameraMode == CameraMode.SUBSYSTEM)
			setDefaultCommand(new Reporter(this));
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				report();
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				SmartDashboard.putString(" File Not found", "");
			}
			Timer.delay(0.05);
		}
	}

	@Override
	public void report()
	{
		NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
		// the camera name (ex "cam0") can be found through the roborio web
		// interface
		// instantiate the command used for the autonomous period
		NIVision.IMAQdxGrab(session, frame, 1);
		CameraServer.getInstance().setImage(frame);
		
		//Extra Credit Stuff STARTS HERE
		// Make sure the directory that will hold the data files exists:
		new File("/home/lvuser/data").mkdirs();

		// Dump the supported video modes to a file:
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter("/home/lvuser/data/NIVision_VideoModes.txt");
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NIVision.dxEnumerateVideoModesResult result = NIVision.IMAQdxEnumerateVideoModes(session);
		pw.println("Current: \"" + result.videoModeArray[result.currentMode].Name + '"');
		pw.println();
		for (NIVision.IMAQdxEnumItem item : result.videoModeArray)
		{
			pw.println('"' + item.Name + '"');
		}
		pw.close();

		// Dump the supported vision attributes to a file:
		NIVision.IMAQdxWriteAttributes(session, "/home/lvuser/data/NIVision_Attributes.txt");
		
	}
}
