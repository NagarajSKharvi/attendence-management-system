package ams.entity;

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
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "activated")
	private boolean activated;

	public User(Long id, String username, String password, String accountType, boolean activated) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.activated = activated;
	}
}
