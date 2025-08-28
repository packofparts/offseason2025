package poplibv2.control_systems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;

/**
 * Respondible for holding PID constants and applying them to a motor.
 */
public class PIDConfig {
    private int P;
    private int I;
    private int D;
    private int F;
    private boolean usePID;

    /**
     * Full Constuctor for creating PIDConfig object. If you do not need to use a value, simple set it to zero.
     * @param P the proportional value of the your PID Controller
     * @param I the interval value multiplier of the your PID Controller
     * @param D the dervirative value multiplier of the your PID Controller
     * @param F the velocity Feedforward value
     */
    public PIDConfig(int P, int I, int D, int F) {
        this.P = P;
        this.I = I;
        this.D = D;
        this.F = F;
        this.usePID = true;
    }

    /**
     * Use this constructor if you do not want to use PID for the motor.
     */
    public PIDConfig() {
        this.usePID = false;
    }

    /**
     * You probably wont have to use this. It just creates a normal PIDController. 
     * When you create a MotorConfig, the MotorConfig automagically applies this PIDConfig to the Motor.
     * @return PIDController
     */
    public PIDController createPIDController() {
        return new PIDController(P, I, D);
    }

    /**
     * INTERNAL POPLIB FUNCTION.
     * <p></p>
     * Used to apply PID Constants to a Spark Motor config
     * @param config
     */
    public void applyToMotor(SparkMaxConfig config) {
        config.closedLoop.pidf(P,I,D,F);
    }

    /**
     * INTERNAL POPLIB FUNCTION.
     * <p></p>
     * Used to apply PID Constants to a Talon Motor config
     * @param config 
     */
    public void applyToMotor(TalonFXConfiguration config) {

    }
    

}
