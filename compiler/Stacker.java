package compiler;

/**
 * Stacker object that in many ways just acts as a stand in for the java stack
 * but allowing for more control over its functions via methods within kcc
 * @author Jacob Morris
 * @author Aaron Bone
 * @version 1.0
 * Compiler Assignment 4
 * CS322 - Compiler Construction
 * Fall 2021
 *
 */

public class Stacker {

	public int[]stack;
	public int head;


	/*
	 * Empty constructor
	 */
	public Stacker(){
		stack = new int[30];
		head = 0;
	}
}
