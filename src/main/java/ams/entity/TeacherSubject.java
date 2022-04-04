package ams.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "teacher_subject")
public class TeacherSubject {
	
	@EmbeddedId
	@JsonIgnore
	private TeacherSubjectId teacherSubjectId;
	
	@OneToOne
    @JoinColumn(name = "sub_id", insertable = false, updatable = false, nullable = false)
    private SectionSubject subject;

	public TeacherSubject(Long teachId, Long subId) {
		super();
		this.teacherSubjectId = new TeacherSubjectId(teachId, subId);
	}
}
