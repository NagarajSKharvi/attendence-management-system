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
@Table(name = "class_section")
public class ClassSection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "section_id")
	private Long sectionId;
	
	@Column(name = "section_name")
	private String sectionName;
	
	@JsonIgnore
	@Column(name = "class_id")
	private Long classId;
	
	@ManyToOne
	@JoinColumn(name = "class_id", insertable = false, updatable = false, nullable = false)
	private StudClass studClass;
	
	public ClassSection(String sectionName, Long classId) {
		super();
		this.sectionName = sectionName;
		this.classId = classId;
	}
}