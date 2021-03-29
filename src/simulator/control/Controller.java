package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Controller {
// RECIBE DOS OBJETOS 
	/*
	METODOS
	
	public void loadBodies(InputStream in);
	public void run(int n,OutputStream out,InputStream expOut,StateComparator cmp);
	
	
	*/
	
	
	public void run(int steps,OutputStream out,InputStream expOut,StateComparator cmp)
	{
		JSONObject expOutJO=null;
		
		if(expOut!=null)
		{
			expOutJO=new JSONObject (new JSONTokener(expOut));
			//si out es null usamos un output stream que no pinta nada
			if(out==null)
			{
				out=new OutputStream()
						{
							public void write(int b)throws IOException{}
							
						};
			}
			PrintStream p=new PrintStream(out);
			p.println("{");
			p.println(" \"states\":[");
		}
	}
}
