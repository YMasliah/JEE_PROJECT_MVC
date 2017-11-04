<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/actions/directory/group/edit" />
<c:url var="view" value="/actions/directory/group/view" />

<html>
<head>
<title>Hello :: Spring Application</title>
</head>
<body>
    <div class="container">
        <h1>Group List (bootstrap)</h1>
        <table class="table table-hover">
            <c:forEach items="${groups}" var="group">
                <tr>
                    <td><a href="${view}?id=${group.id}">
                        <c:out value="${group.name}" />
                    </a></td>
                     <td><a class="btn btn-info" href="${edit}?id=${group.id}">
                        modification
                    </a></td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <a class="btn btn-info" href="${edit}">Create new group</a>
        </p>
    </div>
</body>
</html>