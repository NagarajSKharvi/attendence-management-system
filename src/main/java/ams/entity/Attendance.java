package ams.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_id")
	private Long attendanceId;

	@Column(name = "period_id")
	private Long periodId;

	@Column(name = "subject_id")
	private Long subjectId;

	@Column(name = "teach_id")
	private Long teachId;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "json", length = 1000)
	private String json;

	public Attendance(Long periodId, Long subjectId, Long teachId, LocalDate date, String json) {
		super();
		this.periodId = periodId;
		this.subjectId = subjectId;
		this.teachId = teachId;
		this.date = date;
		this.json = json;
	}
}