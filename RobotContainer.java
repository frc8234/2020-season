/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.AutoDriveCommand;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.HighGoalCommand;
import frc.robot.commands.LowGoalCommand;
import frc.robot.commands.PickupCommand;
import frc.robot.commands.WheelCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GunRotationSubsystem;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final DriveSubsystem m_driveTrain = new DriveSubsystem();
  private final PickupSubsystem m_PickupSubsystem = new PickupSubsystem();
  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  private final ClimbSubsystem m_ClimbSubsystem = new ClimbSubsystem();
  private final GunRotationSubsystem m_GunRotationSubsystem = new GunRotationSubsystem();

  //Declare commands
  private final AutoDriveCommand m_autoCommand = new AutoDriveCommand(m_driveTrain);
  //private final HighGoalCommand m_highGoal = new HighGoalCommand(m_ShooterSubsystem, m_GunRotationSubsystem);
  private final LowGoalCommand m_lowGoal = new LowGoalCommand(m_ShooterSubsystem, m_GunRotationSubsystem);
  private final PickupCommand m_pickup = new PickupCommand(m_PickupSubsystem, m_ShooterSubsystem, m_GunRotationSubsystem);
  private final WheelCommand m_wheel = new WheelCommand(m_ShooterSubsystem, m_GunRotationSubsystem);
  private final ClimbCommand m_climb = new ClimbCommand();

  //private final XboxController m_controller = new XboxController(0);
  private final Joystick m_primaryTank = new Joystick(0);
  private final Joystick m_primaryTurn = new Joystick(1);
  private final Joystick m_secondaryDriver = new Joystick(2);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    System.out.println("Creating RobotContainer");

    // Configure the button bindings
    configureButtonBindings();

    //Set the command to drive to bot
    m_driveTrain.setDefaultCommand(
      new RunCommand(
//        () -> m_driveTrain.drive(m_controller.getY(Hand.kLeft), m_controller.getX(Hand.kLeft)),
        () -> m_driveTrain.drive(m_primaryTank.getY(), m_primaryTurn.getX()),
        m_driveTrain
      )
    );

    //Set the command to move the motors to pickup balls
    m_PickupSubsystem.setDefaultCommand(
      new RunCommand(
        () -> m_PickupSubsystem.Move(m_secondaryDriver.getY()), 
        m_PickupSubsystem
      )
    );

    m_ShooterSubsystem.Stop();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   * 
   */

  
  private void configureButtonBindings() {
    JoystickButton intakeButton = new JoystickButton(m_primaryTurn, 4);
    JoystickButton reverseIntake = new JoystickButton(m_primaryTurn, 5);
    
    //Create all the new JoystickButton objects to bind to different functions
    //final JoystickButton shooterButton = new JoystickButton(m_primaryTank, 1);
    // final JoystickButton climbUpButton = new JoystickButton(m_primaryTank, 3);
    // final JoystickButton climbDownButton = new JoystickButton(m_primaryTank, 2);

    final JoystickButton rotateUpGun = new JoystickButton(m_primaryTank, 3);
    final JoystickButton rotateDownGun = new JoystickButton(m_primaryTank, 2);

    final JoystickButton forwardConveyer = new JoystickButton(m_secondaryDriver, 3);
    final JoystickButton reverseConveyer = new JoystickButton(m_secondaryDriver, 2);

    final JoystickButton gunUp = new JoystickButton(m_secondaryDriver, 5);
    final JoystickButton gunDown = new JoystickButton(m_secondaryDriver, 4);

    final JoystickButton shooterButtonSecondary = new JoystickButton(m_secondaryDriver, 1);

    final JoystickButton shootHigh = new JoystickButton(m_primaryTank, 5);
    final JoystickButton shootLow = new JoystickButton(m_primaryTank, 4);

    final JoystickButton pickupBalls = new JoystickButton(m_primaryTank, 2);
    //final JoystickButton wheelOfDestiny = new JoystickButton(m_primaryTank, 3);

    //final JoystickButton climbUp = new JoystickButton(m_primaryTurn, 3);

    //final JoystickButton setManual = new JoystickButton(m_primaryTank, 9);

    //Command buttons

    intakeButton.whenPressed(() -> m_ShooterSubsystem.Foward());
    intakeButton.whenReleased(() -> m_ShooterSubsystem.Stop());

    reverseIntake.whenPressed(() -> m_ShooterSubsystem.Reverse());
    reverseIntake.whenReleased(() -> m_ShooterSubsystem.Stop());

    //Bind all the buttons to functions
    //shooterButton.whenPressed(() -> m_ShooterSubsystem.Shoot());
    //shooterButton.whenReleased(() -> m_ShooterSubsystem.Stop());

    // Command buttons
    // climbUpButton.whenPressed(() -> m_ClimbSubsystem.Up());
    // climbUpButton.whenReleased(() -> m_ClimbSubsystem.Stop());

    // climbDownButton.whenPressed(() -> m_ClimbSubsystem.Down());
    // climbDownButton.whenReleased(() -> m_ClimbSubsystem.Stop());

    rotateUpGun.whenPressed(() -> m_GunRotationSubsystem.Up());
    rotateUpGun.whenReleased(() -> m_GunRotationSubsystem.Stop());

    rotateDownGun.whenPressed(() -> m_GunRotationSubsystem.Down());
    rotateDownGun.whenReleased(() -> m_GunRotationSubsystem.Stop());

    forwardConveyer.whenPressed(() -> m_ShooterSubsystem.Foward());
    forwardConveyer.whenReleased(() -> m_ShooterSubsystem.StopConveyor());

    reverseConveyer.whenPressed(() -> m_ShooterSubsystem.Reverse());
    reverseConveyer.whenReleased(() -> m_ShooterSubsystem.StopConveyor());

    gunUp.whenPressed(() -> m_GunRotationSubsystem.Up());
    gunUp.whenReleased(() -> m_GunRotationSubsystem.Stop());

    gunDown.whenPressed(() -> m_GunRotationSubsystem.Down());
    gunDown.whenReleased(() -> m_GunRotationSubsystem.Stop());

    shooterButtonSecondary.whenPressed(() -> m_ShooterSubsystem.Shoot());
    shooterButtonSecondary.whenReleased(() -> m_ShooterSubsystem.Stop());

    // shootHigh.whenPressed(m_highGoal);
    // shootHigh.whenReleased(() -> m_highGoal.end(false));

    shootLow.whenPressed(m_lowGoal);
    shootLow.whenReleased(() -> m_lowGoal.end(false));

    pickupBalls.whenPressed(m_pickup);
    //wheelOfDestiny.whenPressed(m_wheel);

    //climbUp.whenPressed(m_climb);

    //setManual.whenPressed(() -> m_highGoal.setManual());
    //setManual.whenPressed(() -> m_lowGoal.setManual());
    //setManual.whenPressed(() -> m_pickup.setManual());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }


}