package simulator.factories;

import org.json.JSONObject;


public abstract class Builder<T> 
{
	protected  String _typeTag;
	protected String _desc;
	
	public Builder() {}
	
	public Builder(String typeTag, String desc)
	{
		super();
		_typeTag = typeTag;
		_desc = desc;
	}

	protected abstract T createTheInstance(JSONObject jsonObject);
	
	protected JSONObject createData()
	{
		return null;
	}
	
	public T createInstance(JSONObject js) throws IllegalArgumentException // Cambiado por Pezo 3/4 siguiendo el enunciado. Falta la excepcion
	{
		try
		{
			T b = null;
			if(_typeTag != null && _typeTag.equals(js.get("type")))
			{
				b = createTheInstance(js.getJSONObject("data"));
			}

			return b;
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Illegal arguments");
		}
	}
	
	public JSONObject getBuilderInfo()
	{
		JSONObject info = new JSONObject();
		info.put("type", _typeTag);
		info.put("data", createData()); 
		info.put("desc",_desc);
		return info;
	}	
}
