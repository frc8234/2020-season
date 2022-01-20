/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GunRotationSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class WheelCommand extends CommandBase {
   ShooterSubsystem m_shooter;
   GunRotationSubsystem m_rotation;

   public static boolean isManual = false;

   
  public WheelCommand(ShooterSubsystem shoot, GunRotationSubsystem rotation) {
    addRequirements(shoot);
    addRequirements(rotation);

    m_shooter = shoot;
    m_rotation = rotation;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
