<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for="department">部</label><br />
<select name="department">
    <c:forEach var="department" items="${departments}">
        <option value="${department.id}">${department.name}</option>
    </c:forEach>
</select>
<br /><br />
<label for="division">課</label><br />
<input type="text" name="name" value="${division.name}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">確定</button>
