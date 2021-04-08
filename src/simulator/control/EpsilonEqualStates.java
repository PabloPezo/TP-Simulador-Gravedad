package simulator.control;

import org.json.JSONObject;
import org.json.JSONArray;
import simulator.misc.Vector2D;
public class EpsilonEqualStates implements StateComparator
{
	private final double _eps;
	
	public EpsilonEqualStates(double eps)
	{
		_eps = eps;
	}
	
	public boolean equal(JSONObject s1,JSONObject s2)
	{		
		JSONArray js1 = s1.getJSONArray("bodies");
        JSONArray js2 = s2.getJSONArray("bodies");

    	if(Math.abs(s1.getDouble("time") - s2.getDouble("time")) > _eps)
		{
			return false;
		}
		else if(js1.length() != js2.length())
		{
			return false;
		}

		for(int i=0; i < js1.length(); i++)
		{
			JSONObject jso1 = js1.getJSONObject(i);
			JSONObject jso2 = js2.getJSONObject(i);
			Vector2D v1, v2, f1, f2, p1, p2;
			
			p1 = new Vector2D(jso1.getJSONArray("p").getDouble(0), jso1.getJSONArray("p").getDouble(1));
			p2 = new Vector2D(jso2.getJSONArray("p").getDouble(0), jso2.getJSONArray("p").getDouble(1));
			v1 = new Vector2D(jso1.getJSONArray("v").getDouble(0), jso1.getJSONArray("v").getDouble(1));
			v2 = new Vector2D(jso2.getJSONArray("v").getDouble(0), jso2.getJSONArray("v").getDouble(1));
			f1 = new Vector2D(jso1.getJSONArray("f").getDouble(0), jso1.getJSONArray("f").getDouble(1));
			f2 = new Vector2D(jso2.getJSONArray("f").getDouble(0), jso2.getJSONArray("f").getDouble(1));
			
			if(!jso1.getString("id").equals(jso2.getString("id")))
			{
				return false;
			}
			else if(jso1.getFloat("m") == jso2.getFloat("m"))
			{
				return false;
			}
			else if(p1.distanceTo(p2) > _eps || v1.distanceTo(v2) > _eps || f1.distanceTo(f2) > _eps)
			{
				return false;
			}
			
			if(Math.abs(js1.getJSONObject(i).getDouble("m")-js2.getJSONObject(i).getDouble("m"))>_eps && v1.distanceTo(v2)>_eps && f1.distanceTo(f2)>_eps && p1.distanceTo(p2)>_eps)
			{
				return false;
			}
		}
		return true;
	}
}
