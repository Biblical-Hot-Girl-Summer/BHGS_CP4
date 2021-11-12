package compiler;

public class variable{

	public String variableType = "";
	public String value = "";
	public int memLoc = -1;
	public boolean valueSet = false;


	public variable(String type, String val){
		variableType = type;
		value = val;
	
	}
	
	public variable(){
		variableType = "";
		value = "";
	}


}
