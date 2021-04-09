package simulator.factories;

import org.json.JSONObject;
import simulator.control.StateComparator;
import simulator.control.MassEqualStates;

public class MassEqualStateBuilder extends Builder<StateComparator> 
{
	static String _type = "masseq";
	static String _desc = "mass equal";
	
	public MassEqualStateBuilder()
	{
		super(_type, _desc);
	}
	
	protected StateComparator createTheInstance(JSONObject js)
	{
		if(!js.getString("type").equals(_type)) {return null;} 
		return new MassEqualStates();
	}
	
	protected JSONObject createData() 	// Añade la información
	{
		JSONObject data = new JSONObject();
		return data;
	}
}
