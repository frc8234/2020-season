/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */

   //Declare Motor Controllers
   private WPI_VictorSPX leftMaster;
   private WPI_VictorSPX rightMaster;
   private WPI_VictorSPX leftFollower;
   private WPI_VictorSPX rightFollower;

   //Declare Speed Controller Groups
   private SpeedControllerGroup left;
   private SpeedControllerGroup right;

   //Declare Differential Drive
   private DifferentialDrive m_drive;

  public DriveSubsystem() {
    //Instantiate left motor controller and right motor controller
    leftMaster = new WPI_VictorSPX(0);
    leftFollower = new WPI_VictorSPX(1);

    rightMaster = new WPI_VictorSPX(2);
    rightFollower = new WPI_VictorSPX(3);

    //Set motors to factory default settings
    leftMaster.configFactoryDefault();
    leftFollower.configFactoryDefault();
    rightMaster.configFactoryDefault();
    rightFollower.configFactoryDefault();

    //Set neutral mode to coast
    leftMaster.setNeutralMode(NeutralMode.Coast);
    rightMaster.setNeutralMode(NeutralMode.Coast);
    rightFollower.setNeutralMode(NeutralMode.Coast);
    leftFollower.setNeutralMode(NeutralMode.Coast);

    //Instantiate speed controller groups
    left = new SpeedControllerGroup(leftMaster, leftFollower);
    right = new SpeedControllerGroup(rightMaster, rightFollower);

    //Instantiate the new differential drive
    m_drive = new DifferentialDrive(left, right);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //Drive function, passes in x and y values for speed
  public void drive(double x, double y)
  {

    //Reverse input
    x *= -1;

    //Make the turning less jumpy
    y *= 0.7;

    //Use arcade drive to drive the bot
    m_drive.arcadeDrive(x, y);
  }

  public void AutoDrive(double x, double y)
  {
    x *= -1;
    m_drive.arcadeDrive(x, y);
  }
}
