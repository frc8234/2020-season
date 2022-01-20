/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.DigitalInput;

public class GunRotationSubsystem extends SubsystemBase {
  /**
   * Creates a new GunRotationSubsystem.
   */

   private final WPI_VictorSPX m_motor;

  public GunRotationSubsystem() {
    m_motor = new WPI_VictorSPX(5);
    m_motor.setNeutralMode(NeutralMode.Brake);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    }

  //Minimum 28.03
  //Maximum 55.5
  //Set the motor to rotate the gun up
  public void Up()
  {
    m_motor.set(-0.6);

    
  }

  //Set the motor to rotate the gun down
  public void Down()
  {
    //if(!m_limitSwitch.get())
      m_motor.set(0.2);
    //else
  }

  //Stop the motor
  public void Stop()
  {
    m_motor.stopMotor();
  }

  // public boolean isUp()
  // {
  //   return m_limitSwitch.get();
  // }
}
