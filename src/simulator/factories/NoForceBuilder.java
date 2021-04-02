package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws> {

	public NoForceBuilder()
	{
		super("nf", "no force");
	}
	
	@Override
	protected ForceLaws createTheInstance(JSONObject js) {
		if(js.similar(super.getBuilderInfo().get("data")))
		{
			return new NoForce();
		}
		return null;
	}

}
