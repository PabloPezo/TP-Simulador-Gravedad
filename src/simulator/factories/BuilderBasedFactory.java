package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory{	//COPIADO TODO DE GONZALO ASI QUE NO SE QUE HACER
	List<Builder<T>> builders;
	List<JSONObject> factoryElements;
	
	public BuilderBasedFactory(List<Builder<T>> builders)
	{
		builders = new ArrayList<Builder<T>>();
		for (Builder<T> builder : builders)
		{
			factoryElements.add(builder.getBuilderInfo());
		}
	}

	@Override
	public Object createInstance(JSONObject info) {
		Object obj = null;
		int i = 0;
		boolean exito = false;
		if (info == null)
		{
			throw new IllegalArgumentException("Invalid value of createInstance: null");
		}
		while(i < builders.size() && !exito)
		{
			obj = builders.get(i).createInstance(info);
			if (obj != null)
			{
				exito = true;
			}
			i++;
		}
		if (obj == null)
		{
			throw new IllegalArgumentException("Null object");
		}
		else
		{
			return obj;
		}
	}

	@Override
	public java.util.List getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
