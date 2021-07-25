<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>課 一覧</h2>
        <table id="division_list">
            <tbody>
                <tr>
                    <th>課</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="division" items="${divisions}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${division.name}" /></td>
                        <td><a href="<c:url value='/divisions/show?id=${division.id}' />">詳細を表示</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${divisions_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((divisions_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/divisions/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/divisions/new' />">新規部署(課)の登録</a></p>

    </c:param>
</c:import>