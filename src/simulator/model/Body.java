package simulator.model;

import org.json.JSONObject;
import simulator.misc.Vector2D;

public class Body {
	
	protected String _id;
	protected Vector2D _vel;
	protected Vector2D _force;
	protected Vector2D _pos;
	protected double _mass;
	
	public Body(String id, Vector2D v, Vector2D p, double m)
	{
		_id = id;
		_mass = m;
		_pos = new Vector2D(p);
		_vel = new Vector2D(v);
		_force = new Vector2D();
	}

	public String getId()
	{
		return _id;
	}
	
	public Vector2D getVelocity()
	{
		return _vel;
	}
	
	public Vector2D getForce()
	{
		return _force;
	}
	
	public Vector2D getPosition()
	{
		return _pos;
	}
	
	public double getMass()
	{
		return _mass;
	}
	
	void addForce (Vector2D f)
	{
		_force = _force.plus(f);
	}
	
	void resetForce()
	{
		_force = new Vector2D();
	}
	
	void move(double t)
	{
		Vector2D accel;
		if (_mass == 0)
		{
			accel = new Vector2D();
		}
		else
		{
			accel = new Vector2D(_force.scale(1 / _mass));
		}
		_pos = _pos.plus((_vel.scale(t)).plus(accel.scale((1/2) * t * t)));
		_vel = _vel.plus(accel.scale(t));
	}
	
	public JSONObject getState()
	{
		JSONObject jso = new JSONObject();
		
			jso.put("id",getId());
			jso.put("m",getMass());
			jso.put("p",getPosition().asJSONArray());
			jso.put("v",getVelocity().asJSONArray());
			jso.put("f",getForce().asJSONArray());
		
		return jso;
	}
	
	public String toString()
	{
		return getState().toString();
	}
	
}
