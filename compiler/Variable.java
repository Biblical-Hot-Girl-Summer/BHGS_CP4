package compiler;

public class Variable {
	public String variableType = "";
	public String value = "";
	public int memLoc = -1;
	public boolean valueSet = false;


	public Variable(String type, String val){
		variableType = type;
		value = val;
	
	}
	
	public Variable(){
		variableType = "";
		value = "";
	}
	
	public boolean isString(){
		
		if(variableType.equals("STRING"))
			return true;
		return false;
	}

}
