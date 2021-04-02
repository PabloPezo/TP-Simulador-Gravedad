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
		Vector2D appliedForce = new Vector2D();
		double forceModulo;
		double distance2;
		
		for (Body bodyPrincipal : bs)
		{
			appliedForce = new Vector2D();
			
			for (Body bodySecundario : bs)
			{
				if (bodyPrincipal != bodySecundario)
				{
					forceModulo = 0;
					
					if (bodyPrincipal.getMass() != 0.0)
					{
						distance2 = Math.pow(bodyPrincipal.getPosition().distanceTo(bodySecundario.getPosition()), 2);
						forceModulo = (_gConst * bodyPrincipal.getMass() * bodySecundario.getMass()) / distance2;
						appliedForce = appliedForce.plus(bodySecundario.getPosition().minus(bodyPrincipal.getPosition()).direction().scale(forceModulo));
					}
					else
					{
						bodyPrincipal._vel = new Vector2D();
					}
				}
			}
			
			bodyPrincipal.addForce(appliedForce);
		}
	}
}
