/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dto;

/**
 *
 * @author ari-prasetiyo
 */
public class RolesDto extends Dto {

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	@Override
	public String toString() {
		return "RolesDto [roleName=" + roleName + ", getId()=" + getId() + "]";
	}
    
}
