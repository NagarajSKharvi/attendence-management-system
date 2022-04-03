package ams.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "section_subject")
public class SectionSubject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id")
	private Long subjectId;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@JsonIgnore
	@Column(name = "section_id")
	private Long sectionId;
	
	@Column(name = "sem_id")
	private Long semId;
	
	@ManyToOne
	@JoinColumn(name = "section_id", insertable = false, updatable = false, nullable = false)
	private ClassSection classSection;

	@ManyToOne
	@JoinColumn(name = "sem_id", insertable = false, updatable = false, nullable = false)
	private Semester semester;
	
	public SectionSubject(String subjectName, Long sectionId, Long semId) {
		super();
		this.subjectName = subjectName;
		this.sectionId = sectionId;
		this.semId = semId;
	}
}