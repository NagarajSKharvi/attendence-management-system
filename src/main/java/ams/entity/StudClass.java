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
@Table(name = "stud_class")
public class StudClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_id")
	private Long classId;
	
	@Column(name = "class_name")
	private String className;
	
	@Column(name = "class_year")
	private Integer classYear;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	public StudClass(String className, Integer classYear, LocalDate startDate, LocalDate endDate) {
		super();
		this.className = className;
		this.classYear = classYear;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
