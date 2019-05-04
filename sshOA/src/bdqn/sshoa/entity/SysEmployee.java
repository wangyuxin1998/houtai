package bdqn.sshoa.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SysEmployee entity. @author MyEclipse Persistence Tools
 */

public class SysEmployee implements java.io.Serializable {

	// Fields

	private String sn;
	private SysDepartment sysDepartment;
	/*private Long positionId;*/
	private  SysPosition position;
	private String name;
	private String password;
	private String status;
	private Set bizClaimVouchersForNextDealSn = new HashSet(0);
	private Set bizClaimVouchersForCreateSn = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysEmployee() {
	}

	/** minimal constructor */
	public SysEmployee(SysDepartment sysDepartment,
			String name, String password, String status) {
		this.sysDepartment = sysDepartment;
		this.name = name;
		this.password = password;
		this.status = status;
	}

	/** full constructor */
	public SysEmployee(SysDepartment sysDepartment,
			String name, String password, String status,
			Set bizClaimVouchersForNextDealSn, Set bizClaimVouchersForCreateSn) {
		this.sysDepartment = sysDepartment;
		this.name = name;
		this.password = password;
		this.status = status;
		this.bizClaimVouchersForNextDealSn = bizClaimVouchersForNextDealSn;
		this.bizClaimVouchersForCreateSn = bizClaimVouchersForCreateSn;
	}

	// Property accessors

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public SysDepartment getSysDepartment() {
		return this.sysDepartment;
	}

	public void setSysDepartment(SysDepartment sysDepartment) {
		this.sysDepartment = sysDepartment;
	}


	

	public SysPosition getPosition() {
		return position;
	}

	public void setPosition(SysPosition position) {
		this.position = position;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set getBizClaimVouchersForNextDealSn() {
		return this.bizClaimVouchersForNextDealSn;
	}

	public void setBizClaimVouchersForNextDealSn(
			Set bizClaimVouchersForNextDealSn) {
		this.bizClaimVouchersForNextDealSn = bizClaimVouchersForNextDealSn;
	}

	public Set getBizClaimVouchersForCreateSn() {
		return this.bizClaimVouchersForCreateSn;
	}

	public void setBizClaimVouchersForCreateSn(Set bizClaimVouchersForCreateSn) {
		this.bizClaimVouchersForCreateSn = bizClaimVouchersForCreateSn;
	}

}