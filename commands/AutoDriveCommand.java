/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

@SuppressWarnings("unused")
public class AutoDriveCommand extends CommandBase {
   //Create all the subsystems needed
   private final DriveSubsystem m_drive;

  private final Timer m_timer = new Timer();

  public AutoDriveCommand(DriveSubsystem drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);

    String alliance = DriverStation.getInstance().getAlliance().toString();
    String gameMessage = DriverStation.getInstance().getGameSpecificMessage();

    System.out.println(alliance);

    m_drive = drive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_timer.get() < 3)
    {
      //m_drive.AutoDrive(0.5, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return m_timer.get() > 15;
  }
}
