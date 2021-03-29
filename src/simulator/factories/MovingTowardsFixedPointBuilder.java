package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{
	public  MovingTowardsFixedPointBuilder()
	{
		
	}
	@Override
	protected ForceLaws createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		double g = data.has("g")? data.getDouble("g"):9.81;
		//CALCULAR C
		
		
		
		return new MovingTowardsFixedPoint(c,g);
	}

	
	protected JSONObject createData()
	{
		JSONObject data=new JSONObject();
		
		//... los put
		
		
		return data;
	}
}
