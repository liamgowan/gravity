package dal.gravity;

public class GravityConstant implements GravityModel{
	private final double gravity;
	public GravityConstant(double gravity){
		this.gravity=gravity;
	}
	
	@Override
	public double getGravitationalField(){
		return gravity;
	}
}
