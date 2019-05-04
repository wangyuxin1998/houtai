package bdqn.sshoa.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bdqn.sshoa.dao.BizCheckResultDao;
import bdqn.sshoa.dao.BizClaimVoucherDao;
import bdqn.sshoa.dao.EmployeeDao;
import bdqn.sshoa.entity.BizCheckResult;
import bdqn.sshoa.entity.BizClaimVoucher;
import bdqn.sshoa.entity.BizClaimVoucherDetail;
import bdqn.sshoa.entity.SysEmployee;
import bdqn.sshoa.service.BizClaimVoucherService;
import bdqn.sshoa.util.ClaimCondition;
import bdqn.sshoa.util.Constrants;
import bdqn.sshoa.util.PageTurn;

public class BizClaimVoucherServiceImpl implements BizClaimVoucherService {
	BizClaimVoucherDao bizClaimVoucherDao;
	BizCheckResultDao bizCheckResultDao;
	EmployeeDao empDao;
	
	@Override
	public void checkClaimVoucher(BizCheckResult bizCheckResult) {
		//1����˽���л�ȡ������
		BizClaimVoucher bizClaimVoucher=bizCheckResult.getBizClaimVoucher();
		//2�ڱ�����ʵ�����޸ı�����״̬����һ�����
		updateStatusAndNextDeal(bizClaimVoucher,bizCheckResult.getResult());
		//3����dao���޸���˽��
		bizClaimVoucherDao.updateBlizClaimVouche(bizClaimVoucher);
		//4��ӱ�������˽��
		bizCheckResultDao.addBizCheckResult(bizCheckResult);
	}
	//������˽���ͱ�����״̬��ȡ,��ȡ��˺����һ������˺�״̬
	private void updateStatusAndNextDeal(BizClaimVoucher bizClaimVoucher,String result) {
		if (Constrants.RESULT_PASS.equals(result)) {//ͨ��
			if(bizClaimVoucher.getStatus().equals(Constrants.SUBMIT)){//״̬���ύ
				if(bizClaimVoucher.getTotalAccount()>=5000){
					//��һ�����Ϊ�ܾ��� ״̬Ϊ�����
					bizClaimVoucher.setSysEmployeeByNextDealSn(empDao.findMannager(null,Constrants.GENERALMANAGER));
					bizClaimVoucher.setStatus(Constrants.WAIT);
				}else if(bizClaimVoucher.getTotalAccount()<=5000){
					//��һ�����Ϊ���� ״̬Ϊ�����
					bizClaimVoucher.setStatus(Constrants.CHECKED);
					bizClaimVoucher.setSysEmployeeByNextDealSn(empDao.findMannager(null,Constrants.CASHIER));
				}
			}else if(bizClaimVoucher.getStatus().equals(Constrants.WAIT)){//����� �����Ϊ�ܾ���
				//��һ�����Ϊ���� ״̬Ϊ�����
				bizClaimVoucher.setStatus(Constrants.CHECKED);
				bizClaimVoucher.setSysEmployeeByNextDealSn(empDao.findMannager(null,Constrants.CASHIER));
			}else if(bizClaimVoucher.getStatus().equals(Constrants.CHECKED)){//�����
				bizClaimVoucher.setStatus(Constrants.END);
				bizClaimVoucher.setSysEmployeeByNextDealSn(null);
			}
		}
		else if (Constrants.RESULT_NOWAY.equals(result)) {//�ܾ�
			bizClaimVoucher.setStatus(Constrants.END);
			bizClaimVoucher.setSysEmployeeByNextDealSn(null);
		}
		else if (Constrants.RESULT_BACK.equals(result)) {//���
			bizClaimVoucher.setStatus(Constrants.BACK);
			bizClaimVoucher.setSysEmployeeByNextDealSn(bizClaimVoucher.getSysEmployeeByCreateSn());
		}
	}
	@Override
	public void addBizClaimVouche(BizClaimVoucher bizClaimVoucher) {
		//��ӱ�������ϸ�뱨��������
		for(BizClaimVoucherDetail detail:bizClaimVoucher.getBizClaimVoucherDetails())
		{
			detail.setBizClaimVoucher(bizClaimVoucher);
		}
		bizClaimVoucherDao.addBizClaimVouche(bizClaimVoucher);
	}
	@Override
	public Map<String, String> finStatusMap() {
		Map<String,String> map=new HashMap<String, String>();
		map.put(Constrants.NEWCREATE, Constrants.NEWCREATE);
		map.put(Constrants.SUBMIT,Constrants.SUBMIT);
		map.put(Constrants.WAIT, Constrants.WAIT);
		map.put(Constrants.CHECKED, Constrants.CHECKED);
		map.put(Constrants.END, Constrants.END);
		map.put(Constrants.BACK, Constrants.BACK);
		map.put(Constrants.NOWAY, Constrants.NOWAY);
		return map;
	}
	@Override
	public BizClaimVoucher findBizClaimVoucherId(Serializable id) {
		// TODO Auto-generated method stub
		return bizClaimVoucherDao.findBizClaimVoucherId(id);
	}
	@Override
	public PageTurn<BizClaimVoucher> finBizClaimVoucher(
			ClaimCondition condition, int PageIndex, int PageSize) {
		// TODO Auto-generated method stub
		return bizClaimVoucherDao.finBizClaimVoucher(condition, PageIndex, PageSize);
	}
	
	public BizClaimVoucherDao getBizClaimVoucherDao() {
		return bizClaimVoucherDao;
	}
	public void setBizClaimVoucherDao(BizClaimVoucherDao bizClaimVoucherDao) {
		this.bizClaimVoucherDao = bizClaimVoucherDao;
	}


	public BizCheckResultDao getBizCheckResultDao() {
		return bizCheckResultDao;
	}


	public void setBizCheckResultDao(BizCheckResultDao bizCheckResultDao) {
		this.bizCheckResultDao = bizCheckResultDao;
	}


	public EmployeeDao getEmpDao() {
		return empDao;
	}


	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}
	
	

}
