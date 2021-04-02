package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws> {

	public NewtonUniversalGravitationBuilder()
	{
		super("nlug", "Newton's law universal gravitation");
	}
	
	@Override
	protected ForceLaws createTheInstance(JSONObject js)
	{
		double gConst = js.has("g") ? js.getDouble("g") : 6.67E-11;

		if (js.similar( super.getBuilderInfo().get("data")))
		{
			return new NewtonUniversalGravitation(gConst);
		}
		
		return null;
	}
	
	protected JSONObject createData()
	{
		JSONObject js = new JSONObject();
		
		js.put("g", "gravitation constant");
		
		return js;
	}
}
