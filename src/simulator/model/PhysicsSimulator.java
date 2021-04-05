package simulator.model;

import org.json.JSONObject;

import java.util.List;
import org.json.JSONArray;

public class PhysicsSimulator
{
	private double _realTime;
	private List <Body>bodyList;
	private ForceLaws _forceLaws;
	private double _currentTime;
	
	public PhysicsSimulator(double t, ForceLaws fl) throws IllegalArgumentException
	{
		if (t <= 0.0 || fl == null) throw new IllegalArgumentException();
		{
			_realTime = t;
			_forceLaws = fl;
			_currentTime = 0.0;
		}	
	}
	 	
 	public void advance()
 	{
 		for (Body body : bodyList)
		{
			body.resetForce();
		}
 		_forceLaws.apply(bodyList);
 		
 		for (Body body : bodyList)
		{
 			body.move(_realTime);
		}
		this._currentTime += _realTime;
 	}
 	
 	public void addBody(Body b) throws IllegalArgumentException
 	{
 		if (bodyList.contains(b))
 		{
 			throw new IllegalArgumentException();
 		}
 		bodyList.add(b);
 	}
 	
 	public JSONObject getState()
 	{
		JSONObject js = new JSONObject();
		js.put("time", _currentTime);
		js.put("bodies", bodyList);
		
		return js;
 	}
 	
 	public String toString()
 	{
		return getState().toString();
 	}
}
