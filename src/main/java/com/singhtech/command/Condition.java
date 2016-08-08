package com.singhtech.command;

import com.singhtech.model.Path;

import java.util.Map;
import java.util.TreeMap;

public class Condition {
	private int value;
	private Operation operation;

	public boolean evaluate(Path path) {
		boolean rc;
		if (operation != null) {
			return operation.evaluate(path, value);
		} else {
			throw new IllegalArgumentException("Opration cannot be null");
		}
	}

	/**
	 * Constructs a Condition object that can be evaluated against a given Path
	 * later. 
	 * @param value to be used in the comparison
	 * @param operation the operation need to perform.
	 */
	public Condition(int value, Operation operation) {
		this.operation = operation;
		
		if (this.operation == null) {
			throw new IllegalArgumentException("unknown operator");
		}
		this.value  = value;
	}
}
