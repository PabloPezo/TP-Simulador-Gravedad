package simulator.factories;

import org.json.JSONObject;
import simulator.model.NoForce;
import simulator.model.ForceLaws;


public class NoForceBuilder extends Builder<ForceLaws>
{
	public NoForceBuilder()
	{
		super("nf", "no force");
	}

	protected ForceLaws createTheInstance(JSONObject js) // en principio no hace falta excepcion
	{
//		if(js.similar( super.getBuilderInfo().get("data")))
//		{
			return new NoForce();
//		}
//		
//		return null;
	}
	
	// EL JSON ESE RARO?
}
