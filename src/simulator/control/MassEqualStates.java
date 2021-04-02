	package simulator.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MassEqualStates implements StateComparator
{
	public boolean equal(JSONObject s1, JSONObject s2)	//TODOS LO TIENEN IGUAL, CAMBIAR UN POCO(?)
	{
		if(s1.getDouble("time") != s2.getDouble("time")) { return false; }
	    JSONArray js1 = s1.getJSONArray("bodies");
	    JSONArray js2 = s2.getJSONArray("bodies");

	    if(js1.length()!=js2.length()) { return false; }

	    for(int i = 0; i < js1.length(); i++)
	    {
	        if ((js1.getString(i) != js2.getString(i)) || (js1.getDouble(i) != js2.getDouble(i)))
	        {
	        	return false;
	        }
	    }
	    return true;
	}
	
}

