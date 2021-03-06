package ams.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentAttendanceResponse {
	
	private Long studentId;
	private String rollNumber;
	private boolean present;
	
	public StudentAttendanceResponse(Long studentId, String rollNumber) {
		super();
		this.studentId = studentId;
		this.rollNumber = rollNumber;
	}
}
