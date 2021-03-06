<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Screen to change supervisory organization.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.placement.msgs.placement" var="placementBundle"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>
			<c:out value="${action.name}"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</title>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/placement/scripts/changeSupervisoryOrganization.js"> </script>
		<script type="text/javascript">
			;
    	</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/placement/changeSupervisoryOrganization/actionMenu.html?action=${action.id}&amp;offender=${offenderSummary.id}"></a>
			<c:out value="${action.name}"/>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
</html>