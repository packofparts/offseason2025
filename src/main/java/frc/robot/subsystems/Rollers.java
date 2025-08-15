// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import poplib.sensors.beam_break.BeamBreak;

public class Rollers extends SubsystemBase {
  /** Creates a new Rollers. */

  private static Rollers instance;

  public static Rollers getInstance() {
    if (instance == null) {
      instance = new Rollers();
    }
    return instance;
  }

  private SparkMax motor;
  public BeamBreak beamBreak;

  public Rollers() {
    motor = Constants.Rollers.MOTOR_CONFIG.createSparkMax();
    beamBreak = Constants.Rollers.BEAMBREAK_CONFIG.createBeamBreak();
  }

  public Command runRollers() {
    return runOnce( () -> {
      motor.set(Constants.Rollers.SPEED);
    });
  }

  public Command runRollersBackward() {
    return runOnce( () -> {
      motor.set(-Constants.Rollers.SPEED);
    });
  }

  public Command stopRollers() {
    return runOnce( () -> {
      motor.set(0.0);
    });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
