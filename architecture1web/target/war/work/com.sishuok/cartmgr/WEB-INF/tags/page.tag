<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="page" type="com.github.pagehelper.Page" required="true" description="分页对象" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table width="100%" align="center">
    <tr>
    	<td>
    	<span class="page-info">[共有记录${page.getTotal()}条  / 共分${page.getPages()}页  ， 当前是第 ${page.getPageNum()} 页]</span >
    </td>
    </tr>
    <tr>
    	<td>	
        <c:choose>
            <c:when test="${page.getPageNum()==1}">
                	首页 &nbsp;&nbsp;
                	上一页&nbsp;&nbsp;
            </c:when>
            <c:otherwise>
                <a href="#" onclick="turnPage(1);" title="首页">首页</a>&nbsp;&nbsp;
                <a href="#" onclick="turnPage(${page.getPageNum() - 1});" title="上一页">上一页</a>&nbsp;&nbsp;
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${page.getPageNum()==page.getPages()}">
                	下一页&nbsp;&nbsp;
                	尾页&nbsp;&nbsp;
            </c:when>
            <c:otherwise>
                <a href="#" onclick="turnPage(${page.getPageNum() + 1});" title="下一页">下一页</a>&nbsp;&nbsp;
                <a href="#" onclick="turnPage(${page.getPages()});" title="尾页">尾页</a>&nbsp;&nbsp;
            </c:otherwise>
        </c:choose>
        
</td>
</tr>
</table>