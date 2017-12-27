package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import entity.Member;
import service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDao memberDao;

	public boolean save(Member member) {
		if(1==memberDao.save(member)){
			return true;
		}
		return false;
	}
}
