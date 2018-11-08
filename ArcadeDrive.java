package org.usfirst.frc.team972.robot;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; 

public class ArcadeDrive {
	 Joystick stick;
	 WPI_TalonSRX leftm1;
	 WPI_TalonSRX rightm1;
	 WPI_TalonSRX leftm2;
	 WPI_TalonSRX rightm2;
	 
	 double x;
	 double y;
	 double turnAngle;
	 boolean left = false;
	 boolean upper = false;
	 double lMag;
	 double rMag;
	 double speedMult;
	 
	public ArcadeDrive(Joystick stick, WPI_TalonSRX leftm1, WPI_TalonSRX rightm1, WPI_TalonSRX leftm2, WPI_TalonSRX rightm2) {
		this.stick=stick;
		this.rightm1=rightm1;
		this.leftm2=leftm2;
		this.leftm1=leftm1;
		this.rightm2=rightm2;
	}
	
	public void loop() {
		x = stick.getRawAxis(4);
		y = stick.getRawAxis(5);
		speedMult = stick.getRawAxis(1);
		
		if (x<0) {
			left=true;
		}
		else {
			left=false;
		}
		if (y>0) {
			upper=true;
		}
		else {
			upper=false;
		}
		
		if (x == 0 && y == 0) {
			leftm1.set(0.0);
			leftm2.set(0.0);
			rightm1.set(0.0);
			rightm2.set(0.0);
		} else {
			if (!upper) {
				if (!left) {
					leftm2.set(0.3 * speedMult);
					leftm1.set(0.3 * speedMult);
					rightm1.set(0.3 * speedMult);
					rightm2.set(0.3 * speedMult);
				} 
				else {
					leftm2.set(-0.3 * speedMult);
					leftm1.set(-0.3 * speedMult);
					rightm1.set(-0.3 * speedMult);
					rightm2.set(-0.3 * speedMult);
				}	
			} else {
				if (!left) {
					if (x == 0) {
						leftm1.set(0.3  * speedMult);
						leftm2.set(0.3  * speedMult);
						rightm1.set(-0.3  * speedMult);
						rightm2.set(-0.3  * speedMult);
						
					} else {
						tan();

						rMag = 4 * (Math.PI - turnAngle) * ((13.25 * Math.PI)/(2*(Math.PI - turnAngle)) - 13.25);
						lMag = 4 * (Math.PI - turnAngle) * ((13.25 * Math.PI)/(2*(Math.PI - turnAngle)) + 13.25);

						leftm1.set(0.3 * lMag/(13.25 * (2 * Math.PI + 1)) * speedMult);
						leftm2.set(0.3 * lMag/(13.25 * (2 * Math.PI + 1)) * speedMult);
						rightm1.set(-0.3 * rMag/(13.25 * (2 * Math.PI - 1)) * speedMult);
						rightm2.set(-0.3 * rMag/(13.25 * (2 * Math.PI - 1)) * speedMult);
						
					}
				} 
				else {
					if (x == 0) {
						
						leftm1.set(0.3 * speedMult);
						leftm2.set(0.3 * speedMult);
						rightm1.set(-0.3 * speedMult);
						rightm2.set(-0.3 * speedMult);
						
					} else {
						tan();

						lMag = 4 * (Math.PI - turnAngle) * ((13.25 * Math.PI)/(2*(Math.PI - turnAngle)) - 13.25);
						rMag = 4 * (Math.PI - turnAngle) * ((13.25 * Math.PI)/(2*(Math.PI - turnAngle)) + 13.25);

						leftm1.set(0.3 * lMag/(13.25 * (2*Math.PI - 1)) * speedMult);
						leftm2.set(0.3 * lMag/(13.25 * (2*Math.PI - 1)) * speedMult);
						rightm1.set(-0.3 * rMag/(13.25 * (2*Math.PI + 1)) * speedMult);
						rightm2.set(-0.3 * rMag/(13.25 * (2*Math.PI + 1)) * speedMult);
						
					}
				}	
			}
		}
		
		turnAngle = 0;
	}
	
	public void tan() {
		if (x == 0) {
			turnAngle = Math.PI/2;
		} else {
			turnAngle = Math.atan(Math.abs(y/x)) + Math.PI/2;
		}
		
	}

}
