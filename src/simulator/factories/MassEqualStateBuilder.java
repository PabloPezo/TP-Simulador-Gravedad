package simulator.factories;

import org.json.JSONObject;
import simulator.control.StateComparator;
import simulator.control.MassEqualStates;

public class MassEqualStateBuilder extends Builder<StateComparator> 
{

	public MassEqualStateBuilder()
	{
		super("masseq", "mass equal");
	}
	
	protected StateComparator createTheInstance(JSONObject js)
	{
        if (js.similar( super.getBuilderInfo().get("data")))
        {
        	return new MassEqualStates();
        }
        return null;
	}
}
