<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/actions/directory/person/edit" />

<html>
<head>
<title>Hello :: Spring Application</title>
</head>
<body>
    <div class="container">
        <h1><c:out value="${group.name}"></c:out> group</h1>
        <table class="table table-hover">
            <c:forEach items="${group.persons}" var="person">
                <tr>
                    <td><a href="${edit}?id=${person.id}">
                        <c:out value="${person.lastName}" />
                    </a></td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <a class="btn btn-info" href="${edit}">add new person</a>
        </p>
    </div>
</body>
</html>