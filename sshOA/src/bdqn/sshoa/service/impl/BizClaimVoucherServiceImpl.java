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
		//1从审核结果中获取报销单
		BizClaimVoucher bizClaimVoucher=bizCheckResult.getBizClaimVoucher();
		//2在报销单实体中修改报销单状态和下一审核人
		updateStatusAndNextDeal(bizClaimVoucher,bizCheckResult.getResult());
		//3调用dao层修改审核结果
		bizClaimVoucherDao.updateBlizClaimVouche(bizClaimVoucher);
		//4添加报销单审核结果
		bizCheckResultDao.addBizCheckResult(bizCheckResult);
	}
	//根据审核结果和报销单状态获取,获取审核后的下一级审核人和状态
	private void updateStatusAndNextDeal(BizClaimVoucher bizClaimVoucher,String result) {
		if (Constrants.RESULT_PASS.equals(result)) {//通过
			if(bizClaimVoucher.getStatus().equals(Constrants.SUBMIT)){//状态已提交
				if(bizClaimVoucher.getTotalAccount()>=5000){
					//下一审核人为总经理 状态为待审核
					bizClaimVoucher.setSysEmployeeByNextDealSn(empDao.findMannager(null,Constrants.GENERALMANAGER));
					bizClaimVoucher.setStatus(Constrants.WAIT);
				}else if(bizClaimVoucher.getTotalAccount()<=5000){
					//下一审核人为财务 状态为已审核
					bizClaimVoucher.setStatus(Constrants.CHECKED);
					bizClaimVoucher.setSysEmployeeByNextDealSn(empDao.findMannager(null,Constrants.CASHIER));
				}
			}else if(bizClaimVoucher.getStatus().equals(Constrants.WAIT)){//待审核 审核人为总经理
				//下一审核人为财务 状态为待审核
				bizClaimVoucher.setStatus(Constrants.CHECKED);
				bizClaimVoucher.setSysEmployeeByNextDealSn(empDao.findMannager(null,Constrants.CASHIER));
			}else if(bizClaimVoucher.getStatus().equals(Constrants.CHECKED)){//已审核
				bizClaimVoucher.setStatus(Constrants.END);
				bizClaimVoucher.setSysEmployeeByNextDealSn(null);
			}
		}
		else if (Constrants.RESULT_NOWAY.equals(result)) {//拒绝
			bizClaimVoucher.setStatus(Constrants.END);
			bizClaimVoucher.setSysEmployeeByNextDealSn(null);
		}
		else if (Constrants.RESULT_BACK.equals(result)) {//打回
			bizClaimVoucher.setStatus(Constrants.BACK);
			bizClaimVoucher.setSysEmployeeByNextDealSn(bizClaimVoucher.getSysEmployeeByCreateSn());
		}
	}
	@Override
	public void addBizClaimVouche(BizClaimVoucher bizClaimVoucher) {
		//添加报销单明细与报销单关联
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
