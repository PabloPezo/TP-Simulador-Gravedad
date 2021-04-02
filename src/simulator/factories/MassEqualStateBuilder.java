package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStateBuilder extends Builder<StateComparator> {

	public MassEqualStateBuilder()
	{
		super("masseq", "mass equal");
	}
	
	@Override
	protected StateComparator createTheInstance(JSONObject js) {

        if (js.similar(super.getBuilderInfo().get("data")))
        {
        	return new MassEqualStates();
        }
        return null;
	}
}
