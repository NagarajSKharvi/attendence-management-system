package ams.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import ams.entity.ClassPeriod;
import ams.entity.SectionSubject;
import ams.entity.Teacher;
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
public class AttendanceResponse {
	
	private Long attendanceId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	private ClassPeriod classPeriod;
	private SectionSubject sectionSubject;
	private Teacher teacher;
	private List<StudentAttendanceResponse> studentAttendanceResponses;
}
