<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>部 一覧</h2>
        <table id="departments_list">
            <tbody>
                <tr>
                    <th>部</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="department" items="${departments}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${department.name}" /></td>
                        <td><a href="<c:url value='/departments/show?id=${department.id}' />">詳細を表示</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${departments_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((departments_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/departments/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/departments/new' />">新規部署(部)の登録</a></p>

    </c:param>
</c:import>