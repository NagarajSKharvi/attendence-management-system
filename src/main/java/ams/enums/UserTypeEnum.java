package ams.enums;

public enum UserTypeEnum {
	
	STUDENT("student"),
	TEACHER("teacher"),
	ADMIN("admin");
	
	String description;
	
	UserTypeEnum() {}

	UserTypeEnum(String description) {
		this.description = description;
	}
}
