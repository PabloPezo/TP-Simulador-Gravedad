package simulator.model;

import java.util.List;
import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{
	
	private Vector2D point;
	private double gConst;
	
	public MovingTowardsFixedPoint()
	{
		point = new Vector2D();
		gConst = 9.81;
	}
	public MovingTowardsFixedPoint(double gConst)
	{
		this.gConst = gConst;
		point = new Vector2D();
	}
	
	public void apply(List<Body> bs) {
		for (Body body : bs)
		{
			Vector2D f = new Vector2D(point.minus(body.getPosition()).direction().scale(gConst * body.getMass()));
			body.addForce(f);
		}
	}
	

	//aplicacion de una fuerza hacia el centro
	
}
