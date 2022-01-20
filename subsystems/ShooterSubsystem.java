/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */

   //Create an array of Talons to shoot, only 2
   private WPI_VictorSPX[] shootMotors = new WPI_VictorSPX[2];
   //Create a Relay variable for the spike controlling the conveyor
   private Relay conveyorRelay = new Relay(1);
  
   //Instantiate the talons
  public ShooterSubsystem() 
  {
    shootMotors[0] = new WPI_VictorSPX(7);
    shootMotors[1] = new WPI_VictorSPX(4);
  }

  @Override
  public void periodic() {
    // This method will be called once per sceduler run
  }
  

  //Shoot the balls
  public void Shoot()
  {
    shootMotors[0].set(-0.8);
    shootMotors[1].set(0.8);
  }

  //Stop the shooter motors
  public void Stop()
  {
    shootMotors[0].set(0.2);
    shootMotors[1].set(-0.2);
  }

  //Reverse the shooter, in case of jams
  public void ReverseShooter()
  {
    shootMotors[0].set(0.3);
    shootMotors[1].set(-0.3);
  }

  //Stop the conveyor motors
  public void StopConveyor()
  {
    conveyorRelay.stopMotor();
  }

  //Set the motors to go forward
  public void Foward()
  {
    conveyorRelay.set(Value.kReverse);
  }

  //Set the motors to reverse
  public void Reverse()
  {
    conveyorRelay.set(Value.kForward);
  }

  //Spin the wheel forward
  public void SpinWheel()
  {
    shootMotors[0].set(-0.2);
  }

  //Spin the wheel backwards
  public void SpinWheelBackwards()
  {
    shootMotors[0].set(0.2);
  }
}
