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
	  /* si los campos "time" son distintos, entonces falso
	   * si contienen distinto numero de cuerpos,entonces falso
	   * 
	   * recorrer los cuerpos y comparar
	   * para double usar Math.abs(d1-d2)
	   * para posicion, velocidad.. (es decir vectores):
	   * crear un vector 2D v1, con las dos componentes extraidas del campo correspondiente de s1
	   * crear un vector 2D v2, con las dos componentes extraidas del campo correspondiente de s2
	   * comprobar con v1.distanceTo(v2)<=_eps
	   * 
	   */
		
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

		for(int i=0; i < js1.length(); i++)	//Cambiar que samu tiene esto pero gonsalo tiene otras cosas
		{
			JSONObject jso1 = js1.getJSONObject(i);
			JSONObject jso2 = js2.getJSONObject(i);
			Vector2D v1, v2;

			if(jso1.getString("id") != jso2.getString("id")) { return false; }
			
			if( jso1.getFloat("m") != jso2.getFloat("m") ) { return false; }	// AÑADIDO POR PEZO REVISAR 
			
			v1 = new Vector2D (jso1.getJSONArray("p").getDouble(0),jso1.getJSONArray("p").getDouble(1));
			v2 = new Vector2D (jso2.getJSONArray("p").getDouble(0),jso2.getJSONArray("p").getDouble(1));
			
			if(v1.distanceTo(v2) > _eps) { return false; }

			v1 = new Vector2D(jso1.getJSONArray("v").getDouble(0),jso1.getJSONArray("v").getDouble(1));
			v2 = new Vector2D(jso2.getJSONArray("v").getDouble(0),jso2.getJSONArray("v").getDouble(1));

			if(v1.distanceTo(v2) > _eps) { return false; }

			v1 = new Vector2D (jso1.getJSONArray("f").getDouble(0),jso1.getJSONArray("f").getDouble(1));
			v2 = new Vector2D (jso2.getJSONArray("f").getDouble(0),jso2.getJSONArray("f").getDouble(1));
			
			if(v1.distanceTo(v2) > _eps) { return false; }
		}
		return true;
	}
}
