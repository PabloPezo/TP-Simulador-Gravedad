package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class Controller 
{
	private PhysicsSimulator _phySimulator;
	private Factory<Body> _bodiesFactory;
	private Factory<ForceLaws> _forceFactory;

	public Controller(PhysicsSimulator phySimulator, Factory<Body> bodiesFactory, Factory<ForceLaws> forceFactory)
	{
		_phySimulator = phySimulator;
		_bodiesFactory = bodiesFactory;
		_forceFactory = forceFactory;
	}

	public void loadBodies(InputStream in)  
	{
		JSONObject js = new JSONObject(new JSONTokener(in));
		JSONArray bodies = js.getJSONArray("bodies");

		for(int i = 0; i < bodies.length(); i++)
		{
			_phySimulator.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));
		}
	}	

	public void run(int steps, OutputStream out, InputStream expOut, StateComparator cmp) throws NotEqualStatesException 
	{
		JSONObject expOutJO = null;

		if (expOut != null)
		{
			expOutJO = new JSONObject(new JSONTokener(expOut));
		}

		if (out == null)
		{
			out = new OutputStream() { public void write(int b) throws IOException {}};
		}

		PrintStream pr = new PrintStream(out);

		pr.println("{");
		pr.println("\"states\": [");

		JSONObject expectatedState = null;
		JSONObject currentState = null;

		currentState = _phySimulator.getState();

		pr.println(currentState);
		pr.print(",");

		if(expOutJO != null)
		{
			expectatedState = expOutJO.getJSONArray("states").getJSONObject(0);
			if(!cmp.equal(expectatedState, currentState)) throw new NotEqualStatesException(expectatedState, currentState, expectatedState, currentState, 0);
		}

		for(int i=1; i <= steps; i++)
		{
			_phySimulator.advance();
			currentState = _phySimulator.getState();

			pr.println( currentState);

			if(i != steps) { pr.print(","); }

			if(expOutJO != null)
			{
				expectatedState = expOutJO.getJSONArray("states").getJSONObject(i);
				if(!cmp.equal(expectatedState, currentState)) throw new NotEqualStatesException(expectatedState, currentState, expectatedState, currentState, i);
			}
		}
		pr.println("]");
		pr.println("}");
	}

	public void run(int steps) // Método run para la interfaz gráfica. Tal como esta configurada la práctica ahora solo se ejecuta run(1), pero esta preparado para ejecutar run(n)
	{
		for(int i=1; i <= steps; i++)
		{
			_phySimulator.advance();
		}
	}

	public void reset()
	{
		_phySimulator.reset();
	}

	public void setDeltaTime(double dt) 
	{
		_phySimulator.setDeltaTime(dt);
	}

	public void addObserver(SimulatorObserver o)
	{
		_phySimulator.addObserver(o);
	}	

	public List<JSONObject> getForceLawsInfo()
	{
		return _forceFactory.getInfo();
	}

	public void setForceLaws(JSONObject info)
<<<<<<< HEAD
	{				
=======
	{		
>>>>>>> a66a939714571b6011c83a166608524477c7c80d
		_phySimulator.setForceLaws(_forceFactory.createInstance(info));
	}
}
