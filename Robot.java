/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
//import java.util.logging.LogManager;

//import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final Timer m_timer = new Timer();

  //ADXRS450_Gyro m_gyro = new ADXRS450_Gyro(port);

  //private final CAN m_can = new CAN(0, 5, 2);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    //m_gyro.calibrate();
    // if(CameraServer.getInstance().startAutomaticCapture(0) != null)
    //   System.out.println("camera work");
    // else
    //   System.out.println("no work");

    //UsbCamera a = CameraServer.getInstance().startAutomaticCapture(0);
    
    // if(a.isConnected())
    // {
    //   System.out.println("bruh");
    // }
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    System.out.println("Nice job, dummy, now you're disabled!");
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null)
      m_autonomousCommand.schedule();
    else
      System.out.println("Auto command did not schedule :(");
    

    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    m_autonomousCommand.execute();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

    if(DriverStation.getInstance().getGameSpecificMessage().length() > 0)
      System.out.printf("Message got: %s", DriverStation.getInstance().getGameSpecificMessage());
    else
      System.out.println("Not here :(");

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    // if(m_primaryTank.getRawButtonPressed(8))
    //   System.out.println(m_gyro.getAngle());
    // else if(m_primaryTank.getRawButtonPressed(9))
    //   m_gyro.reset();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    
    CommandScheduler.getInstance().cancelAll();
    System.out.println("Initialized! <3");
  }

  /**
   * This function is called periodically during test mode.
   */
   //UP IS NEGATIVE DOWN IS POSITIVE
   //RIGHT IS POSITIVE LEFT IS NEGATIVE
  @Override
  public void testPeriodic() {

  }
}
