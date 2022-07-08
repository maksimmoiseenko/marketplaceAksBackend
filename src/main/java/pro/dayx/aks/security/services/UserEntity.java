package pro.dayx.aks.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import pro.dayx.aks.models.OrderEntity;
import pro.dayx.aks.models.SuggestionEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name="user", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String email;
	private String lastname;
	private String firstname;
	private String patronymic;
	private String sex;
	private String role;
	@Column(nullable = false)
	private String password;
	@OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SuggestionEntity> suggestionEntities = new ArrayList<>();
	@OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderEntity> orderEntities = new ArrayList<>();

	public UserEntity(String email, String password) {
		this.email = email;
		this.password = password;
	}


	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
	}
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserEntity user = (UserEntity) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public String toString() {
		String object = "{" +
				"\"id\":" + id +
				", \"email\":\"" + email + "\"" +
				", \"role\":\"" + role + "\"" ;
		if(lastname != null) object+= ", \"lastname\":\"" + lastname + "\"";
		if(firstname != null) object+= ", \"firstname\":\"" + firstname + "\"";
		if(patronymic != null) object+= ", \"patronymic\":\"" + patronymic + "\"";
		if(sex != null) object+= ", \"sex\":\"" + sex + "\"";
		object += '}';

		return object;
	}
}
