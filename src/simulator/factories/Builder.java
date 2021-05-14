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

	protected abstract T createTheInstance(JSONObject jsonObject) throws IllegalArgumentException; // Mét
	
	protected JSONObject createData() 
	{
		return new JSONObject();
	}
	
	public T createInstance(JSONObject js) 	// Devuelve un objeto JSON que sirve de plantilla para el correspondiente constructor, i.e., un valor válido para el parámetro de createInstance
	{
		T b = null;
		if(_typeTag != null && _typeTag.equals(js.get("type")))
		{
			b = createTheInstance(js);
		}

		return b;
	}
	
	public JSONObject getBuilderInfo()  
	{
		JSONObject info = new JSONObject();
		info.put("type", _typeTag);
<<<<<<< HEAD
//		System.out.println("queremos" + this.createData());
//		System.out.println("queremos2" + createData());
=======
>>>>>>> 68e40753efad3879c00c6efe1345de561479a562
		info.put("data", this.createData()); //Puse el toString para que no salga como que no es string je
		info.put("desc",_desc);
		return info;
	}	
}
