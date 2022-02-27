package ams.request;

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
public class StudentAttendanceRequest {
	
	private Long studentId;
	private boolean present;
	
	public StudentAttendanceRequest(Long studentId) {
		super();
		this.studentId = studentId;
	}
}
