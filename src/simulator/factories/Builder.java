package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {

	protected  String _typeTag;
	protected String _desc; // para la parte 2 
	
	public Builder()
	{
		
	}
	
	public Builder(String typeTag, String desc)
	{
		super();
		_typeTag = typeTag;
		_desc = desc;
	}
	
	protected abstract T createTheInstance(JSONObject jsonObject);
	
	protected JSONObject createData()
	{
		//return new JSONObject();
		return null;	//NO SE CUAL DE LOS DOS JE
	}
	
	public T createInstance(JSONObject js)
	{
		T b=null;
		if(_typeTag != null && _typeTag.equals(js.get("type")))
		{
			b=createTheInstance(js.getJSONObject("data"));
		}
		return b;
	}
	
	public JSONObject getBuilderInfo()
	{
		JSONObject info=new JSONObject();
		info.put("type", _typeTag);
		info.put("data", createData()); 
		info.put("desc",_desc);
		return info;
	}
	

	
	
}
