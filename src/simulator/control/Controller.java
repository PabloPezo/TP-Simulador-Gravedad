package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {
	
	private PhysicsSimulator _phySimulator;
	private Factory<Body> _bodiesFactory;
	
	public Controller(PhysicsSimulator phySimulator, Factory<Body> bodiesFactory)
	{
		_phySimulator = phySimulator;
		_bodiesFactory = bodiesFactory;
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
		
		if(expOut != null)
		{
			expOutJO = new JSONObject (new JSONTokener(expOut));
		}
		
		if(out == null)
		{
			out = new OutputStream() { public void write(int b)throws IOException {}};
		}
		
		PrintStream p = new PrintStream(out);
		p.println("{");
		p.println("\"states\": [");
			
		JSONObject expectatedState = null;
		JSONObject currentState = null;
		
		currentState = _phySimulator.getState();
        p.println(currentState);
        
        if(expOutJO != null)
        {
        	expectatedState = expOutJO.getJSONArray("states").getJSONObject(0);
            if(!cmp.equal(expectatedState, currentState))throw new NotEqualStatesException(expectatedState, currentState, 0);
        }
        
        for(int i=1; i<expOutJO.getJSONArray("states").length(); i++)
        {
        	expectatedState = expOutJO.getJSONArray("states").getJSONObject(i);
            if(!cmp.equal(expectatedState, currentState)) throw new NotEqualStatesException(expectatedState, currentState, i);
        }
        //comparacion del resto de pasos mediante un for 
        // lanzar excepcion en caso de que la comparacion se solicite y 
        // los estados no sean iguales

        p.println("]");
        p.println("}");
    }	
}
