// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import java.util.ArrayList;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.RobotConfig;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import poplib.sensors.camera.CameraConfig;
import poplib.sensors.camera.LimelightConfig;
import poplib.sensors.gyro.Pigeon;
import poplib.swerve.swerve_modules.SwerveModuleTalon;
import poplib.swerve.swerve_templates.VisionBaseSwerve;

public class Swerve extends VisionBaseSwerve {
  /** Creates a new Swerve. */

  private static Swerve instance;

  public static Swerve getInstance() {
    if (instance == null) {
      instance = new Swerve();
    }
    return instance;
  }

  public Swerve() {
    super(
          new SwerveModuleTalon[] {
                  new SwerveModuleTalon(Constants.Swerve.SWERVE_MODULE_CONSTANTS[0]),
                  new SwerveModuleTalon(Constants.Swerve.SWERVE_MODULE_CONSTANTS[1]),
                  new SwerveModuleTalon(Constants.Swerve.SWERVE_MODULE_CONSTANTS[2]),
                  new SwerveModuleTalon(Constants.Swerve.SWERVE_MODULE_CONSTANTS[3]),
          },
          new Pigeon(Constants.Swerve.PIGEON_ID, Constants.Swerve.GYRO_INVERSION, "cantBUS"),
          Constants.Swerve.SWERVE_KINEMATICS, new ArrayList<CameraConfig>(), new ArrayList<LimelightConfig>()
        );


        RobotConfig config = null;
        try {
          config = RobotConfig.fromGUISettings();
        } catch (Exception e) {
          DriverStation.reportError("Can't run autos, failed to get the robot config", false);
        }


        AutoBuilder.configure(
          super::getOdomPose, 
          super::setOdomPose, 
          super::getChassisSpeeds, 
          (speeds, feedforward) -> driveChassis(speeds), 
          Constants.Swerve.AUTO_DRIVE_CONTROLLER, 
          config, 
          () -> {
            var alliance = DriverStation.getAlliance();
            if (alliance.isPresent()) {
                return alliance.get() == DriverStation.Alliance.Red;
            }
            return false;
          },
          this);
  }

  @Override
  public void periodic() {
    super.periodic();
  }

  public Command runSwerve() {
    return runOnce(() -> super.driveRobotOriented(new Translation2d(1,0), 0.5));
  }
}
