package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

public class MassEqualStates implements StateComparator{

	public boolean equal(JSONObject s1, JSONObject s2)
	{
		boolean first = false;
		boolean second = false;
		boolean third = false;
		boolean full=false;
		
		if(s1.getDouble("time")==s2.getDouble("time")){first = true;}
		JSONArray json1 = s1.getJSONArray("bodies");
		JSONArray json2 = s2.getJSONArray("bodies");
		
		if(json1.length()==json2.length()){second=true;}
		//else if(s1.length()==s2.length()){second=true;}
		
		for(int i=0;i<json1.length();i++)
		{
			if(json1.getJSONObject(i).getString("id").equals(json2.getJSONObject(i).getString("id")) && json1.getJSONObject(i).getDouble("m")==json2.getJSONObject(i).getDouble("m"))
			{
				third = true;
			}	 
		}
		
		if(first==true&&second==true&&third==true)
		{
			full= true;
		}
	
		return full;
	}
	/* si los tiempos son distintos entonces false
	 * si el numero de cuerpos(bodies)es distinto entonces falso
	 * recorrer los cuerpos en ambos estados y prguntar por los campos "id" y "m"
	 */
	
	
	
	
}
