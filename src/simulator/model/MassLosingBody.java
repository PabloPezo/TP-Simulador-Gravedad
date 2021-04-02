package simulator.model;


import simulator.misc.Vector2D;

public class MassLosingBody extends Body
{
	protected double _contador, _lossFactor, _lossFrequency;
	
	public MassLosingBody(String id, Vector2D v, Vector2D p, double m, double factor, double frequency)
	{
		super(id, v, p, m);
		
		_contador = 0.0;
		_lossFactor = factor;
		_lossFrequency = frequency;
	}
	
	public double getLossFrequency() { return _lossFrequency; }
	
	public double getLossFactor() { return _lossFactor; }
	
	void move(double t)
	{
		super.move(t);
		
		_contador += t;
		
		if (_lossFrequency <= _contador)
		{
			_mass = _mass * (1 - _lossFactor);
			_contador = 0.0;
		}
	}
}
