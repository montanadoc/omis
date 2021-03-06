<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 14, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.offenderphoto.msgs.offenderPhoto">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/offenderPhoto/list.html?offender=${offenderSummary.id}">
			<span>
    			<fmt:message key="offenderPhotosLink">
    				<fmt:param value="${offenderPhotoAssociationCount}"/>
    			</fmt:message>
    		</span>
    	</a>
    </div>
</fmt:bundle>
