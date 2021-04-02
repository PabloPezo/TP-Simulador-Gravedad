package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body> 
{
	public BasicBodyBuilder()
	{
		super();
	}
	
	protected Body createTheInstance(JSONObject js)
	{
		double m = js.getDouble("m");
		String id = js.getString("id");
		
		JSONArray p = js.getJSONArray("p");
		JSONArray v = js.getJSONArray("v");
		
		Vector2D pos = new Vector2D(p.getDouble(0),p.getDouble(1));
		Vector2D vel = new Vector2D(v.getDouble(0),v.getDouble(1));
		
		if(js.similar(super.getBuilderInfo().get("data")))
		{
			return new Body(id, vel, pos, m);
		}
		return null;
	}
	
	protected JSONObject createData()
	{
		JSONObject js = new JSONObject();
		js.put("id", "unique id");
		js.put("m", "mass");
		js.put("p", "position");
		js.put("v", "velocity");
		
		return js;
	}
	
	
}
