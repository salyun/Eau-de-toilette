<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<style type="text/css">    
.order-manage-h2{    
float:left; margin-bottom: 15px;
}
.order-manage-div{
float:right; margin-bottom: 15px; cursor: pointer;
}
.order-manage-div > a{
color:#5f0080;
}
#order-manage-table{
width: 100%; margin-top: 20px; border-collapse: collapse; 
}
.order-manage-td1{
padding: 0 0 15px 0; border-bottom: 2px solid black;
}
.order-manage-td1-top{
padding: 0 0 15px 10px; border-bottom: 2px solid black;
}
.order-manage-td-center1{
vertical-align: middle;
}
.order-manage-delete{
width: 20px; height: 20px; cursor: pointer; line-height: 20px; float: right; padding-top: 65px;
}
.order-manage-td-title2{
font-weight: bold;
}
.order-manage-td2{
padding: 15px 0 15px 0; border-bottom: 1px solid #f7f7f7; height: 124px; overflow: hidden;
}
.mypage_manage_user_id{
padding: 15px 0 15px 10px; border-bottom: 1px solid #f7f7f7;
}
.order-manage-paging{
width: 100%; text-align: center; margin:20px 0 100px 0;
}

.mypage_manage_user_submit{
float:right; width: 25px; height: 25px; margin:20px 0 0 10px; cursor: pointer;
}
.mypage_manage_user_input{
font-size:12px; float:right; margin-top:15px;  padding-left: 10px; width: 160px; height: 30px; border:1px solid #ddd;  border-radius: 50px;
}
</style>
</head>
<body>
 <jsp:include page="header.jsp"></jsp:include>

     <div id="main">
<c:if test="${manageChk eq 'N' }">
 <jsp:include page="myPageTop.jsp"></jsp:include>
 </c:if>
 <c:if test="${manageChk eq 'Y' }">
 <jsp:include page="myPageManageTop.jsp"></jsp:include>
 </c:if>
        <div id="mypage_section">
          <div id="mypage-side-section">
            <div id="mypage-side-menu">
                <ul id="mypage-side-menus">
                <c:if test="${manageChk eq 'Y' }">
                    <li class= "menu">&nbsp;&nbsp;&nbsp;<a href = "#" onclick="location.href='myPageManageUser.do'">회원 관리&nbsp;<img src ="${pageContext.request.contextPath}/resources/images/point-right.png" style="width: 12px; height: 12px; margin-left: 90px;"></a></li>
                    <li class= "menu">&nbsp;&nbsp;&nbsp;<a href = "#" onclick="location.href='myPageManageOrder.do'">주문 관리&nbsp;<img src ="${pageContext.request.contextPath}/resources/images/point-right.png" style="width: 12px; height: 12px; margin-left: 90px;"></a></li>
                    <li class= "menu menu-on">&nbsp;&nbsp;&nbsp;<a href = "#" onclick="location.href='mypage_manager_event.do'">이벤트&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src ="${pageContext.request.contextPath}/resources/images/point-right.png" style="width: 12px; height: 12px; margin-left: 90px;"></a></li>
                </c:if>
                <c:if test="${manageChk eq 'N' }">
                <li class= "menu">&nbsp;&nbsp;&nbsp;<a href = "#" onclick="location.href='/eaude/myPageOrderList.do'">주문내역&nbsp;<img src ="${pageContext.request.contextPath}/resources/images/point-right.png" style="width: 12px; height: 12px; margin-left: 90px;"></a></li>
                    <li class= "menu">&nbsp;&nbsp;&nbsp;<a href = "#" onclick="location.href='/eaude/mypageShippingDestination.do'">배송지 관리&nbsp;<img src ="${pageContext.request.contextPath}/resources/images/point-right.png" style="width: 12px; height: 12px; margin-left: 75px;"></a></li>
                    <li class= "menu">&nbsp;&nbsp;&nbsp;<a href = "#" onclick="location.href='/eaude/myPageInfo.do'">개인 정보 수정&nbsp;<img src ="${pageContext.request.contextPath}/resources/images/point-right.png" style="width: 12px; height: 12px; margin-left: 55px;"></a></li> 
                	<li class= "menu menu-on">&nbsp;&nbsp;&nbsp;<a href = "#" onclick="location.href='mypage_manager_event.do'">이벤트&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src ="${pageContext.request.contextPath}/resources/images/point-right.png" style="width: 12px; height: 12px; margin-left: 90px;"></a></li>
                </c:if>
                </ul>
            </div>
		</div>
            <div class="mypage-personer-info">
                <div class = "mypage-personer-info-section">
                    <h2 class="order-manage-h2">이벤트 관리</h2>
                     <c:if test="${manageChk eq 'Y' }">
                    <div class="order-manage-div"><a href = "#" onclick="location.href='myPageAddEvent.do'">★이벤트 추가★</a></div>
                    </c:if>
					 
                    <br>
                    <hr style="background-color: black; height: 1px; margin-top: 30px;">
                    <div>
                    <table id="order-manage-table">
                    	<tr>
                    		<td class="order-manage-td1-top">이벤트 목록</td>
                    		
                    	</tr>
                    	<c:if test="${not empty eList }">
                    	<c:forEach items="${eList }" varStatus="s" var="vo"> 
                    	<tr>
                    		
                    		<td class="order-manage-td-title2">
                    			${vo.event_title}
                    			<span style="float: right;">마감일 : ${vo.event_end}</span>
                    			
                    		</td>
                    	</tr>
                    	<tr>
                    		
                    		<td class="order-manage-td2">
                    			<input type="hidden" value="${vo.event_id}">
                    			<c:if test="${manageChk eq 'Y' }">
                    			<a href = "#" onclick="location.href='mypage_manager_event_detail.do?page=${vo.event_id}'"> <img src ="${pageContext.request.contextPath}/resources/eventFiles/${vo.event_img}" style="width: 95%;"></a>
                    			<img class="order-manage-delete" src ="${pageContext.request.contextPath}/resources/images/trash.png">
                    			</c:if>
                    			<c:if test="${manageChk eq 'N' }">
                    			<a href = "#" onclick="location.href='mypage_manager_event_detail.do?page=${vo.event_id}'"> <img src ="${pageContext.request.contextPath}/resources/eventFiles/${vo.event_img}" style="width: 100%;"></a>
                    			
                    			</c:if>
                    		</td>
                    	</tr>
                    	</c:forEach>
                    	</c:if>
                    	<c:if test="${empty eList }">
                    		<tr>
                    			<td style="text-align: center;">이벤트가 없습니다.</td>
                    		</tr>
                    	</c:if>
                    </table>
                  <div class="order-manage-paging">
		                   <c:if test="${currentPage <= 1}">
				 				[이전]&nbsp;
				 			</c:if> 
				 			
				 			<c:if test="${currentPage > 1}">
									<c:url var="mlistST" value="mypage_manager_event.do">
										<c:param name="page" value="${currentPage-1}" />
									</c:url>
									<a href="${mlistST}">[이전]</a>
							</c:if> <!-- 끝 페이지 번호 처리 --> 
							<c:set var="endPage" value="${maxPage}" /> 
							<c:forEach var="p" begin="${startPage+1}" end="${endPage}">
									<c:if test="${p eq currentPage}">
										<font color="red" size="4"><b>[${p}]</b></font>
									</c:if>
									<c:if test="${p ne currentPage}">
										<c:url var="mlistchk" value="mypage_manager_event.do">
											<c:param name="page" value="${p}" />
										</c:url>
										<a href="${mlistchk}">${p}</a>
									</c:if>
								</c:forEach> 
						<c:if test="${currentPage >= maxPage}">
				 					[다음]
				 		</c:if>
						<c:if test="${currentPage < maxPage}">
									<c:url var="mlistEND" value="mypage_manager_event.do">
										<c:param name="page" value="${currentPage+1}" />
									</c:url>
									<a href="${mlistEND}">[다음]</a>
						</c:if>                    
                    </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
 <jsp:include page="footer.jsp"></jsp:include>
 <script type="text/javascript">


 $('.order-manage-delete').click(function() {
	 var eid = $(this).parent().find($('input[type=hidden]'));
	if (confirm("정말 삭제하시겠습니까??") == true){
	
	  $.ajax({
		 url : "${pageContext.request.contextPath}/eventDelete.do",
     type : 'POST',
     dataType : 'json',
     data : {
    	 event_id: eid.val(),
        	
        },
	 success : function(data) {
		 alert('삭제 되었습니다.');
		 location.reload();
	 },
});   
	}else{
		return false;
	}
	
}) 
 
 </script>
</body>
</html>
