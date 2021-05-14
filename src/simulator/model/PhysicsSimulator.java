package simulator.model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class PhysicsSimulator
{
	private double _realTime;
	private List <Body>bodyList;
	private List <SimulatorObserver> observers;
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
		this.observers = new ArrayList<SimulatorObserver>();
		this.bodyList = new ArrayList<Body>();
	}

	public void advance()	// Aplica un paso de simulación
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
		_currentTime += _realTime;

		for (int i = 0; i < observers.size(); i++)
		{
			observers.get(i).onAdvance(bodyList, _currentTime);
		}

	}

	public void addBody(Body b) throws IllegalArgumentException		// Añade el cuerpo b al simulador
	{
		if (!bodyList.contains(b))
		{
			bodyList.add(b);
			if(observers != null && observers.size() != 0)
			{
				observers.get(observers.size() - 1).onBodyAdded(bodyList, b); 
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public JSONObject getState() 
	{
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();

		for (Body body : bodyList)
			arr.put(body.getState());

		obj.put("bodies", arr);
		obj.put("time", _currentTime);
		return obj;
	}

	public String toString() { return getState().toString(); }

	public void reset()
	{
		this.bodyList = new ArrayList<Body>();
		_currentTime = 0.0;

		for (int i = 0; i < observers.size(); i++)
		{
			observers.get(i).onReset(bodyList, _currentTime, _currentTime, null);
		}
	}

	public void setDeltaTime(double dt) throws IllegalArgumentException
	{
		if (dt < 0.0) throw new IllegalArgumentException();
		{
			_realTime = dt;
		}

		for (int i = 0; i < observers.size(); i++)
		{
			observers.get(i).onDeltaTimeChanged(dt);
		}
	}

	public void setForceLaws(ForceLaws forceLaws) throws IllegalArgumentException
	{
		if(forceLaws == null) {throw new IllegalArgumentException();}
		_forceLaws = forceLaws;

		for (int i = 0; i < observers.size(); i++)
		{ 			
			observers.get(i).onForceLawsChanged(forceLaws.toString());
		}
	}

	public void addObserver(SimulatorObserver o)	
	{

		observers.add(o);
		o.onRegister(bodyList, _currentTime, _currentTime, null);
	}
}
