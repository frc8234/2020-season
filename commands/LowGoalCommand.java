/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GunRotationSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class LowGoalCommand extends CommandBase {
  /**
   * Creates a new LowGoalCommand.
   */

  ShooterSubsystem m_shooter;
  GunRotationSubsystem m_rotate;

  private boolean running;

  Timer m_timer;

  public static boolean isManual = false;

  public LowGoalCommand(ShooterSubsystem shooter, GunRotationSubsystem rotate) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    addRequirements(rotate);

    m_shooter = shooter;
    m_rotate = rotate;

    m_timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if(!isManual)
    // {
    //   if(m_timer.get() <= 3)
    //     m_rotate.Down();
    //   else
    //     m_rotate.Stop();
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_timer.stop();
    m_timer.reset();
    
    m_rotate.Stop();

    m_shooter.Stop();
    m_shooter.StopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!running)
      return true;
    return false;
  }

  public void setManual()
  {
    if(isManual)
      isManual = false;
    else
      isManual = true;
  }
}
