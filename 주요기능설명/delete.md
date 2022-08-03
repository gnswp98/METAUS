# 글 삭제

***

글 수정, 삭제는 본인만 할 수 있음
세션에 로그인 돼어있는 Id(memName)와 글을 작성한 Id(memName2)를 비교하여 버튼생성 여부 확인

![수정 삭제 버튼](https://user-images.githubusercontent.com/105349816/182631295-f9d16a3f-8c72-4a40-b8c5-3a6f24313339.JPG)

```java

<c:if test="${memName == memName2 }">
	<div>
		<a
		href="<c:url value='/board/boardUpdate?boardNo=${vo.boardNo }&bfileNo=${AtcVo.bfileNo}'/>">
			<button class="btn btn-large btn-blue btn-effect mt30"
			id="writeBoard">수정</button>
		</a>
		<form id="boardDelete"
		action="<c:url value='/board/boardDelete'/>"
		style="display: inline-block;">
			<input type="hidden" name="boardNo" value="${vo.boardNo }">
			<input type="hidden" name="btypeNo" value="${vo.btypeNo }">
			<button class="btn btn-large btn-blue btn-effect mt30"
			id="boardDeleteBt" type="button">삭제</button>
		</form>
	</div>
</c:if>

```

***

삭제버튼 클릭 시 삭제 여부 확인

![삭제 메세지](https://user-images.githubusercontent.com/105349816/182631583-fc6b5566-5487-4cc4-8a18-4f8295ac3b93.JPG)

***

# 컨트롤러

```java

@RequestMapping("/boardDelete")
	public String boardDelete(@ModelAttribute BoardVO vo) {
		logger.info("게시글 삭제, 파라미터 vo.getBoardNo()={}", vo.getBoardNo());

		int cnt = boardService.deleteBoard(vo);
		logger.info("게시글 삭제, 결과 cnt={}",cnt);

		if(vo.getBtypeNo()==3) {
			return "redirect:/board/qna?btypeNo=3";
		}else if(vo.getBtypeNo()==8) {
			return "redirect:/board/freeBoard?btypeNo=8";
		}else if(vo.getBtypeNo()==5) {
			return "redirect:/board/QuestionBoard?btypeNo=5";
		}else if(vo.getBtypeNo()==6) {
			return "redirect:/board/shareBoard?btypeNo=6";
		}else if(vo.getBtypeNo()==7) {
			return "redirect:/board/requestBoard?btypeNo=7";
		}
		return "/";
	}

```

***
