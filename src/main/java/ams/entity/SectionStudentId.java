package ams.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionStudentId implements Serializable {
	
	@Column(name = "section_id")
	private Long sectionId;
	
	@Column(name = "stud_id")
	private Long studId;
}