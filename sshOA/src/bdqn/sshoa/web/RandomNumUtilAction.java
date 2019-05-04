package bdqn.sshoa.web;

import java.io.ByteArrayInputStream;







import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import bdqn.sshoa.util.RandomNumUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *ÑéÖ¤Âëaction
 * @author Administrator
 *
 */
public class RandomNumUtilAction  extends ActionSupport{
	private  ByteArrayInputStream inputStream;
	@Override
	public String execute() throws Exception {
		RandomNumUtil randUtil=RandomNumUtil.Instance();
		inputStream=randUtil.getImage();
		String ranstr=randUtil.getString();
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("randStr", ranstr);
		return super.execute();
	}
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
	
}
