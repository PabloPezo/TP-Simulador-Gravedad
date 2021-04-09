package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws> 
{
	public NewtonUniversalGravitationBuilder()
	{
		super("nlug", "Newton's law universal gravitation");
	}

	protected ForceLaws createTheInstance(JSONObject js)
<<<<<<< HEAD
	{
		double gConst = js.has("g") ? js.getDouble("g") : 6.67E-11;
		
//		if (js.similar( super.getBuilderInfo().get("data")))
//		{
			return new NewtonUniversalGravitation(gConst);
//		}
//		
//		return null;
=======
	{	
		if(!js.getString("type").equals(_type)) {return null;} // Si el tipo coincide empezamos a comparar con el campo data
		js = js.getJSONObject("data");
		
		double gConst = js.has("g") ? js.getDouble("g") : 6.67E-11;

		return new NewtonUniversalGravitation(gConst);
>>>>>>> parent of 0fb131d (Mi parte ya esta)
	}
	
	protected JSONObject createData() // REVISAR
	{
		JSONObject js = new JSONObject();
		
		js.put("g", "gravitation constant");
		
		return js;
	}
}
