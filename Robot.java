package org.usfirst.frc.team972.robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick; 

class Robot extends IterativeRobot {
	WPI_TalonSRX leftM1 = new WPI_TalonSRX(1);
	WPI_TalonSRX leftM2 = new WPI_TalonSRX(1);
	WPI_TalonSRX rightM1 = new WPI_TalonSRX(1);
	WPI_TalonSRX rightM2 = new WPI_TalonSRX(1);
	WPI_TalonSRX rolleygrabber = new WPI_TalonSRX(1);
	
	static Joystick stick = new Joystick(1);
	
	ArcadeDrive arcadeDrive;
	
	int toggle = 1;
	double power = 0.8;
	boolean pressedBefore = false;
	
	@Override
	public void robotInit() {
		System.out.println("BBQ is starting up");
		
		arcadeDrive = new ArcadeDrive(stick, leftM1, leftM2, rightM1, rightM2);
	}
	
	@Override
	public void autonomousInit() {
		
	}

	@Override
	public void autonomousPeriodic() {
		
	}
	
	@Override
	public void teleopInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		
		arcadeDrive.loop();
		
		//rollygrabber button
		if (stick.getRawButtonPressed(1)) {
			if (!pressedBefore) {
				if (toggle<0) {
					toggle=1;
				}
				else {
					toggle=-1;
				}
				pressedBefore = true;
				}
				
		}
		else {
			pressedBefore = false;
		}
		
		rolleygrabber.set(ControlMode.PercentOutput, toggle*power);
		
		//arcade drive
		
		
	}

}
