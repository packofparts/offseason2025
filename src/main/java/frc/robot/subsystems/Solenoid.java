// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import poplib.subsytems.pneumatics.Pneumatics;

public class Solenoid extends Pneumatics {
  /** Creates a new Pneumatics. */

  private static Solenoid instance;

  public static Solenoid getInstance() {
    if (instance == null) {
      instance = new Solenoid();
    }
    return instance;
  }

  public Solenoid() {
    super(
      Constants.Solenoid.MODULE_TYPE,
      Constants.Solenoid.MIN_PRESSURE,
      Constants.Solenoid.MAX_PRESSURE
    );
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
