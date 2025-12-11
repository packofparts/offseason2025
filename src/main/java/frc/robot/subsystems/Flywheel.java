package frc.robot.subsystems;

import frc.robot.Constants;
import poplib.subsytems.flywheel.TalonFlywheel;

public class Flywheel extends TalonFlywheel{
    public Flywheel() {
        super(
            Constants.Flywheel.lead, 
            Constants.Flywheel.follow, 
            "Flywheel", 
            Constants.Flywheel.TUNING_MODE, 
            false
        );
    }
}
