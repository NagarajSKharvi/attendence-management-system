package ams.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class Percentage {
	
	private String type;
	private String percentage;
	private List<Percentage> percentages;
	
	public Percentage(String type, String percentage) {
		super();
		this.type = type;
		this.percentage = percentage;
	}
}