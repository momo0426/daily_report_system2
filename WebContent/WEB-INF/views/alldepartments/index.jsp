<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>部署管理</h2>
        <table id="alldepartments_list">
            <tbody>
                <tr>
                    <th>部</th>
                    <th>課</th>
                </tr>
                <tr>
                    <td><a href="<c:url value='/departments/index' />">一覧を見る</a></td>
                    <td><a href="<c:url value='/divisions/index' />">一覧を見る</a></td>
                </tr>
            </tbody>
        </table>
    </c:param>
</c:import>