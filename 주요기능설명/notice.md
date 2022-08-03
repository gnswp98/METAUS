# 커뮤니티 페이지
[1] 공지사항

- 1. 공지사항
- 2. FAQ
- 3. QNA

***

모든 게시판은 로그인 후 작성 가능 및 공지사항과 FAQ는 관리자가 작성가능 기사는 크롤링으로 가져옴

현재 게시판의 페이지와 현제 세션에 로그인 돼어있는 Id를 가져와서 작성가능 여부 확인 코드
```java

<script type="text/javascript">
	function boardList(curPage){
		$('input[name=currentPage]').val(curPage);
		$('form[name=frmPage]').submit();
	}
	
	$(function(){
		$('#writeBoard').click(function(){
			if($('#memId').val()=="" || $('#memId').val()==null){
				alert('로그인 후 이용가능합니다!');
				event.preventDefault();
			}else{
				location.href="<c:url value='/board/boardWrite?btypeNo=3'/>";
				return false;
			}
		});
	});
	
</script>

```



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


# FAQ

![공지사항- FAQ](https://user-images.githubusercontent.com/105349816/182613515-55960c91-e3dc-4ab3-894b-4a835a60e5eb.JPG)

DB에 FAQ가 없을경우 아코디언으로 예시 데이터 

```java

<c:if test="${empty list }">
	<div class="col-md-12 topic">
		<div class="open">
			<h6 class="question"
			data-search-term="1. how does cloudify work?">준비 중 입니다.</h6>
			<i class="fa fa-angle-down hidden-xs"></i>
		</div>

		<p class="answer" style="display: none;">이용에 불편을 드려서 죄송합니다.</p>
	</div>
</c:if>

```

DB에 FAQ데이터가 있을 때

```java
<c:if test="${!empty list }">
	<c:set var="i" value="1"/>
	<c:forEach var="vo" items="${list }">
		<div class="col-md-12 topic">
			<div class="open">
				<h6 class="question"
				data-search-term="1. how does cloudify work?">${i}.
				${vo.boardTitle}</h6>
				<i class="fa fa-angle-down hidden-xs"></i>
			</div>
			<p class="answer" style="display: none;">
			${vo.boardContent.substring(vo.boardContent.indexOf('>')+1,vo.boardContent.indexOf('</p>'))}
			</p>
		</div>
		<c:set var="i" value="${i+1 }" />
	</c:forEach>
</c:if>

```

# QNA

![공지사항- QNA](https://user-images.githubusercontent.com/105349816/182615450-3eb2e1a8-0319-4cc4-a237-a3663bc4b9d5.JPG)

