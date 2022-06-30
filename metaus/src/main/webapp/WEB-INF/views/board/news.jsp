<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../inc/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/search.css'/>">
<script type="text/javascript">
	function boardList(curPage){
		$('input[name=currentPage]').val(curPage);
		$('form[name=frmPage]').submit();
	}
</script>
<!-- =============== Start of Page Header 1 Section =============== -->
<section class="page-header" style="margin-top: 150px;">
	<div class="container">

		<!-- Start of Page Title -->
		<div class="row">
			<div class="col-md-12">
				<h2>News</h2>
			</div>
		</div>
		<!-- End of Page Title -->

		<!-- Start of Breadcrumb -->
		<div class="row">
			<div class="col-md-12">
				<ul class="breadcrumb">
					<li><a href="<c:url value='/'/>">home</a></li>
					<li class="active">커뮤니티</li>
					<li class="active">커뮤니티 - 기사</li>
				</ul>
			</div>
		</div>
		<!-- End of Breadcrumb -->

	</div>
</section>
<!-- =============== End of Page Header 1 Section =============== -->

<!-- 검색 시작 -->
<form id="searchFrm" method="post" action="<c:url value='/board/news?btypeNo=4'/>">
	<div id="searchBox">
		<div class="col-md-3 col-sm-12 search-categories"
			style="display: contents;">
			<label for="search-categories"></label> <select
				name="searchCondition" class="selectpicker" id="searchCondition"
				data-live-search="true" title="검색 조건" data-size="3"
				data-container="body" style="display: flow-root;">
				<option value="memName">작성자</option>
				<option value="boardTitle">제목</option>
				<option value="boardContent">내용</option>
			</select> <input type="text" class="live-search-box form-control mt20"
				placeholder="검색하실 내용을 입력해주세요" name="searchKeyword"
				id="searchKeyword"> <input type="hidden" name="btypeNo"
				value="4">
			<button class="btn btn-large btn-blue btn-effect mt30" id="searchBt"
				type="submit">검색</button>
		</div>
	</div>
</form>
<!-- 검색 끝 -->

<!-- ===== Start of Blog Listing Section ===== -->
<section class="blog-listing ptb80" id="version1">
	<div class="container">
		<div class="row">

			<!-- Start of Blog Posts -->
			<div class="col-md-12 col-xs-12 blog-posts-wrapper">
				<c:if test="${empty list }">
					<img alt="게시글 내용이 없습니다"
						src="<c:url value='/images/board/no_board.gif'/>"
						style="width: 950px; margin-left: 100px;">
				</c:if>
				<c:if test="${!empty list }">
					<!-- 반복 시작 -->
					<c:forEach var="map" items="${list }">

						<!-- Start of Blog Post Article 1 -->
						<article class="col-md-12 blog-post">
							<!-- Blog Post Thumbnail -->
							<div class="col-md-4 blog-thumbnail">
								<a
									href="<c:url value='/board/readCountUp?boardNo=${map["BOARD_NO"] }&btypeNo=4'/>">
									<c:forEach var="vo" items="${atcList }">
										<c:if test="${vo.boardNo==map['BOARD_NO'] }">
											<img src="<c:url value='/img_upload/${vo.bfileFilename }'/>"
												class="img-responsive" alt="이미지" />
										</c:if>
									</c:forEach>
							</div>

							<!-- Blog Post Description -->
							<div class="col-md-8 blog-desc">
								<h5>
									<a
										href="<c:url value='/board/readCountUp?boardNo=${map["BOARD_NO"] }&btypeNo=4'/>">
										${map['BOARD_TITLE'] }</a>
								</h5>
								<div class="post-detail pt10 pb20">
									<span><i class="fa fa-user"></i>${map['MEM_NAME'] }</span> <span><i
										class="fa fa-clock-o"></i>${map['BOARD_REGDATE'] }</span> <span><img
										src="<c:url value='/images/board/eye.png'/>"
										style="width: 14px; height: 14.4px;">
										${map['BOARD_READCOUNT'] }</span>
								</div>
								<a
									href="<c:url value='/board/readCountUp?boardNo=${map["BOARD_NO"] }&btypeNo=4'/>"
									class="btn btn-blue btn-effect mt10">자세히</a>
							</div>
						</article>
						<!-- End of Blog Post Article 1 -->
					</c:forEach>

					<!-- Start of Pagination -->
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
							<!--  페이지 번호 끝 -->
						</ul>
					</div>
					<!-- End of Pagination -->

				</c:if>



				<!-- 페이징 처리를 위한 form 시작-->
				<form name="frmPage" method="post"
					action="<c:url value='/board/news?btypeNo=4'/>">
					<input type="hidden" name="currentPage">
					<input type="hidden" name="searchKeyword" value="${searchVo.searchKeyword }">
					<input type="hidden" name="searchCondition" value="${searchVo.searchCondition }">
				</form>
				<!-- 페이징 처리 form 끝 -->

			</div>
			<!-- End of Blog Posts -->

		</div>
	</div>
</section>
<!-- ===== End of Blog Listing Section ===== -->








<%@ include file="../inc/footer.jsp"%>