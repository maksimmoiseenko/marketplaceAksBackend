package pro.dayx.aks.payload.response;

import lombok.ToString;

@ToString
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String email;
	private String role;
	private String lastname;
	private String firstname;
	private String patronymic;
	private String sex;

	public JwtResponse(String accessToken, Long id, String email, String role,String firstname, String lastname, String patronymic, String sex) {
		this.token = accessToken;
		this.id = id;
		this.email = email;
		this.role = role;
		this.firstname = firstname;
		this.lastname = lastname;
		this.patronymic = patronymic;
		this.sex = sex;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

}
