// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import poplib.sensors.camera.CameraConfig;
import poplib.sensors.camera.LimelightConfig;
import poplib.sensors.gyro.Gyro;
import poplib.swerve.custom_swerve.SwerveKinematics;
import poplib.swerve.swerve_modules.SwerveModule;
import poplib.swerve.swerve_templates.CustomBaseSwerve;
import poplib.swerve.swerve_templates.VisionBaseSwerve;

public class Swerve extends VisionBaseSwerve {
  /** Creates a new Swerve. */
  public Swerve(SwerveModule[] modules, Gyro gyro, SwerveDriveKinematics kinematics) {
    super(modules, gyro, kinematics, new ArrayList<CameraConfig>(), new ArrayList<LimelightConfig>());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
