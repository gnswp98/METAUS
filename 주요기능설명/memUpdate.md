# 회원 정보 수정
  - 1. 개인회원
  - 2. 기업회원
***

# 개인회원

![개인회원 정보수정](https://user-images.githubusercontent.com/105349816/182633635-ae03f253-8ead-48a5-821b-5a39a6d1852a.JPG)

이미지 업로드

```java

/*이미지 업로드*/
		$('.thumb').hide();
		$('#imageSelector').change(function(){
			$('.thumb').show();
		});
		$('.dellink').click(function(){
			$('#imageSelector').val('');
			$('.thumb').hide();
		});

```

일반회원 유효성 검사

```java

/** 일반회원 유효성검사*/
		$('#btnMemSign').click(function() {
			if ($.trim($('#memPw').val()).length < 1) {
				alert("비밀번호를 입력해주세요.");
				$('#memPw').focus();
				event.preventDefault();

			} else if ($.trim($('#memPw').val()) != $.trim($('#memPwCheck').val())) {
				alert("비밀번호가 일치하지 않습니다.");
				event.preventDefault();
						
			} else if ($.trim($('#memName').val())=="") {
				alert("이름을 입력해주세요.");
				$('#memName').focus();
				event.preventDefault();
						
			}else if(!validate_birth($('#memBirth').val())){
				alert("생년월일은 숫자만 입력해주세요.");
				$('#memBirth').focus();
				event.preventDefault();
				
			}else if(!validate_tel($('#memTel').val())){
				alert("휴대전화는 숫자만 입력해주세요.");
				$('#memTel').focus();
				event.preventDefault();				
			}else{
				alert('회원정보가 수정되었습니다.')
			}
			
		});

```

주소 관련 코드

```java

/**일반회원 주소찾기*/
		$('#memZipcode').click(function(){
			execDaumPostcode();
		});
    
 /** 일반회원 우편번호 찾기 */
	function execDaumPostcode() {
	    daum.postcode.load(function(){
	        new daum.Postcode({
	            oncomplete: function(data) {

	              $("#memZipcode").val(data.zonecode);
	              $("#memAdd").val(data.roadAddress);
	            }
	        }).open();
	    });
	}   

```

정규식

```java

function validate_userid(id) {
		var pattern = new RegExp(/^[a-zA-Z0-9_]+$/g);
		return pattern.test(id);		
	}

	function validate_tel(tel) {
		var pattern = new RegExp(/^[0-9]*$/g);
		return pattern.test(tel);
	}
	function validate_birth(birth) {
		var pattern = new RegExp(/^[0-9]*$/g);
		return pattern.test(birth);
	}
	function validate_email(email) {
		var pattern = new RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i);
		return pattern.test(email);
	}

```

***

# 기업회원

![기업회원 정보수정](https://user-images.githubusercontent.com/105349816/182634864-18e1e611-10a9-4008-a763-57bab1472657.JPG)

사업자 등록번호 확인

```java

/** 기업회원 사업자 등록번호 확인 */
		$('#comRrnCheck').click(function(){
			
			if ($.trim($('#comCeo').val()).length < 1) {
				alert("대표자명을 입력해주세요.");
				$('#comCeo').focus();
				event.preventDefault();

			}else if ($.trim($('#comRrn').val()).length < 1) {
				alert("사업자 등록번호를 입력해주세요.");
				$('#comRrn').focus();
				event.preventDefault();

			}else if ($.trim($('#comOpen').val()).length < 1) {
				alert("개업일 입력해주세요.");
				$('#comOpen').focus();
				event.preventDefault();

			}else if(!validate_tel($('#comRrn').val())){
				alert("사업자 등록번호는 숫자만 입력해주세요.");
				$('#comRrn').focus();
				event.preventDefault();				
			}else if(!validate_tel($('#comOpen').val())){
				alert("개업일 숫자만 입력해주세요.");
				$('#comOpen').focus();
				event.preventDefault();				
			}  else{
				var name = $('#comCeo').val();
				var open = $('#comOpen').val();
				
				var rrn = $('#comRrn').val();
				var data = {
						"b_no" : [rrn]
				};
				
				var data2 = {
						"businesses": [
						    {
						      "b_no": rrn,
						      "start_dt": open,
						      "p_nm": name
						    }
						  ]
				}

				$.ajax({
					url: "https://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey=aKSHxC3JN3xtPxQP64ysM2CE9UhYKMLXVKM9w7aL7WiHH6dYWKgG%2FJaYTvF0O82Dawkq6c%2ByK0ByxSovTdkXHw%3D%3D",
					type:"POST",
					data: JSON.stringify(data2),
					dataType: "JSON",
					contentType: "application/json",
					
					async:false,
					success: function(result) {
					      if(result.data[0].valid=='02'){
					    	  alert('사업자 정보가 일치하지 않습니다.');
					      }else if(result.data[0].valid=='01'){
					    	  alert('사업자 정보가 확인되었습니다');
					    	  $('#isComRrnCheck').val('Y');
					      }
					  },
					  error: function(result) {
					      alert('사업자 정보 확인에 실패했습니다.');
					  }
				});
				event.preventDefault();	
			}
			
		});

```

기업회원 주소 위도 경도

```java

/** 기업회원 우편번호 찾기 */
	function execDaumPostcode2() {
	    daum.postcode.load(function(){
	        new daum.Postcode({
	            oncomplete: function(data) {
	              $("#comAdd").val(data.roadAddress);
	              mapsearch();
	            }
	        }).open();
	    });
	}
	
/** 기업 주소 위도경도로 변환*/
	function mapsearch(){
		var address = $('#comAdd').val();
		var geocoder2 = new kakao.maps.services.Geocoder();
		geocoder2.addressSearch(address, function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		        
		        $('#comLati').val(result[0].y);
		    	$('#comLongi').val(result[0].x);
		    } 
		});	
	}
	
/**기업회원 주소찾기*/
	$('#comAdd').click(function(){
		execDaumPostcode2();
			
	});

```

***
