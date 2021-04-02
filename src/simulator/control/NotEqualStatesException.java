package simulator.control;

import org.json.JSONObject;

public class NotEqualStatesException extends Exception
{
	public NotEqualStatesException()
	{
		super();
	}
	
	public NotEqualStatesException(JSONObject expectatedState, JSONObject currentState, int i) 
	{
		System.out.println();
	}
}
