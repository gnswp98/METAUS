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
