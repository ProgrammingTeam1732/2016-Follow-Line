
package org.usfirst.frc.team1732.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	// Motors
	private CANTalon left1; private CANTalon left2; private CANTalon left3;
	private CANTalon right1; private CANTalon right2; private CANTalon right3;
	int reverse = 1;
	boolean doFollow = false;
	double turnSpeed = 0;
	double moveSpeed = 0;
	double delay = 0;
	
	// Photo Sensor
	DigitalInput psRight = new DigitalInput(0);
	DigitalInput psLeft  = new DigitalInput(1);
	
    public void robotInit() {
    	left1 = new CANTalon(11); left2 = new CANTalon(21); left3 = new CANTalon(22);
		right1 = new CANTalon(14); right2 = new CANTalon(12); right3 = new CANTalon(13);
		SmartDashboard.putBoolean("Follow?", false);
		SmartDashboard.putNumber("Turn Speed", 0);
		SmartDashboard.putNumber("Move Speed", 0);
		SmartDashboard.putNumber("Delay", 0);
    }
    
    public void teleopPeriodic() {
    	turnSpeed = SmartDashboard.getNumber("Turn Speed");
		moveSpeed = SmartDashboard.getNumber("Move Speed");
		delay = SmartDashboard.getNumber("Delay");
		SmartDashboard.putBoolean("Photosensor Right",  psRight.get());
		SmartDashboard.putBoolean("Photosensor Left",  psLeft.get());
		if (SmartDashboard.getBoolean("Follow?")) {
			if(psRight.get() == true) {
    			setMotors(-moveSpeed, -moveSpeed);
    			reverse = reverse*-1;
    			Timer.delay(delay);
    		} else setMotors(reverse*turnSpeed, -reverse*turnSpeed);
			
        } else setMotors(0, 0);
    }
    
    private void setMotors(double l, double r) {
		left1.set(-l); left2.set(-l); left3.set(l); 
		right1.set(r); right2.set(r); right3.set(-r);
	}
    
}