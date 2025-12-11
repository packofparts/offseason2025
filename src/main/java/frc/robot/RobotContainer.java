// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ScoringSetpoints;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Rollers;
import frc.robot.subsystems.Swerve;
import poplib.controllers.oi.OI;
import poplib.controllers.oi.XboxOI;
import poplib.swerve.commands.TeleopSwerveDrive;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RobotContainer {

  private final Swerve swerve = Swerve.getInstance(); 
  private final Elevator elevator = Elevator.getInstance();
  private final Rollers rollers = Rollers.getInstance();
  private final OI oi = XboxOI.getInstance();
  private final Flywheel flywheel = new Flywheel();

  private final SendableChooser<PathPlannerAuto> autoChooser = new SendableChooser<>();

  public RobotContainer() {
    swerve.setDefaultCommand(new TeleopSwerveDrive(swerve, oi));
    oi.getDriverButton(XboxController.Button.kStart.value).onTrue(swerve.resetGyroCommand());
    configureBindings();

    NamedCommands.registerCommand("ElevatorToL2", elevator.moveElevator(ScoringSetpoints.L2.getElevator()));
    NamedCommands.registerCommand("ElevatorToL3", elevator.moveElevator(ScoringSetpoints.L3.getElevator()));
    NamedCommands.registerCommand("LaunchCoral", launchCoral());
    NamedCommands.registerCommand("ElevatorToIntake", elevator.moveElevator(ScoringSetpoints.INTAKE.getElevator()));

    autoChooser.addOption("Left", new PathPlannerAuto("Left"));
    autoChooser.addOption("Right", new PathPlannerAuto("Right"));
    autoChooser.addOption("Get outa here", new PathPlannerAuto("Get outa here"));
    //autoChooser.addOption("New Auto", new PathPlannerAuto("New Auto"));
    SmartDashboard.putData(autoChooser);

    
  }


  private void configureBindings() {
    oi.getDriverButton(XboxController.Button.kX.value).onTrue(elevator.moveElevator(ScoringSetpoints.INTAKE.getElevator()));
    oi.getDriverButton(XboxController.Button.kB.value).onTrue(elevator.moveElevator(ScoringSetpoints.L2.getElevator()));
    oi.getDriverButton(XboxController.Button.kY.value).onTrue(elevator.moveElevator(ScoringSetpoints.L3.getElevator()));
    // oi.getDriverButton(XboxController.Button.kA.value).onTrue(elevator.moveElevator(ScoringSetpoints.IDLE.getElevator()));
    oi.getDriverTrigger(XboxController.Axis.kRightTrigger.value).onTrue(rollers.runRollers()).onFalse(rollers.stopRollers());
    oi.getDriverTrigger(XboxController.Axis.kLeftTrigger.value).onTrue(rollers.runRollersBackward()).onFalse(rollers.stopRollers());
    oi.getDriverButton(XboxController.Button.kStart.value).onTrue(swerve.resetGyroCommand());
    //oi.getDriverController().povUp().onTrue(elevator.manuallyControlWithPID(Constants.Elevator.MANUAL_CONTROL_STEP_SIZE, Constants.Elevator.MAX_ERROR));
    //oi.getDriverController().povDown().onTrue(elevator.manuallyControlWithPID(-Constants.Elevator.MANUAL_CONTROL_STEP_SIZE, Constants.Elevator.MAX_ERROR));

    oi.getDriverButton(XboxController.Button.kA.value).onTrue(flywheel.updateSetpointCommand(100)).onFalse(flywheel.updateSetpointCommand(0));
  }
  
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  public Command getTestCommand() {
    return elevator.moveElevator(ScoringSetpoints.INTAKE.getElevator()).
    andThen(new WaitCommand(1)).
    andThen(elevator.moveElevator(ScoringSetpoints.L2.getElevator())).
    andThen(new WaitCommand(1)).
    andThen(elevator.moveElevator(ScoringSetpoints.L3.getElevator())).
    andThen(rollers.runRollers()).
    andThen(new WaitCommand(1)).
    andThen(rollers.stopRollers()).
    andThen(swerve.runSwerve()).
    andThen(elevator.moveElevator(ScoringSetpoints.INTAKE.getElevator())).
    andThen(swerve.runSwerve());
  }

  public Command launchCoral() {
    return rollers.runRollers().
    andThen(new WaitCommand(1)).
    andThen(rollers.stopRollers());
  }
}
