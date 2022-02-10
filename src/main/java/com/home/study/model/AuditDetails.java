package com.home.study.model;

/**
 * @author crlabrada10
 *
 */
public class AuditDetails {

	private String createBy;
	private String rolename;

	public AuditDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuditDetails(String createBy, String rolename) {
		super();
		this.createBy = createBy;
		this.rolename = rolename;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	
}
