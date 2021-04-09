package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>
{
	public  MovingTowardsFixedPointBuilder()
	{
		super("mtfp", "moving towards fixed point");
	}
	
	protected ForceLaws createTheInstance(JSONObject js)
	{
		Vector2D p = null;
		JSONArray vector;
		double g = js.has("g") ? js.getDouble("g") : 9.81;
		
<<<<<<< HEAD
=======
		Vector2D p = null;
		JSONArray vector;
		double g = js.has("g") ? js.getDouble("g") : 9.81;
		
>>>>>>> parent of 0fb131d (Mi parte ya esta)
		if(!js.isEmpty())
		{
			vector = js.getJSONArray("c");
			double x = vector.getDouble(0);
			double y = vector.getDouble(1);
			p = new Vector2D(x, y);
		}
		else
		{
			p = new Vector2D(0.0, 0.0);
		}
		return new MovingTowardsFixedPoint(p,g);
<<<<<<< HEAD
		
=======
>>>>>>> parent of 0fb131d (Mi parte ya esta)
	}
	
	protected JSONObject createData() // REVISAR
	{
		JSONObject js = new JSONObject();
		
		js.put("g", "gravitation constant");
		js.put("c", "fixed point");
		
		return js;
	}
}
