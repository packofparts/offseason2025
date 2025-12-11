package frc.robot.subsystems;

import frc.robot.Constants;
import poplib.subsytems.flywheel.TalonFlywheel;

public class Flywheel extends TalonFlywheel {
    private static Flywheel instance = null;

    public static Flywheel getInstance() {
        if (instance == null) {
            instance = new Flywheel();
        }
        return instance;
    }

    private Flywheel() {
        super(
            Constants.Flywheel.lead, 
            Constants.Flywheel.follow, 
            "Flywheel", 
            Constants.Flywheel.TUNING_MODE, 
            false
        );
    }
}
