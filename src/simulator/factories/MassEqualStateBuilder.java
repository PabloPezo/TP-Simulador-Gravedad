package simulator.factories;

import org.json.JSONObject;

import simulator.control.StateComparator;

public class MassEqualStateBuilder extends Builder<StateComparator> {

	public MassEqualStateBuilder()
	{
		
	}
	@Override
	protected StateComparator createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		return new MassEqualStateBuilder();
	}

	protected JSONObject createData()
	{
		JSONObject data=new JSONObject();
		
		
		
		
		
		
		
		
		return data;
		
	}
	
	
	
	
}
