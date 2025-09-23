package poplib.subsytems.elevator;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import poplib.control.FFConfig;
import poplib.math.MathUtil;
import poplib.motor.FollowerConfig;
import poplib.motor.MotorConfig;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class SparkElevator extends Elevator {
    public SparkMax leadMotor;
    public SparkMax followMotor;
    boolean usePID;

    public SparkElevator(MotorConfig motorConfig, FollowerConfig followerConfig, FFConfig ffConfig, boolean tuningMode, String subsystemName) {
        super(ffConfig, tuningMode, subsystemName);

        leadMotor = motorConfig.createSparkMax();
        leadMotor.getEncoder().setPosition(0.0);
        followMotor = followerConfig.createSparkMax();
        followMotor.getEncoder().setPosition(0.0);
    }

    @Override
    public void periodic() {            
        super.tuning.updatePID(leadMotor);
        updatePID();
        
        SmartDashboard.putNumber("Elevator lead motor pos", getEncoderPos());
        SmartDashboard.putNumber("Elevator follow motor pos", followMotor.getEncoder().getPosition());
        SmartDashboard.putNumber("Elevator Amp to Motor", leadMotor.getOutputCurrent());
        SmartDashboard.putNumber("elevator sp", super.setpoint.get());
    }

    public void updatePID() {
        leadMotor.getClosedLoopController().setReference(
            super.setpoint.get(), 
            ControlType.kPosition, 
            ClosedLoopSlot.kSlot0, 
            super.feedforward.calculate(getEncoderPos()));
        
    }

    public double getEncoderPos() {
        return leadMotor.getEncoder().getPosition();
    }

    public double getError(double setpoint) {
        return MathUtil.getError(leadMotor, setpoint);
    }

    /**
     * Requires usePID to be false in order to work
     */
    public Command moveUp(double speed) {
        return runOnce(() -> {
            leadMotor.set(Math.abs(speed));
        });
    }

     public Command manuallyControlWithPID(double offset, double error) {
        return moveElevator(getEncoderPos() + offset, error);
    }

    /**
     * Requires usePID to be false in order to work
     */
    public Command moveDown(double speed) {
        return runOnce(() -> {
            leadMotor.set(-Math.abs(speed));
        });
    }

    /**
     * Requires usePID to be false in order to work
     */
    public Command stop() {
        return runOnce(() -> {
            leadMotor.set(0.0);
        });
    }
}
