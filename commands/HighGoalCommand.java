/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GunRotationSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class HighGoalCommand extends CommandBase {

  //Create variables for needed subsystems
   ShooterSubsystem m_shooter;
   GunRotationSubsystem m_rotation;

   //Create a boolean to check if the command is running
   private boolean running;

   //Instantiate the limit switch
   private DigitalInput limitSwitch = new DigitalInput(0);

   //Create a timer
   Timer m_timer;

   public static boolean isManual;
   
   //private boolean isStopped;

  public HighGoalCommand(ShooterSubsystem shooter, GunRotationSubsystem rotation) {
    //Declare the dependencies
    addRequirements(shooter);
    addRequirements(rotation);

    //Set the subsystems to be the ones passed in
    m_shooter = shooter;
    m_rotation = rotation;

    //Create the timer 
    m_timer = new Timer();
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Move the conveyor forward
    m_shooter.Foward();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!isManual)
    {
      //Check if the limit switch is off
      if(!limitSwitch.get())
        //If it is, rotate up 
        m_rotation.Up();
      else
      {
        //Otherwise, stop the rotation and start the timer. 
        m_rotation.Stop();
        m_timer.start();
      }

      //If the timer is less than 6 seconds, it is running, and the limit switch is on
      if((m_timer.get() <= 6 && running)) /*&& limitSwitch.get())*/
        //Shoot the gun
        m_shooter.Shoot();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stop the shooter and conveyor
    m_shooter.Stop();
    m_shooter.StopConveyor();

    //Stop the gun's rotation
    m_rotation.Stop();

    //Stop the timer
    m_timer.stop();
    m_timer.reset();

    running = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //If it's running, return true, else return false
  return running;
  }

  public void setManual()
  {
    if(isManual)
      isManual = false;
    else
      isManual = true;
  }
}
