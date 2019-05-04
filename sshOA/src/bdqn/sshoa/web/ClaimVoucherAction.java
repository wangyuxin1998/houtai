package bdqn.sshoa.web;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import bdqn.sshoa.entity.BizCheckResult;
import bdqn.sshoa.entity.BizClaimVoucher;
import bdqn.sshoa.entity.BizClaimVoucherDetail;
import bdqn.sshoa.entity.SysEmployee;
import bdqn.sshoa.service.BizClaimVoucherService;
import bdqn.sshoa.service.EmployeeService;
import bdqn.sshoa.util.ClaimCondition;
import bdqn.sshoa.util.Constrants;
import bdqn.sshoa.util.PageTurn;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ClaimVoucherAction extends ActionSupport {
	private BizClaimVoucher claimVoucher;
	private List<BizClaimVoucherDetail>detailList;
	BizClaimVoucherService bizClaimVoucherService;
	EmployeeService employeeService;
	//显示报销单列表
	private Date startDate;
	private Date endDate;
	private String status;
	private PageTurn<BizClaimVoucher>pageSupport;
	private int pageNo;
	private Map<String,String>statusMap;
	//审核报销单数据
	private BizCheckResult checkResult;
	
	HttpSession session=ServletActionContext.getRequest().getSession();
	
	//显示报销单页面
	public String  claimVoucher_toCheck(){
		claimVoucher=bizClaimVoucherService.findBizClaimVoucherId(claimVoucher.getId());
		return Action.SUCCESS;
	}
	//处理审核请求
	public String doClaimVoucherCheck(){
		
		//审核时间
		checkResult.setCheckTime(new Date());
		//要审核的报销单
		BizClaimVoucher bizClaimVoucher=bizClaimVoucherService.findBizClaimVoucherId(claimVoucher.getId());
		checkResult.setBizClaimVoucher(bizClaimVoucher);
		//审核人
		SysEmployee emp=(SysEmployee) session.getAttribute("emp");
		checkResult.setCheckEmployee(emp);
		
		bizClaimVoucherService.checkClaimVoucher(checkResult);
		
		return Action.SUCCESS;
	}
	
	//显示添加报销单
	public String toAdd(){
		return Action.SUCCESS;
	}
	
	//显示查看报销单
	public String searchClaimVoucher(){
		ClaimCondition condition=new ClaimCondition();
		SysEmployee emp=(SysEmployee) session.getAttribute("emp");
		condition.setEmpSn(emp.getSn());
		condition.setStatus(status);
		condition.setEndDate(endDate);
		condition.setStartDate(startDate);
		int pageSize= Integer.parseInt(Constrants.PAGESIZE);
		if(pageNo==0){
			pageNo=1;
		}
		pageSupport=bizClaimVoucherService.finBizClaimVoucher(condition,pageNo,pageSize);
		statusMap=bizClaimVoucherService.finStatusMap();
		return Action.SUCCESS;
	}
	
	
	//级联添加报销单和明细
	public String addBizClaimVoucher(){
		//创建人
		SysEmployee employee=(SysEmployee) ServletActionContext.getRequest().getSession().getAttribute("emp");
		claimVoucher.setSysEmployeeByCreateSn(employee);
		//创建时间
		claimVoucher.setCreateTime(new Date());
		//修改时间
		claimVoucher.setModifyTime(new Date());
		//获取现在的状态
		String status=claimVoucher.getStatus();
		claimVoucher.setStatus(status);
		if(Constrants.NEWCREATE.equals(status)){//保存
			claimVoucher.setSysEmployeeByNextDealSn(employee);
		}else if(Constrants.SUBMIT.equals(status)){//提交
			claimVoucher.setSysEmployeeByNextDealSn(employeeService.findMannager(employee, Constrants.MANAGER));
		}
		//添加关联 报销单关联报销单明细
		claimVoucher.setBizClaimVoucherDetails(new HashSet<BizClaimVoucherDetail>(detailList));
		//添加方法
		bizClaimVoucherService.addBizClaimVouche(claimVoucher);
		return Action.SUCCESS;	
	}

	
	public BizClaimVoucher getClaimVoucher() {
		return claimVoucher;
	}

	public void setClaimVoucher(BizClaimVoucher claimVoucher) {
		this.claimVoucher = claimVoucher;
	}

	public List<BizClaimVoucherDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<BizClaimVoucherDetail> detailList) {
		this.detailList = detailList;
	}

	public BizClaimVoucherService getBizClaimVoucherService() {
		return bizClaimVoucherService;
	}

	public void setBizClaimVoucherService(
			BizClaimVoucherService bizClaimVoucherService) {
		this.bizClaimVoucherService = bizClaimVoucherService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PageTurn<BizClaimVoucher> getPageSupport() {
		return pageSupport;
	}

	public void setPageSupport(PageTurn<BizClaimVoucher> pageSupport) {
		this.pageSupport = pageSupport;
	}

	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}
	public BizCheckResult getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(BizCheckResult checkResult) {
		this.checkResult = checkResult;
	}
	
}
