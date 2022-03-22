package ams.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "class_period")
public class ClassPeriod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "period_id")
	private Long periodId;
	
	@JsonFormat(pattern = "hh:mm a")
	@Column(name = "from_time")
	private LocalTime fromTime;
	
	@JsonFormat(pattern = "hh:mm a")
	@Column(name = "to_time")
	private LocalTime toTime;

	public ClassPeriod(LocalTime fromTime, LocalTime toTime) {
		super();
		this.fromTime = fromTime;
		this.toTime = toTime;
	}
}
