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
<<<<<<< HEAD
    	double epsilon = js.has("eps") ? js.getDouble("eps") : 0.0;

    //		if (js.similar(super.getBuilderInfo().get("data")))
    	//	{
    			return new EpsilonEqualStates(epsilon);
//    		}
//    		else
//    		{
//    			return null;
//    		}

=======
		if(!js.getString("type").equals(_type)) {return null;} // Si el tipo coincide empezamos a comparar con el campo data
		js = js.getJSONObject("data");
		
    	double epsilon = js.has("eps") ? js.getDouble("eps") : 0.0;

    	return new EpsilonEqualStates(epsilon);
>>>>>>> parent of 0fb131d (Mi parte ya esta)
	}

    protected JSONObject createData() //REVISAR
    {
	    JSONObject js = new JSONObject();
	    js.put("eps", "the allowed error");
	    return js;
    }
}