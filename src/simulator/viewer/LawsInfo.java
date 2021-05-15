package simulator.viewer;

public class LawsInfo 
{
	private String _key;
	private String _value;
	private String _desc;
	
	//Una ley de fuerza tiene estos 3 parámetros de los cuales solo el value es modificable desde la tabla
	public LawsInfo(String key, String value, String desc)
	{
		_key = key;
		_value = value;
		_desc = desc;
	}

	public String getKey()
	{
		return _key;
	}
	
	public String getValue()
	{
		return _value;
	}
	
	public String getDesc()
	{
		return _desc;
	}
	
	public void setValue(String v)
	{
		_value = v;
	}
}
