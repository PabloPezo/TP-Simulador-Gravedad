package simulator.model;


import simulator.misc.Vector2D;

public class MassLossingBody extends Body{

	protected double _contador;
	protected double _lossFactor;
	protected double _lossFrequency;
	
	public MassLossingBody(String id, Vector2D v, Vector2D p, double m, double lossFactor, double lossFrequency) {
		super(id, v, p, m);
		
		_contador = 0.0;
		_lossFactor = lossFactor;
		_lossFrequency = lossFrequency;
		
	}
	
	@Override
	void move(double t)
	{
		super.move(t);
		
		_contador += t;
		
		if (_lossFrequency <= _contador)
		{
			mass = mass * (1 - _lossFactor);
			_contador = 0.0;
		}
	}
}
