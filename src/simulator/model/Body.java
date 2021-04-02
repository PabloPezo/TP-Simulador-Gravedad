package simulator.model;

import org.json.JSONObject;
import simulator.misc.Vector2D;

public class Body {
	
	protected String _id;
	protected Vector2D _vel, _force, _pos;
	protected double _mass;
	private JSONObject js = new JSONObject();
	
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
		_force.plus(f);
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
			accel = getForce().scale(1 / _mass);
		}
		_pos = _pos.plus((_vel.scale(t)).plus(accel.scale((1/2) * t * t)));
		_vel = _vel.plus(accel.scale(t));
	}
	
	public JSONObject getState()
	{
			js.put("id",getId());
			js.put("m",getMass());
			js.put("p",getPosition().asJSONArray());
			js.put("v",getVelocity().asJSONArray());
			js.put("f",getForce().asJSONArray());
		
		return js;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Body b = (Body) obj;
		
		if (obj != null)
		{
			if (obj == this)
			{
				return true;
			}
			else
			{
				if (b._id == null && _id == null)
				{
					return true;
				}
				else if (!_id.equals(b._id))
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString()
	{
		return getState().toString();
	}
	
}
