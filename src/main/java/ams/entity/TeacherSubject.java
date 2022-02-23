package ams.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "teacher_subject")
public class TeacherSubject {

	@Id
	@Column(name = "teach_id")
	private Long teachId;
	
	@Column(name = "sub_id")
	private Long subId;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teach_id", insertable = false, updatable = false, nullable = false)
    private Teacher teacher;
}
