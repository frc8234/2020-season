/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PickupSubsystem extends SubsystemBase {
  /**
   * Creates a new PickupSubsystem.
   */

  //Create a new Talon motor controller
  private final WPI_VictorSPX pickupMotor;

  //Constructor to create a new Talon motor set to PWM ID 2
  public PickupSubsystem() {
    pickupMotor = new WPI_VictorSPX(6);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //Stop the motor
  public void Stop() {
    pickupMotor.stopMotor();
  }

  //Move the the ball pickup system motor
  public void Move(double speed)
  {
    //Check if the speed is less than 0.6
    if(speed < 0.02)
    {
      //Set the motor to be the speed passed in
      pickupMotor.set(speed);
    }
    //Else if it is greater or equal to 0.6...
    else if(speed >= 0.4)
    {
      //Set the pickup speed to its cap
      pickupMotor.set(0.4);
    }
    
    speed *= 0.45;
    pickupMotor.set(speed);
  }
}