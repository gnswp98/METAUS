# Footer
 - 1. 카카오맵 API
***

![푸터](https://user-images.githubusercontent.com/105349816/182635787-768079da-03ac-49a8-83de-c5675d7e6c08.JPG)

***

metaus로고와 대표자 정보

```java

<div class="col-md-3 col-sm-6 col-xs-6 footer-about" style="width: 24%">
		<a class="navbar-brand nomargin" href="<c:url value='/'/>" 
   style="padding: 0px; "><img src="<c:url value='/images/metaus_logo2.png'/>" 
   style=" margin-right: 50px; margin-bottom: 50px"   alt="logo"></a>
				
		<ul class="nopadding">
			<li><i class="fa fa-map-marker"></i>서울특별시 서대문구, 104-48</li>
			<li><i class="fa fa-phone"></i>(대표) 010-3084-3045</li>
			<li><i class="fa fa-envelope-o"></i>kimjin0132@naver.com</li>
		</ul>
</div>

```

metaus 링크

```java

<div class="col-md-3 col-sm-6 col-xs-6 footer-links">
				<h3>메타어스 링크</h3>

				<!-- Links -->
				<ul class="nopadding">
					<li><a href="<c:url value='/request/search'/>"><i
							class="fa fa-angle-double-right"></i>의뢰 찾기</a></li>
					<li><a href="<c:url value='/creater/createrList'/>"><i
							class="fa fa-angle-double-right"></i>크리에이터 찾기</a></li>
					<li><a href="<c:url value='/board/notice?btypeNo=1'/>"><i
							class="fa fa-angle-double-right"></i>공지사항</a></li>
					<li><a href="<c:url value='/board/faq?btypeNo=2'/>"><i
							class="fa fa-angle-double-right"></i>FAQ</a></li>
					<li><a href="<c:url value='/board/news2'/>"><i
							class="fa fa-angle-double-right"></i>기사</a></li>
					<li><a href="<c:url value='/pd/pd'/>"><i
							class="fa fa-angle-double-right"></i>3D Model 목록</a></li>
					<li><a href="<c:url value='/member/register'/>"><i
							class="fa fa-angle-double-right"></i>회원가입</a></li>
				</ul>
			</div>

```

카카오맵 API

```java

<div class="col-md-6 col-sm-6 col-xs-6 footer-posts">
				<h3>찾아오시는 길</h3>

				<!-- Single Post 1 -->
				<div class="footer-blog-post">

					<div id="map2" style="width:100%;height:200px;"></div>
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e35ee665202c956a80b8b31eaa6545be&libraries=services"></script>
					<script>
					var mapContainer = document.getElementById('map2'), // 지도를 표시할 div 
				    mapOption = {
				        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				        level: 3 // 지도의 확대 레벨
				    };  

				// 지도를 생성합니다    
				var map2 = new kakao.maps.Map(mapContainer, mapOption); 

				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new kakao.maps.services.Geocoder();

				// 주소로 좌표를 검색합니다
				geocoder.addressSearch('서울 서대문구 신촌로 141', function(result, status) {

				    // 정상적으로 검색이 완료됐으면 
				     if (status === kakao.maps.services.Status.OK) {

				        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				        // 결과값으로 받은 위치를 마커로 표시합니다
				        var marker = new kakao.maps.Marker({
				            map: map2,
				            position: coords
				        });

				        // 인포윈도우로 장소에 대한 설명을 표시합니다
				        var infowindow = new kakao.maps.InfoWindow({
				            content: '<div style="width:150px;text-align:center;padding:6px 0;">METAUS</div>'
				        });
				        infowindow.open(map2, marker);

				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map2.setCenter(coords);
				    } 
				}); 
					</script>
				</div>
				<p>서울 서대문구 신촌로 141 은하빌딩 1,2,4층</p>
			</div>

```
