<%@page import="com.ruimin.ifs.framework.core.SessionUserInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="home">

	<!-- 商户基本信息: 数据集 -->
	<snow:dataset id="MchtInfo"
		path="com.ruimin.ifs.pmp.mcht.dataset.MchtInfoVO1" submitMode="current"
		init="true" parameters="apprFlag=1"></snow:dataset>
		
	<!-- 终端基本信息：数据集 -->
	<snow:dataset id="termCheck" 
		path="com.ruimin.ifs.pmp.term.dataset.TermCheckMngEntry" 
		init="true" submitMode="current" parameters="apprFlag=4"></snow:dataset>
	
	<!-- <script type="text/javascript">
		$(function() {
			$('#container').highcharts({
				title : {
					text : 'Combination chart'
				},
				xAxis : {
					categories : [ 'Apples', 'Oranges', 'Pears', 'Bananas', 'Plums' ]
				},
				labels : {
					items : [ {
						html : 'Total fruit consumption',
						style : {
							left : '50px',
							top : '18px',
							color : (Highcharts.theme && Highcharts.theme.textColor) || 'black'
						}
					} ]
				},
				series : [ {
					type : 'column',
					name : 'Jane',
					data : [ 3, 2, 1, 3, 4 ]
				}, {
					type : 'column',
					name : 'John',
					data : [ 2, 3, 5, 7, 6 ]
				}, {
					type : 'column',
					name : 'Joe',
					data : [ 4, 3, 3, 9, 0 ]
				}, {
					type : 'spline',
					name : 'Average',
					data : [ 3, 2.67, 3, 6.33, 3.33 ],
					marker : {
						lineWidth : 2,
						lineColor : Highcharts.getOptions().colors[3],
						fillColor : 'white'
					}
				}, {
					type : 'pie',
					name : 'Total consumption',
					data : [ {
						name : 'Jane',
						y : 13,
						color : Highcharts.getOptions().colors[0]
					// Jane's color
					}, {
						name : 'John',
						y : 23,
						color : Highcharts.getOptions().colors[1]
					// John's color
					}, {
						name : 'Joe',
						y : 19,
						color : Highcharts.getOptions().colors[2]
					// Joe's color
					} ],
					center : [ 100, 80 ],
					size : 100,
					showInLegend : false,
					dataLabels : {
						enabled : false
					}
				} ]
			});
		});
	</script> -->
	<!-- 查询 -->	
	<snow:grid dataset="MchtInfo" id="gridId" label="商户待审批流程(请到终端变更审核界面进行审核)" 
		fitcolumns="true" 
		fieldstr="mchtId,mchtCnAbbr,mchtLicnNo,crtDateTime,mchtStat"
		paginationbar=""></snow:grid>
	<snow:grid dataset="termCheck" id="gridId1" label="终端信息审批流程（请到终端变更审核界面进行审核）"
		fitcolumns="true" 
		fieldstr="mchtName,termId,termStat,miscFlag1" 
		paginationbar=""></snow:grid>

</snow:page>
