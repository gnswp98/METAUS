<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/resume.css'/>">

 <section class="ptb80" id="job-page">
 	<section class="page-header">
        <div class="container">
            <!-- Start of Page Title -->
            <div class="row">
                <div class="col-md-12">
                    <h2>Portfolio</h2>
                </div>
            </div>
            <!-- End of Page Title -->
        </div>
    </section>
    
    <!-- Start of Post Resume Form -->
    
 <section class="ptb80" id="post-job">
        <div class="container">
            <form action="<c:url value='/resume/portfolioDetail'/>" class="post-job-resume mt50" method="get" enctype="multipart/form-data">
            <input type="hidden" name="portNo" value="${pvo.portNo }">
               <div class="row candidate-profile nomargin">            
                <!-- Start of Profile Description -->
                <div class="col-md-9 col-xs-12">
                    <div class="profile-descr">
						 
                        <!-- Profile Title -->
                         <div class="form-group" style="font-size: 17px;">
                            <label>포트폴리오 제목 : 
                            &nbsp;
                            <span>${pvo.portTitle }</span>
                            </label>
                        </div>
                        <br>
                        <div class="form-group" style="font-size: 17px;">
                            <label>작업 사진 : 
                            &nbsp;
                            <span>${pvo.portOriginname }</span>
                            </label>
                        </div> 
                        <br>             
                        <div class="form-group" style="font-size: 17px;">
                            <label>작업 내용 : 
                            &nbsp;
                            <span>${pvo.portContent }</span>
                            </label>
                        </div> 
                        <br>             

                        <!-- Form Group -->
                        <div class="form-group pt30 nomargin" id="last">
                            <button class="btn btn-blue btn-effect">삭제</button>
                            <a href="<c:url value='/resume/resumeDetail'/>" class="btn btn-blue btn-effect">취소</a>
                        </div> 
					  </div>	
                    </div>
                </div>
                <!-- End of Resume Details -->

            </form>
            <!-- End of Post Resume Form -->
		</div>
        
    </section>
    <!-- ===== End of Main Wrapper Section ===== -->
 </section>           


 
     

        
 
<%@ include file="../inc/footer.jsp" %>