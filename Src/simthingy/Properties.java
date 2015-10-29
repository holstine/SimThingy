package simthingy;
public class Properties
{
	// here in we put all simulator properties

	public static double deltaT = .00001; // seconds
	public static double maxDeltaT = 100*deltaT; // seconds
	public static double totalTime = 100; // seconds
	public static double springK = 100000000;
	public static double torqueK = 500000;
	public static double dampingConst = -100.1;
	public static double torqueDampingConst = -.05;
	public static double gravity =9.8; // M/s2
	public static boolean goFlag = true;
	public static boolean trigger = false;
	public static boolean redFlag = false;
	public static double redFlagConst = 100; // the smaller the more red flags you get
	public static double bungeeDoubleForce = 270;
	public static double time =0;
	public static double timeAlpha =.09999; // [0,1] closer to 1 is slower rise to maxDeltaT
	public static double connectorAlpha = 1.5; // [0,1] closer to 0 is smoother force profile
	public static boolean winch = false;
	public static double winchSpeed = -50;
        public static double finalTime = 1000;
}
