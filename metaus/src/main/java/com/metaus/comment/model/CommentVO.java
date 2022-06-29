package com.metaus.comment.model;

import java.sql.Timestamp;

import com.metaus.member.model.MemberVO;

import lombok.Data;

@Data
public class CommentVO extends MemberVO{
	private int cmtNo ; /* 댓글 번호 */
	private Timestamp cmtRegdate ; /* 작성일 */
	private String cmtContent ; /* 내용 */
	private int boardNo ; /* 글 번호 */
	private int memNo ; /* 회원번호 */
}
