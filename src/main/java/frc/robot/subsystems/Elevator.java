// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import poplib.subsytems.elevator.TalonElevator;

public class Elevator extends TalonElevator {
  /** Creates a new Elevator. */

  private static Elevator instance;

  public static Elevator getInstance() {
    if (instance == null) {
      instance = new Elevator();
    }
    return instance;
  }

  public Elevator() {
    super(
      Constants.Elevator.RIGHT_MOTOR_CONFIG, 
      Constants.Elevator.LEFT_MOTOR_CONFIG, 
      Constants.Elevator.FEEDFORWARD_CONFIG, 
      Constants.Elevator.TUNING_MODE, 
      "Elevator"
    );
  }

  public Command moveElevator(double setpoint) {
    return super.moveElevator(setpoint, Constants.Elevator.MAX_ERROR);
  }

  @Override
  public void periodic() {
    super.periodic();
  }
}
