package simulator.control;

import org.json.JSONObject;
import org.json.JSONArray;
import simulator.misc.Vector2D;
public class EpsilonEqualStates implements StateComparator {

	private final double eps;
	public EpsilonEqualStates(double eps)
	{
		this.eps=eps;
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
		if(Math.abs(s1.getDouble("time")-s2.getDouble("time"))>eps)return false;
		JSONArray jsa1 = s1.getJSONArray("bodies");
		
		
		
		return true;
	}
}
