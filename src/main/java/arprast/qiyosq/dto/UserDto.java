/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dto;

import java.util.Arrays;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 *
 * @author ari-prasetiyo
 */
public class UserDto extends Dto {

	@Size(min = 2, max = 50)
	@NotNull
    private String username;

	@Size(min = 2, max = 50)
	@NotNull
    private String name;

	@Size(min = 2, max = 50)
	@NotNull
    private String password;

	@NotNull
	@Email
    private String email;

	@Size(min = 10, max = 13)
	@NotNull
    private String noHp;

	@NotNull
	private boolean isActive;

    private String roleName;

    private long[] roleId;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public long[] getRoleId() {
        return roleId;
    }

    public void setRoleId(long[] roleId) {
        this.roleId = roleId;
    }

	@Override
	public String toString() {
		return "UserDto [username=" + username + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", noHp=" + noHp + ", isActive=" + isActive + ", roleName=" + roleName + ", roleId="
				+ Arrays.toString(roleId) + ", url=" + url + "]";
	}
   
}
