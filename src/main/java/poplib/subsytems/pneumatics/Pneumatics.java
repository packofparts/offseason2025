// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package poplib.subsytems.pneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
  /** Creates a new Pneumatics. */

  Compressor compressor;
  PneumaticHub hub;
  Solenoid solenoid;
  PneumaticsModuleType type;
  int minPressure;
  int maxPressure;

  public Pneumatics(PneumaticsModuleType type, int minPressure, int maxPressure) {
    this.type = type;
    compressor = new Compressor(1, type);
    hub = new PneumaticHub();
    solenoid = new Solenoid(type, 0);
    this.minPressure = minPressure;
    this.maxPressure = maxPressure;
  }

  public Command toggleSolenoid() {
    return runOnce(() -> {
      solenoid.set(!solenoid.get());
    });
  }

  public Command extendSolenoid() {
    return runOnce(() -> {
      solenoid.set(true);
    });
  } 

  public Command retractSolenoid() {
    return runOnce(() -> {
      solenoid.set(false);
    });
  }

  public Command changePneumatics(boolean extended){
    return runOnce(() -> {
      solenoid.set(extended);
    });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Pressure", compressor.getPressure());
    SmartDashboard.putBoolean("Solenoid Extended", solenoid.get());
    compressor.enableAnalog(85, 105);
  }
}
