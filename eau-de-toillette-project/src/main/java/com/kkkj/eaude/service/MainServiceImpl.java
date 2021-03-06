package com.kkkj.eaude.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkkj.eaude.dao.MainDao;
import com.kkkj.eaude.domain.MainVO;
import com.kkkj.eaude.domain.Product;

@Service("maService")
public class MainServiceImpl implements MainService {

	@Autowired
	private MainDao maDao;
	
	@Override
	public int idCheck(String m_id) {
		return maDao.idCheck(m_id);
	}

	@Override
	public int emailCheck(String m_email) {
		return maDao.emailCheck(m_email);
	}

	@Override
	public int insertMember(MainVO vo) {
		return maDao.insertMember(vo);
	}

	@Override
	public int idExist(MainVO vo) {
		return maDao.idExist(vo);
	}

	@Override
	public int pwExist(MainVO vo) {
		return maDao.pwExist(vo);
	}

	@Override
	public MainVO regInfo(String my_name) {
		return maDao.regInfo(my_name);
	}

	@Override
	public int findid(MainVO vo) {
		return maDao.findid(vo);
	}

	@Override
	public String findidresult(MainVO vo) {
		return maDao.findidresult(vo);
	}

	@Override
	public int findpw(MainVO vo) {
		return maDao.findpw(vo);
	}

	@Override
	public int changepw(MainVO vo) {
		return maDao.changepw(vo);
	}

	@Override
	public int insertMemberAddr(MainVO vo) {
		return maDao.insertMemberAddr(vo);
	}

	@Override
	public List<Product> showMainCandle() {
		return maDao.showMainCandle();
	}

	@Override
	public List<Product> showMainDifuser() {
		return maDao.showMainDifuser();
	}

	@Override
	public List<Product> showMainPerfume() {
		return maDao.showMainPerfume();
	}

	@Override
	public List<Product> showMainBodyCream() {
		return maDao.showMainBodyCream();
	}


}
