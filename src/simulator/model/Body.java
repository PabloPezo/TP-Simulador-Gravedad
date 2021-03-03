package simulator.model;
//Masacao de quicio tp
//Puto el de tp
import org.json.JSONObject;

import simulator.misc.Vector2D;

public class Body {
	
	protected String id;
	protected Vector2D pos;
	protected Vector2D vel;
	protected Vector2D force;
	protected double mass;
	
	public Body(String id, Vector2D v, Vector2D p, double m)
	{
		this.id = id;
		mass  = m;
		pos   = new Vector2D(p);
		vel   = new Vector2D(v);
		force = new Vector2D();		
	}
	
		public String getId()	// Identificador del cuerpo
		{
			return id;
		}
		
		public double getMass()	// Masa del cuerpo
		{
			return mass;
		}
		
		public Vector2D getPosition()	// Vector de posicion
		{
			return pos;
		}
		
		public Vector2D getVelocity()	// Vector de velocidad
		{
			return vel;
		}
		
		public Vector2D getForce()	// Vector de fuerza 
		{
			return force;
		}
		
		void resetForce()
		{
			force=new Vector2D();
		}
		
		void addForce (Vector2D f)	// Sumar fuerza
		{
			force = force.plus(f);
		}
		
		void move(double t)
		{
			Vector2D accel;
			
			if (mass == 0)
			{
				accel = new Vector2D();
			}
			else 
			{
				accel = new Vector2D(force.scale(1 / mass));
			}
			
			pos = pos.plus((vel.scale(t)).plus(accel.scale( t * t / 2 )));
			vel = vel.plus(accel.scale(t));
		}
		
		public JSONObject getState()
		{
			JSONObject jso = new JSONObject();
			
			jso.put("id", id);
			jso.put("m", mass);
			jso.put("p", pos);
			jso.put("v", vel);
			jso.put("f", force);
			
			return jso;
		}
		
		public String toString()
		{
			return getState().toString();
		}	
}