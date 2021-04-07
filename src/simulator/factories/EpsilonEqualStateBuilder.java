package simulator.factories;

import org.json.JSONObject;
import simulator.control.StateComparator;
import simulator.control.EpsilonEqualStates;


public class EpsilonEqualStateBuilder extends Builder<StateComparator>
{
	public  EpsilonEqualStateBuilder()
	{
		super("epseq", "Epsilon-equal states comparator");			
	}
	
    protected StateComparator createTheInstance(JSONObject js) 
	{
    	double epsilon = js.has("eps") ? js.getDouble("eps") : 0.0;

    //		if (js.similar(super.getBuilderInfo().get("data")))
    	//	{
    			return new EpsilonEqualStates(epsilon);
//    		}
//    		else
//    		{
//    			return null;
//    		}

	}

    protected JSONObject createData()
    {
	    JSONObject js = new JSONObject();
	    js.put("eps", "the allowed error");
	    return js;
    }
}