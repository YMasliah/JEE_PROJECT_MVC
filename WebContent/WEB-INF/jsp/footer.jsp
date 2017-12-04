<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="groupList"   value="/actions/directory/group/list" />
<c:url var="search" value="/actions/directory/search/1" />

	<aside style="float:right;width:25%;margin: 0 1.5% 24px 1.5%;">
       	<c:if test="${user.name !='No User' && not empty user.name}">

	<div >
		<form:form method="POST" action="${search}">

			<form:errors path="*" cssClass="alert alert-danger" element="div" />

			<div class="form-group">
			   <select class="custom-select" id="type" name="type">
                    <c:forEach items="${dataTypes}" var="dataType">
						<option value="${dataType.key}"> 
						 <c:out value="${dataType.value}"></c:out>
						</option>
					</c:forEach>
               </select>
			</div>
			
			<div class="form-group">
				<input name="key" size="20" class="form-control" />
			</div>
			
			<div class="form-group">
				<input type="submit" value="recherche"
					class="form-control btn btn-info" />
			</div>
		</form:form>
	</div>

	       <div><a href="${groupList}">liste des groupes</a></div>
	    </c:if>        
    </aside>
    
    
    <footer style="clear: both; margin-bottom: 0;margin: 0 1.5% 24px 1.5%;">
  <div class="row">
  <hr>
    <div class="col-lg-12">
      <div class="col-md-8">
        <a href="#">Java Doc</a> | <a href="#">Cahier des charges</a>    
      </div>
      <div class="col-md-4">
        <p class="muted pull-right">© 2017 Maslia&Tigrara Co. All rights reserved</p>
      </div>
    </div>
  </div>
</footer>