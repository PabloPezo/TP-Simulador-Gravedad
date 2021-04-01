package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws {

	private final double _gConst;
	
	public NewtonUniversalGravitation()
	{
		_gConst = 6.67E-11;	
	}
	
	public NewtonUniversalGravitation(double gConst)
	{
		_gConst = gConst;
	}
	
	public void apply(List<Body> bs)	//Cambiar un poquillo
	{
		Vector2D Force = new Vector2D();
		double forceModulo;
		
		for (Body bodyPrincipal : bs)
		{
			Force = new Vector2D();
			
			for (Body bodySecundario : bs)
			{
				if (bodyPrincipal != bodySecundario)
				{
					forceModulo = 0;
					
					if (bodyPrincipal.getMass() == 0.0)
					{
						bodyPrincipal._vel = new Vector2D();
					}
					else
					{
						forceModulo = (_gConst * bodyPrincipal.getMass() * bodySecundario.getMass()) / Math.pow(bodyPrincipal.getPosition().distanceTo(bodySecundario.getPosition()), 2);
						Force = Force.plus(bodySecundario.getPosition().minus(bodyPrincipal.getPosition()).direction().scale(forceModulo));
					}
				}
			}
			
			bodyPrincipal.addForce(Force);
		}
	}
}
