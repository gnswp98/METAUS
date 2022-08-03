# 글 작성 페이지

***

![글 작성 페이지](https://user-images.githubusercontent.com/105349816/182619797-b4a5d192-88d8-434d-b6e7-7a4677b8ce32.JPG)

게시판의 종류가 바뀔 때마다 알림창 생성

```java

<script type="text/javascript">
	$(function(){
		$('select[name=search-categories]').change(function(){
			alert('게시판 종류가 변경되었습니다');
			$('#btypeNo').val($(this).val());
		});

	});
</script>

```

게시판 <select> 태그 코드

```java
	
<label for="search-categories">게시판 종류</label> <select
							name="search-categories" class="selectpicker"
							id="search-categories" data-live-search="true"
							title="게시판을 선택해주세요" data-size="5" data-container="body"
							style="display: flow-root">

							<option value="3"
								<c:if test="${param.btypeNo==3 }">selected="selected"</c:if>>QNA
								게시판</option>
							<option value="8"
								<c:if test="${param.btypeNo==8 }">selected="selected"</c:if>>자유
								게시판</option>
							<option value="5"
								<c:if test="${param.btypeNo==5 }">selected="selected"</c:if>>질문
								게시판</option>
							<option value="6"
								<c:if test="${param.btypeNo==6 }">selected="selected"</c:if>>공유/정보
								게시판</option>
							<option value="7"
								<c:if test="${param.btypeNo==7 }">selected="selected"</c:if>>개인의뢰
								게시판</option>
</select>

```
