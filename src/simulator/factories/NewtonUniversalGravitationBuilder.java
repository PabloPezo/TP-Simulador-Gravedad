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

	protected ForceLaws createTheInstance(JSONObject js)	// Compara la información con Data
	{
		if(!js.getString("type").equals(_type)) {return null;}
		js = js.getJSONObject("data");

		try
		{
			double gConst = js.has("g") ? js.getDouble("g") : 6.67E-11;
			return new NewtonUniversalGravitation(gConst);
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Illegal argument");
		}

	}
	
	protected JSONObject createData()  	// Añade la información
	{
		JSONObject js = new JSONObject();
		
		js.put("g", "gravitation constant");
		
		return js;
	}
}
