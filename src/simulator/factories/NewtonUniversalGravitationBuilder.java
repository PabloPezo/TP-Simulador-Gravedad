package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws> 
{
	static String _type = "nlug";
	static String _desc = "Newton's law universal gravitation";
	
	public NewtonUniversalGravitationBuilder()
	{
		super(_type, _desc);
	}

	protected ForceLaws createTheInstance(JSONObject js)
	{	
		if(!js.getString("type").equals(_type)) {return null;} // Si el tipo coincide empezamos a comparar con el campo data
		js = js.getJSONObject("data");
		
		double gConst = js.has("g") ? js.getDouble("g") : 6.67E-11;

		return new NewtonUniversalGravitation(gConst);
	}
	
	protected JSONObject createData() // REVISAR
	{
		JSONObject js = new JSONObject();
		
		js.put("g", "gravitation constant");
		
		return js;
	}
}
