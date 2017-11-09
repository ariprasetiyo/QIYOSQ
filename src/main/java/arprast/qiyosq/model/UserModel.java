/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "sys_user")
public class UserModel extends ModelSerializable {

	private static final long serialVersionUID = 2432434267482377275L;

	@Column(length = 50, nullable = false)
	@NotNull
	@NotBlank
	private String username;

	@Column(length = 50, nullable = false)
	@NotNull
	@NotBlank
	private String name;

	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String password;

	@Column(length = 30)
	@Email
	@NotBlank
	private String email;

	@Column(name = "no_hp", length = 13)
	@NotNull
	@NotBlank
	@Size(min = 10, max = 13)
	private String noHp;

	@Column(name = "is_active")
	@NotNull
	@NotBlank
	private boolean isActive;

	// @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// @JoinColumn(nullable = true)
	// private SysRoles SysUserRoles;
	//
	// public SysRoles getSysUserRoles() {
	// return SysUserRoles;
	// }
	//
	// public void setSysUserRoles(SysRoles SysUserRoles) {
	// this.SysUserRoles = SysUserRoles;
	// }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNoHp() {
		return noHp;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public boolean isIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "UserModel [username=" + username + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", noHp=" + noHp + ", isActive=" + isActive + "]";
	}

}
