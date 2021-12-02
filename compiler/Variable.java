package compiler;

/**
 * Object that served the purpose of holding the value of the type of 
 * variable that may be found in the KC files as well as what the
 * variables may contain.
 * @author Jacob Morris
 * @author Aaron Bone
 * @version 1.0
 * Compiler Assignment 4
 * CS322 - Compiler Construction
 * Fall 2021
 *
 */

public class Variable {
	public String variableType;
	public String value;
	public int memLoc;
	public boolean valueSet;


	/*
	 * Variable constructor take takes in what type of variable object the c
	 * constructor will be as well as ofcourse the value of the KC variable.
	 */
	public Variable(String type, String val){
		variableType = type;
		value = val;
		memLoc = -1;
		valueSet = false;
	
	}
	
	/*
	 * Empty constructor with default values being set
	 */
	public Variable(){
		variableType = "";
		value = "";
		memLoc = -1;
		valueSet = false;
	}
	
	/*
	 * Method that determines the data type the value object may hold (specifically
	 * if it's a string data type (and if not, implictly if it's an integer))
	 */
	public boolean isString(){
		
		if(variableType.equals("STRING"))
			return true;
		else {
			return false;
		}
	}

}
