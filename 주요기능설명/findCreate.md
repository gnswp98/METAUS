# 크리에이터 찾기 주요 기능
- 1. 페이징 처리

***

# 1. 페이징 처리
![리스트](https://user-images.githubusercontent.com/99188096/180380885-44f48458-83d8-4a04-aa09-b6cee910e677.PNG)   

- 일반회원중 포트폴리오가 등록된 회원만 노출
- 매퍼 sql문   
  - 검색조건과 페이징 처리 상수를 SearchVO에 파라미터로 담는다   

```html
<select id="selectAllCreater" resultType="MemberVO" parameterType="SearchVO">
		select * from (
			select rownum as RNUM, R.* from(
				select A.*, (select count(*) from fp_port B where A.mem_no = B.mem_no) as memItem, (select count(*) from fp_contact C where A.mem_no = C.mem_no) as memComplete  from fp_mem A where mem_no in (select mem_no from fp_port)
				<include refid="searchAndWhere"></include>
			) R
		)
		where
		RNUM>#{firstRecordIndex} 
		<![CDATA[	
		and RNUM<=#{firstRecordIndex} + #{recordCountPerPage} ]]>
</select>
```

- 뷰페이지에서 검색 혹은 페이지 이동 시 form   

```javascript
  <form name="frmPage" method="post"
		action="<c:url value='/creater/createrList'/>">
    
    //페이지 버튼 클릭시 현재 페이지를 담아 유지
		<input type="hidden" name="currentPage">
    
    //검색 이용시 페이지를 넘어가도 검색 조건이 유지되도록 검색 조건을 받아온다
		<input type="hidden" name="searchKeyword" value="${searchVo.searchKeyword }">

	</form>
```
- 컨트롤러   

```java
	@RequestMapping("/createrList")
	public void createrList(@RequestParam(required = false) String conFlag, @RequestParam(defaultValue = "0") int recNo
			, @ModelAttribute SearchVO searchVo,Model model) {
		logger.info("크리에이터 찾기 뷰");
		
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCKSIZE);
		pagingInfo.setBlockSize(ConstUtil.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		logger.info("t ={}",pagingInfo.getCurrentPage());
		
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("t2={}",pagingInfo.getFirstRecordIndex());
		logger.info("t3={}",searchVo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		
		int totalRecord=0;
		List<MemberVO> list=null;
		int contractedMemNo=-1;
		if(recNo < 1) {
			//전체조회
			list =  memberService.selectAllCreater(searchVo);
			logger.info("크리에이터 찾기 list={}",list);
			
			totalRecord = memberService.getTotalRecord(searchVo);
			
		}else { //기업의뢰 지원자 현황
			String searchKeyword = searchVo.getSearchKeyword();
			int firstRecordIndex = searchVo.getFirstRecordIndex();
			int recordCountPerPage = searchVo.getRecordCountPerPage();
			Map<String, Object> map = new HashMap<>();
			map.put("searchKeyword", searchKeyword);
			map.put("recNo", recNo);
			map.put("firstRecordIndex", firstRecordIndex);
			map.put("recordCountPerPage", recordCountPerPage);
			
			list = memberService.selectCreaterByRecNo(map);
			logger.info("기업의뢰 지원자 현황, list.size={}", list.size());
			
			totalRecord = memberService.getTotalRecordByrecNo(map);
			
			//계약 멤버 조회
			if(!conFlag.equals("recruiting")) {
				contractedMemNo = commissionService.selectMemNoByRecNo(recNo);
			}
		}
		
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("크리에이터 목록 조회-레코드 개수, totalRecord={}", totalRecord);
		logger.info("크리에이터 목록 조회-pagingInfo, pagingInfo.getFirstPage={}", pagingInfo.getFirstPage());
		logger.info("크리에이터 목록 조회-pagingInfo, pagingInfo.getLastPage={}", pagingInfo.getLastPage());
		
		model.addAttribute("searchVo",searchVo);
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("list",list);
		model.addAttribute("recNo", recNo);
		model.addAttribute("conFlag", conFlag);
		model.addAttribute("contractedMemNo", contractedMemNo);
	}
```


***
