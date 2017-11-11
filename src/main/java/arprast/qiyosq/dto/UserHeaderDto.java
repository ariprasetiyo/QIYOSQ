/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ari-prasetiyo
 */
public class UserHeaderDto {

	private float totalRecord;
	private List<UserDto> listSysUserDto = new ArrayList<UserDto>();

	public float getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(float totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<UserDto> getListSysUserDto() {
		return listSysUserDto;
	}

	public void setListSysUserDto(List<UserDto> listSysUserDto) {
		this.listSysUserDto = listSysUserDto;
	}

	@Override
	public String toString() {
		return "UserHeaderDto [totalRecord=" + totalRecord + ", listSysUserDto=" + listSysUserDto + "]";
	}

}
