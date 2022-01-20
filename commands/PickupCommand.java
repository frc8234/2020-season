/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.*;

public class PickupCommand extends CommandBase {
  /**
   * Creates a new PickupCommands.
   */
  private PickupSubsystem pickupSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private GunRotationSubsystem rotationSubsystem;

  public static boolean running;
  public static boolean isManual = false;


  Timer m_timer;

  public PickupCommand(PickupSubsystem pickup, ShooterSubsystem shooter, GunRotationSubsystem rotation) {
    addRequirements(pickup);
    addRequirements(shooter);
    addRequirements(rotation);

    pickupSubsystem = pickup;
    shooterSubsystem = shooter;
    rotationSubsystem = rotation;

    m_timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    shooterSubsystem.Stop();

    running = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!isManual)
    {
        //if(rotationSubsystem.isUp())
          //rotationSubsystem.Up(); 
        pickupSubsystem.Move(1);
        shooterSubsystem.Foward();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    running = true;
    pickupSubsystem.Stop();
    shooterSubsystem.StopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(running)
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
