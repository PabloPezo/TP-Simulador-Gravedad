package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLosingBody;

public class MassLosingBodyBuilder extends Builder<Body> 
{
	public MassLosingBodyBuilder()
	{
		super("mlb", "mass losing body");
	}

	protected Body createTheInstance(JSONObject js)
	{
		double m = js.getDouble("m");
		String id = js.getString("id");
		
		JSONArray p = js.getJSONArray("p");
		JSONArray v = js.getJSONArray("v");
		
		Vector2D pos = new Vector2D(p.getDouble(0),p.getDouble(1));
		Vector2D vel = new Vector2D(v.getDouble(0),v.getDouble(1));
		
		double factor = js.getDouble("factor");
		double freq = js.getDouble("freq");
		
		if(js.similar(super.getBuilderInfo().get("data")))
		{
			return new MassLosingBody(id, vel, pos, m, factor, freq);
		}
		
		return null;
	}

	protected JSONObject createData() 
	{
        JSONObject js = new JSONObject();
        js.put("id", "the identifier");
        js.put("m", "the mass");
        js.put("v", "the velocity");
        js.put("p", "the position");
        js.put("factor", "the loss factor");
        js.put("freq", "the loss frequency");
        return js;
    }
}
