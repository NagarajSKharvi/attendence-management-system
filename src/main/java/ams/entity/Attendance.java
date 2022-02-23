package ams.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	
	@ElementCollection
	@Column(name = "s_ids")
	private Set<Long> sIds;

	public Attendance(Long periodId, Long subjectId, Long teachId, LocalDate date, Set<Long> sIds) {
		super();
		this.periodId = periodId;
		this.subjectId = subjectId;
		this.teachId = teachId;
		this.date = date;
		this.sIds = sIds;
	}
}