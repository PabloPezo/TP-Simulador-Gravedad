package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body> {

	@Override
	protected Body createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		double m = data.getDouble("m");
		String id = data.getString("id");
		// para los vectores velocidad y posicion
		// JSONArray pos= data.getJSONArray("p");
		//con pos.getDouble(i) podemos acceder al iesimo componente
		// Vector2D p = new Vector2D ( )
		
		//UNA VEZ EXTRAIDOS LOS VALORES
		return new Body(id,Vector2D p ,Vector2D v , m); // calcular las cosas
	}
		
	// createData hay q sobreescribirlo
	
	protected JSONObject createData()
	{
		JSONObject data=new JSONObject();
		data.put("id", "the identifier");
		data.put("type", "basic");
		data.put("data", createData()); 
		data.put("desc","Default Body");
		return data;
	}
	
	
}
