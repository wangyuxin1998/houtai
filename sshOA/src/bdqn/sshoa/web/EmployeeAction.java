package bdqn.sshoa.web;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.omg.PortableInterceptor.SUCCESSFUL;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import bdqn.sshoa.entity.SysEmployee;
import bdqn.sshoa.service.EmployeeService;

public class EmployeeAction extends ActionSupport {
	private String sn;
	private EmployeeService employeeService;
	private SysEmployee employee;
	private String random;
	HttpSession session= ServletActionContext.getRequest().getSession();
	public String fingEmpBysn(){
		employee=employeeService.findEmployee(sn);
		return Action.SUCCESS;
	}
	
	//登录验证
	public String login(){
		
		//验证验证码
		String randStr=(String) session.getAttribute("randStr");
		if(random==null||!randStr.equals(random)){
			session.setAttribute("error", "验证码错误");
			return Action.LOGIN;
		}
		
		if(employee.getSn()==""||employee.getPassword()==""){
			session.setAttribute("error", "账号或密码不能为空");
			return Action.LOGIN;
		}
		int i=employeeService.findEmployeeLogins(employee.getSn(), employee.getPassword());
		if(i==1){
			SysEmployee emp=employeeService.findEmployeeLogin(employee.getSn(), employee.getPassword());
			session.setAttribute("emp", emp);	
		}
		else if(i==-1){
			session.setAttribute("error", "密码错误");
			return Action.LOGIN;
		}
		else if(i==0){
			session.setAttribute("error", "账号错误");
			return Action.LOGIN;
		}
		return Action.SUCCESS;
	}
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}


	public SysEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(SysEmployee employee) {
		this.employee = employee;
	}
	public EmployeeService getEmployeeService() {
		return employeeService;
	}


	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	
	
}
