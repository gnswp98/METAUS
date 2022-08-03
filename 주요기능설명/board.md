__# 커뮤니티 페이지
[1] 공지사항

- 1. 공지사항
- 2. FAQ
- 3. QNA


[2] 커뮤니티

- 1. 자유 게시판
- 2. 질문 게시판
- 3. 공유/정보 게시판


[3] 실시간 채팅

***

# 공지사항

![공지사항- 공지사항](https://user-images.githubusercontent.com/105349816/182611330-c00142bb-fdf7-444b-b8a7-71742f5236a9.JPG)

```java
 <div class="col-md-12 col-xs-12 blog-posts-wrapper">
 <!-- 게시판에 글이 있을 경우만 나타남 -->
	<c:if test="${empty list }">
		<img alt="게시글 내용이 없습니다" 
		src="<c:url value='/images/board/no_board.gif'/>" style="width: 950px; margin-left: 100px;">
	</c:if>
	<c:if test="${!empty list }">
	<!-- 반복 시작 -->
		<c:forEach var="map" items="${list }">
			<article class="col-md-12 blog-post">
				<div class="col-md-12 blog-thumbnail">
					<a href="<c:url value='/board/readCountUp?boardNo=${map["BOARD_NO"] }&btypeNo=1'/>">
						<c:forEach var="vo" items="${atcList }">
							<c:if test="${vo.boardNo==map['BOARD_NO'] }">
								<img src="<c:url value='/img_upload/${vo.bfileFilename }'/>"
								class="img-responsive" alt="이미지" />
							</c:if>
						</c:forEach>
					</a>
				</div>
				<div class="col-md-12 blog-desc">
					<h5>
						<a href="<c:url value='/board/readCountUp?boardNo=${map["BOARD_NO"] }&btypeNo=1'/>">
							${map['BOARD_TITLE'] }</a>
					</h5>
					<div class="post-detail pt10 pb20">
						<span><i class="fa fa-user"></i>${map['MEM_NAME'] }</span> <span><i
						class="fa fa-clock-o"></i> <fmt:formatDate
						value="${map['BOARD_REGDATE'] }" pattern="yyyy-MM-dd" /> </span> <span><img
						src="<c:url value='/images/board/eye.png'/>"
						style="width: 14px; height: 14.4px;">
						${map['BOARD_READCOUNT'] }</span>
					</div>
						<a href="<c:url value='/board/readCountUp?boardNo=${map["BOARD_NO"] }&btypeNo=1'/>"
						class="btn btn-blue btn-effect mt10">상세보기</a>
					</div>
				</article>
			</c:forEach>
			<!-- 반복 끝 -->

			<!-- 게시판 페이징 처리 -->
			<div class="col-md-12">
				<ul class="pagination list-inline text-center">
					<c:if test="${pagingInfo.firstPage>1 }">
						<li><a href="#"
						onclick="boardList(${pagingInfo.firstPage-1})">prev</a></li>
					</c:if>

				<!-- [1][2][3][4][5][6][7][8][9][10] -->
					<c:forEach var="i" begin="${pagingInfo.firstPage }"
					end="${pagingInfo.lastPage }">
						<c:if test="${i==pagingInfo.currentPage }">
							<li class="active"><a>${i }</a></li>
						</c:if>
						<c:if test="${i!=pagingInfo.currentPage }">
							<li><a href="#" onclick="boardList(${i})">${i } </a></li>
						</c:if>
					</c:forEach>
						<c:if test="${pagingInfo.lastPage<pagingInfo.totalPage }">
							<li><a href="#"
							onclick="boardList(${pagingInfo.lastPage+1})">Next</a></li>
						</c:if>
						</ul>
					</div>
					<!-- 게시판 페이징 처리 끝 -->
				</c:if>


				<!-- 페이징 처리를 위한 form 시작-->
				<form name="frmPage" method="post"
					action="<c:url value='/board/notice?btypeNo=1'/>">
					<input type="hidden" name="currentPage">
					<input type="hidden" name="searchKeyword" value="${searchVo.searchKeyword }">
					<input type="hidden" name="searchCondition" value="${searchVo.searchCondition }">
				</form>
				<!-- 페이징 처리 form 끝 -->

			</div>
			<!-- End of Blog Posts -->

```
__
