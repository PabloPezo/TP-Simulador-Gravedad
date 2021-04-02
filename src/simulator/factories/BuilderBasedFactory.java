package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<Object>{	//COPIADO TODO DE GONZALO ASI QUE NO SE QUE HACER
	
	List<JSONObject> factoryElements;
	List<Builder<T>> builderList;
	
	public BuilderBasedFactory(List<Builder<T>> builderList)
	{
		builderList = new ArrayList<Builder<T>>();
		
		for (Builder<T> bu : builderList)
		{
			factoryElements.add(bu.getBuilderInfo());
		}
	}

	public Object createInstance(JSONObject js) throws IllegalArgumentException
	{
		Object object = null;
		
		if (js != null)
		{
			boolean done = false;
			int i = 0;
			
			while(!done && i < builderList.size())
			{
				object = builderList.get(i).createInstance(js);
				if (object != null)
				{
					done = true;
				}
				i++;
			}
			if (object != null)
			{
				return object;
			}
			else
			{
				throw new IllegalArgumentException("Null object");
			}
		}
		else
		{
			throw new IllegalArgumentException("Invalid value of createInstance: Null");
		}
	}

	public java.util.List getInfo() // BUILDER RELLENAR
	{
		// TODO Auto-generated method stub
		return null;
	}

}
