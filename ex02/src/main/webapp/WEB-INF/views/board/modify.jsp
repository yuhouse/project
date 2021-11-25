<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<%@include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">공지사항</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>

<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">게시판 글읽기</div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                
                <form role="form" action="/board/modify" method="post">
                	<input type='hidden' name='pageNum' value="${cri.pageNum }">
           			<input type='hidden' name='amount' value="${cri.amount }">
           			<input type="hidden" name="type" value="${cri.type }">
                	<input type="hidden" name="keyword" value="${cri.keyword }">
           			
	           		<div class="form-group">
	           			<label>Bno</label>
	           			<input class="form-control" name="bno" readonly="readonly" value="${board.bno }">
	           		</div>
	           		<div class="form-group">
	           			<label>Title</label>
	           			<input class="form-control" name="title" value="${board.title }">
	           		</div>
	           		<div class="form-group">
	           			<label>Text area</label>
	           			<textarea class="form-control" rows="3" name="content">${board.content }</textarea>
	           		</div>
	           		<div class="form-group">
	           			<label>Writer</label>
	           			<input class="form-control" name="writer" readonly="readonly" value="${board.writer }">
	           		</div>
	           		<button type="submit" data-oper="modify" class="btn btn-default">수정</button>
	           		<button type="submit" data-oper="remove" class="btn btn-danger">삭제</button>
	           		<button type="submit" data-oper="list" class="btn btn-info">목록</button>
            	</form>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->            

<script>
$(document).ready(function(){
	var formObj=$("form");
	
	$('button').on("click",function(e){
		e.preventDefault();
		
		var operation=$(this).data("oper");
		console.log(operation);
		
		if(operation==='remove'){
			formObj.attr("action","/board/remove");
		}else if(operation==='list'){
			formObj.attr("action","/board/list").attr("method","get");
			
			var pageNumTag=$("input[name='pageNum']").clone();
			var amountTag=$("input[name='amount']").clone();
			var keywordTag=$("input[name='keyword']").clone();
			var typeTag=$("input[name='type']").clone();
			
			formObj.empty();	//form 내부 태그들 삭제
			
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(keywordTag);
			formObj.append(typeTag);
		}
		formObj.submit();
	});
});
</script>

<%@include file="../includes/footer.jsp" %>
            