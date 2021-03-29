package simulator.model;

import org.json.JSONObject;

import java.util.List;
import org.json.JSONArray;

public class PhysicsSimulator {

	private double RTpS;
	private List <Body>bodyList;
	private ForceLaws forceLaws;
	private double currentTime;
	
	public PhysicsSimulator(double t, ForceLaws fl) throws IllegalArgumentException
	{
		if (t <= 0.0 || fl == null) throw new IllegalArgumentException();
		{
			RTpS = t;
			forceLaws = fl;
			currentTime = 0.0;
		}	
	}
	 	
 	public void advance()
 	{
 		for (Body body : bodyList)body.resetForce();
 		forceLaws.apply(bodyList);
 		for (Body body : bodyList)body.move(RTpS);
 		currentTime += RTpS;
 	}
 	
 	public void addBody(Body b) throws IllegalArgumentException
 	{
 		for (Body other: bodyList)
 		{
 			if (b.getId() == other.getId()) throw new IllegalArgumentException();
 		}
 			
 		bodyList.add(b);
 	}
 	
 	public JSONObject getState()
 	{
 			JSONObject JSON = new JSONObject();
 			JSON.put("time",currentTime);
 			JSON.put("bodies", bodyList);
 			return JSON;
 	}
 	
 	public String toString()
 	{
		return getState().toString();
 		
 	}
	
}
