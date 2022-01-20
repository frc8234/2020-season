/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Talon;

public class ClimbSubsystem extends SubsystemBase {
  /**
   * Creates a new ClimbSubsystem.
   */

   //Placeholder motor
  private final Talon m_motor = new Talon(0);
  
  public ClimbSubsystem() {

  }

  @Override
  public void periodic()
  {
    // This method will be called once per scheduler run
  }

  public void Up()
  {
    m_motor.set(0.3);
  }

  public void Down()
  {
    m_motor.set(-0.3);
  }

  public void Stop()
  {
    m_motor.stopMotor();
  }
}
