// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import poplib.control.FFConfig;
import poplib.control.PIDConfig;
import poplib.motor.FollowerConfig;
import poplib.motor.Mode;
import poplib.motor.MotorConfig;
import poplib.sensors.beam_break.BeamBreakConfig;
import poplib.swerve.swerve_constants.SDSModules;
import poplib.swerve.swerve_constants.SwerveModuleConstants;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    static final String CANIVORE_NAME = "cantBUS";

    public static final class Swerve { 

        static final SDSModules MODULE_TYPE = SDSModules.MK4iL3;  // change
        static final boolean TUNING_MODE = true;
        static final int SWERVE_CAN_ID_OFFSET = 5;      

        static final MotorConfig DRIVE_CONFIG = new MotorConfig(CANIVORE_NAME, 80, false, PIDConfig.getPid(0.01, 0.2), Mode.BRAKE);
        static final MotorConfig ANGLE_CONFIG = new MotorConfig(CANIVORE_NAME, 25, false, PIDConfig.getPid(5.0), Mode.BRAKE);
        
        public static final SwerveModuleConstants[] SWERVE_MODULE_CONSTANTS = SwerveModuleConstants.generateConstants(
            new Rotation2d[] {
                // Rotation2d.fromDegrees(42.099609), 
                // Rotation2d.fromDegrees(283.007812),
                // Rotation2d.fromDegrees(162.421875),
                // Rotation2d.fromDegrees(173.408203)
                Rotation2d.fromDegrees(38.7),              // set offsets
                Rotation2d.fromDegrees(281.074),           // set offsets
                Rotation2d.fromDegrees(165.0),             // set offsets
                Rotation2d.fromDegrees(177.6)              //  set offsets
            },
            MODULE_TYPE, 
            TUNING_MODE, 
            DRIVE_CONFIG, 
            ANGLE_CONFIG,
            SWERVE_CAN_ID_OFFSET
        );

        public static final int PIGEON_ID = 20;    // change
        public static final boolean GYRO_INVERSION = false;      // change - gyro should be ccw+ and cw-

        public static final double WHEEL_BASE =  edu.wpi.first.math.util.Units.inchesToMeters(23);    // change
        public static final double TRACK_WIDTH = edu.wpi.first.math.util.Units.inchesToMeters(23);    // change 
        public static final SwerveDriveKinematics SWERVE_KINEMATICS = new SwerveDriveKinematics(
            new Translation2d(WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
            new Translation2d(WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0),
            new Translation2d(-WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0),
            new Translation2d(-WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0)
        );
    }


    public static class Elevator {
        public static boolean TUNING_MODE = false;

        public static MotorConfig RIGHT_MOTOR_CONFIG = new MotorConfig(
            33,                                                 // change
            60,
            true,                                           // change 
            new PIDConfig(0.1, 0, 0.0, 0.02),                 // maybe change
            Mode.BRAKE
        );
        public static FollowerConfig LEFT_MOTOR_CONFIG = new FollowerConfig(
            RIGHT_MOTOR_CONFIG, 
            false,                                            // change
            34                                                   // change
        );

        public static FFConfig FEEDFORWARD_CONFIG = new FFConfig(0.012);      // maybe change

        public static final double MAX_ERROR = 1.0;
        
        public static final double MANUAL_CONTROL_STEP_SIZE = 5.0;
    }

    public static class Rollers {
        public static MotorConfig MOTOR_CONFIG = new MotorConfig(
            55,
            40,
            false,
            Mode.COAST
        );

        public static double SPEED = 1.0;
    }

    public enum ScoringSetpoints {
        IDLE(0),
        INTAKE(45),
        L2(30),
        L3(60);

        private double elevator;
        
        private ScoringSetpoints (double elevator) {
            this.elevator = elevator;
        }

        public double getElevator() {
            return this.elevator;
        }
    }
}
