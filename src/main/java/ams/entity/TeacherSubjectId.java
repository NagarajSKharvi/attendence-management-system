package ams.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TeacherSubjectId implements Serializable {
	
	@Column(name = "teach_id")
	private Long teachId;
	
	@Column(name = "sub_id")
	private Long subId;
}