// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ScoringSetpoints;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Rollers;
import frc.robot.subsystems.Swerve;
import poplib.controllers.oi.OI;
import poplib.controllers.oi.XboxOI;
import poplib.swerve.commands.TeleopSwerveDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {

  private final Swerve swerve = Swerve.getInstance(); 
  private final Elevator elevator = Elevator.getInstance();
  private final Rollers rollers = Rollers.getInstance();

  private final OI oi = XboxOI.getInstance();

  public RobotContainer() {
    swerve.setDefaultCommand(new TeleopSwerveDrive(swerve, oi));
    configureBindings();
  }


  private void configureBindings() {
    oi.getDriverButton(XboxController.Button.kX.value).onTrue(elevator.moveElevator(ScoringSetpoints.INTAKE.getElevator()));
    oi.getDriverButton(XboxController.Button.kB.value).onTrue(elevator.moveElevator(ScoringSetpoints.L2.getElevator()));
    oi.getDriverButton(XboxController.Button.kY.value).onTrue(elevator.moveElevator(ScoringSetpoints.L3.getElevator()));
    oi.getDriverButton(XboxController.Button.kA.value).onTrue(elevator.moveElevator(ScoringSetpoints.IDLE.getElevator()));
    oi.getDriverTrigger(XboxController.Axis.kRightTrigger.value).onTrue(rollers.runRollers()).onFalse(rollers.stopRollers());
    oi.getDriverTrigger(XboxController.Axis.kLeftTrigger.value).onTrue(rollers.runRollersBackward()).onFalse(rollers.stopRollers());
    oi.getDriverButton(XboxController.Button.kStart.value).onTrue(swerve.resetGyroCommand());
    oi.getDriverController().povUp().onTrue(elevator.manuallyControlWithPID(Constants.Elevator.MANUAL_CONTROL_STEP_SIZE, Constants.Elevator.MAX_ERROR));
    oi.getDriverController().povDown().onTrue(elevator.manuallyControlWithPID(-Constants.Elevator.MANUAL_CONTROL_STEP_SIZE, Constants.Elevator.MAX_ERROR));
  }
  
  public Command getAutonomousCommand() {
    return null;
  }
}
