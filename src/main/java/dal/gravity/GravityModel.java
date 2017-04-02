package dal.gravity;

public interface GravityModel {
	public static double EARTHGRAVITY = 9.80655;
	public static double JUPITERGRAVITY = 25;
	public double getGravitationalField();
}
