<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>热门新闻</title>
<style>
.table_wrapper {
	border-left: 1px solid #78C7E7;
	border-top: 1px solid #78C7E7;
	color: #555;
	text-align: left;
}

.table_wrapper td,.table_wrapper th {
	border-right: 1px solid #78C7E7;
	border-bottom: 1px solid #78C7E7;
	height: 25px;
	line-height: 25px;
	padding-left: 5px;
}

.table_wrapper th {
	background-color: #8BCEEB;
	color: #fff;
}

.table_wrapper .table_header {
	background-color: #8BCEEB;
	color: blue;
}

tr:nth-child(even) {
	background-color: #DCEEFC;
}
</style>
</head>
<body>
	<table class="table_wrapper">
		<tr>
			<th>序号</th>
			<th>频道号</th>
			<th>url</th>
			<th>标题</th>
			<th>权重</th>
			<th>总量</th>
			<th>增量</th>
			<th>更新前排名</th>
			<th>更新后排名</th>
			<th>插入时间</th>
			<th>更新时间</th>
		</tr>


		<c:forEach items="${newsList}" var="item" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${item.channelId}</td>
				<td><a href="${item.url}" target="_blank">${item.url}</a></td>
				<td>${item.title}</td>
				<td>${item.weight}</td>
				<td>${item.total}</td>
				<td>${item.increment}</td>
				<td>${item.lastRank}</td>
				<td>${item.rank}</td>
				<td>${item.insertTime}</td>
				<td>${item.updateTime}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>
