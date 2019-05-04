package bdqn.sshoa.util;

import java.util.List;

/**
 * ��ҳ�ķ�װ��
 * @author Administrator
 *
 */
public class PageTurn<T> {
	private int pageSize=0;//ҳ��С
	private int pageIndex=1;//��ǰҳ
	private int totalCount;//�ܼ�¼����
	private int totalPage;//��ҳ��
	private List<T>list;
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		if(totalCount%pageSize==0)
		{
			totalPage=totalCount/pageSize;
		}
		else{
			totalPage=totalCount/pageSize+1;
		}
		return totalPage;
	}
	
	
}
