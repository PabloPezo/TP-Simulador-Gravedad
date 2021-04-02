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
		super("mtcp", "moving towards fixed point");
	}
	
	protected ForceLaws createTheInstance(JSONObject js)
	{
		double g = js.has("g")? js.getDouble("g") : 9.81;
		
		JSONArray vector = js.getJSONArray("c");
		
		double x = vector.isEmpty() ? vector.getDouble(0) : 0.0;
		double y = vector.isEmpty() ? vector.getDouble(1) : 0.0;
		
		Vector2D c = new Vector2D(x, y);
		
		if (js.similar (super.getBuilderInfo().get("data")))
		{
			return new MovingTowardsFixedPoint(c, g);
		}
		return null;
	}
	
	protected JSONObject createData()
	{
		JSONObject js = new JSONObject();
		
		js.put("g", "gravitation constant");
		js.put("c", "fixed point");
		
		return js;
	}
}
