package simulator.factories;

import org.json.JSONObject;
import simulator.control.StateComparator;
import simulator.control.EpsilonEqualStates;


public class EpsilonEqualStateBuilder extends Builder<StateComparator>
{
	static String _type = "epseq";
	static String _desc = "Epsilon-equal states comparator";
	
	public  EpsilonEqualStateBuilder()
	{
		super(_type, _desc);			
	}
	
    protected StateComparator createTheInstance(JSONObject js) 
	{
		if(!js.getString("type").equals(_type)) {return null;} // Si el tipo coincide empezamos a comparar con el campo data
		js = js.getJSONObject("data");
		try
		{
			double epsilon = js.has("eps") ? js.getDouble("eps") : 0.0;
			return new EpsilonEqualStates(epsilon);
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Illegal argument");
		}
	}

    protected JSONObject createData() 
    {
	    JSONObject js = new JSONObject();
	    js.put("eps", "the allowed error");
	    return js;
    }
}