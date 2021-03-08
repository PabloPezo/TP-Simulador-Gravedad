package simulator.model;

import java.util.List;

public class MovingTowardsFixedPoint implements ForceLaws
{
	public MovingTowardsFixedPoint()
	{
		
	}
	
	public void apply(List<Body> bodies) 
	{
		for(int i = 0; i < bodies.size(); i++)
		{
			bodies.get(i).pos();
		}
	}
	
	public String toString()
	{
		return null;
	}

}
