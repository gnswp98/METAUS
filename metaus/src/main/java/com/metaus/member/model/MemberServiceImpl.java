package com.metaus.member.model;

import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberDAO memberDao;
	
	public int insertMember(MemberVO vo){
		int cnt=memberDao.insertMember(vo);
		return cnt;
	}
	
	public int checkLogin(String userid, String pwd) {
		String dbPwd = memberDao.selectPwd(userid);
		
		int result=0;
		if(dbPwd !=null && !dbPwd.isEmpty()) {
			if(dbPwd.equals(pwd)) {
				result=MemberService.LOGIN_OK;
			}else {
				result=MemberService.DISAGREE_PWD;				
			}
		}else {
			result=MemberService.NONE_USERID;
		}
		
		return result;
	}
	
	public MemberVO selectByUserid(String memId) {
		return memberDao.selectByUserid(memId);
	}
	public MemberVO selectBySocialid(String memId) {
		return memberDao.selectBySocialid(memId);
	}
	public MemberVO selectByMemNo(int memNo) {
		return memberDao.selectByMemNo(memNo);
	}
	
	public int duplicateId(String memId){
		int count=memberDao.duplicateId(memId);
		
		int result=0;
		if(count>0) {  //이미 존재 => 사용불가
			result=MemberService.UNUSABLE_ID;
		}else { //사용가능
			result=MemberService.USABLE_ID;			
		}
		
		return result;
	}
	public int updateKakao(int memNo) {
		return memberDao.updateKakao(memNo);
	}
	public int updateNaver(int memNo) {
		return memberDao.updateNaver(memNo);
	}
	public int updateFacebook(int memNo) {
		return memberDao.updateFacebook(memNo);
	}
	
	public String findId(MemberVO vo) {
		return memberDao.findId(vo);
	}
	public String findPw(MemberVO vo) {
		return memberDao.findPw(vo);
	}
	public int updatePw(MemberVO vo) {
		return memberDao.updatePw(vo);
	}

}
