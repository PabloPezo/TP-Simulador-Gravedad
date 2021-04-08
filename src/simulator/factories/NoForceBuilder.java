package simulator.factories;

import org.json.JSONObject;
import simulator.model.NoForce;
import simulator.model.ForceLaws;


public class NoForceBuilder extends Builder<ForceLaws>
{
	static String _type = "nf";
	static String _desc = "no force";
	
	public NoForceBuilder()
	{
		super(_type, _desc);
	}

	protected ForceLaws createTheInstance(JSONObject js)
	{
		if(!js.getString("type").equals(_type)) {return null;} 
		return new NoForce();
	}
	
	// EL JSON ESE RARO?
}
