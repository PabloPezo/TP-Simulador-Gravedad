package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {

	private  String _typeTag;
	protected String _desc; // para la parte 2 
	
	
	
	public T createInstance(JSONObject info)// en cada clase se sabe que new hacer por ejemple en BasicBodyBuilder un new Body
	{
		T b=null;
		if(_typeTag!=null&&_typeTag.equals(info.get("type")))
		{
			b=createTheInstance(info.getJSONObject("data"));
		}
		return b;
	}
	protected abstract T createTheInstance(JSONObject jsonObject);
	
	
	public JSONObject getBuilderInfo()
	{
		JSONObject info=new JSONObject();
		info.put("type", _typeTag);
		info.put("data", createData()); 
		info.put("desc",_desc);
		return info;
	}
	
	
	protected JSONObject createData()
	{
		return new JSONObject();
	}
	
	
}
