package simulator.factories;

import org.json.JSONObject;

import simulator.control.StateComparator;

public class EpsilonEqualStateBuilder extends Builder<StateComparator> {
		public  EpsilonEqualStateBuilder()
		{
			_typeTag="epseq";
			_desc="Epsilon-equal states comparator";
			
		}
	@Override
	protected StateComparator createTheInstance(JSONObject data) {
		// lo q sea q debamos hacer
		double eps=jsonObject.has("eps")? jsonObject.getDouble("eps"): 0.0;
		
		return  new EpsilonEqualStates(eps);
	}




	protected JSONObject createData()
	{
	JSONObject data=new JSONObject();
	data.put("eps", "the allowed error");
	
	return data;
	}
}