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
        <h1>Group List (bootstrap), bug de modification si un groupe a le meme id qu'un nouveau groupe.<br> surement resolu lors de l'implementation de la base de donnée</h1>
        <table class="table table-hover">
            <c:forEach items="${groups}" var="groupList">
                <tr>
                    <td><a href="${view}?id=${groupList.id}">
                        <c:out value="${groupList.name}" />
                    </a></td>
                     <td><a class="btn btn-info" href="${edit}?id=${groupList.id}">
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