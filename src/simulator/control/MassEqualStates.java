package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;


public class MassEqualStates implements StateComparator
{
	public boolean equal(JSONObject s1, JSONObject s2)
	{
		if(s1.getDouble("time") != s2.getDouble("time")) { return false; }
	    JSONArray js1 = s1.getJSONArray("bodies");
	    JSONArray js2 = s2.getJSONArray("bodies");

	    if(js1.length()!=js2.length()) { return false; }

	    for(int i = 0; i < js1.length(); i++)
	    {
<<<<<<< HEAD
	    	//Con string si pero double no?
	    	if(js1.getJSONObject(i).getString("id").equals(js2.getJSONObject(i).getString("id")) || js1.getJSONObject(i).getDouble("m")!=js2.getJSONObject(i).getDouble("m")) 
=======
	    	if(!js1.getJSONObject(i).getString("id").equals(js2.getJSONObject(i).getString("id") )|| js1.getJSONObject(i).getDouble("m")!=js2.getJSONObject(i).getDouble("m")) 
>>>>>>> d6bb4e5b8a1334b25d9c44fdefe3e56bc2d6de45
	    	{
	    		return false;
	    	}
	    }
	    return true;
	}
	
}

