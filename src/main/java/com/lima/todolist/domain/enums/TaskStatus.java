package com.lima.todolist.domain.enums;

public enum TaskStatus {
	
	TODO(1, "TODO"),
	DONE(2, "DONE");
	
	private int code;
	private String description;
	
	private TaskStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static TaskStatus toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for (TaskStatus status : TaskStatus.values()) {
			if (code.equals(status.getCode())) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + code);
	}
	
	public static TaskStatus toEnum(String description) {
		if (description == null) {
			return null;
		}
		
		for (TaskStatus status : TaskStatus.values()) {
			if (description.equalsIgnoreCase(status.getDescription())) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + description);
	}
}
