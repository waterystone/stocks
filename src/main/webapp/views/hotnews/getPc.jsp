<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<html>
<body>

	<form action="${pageContext.request.contextPath}/hotnews/getPcHotNews"
		method="post" target="_blank">
		n:<input type="text" name="n" value="100" size="5" /> 
		
		<select
			name="channelId">
			<option value="0">所有</option>
			<option value="102">体育频道</option>
			<option value="103">新闻频道</option>
			<option value="104">财经频道</option>
			<option value="111">女人频道</option>
			<option value="115">娱乐频道</option>
			<option value="207">军事频道</option>
		</select> 
		
		<select
			name="orderBy">
			<option value="weight">权重</option>
			<option value="total">总量</option>
			<option value="increment">增量</option>
		</select> <input type="submit" />
	</form>
</body>
</html>

