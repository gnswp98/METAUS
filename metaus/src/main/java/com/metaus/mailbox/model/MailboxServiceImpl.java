package com.metaus.mailbox.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailboxServiceImpl implements MailboxService{
	private final MailboxDAO mailboxDao;
	
	public int insertMailbox(MailboxVO vo) {
		return mailboxDao.insertMailbox(vo);
	}
	
	public int selectMsgNo() {
		return mailboxDao.selectMsgNo();
	}
	
	public int insertRecipient(RecipientVO vo) {
		return mailboxDao.insertRecipient(vo);
	}

	public List<Map<String, Object>> selectMsgView(String userId, int msgFlag) {
		List<Map<String, Object>> list = null;
		
		if(msgFlag == MailboxUtil.MSG_SENT_FLAG) { //보낸 메세지
			list = mailboxDao.selectMsgViewBySender(userId);
		}else if(msgFlag == MailboxUtil.MSG_RECEIVED_FLAG) { //받은 메세지
			list = mailboxDao.selectMsgViewByRecipient(userId);
		}
		
		return list;
	}

	@Override
	public Map<String, Object> selectByMsgNo(int msgNo) {
		return mailboxDao.selectByMsgNo(msgNo);
	}
	
}
