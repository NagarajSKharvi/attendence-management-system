package ams.response;

import java.time.LocalDate;

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
public class SAttendanceResponse {
	
	private Long attendanceId;
	private LocalDate date;
	private ClassPeriod classPeriod;
	private SectionSubject sectionSubject;
	private Teacher teacher;
	private String isPresent;
}
