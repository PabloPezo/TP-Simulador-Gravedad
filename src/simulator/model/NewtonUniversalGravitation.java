package simulator.model;

import java.util.List;

public class NewtonUniversalGravitation implements ForceLaws
{
	private final static double Gconst = 6.67E - 11;
	
	public NewtonUniversalGravitation()
	{
		super();
	}
	
	public void apply(List<Body> bodies) 
	{

		for(int i = 0; i < bodies.size(); i++)
		{
			double force = 0.0;
			for(int j = 0; j < bodies.size(); j++)
			{
				if(i != j)
				{
					double mass = bodies.get(i).getMass() * bodies.get(j).getMass();
					
					if(mass == 0)
					{
						
					}
					else
					{
						double dist = bodies.get(i).pos.distanceTo(bodies.get(j).pos);
						force = Gconst * (mass / (dist * dist) );
						
						bodies.get(j).pos.direction().scale(force);
					}
				}
			}
		}
		
	}
	
	public String toString()
	{
		return null;

	}
	
}
