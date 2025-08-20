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
import poplib.controllers.oi.Joysticks.OIConstants;
import poplib.swerve.commands.TeleopSwerveDrive;

import java.net.ContentHandler;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

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
    oi.getDriverButton(XboxController.Button.kX.value).onTrue(intake());
    oi.getDriverButton(XboxController.Button.kA.value).onTrue(score(ScoringSetpoints.L2));
    oi.getDriverButton(XboxController.Button.kY.value).onTrue(score(ScoringSetpoints.L3));

    oi.getOperatorButton(XboxController.Button.kX.value).onTrue(rollers.runRollers()).onFalse(rollers.stopRollers());
    oi.getOperatorButton(XboxController.Button.kB.value).onTrue(rollers.runRollersBackward()).onFalse(rollers.stopRollers());
    oi.getOperatorButton(XboxController.Button.kStart.value).onTrue(swerve.resetGyroCommand());
    oi.getOperatorController().povUp().onTrue(elevator.manuallyControlWithPID(Constants.Elevator.MANUAL_CONTROL_STEP_SIZE, Constants.Elevator.MAX_ERROR));
    oi.getOperatorController().povDown().onTrue(elevator.manuallyControlWithPID(-Constants.Elevator.MANUAL_CONTROL_STEP_SIZE, Constants.Elevator.MAX_ERROR));
  }

  private Command intake() {
    return elevator.moveElevator(ScoringSetpoints.INTAKE.getElevator()).
    andThen(rollers.stopRollers()).
    until(rollers.beamBreak.getBlockedSupplier()).
    andThen(elevator.moveElevator(ScoringSetpoints.IDLE.getElevator()));
  }

  private Command score(ScoringSetpoints setpoint) {
    return elevator.moveElevator(setpoint.getElevator()).
    andThen(rollers.runRollers()).
    until(rollers.beamBreak.getUnBlockedSupplier()).
    andThen(rollers.stopRollers()).
    andThen(elevator.moveElevator(ScoringSetpoints.IDLE.getElevator()));
  }
  
  public Command getAutonomousCommand() {
    return null;
  }
}
