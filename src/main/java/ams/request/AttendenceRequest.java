package ams.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttendenceRequest {

	private LocalDate date;
	private Long subjectId;
	private Long teachId;
	private Long periodId;

	private List<StudentAttendanceRequest> studentAttendanceRequests;
}
