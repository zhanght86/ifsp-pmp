<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="demo_field_1">
	<snow:layout id="outter">
		<snow:Layouttop id="">
			<h2>上</h2>
		</snow:Layouttop>
		<snow:Layoutbottom id="">
			<h2>下</h2>
		</snow:Layoutbottom>
		<snow:Layoutleft id="">
			<h2>左</h2>
		</snow:Layoutleft>
		<snow:Layoutright id="">
			<h2>右</h2>
		</snow:Layoutright>
		<snow:Layoutcenter id="">
			<snow:layout id="inner">
				<snow:Layouttop id="">
					<h2>上</h2>
				</snow:Layouttop>
				<snow:Layoutbottom id="">
					<h2>下</h2>
				</snow:Layoutbottom>
				<snow:Layoutleft id="">
					<h2>左</h2>
				</snow:Layoutleft>
				<snow:Layoutright id="">
					<h2>右</h2>
				</snow:Layoutright>
				<snow:Layoutcenter id="">
					<h2>中</h2>
				</snow:Layoutcenter>
			</snow:layout>
		</snow:Layoutcenter>
	</snow:layout>
</snow:page>
