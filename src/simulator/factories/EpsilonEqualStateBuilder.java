package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;

public class EpsilonEqualStateBuilder extends Builder<StateComparator> {//CAMBIAR COSITAS
		public  EpsilonEqualStateBuilder()
		{
			super("epseq", "Epsilon-equal states comparator");			
		}
		
		@Override
	    protected StateComparator createTheInstance(JSONObject js) {

	        double eps=js.has("eps")? js.getDouble("eps"): 0.0;
	        
	        if (js.similar(super.getBuilderInfo().get("data")))
	        {
	        	return new EpsilonEqualStates(eps);
	        }

	        return null;
	    }

	    protected JSONObject createData()
	    {
		    JSONObject js = new JSONObject();
		    js.put("eps", "the allowed error");
		    return js;
	    }
}