package simulator.factories;

import org.json.JSONObject;

import simulator.model.Body;

public class MassLosingBodyBuilder extends Builder<Body> {


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
		return new Body(id,Vector2D p ,Vector2D v , m, freq,factor); // calcular las cosas
	}
		
	// createData hay q sobreescribirlo
	
	protected JSONObject createData()
	{
		JSONObject data=new JSONObject();
		data.put("type", "mlb");
		data.put("data", createData()); 
		data.put("desc","Mass losing body");
		return data;
	}
	
	
	
}
