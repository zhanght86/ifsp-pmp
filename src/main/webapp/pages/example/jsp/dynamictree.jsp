<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="动态树预览">
	<%-- <snow:CommonQuery id="StaticTreeNode" submitMode="current" navigate="false" init="true">
		<div style="float: left; width: 400px">

			<snow:SimpleButton id="b4" desc="刷新">
			</snow:SimpleButton>
			<snow:SimpleButton id="b1" desc="勾选全选">
			</snow:SimpleButton>
			<snow:SimpleButton id="b2" desc="反选全部">
			</snow:SimpleButton>
			<snow:SimpleButton id="b12" desc="勾选选中节点">
			</snow:SimpleButton>
			<snow:SimpleButton id="b22" desc="反选选中节点">
			</snow:SimpleButton>
			<snow:SimpleButton id="b61" desc="勾选一个节点:Node111">
			</snow:SimpleButton>
			<snow:SimpleButton id="b62" desc="勾选多个节点:Node1111, Node1112, Node1114">
			</snow:SimpleButton>
			<hr />

			<snow:SimpleButton id="b31" desc="获取Checked节点">
			</snow:SimpleButton>
			<snow:SimpleButton id="b32" desc="获取UnChecked节点">
			</snow:SimpleButton>
			<snow:SimpleButton id="b33" desc="获取Indeterminate节点">
			</snow:SimpleButton>
			<hr />

			<snow:SimpleButton id="b51" desc="折叠全部">
			</snow:SimpleButton>
			<snow:SimpleButton id="b52" desc="折叠选中的节点">
			</snow:SimpleButton>
			<snow:SimpleButton id="b53" desc="展开全部">
			</snow:SimpleButton>
			<snow:SimpleButton id="b54" desc="展开选中的节点">
			</snow:SimpleButton>
			<snow:SimpleButton id="b55" desc="定位到Node111">
			</snow:SimpleButton>
			<hr />

			<snow:SimpleButton id="b71" desc="追加节点">
			</snow:SimpleButton>
			<snow:SimpleButton id="b72" desc="删除节点">
			</snow:SimpleButton>
			<div id="dd"></div>
		</div>
		<div style="float: left">
			node1>node11>node111>node1112为异步节点

			<snow:DynamicTree dsId="StaticTreeNode" id="tree1" checkbox="true" mode="static">
			</snow:DynamicTree>
		</div>


	</snow:CommonQuery> --%>
	<snow:dataset id="StaticTreeNode" path="" init="true" parameters="async=true"></snow:dataset>
	<div id="tree" extra="tree" datasetid="StaticTreeNode_dataset" data-options="async:true,checkboxs:true"></div>

	<script>
		var d1 = new Date();

		function Menu_mm_onClick(item, data) {
			var t = data.tree;
			var n = data.node;
			DynamicTree_tree1.remove(n.id);
		}
		function b1_onClick() {
			DynamicTree_tree1.selectAll();
		}
		function b2_onClick() {
			DynamicTree_tree1.unSelectAll();
		}
		function b12_onClick() {
			var node = DynamicTree_tree1.getSelected();
			node && DynamicTree_tree1.check(node.id);
		}
		function b22_onClick() {
			var node = DynamicTree_tree1.getSelected();
			node && DynamicTree_tree1.uncheck(node.id);
		}
		function b31_onClick() {
			var node = DynamicTree_tree1.getChecked();
			$('#dd').html('您选中了' + node.length + '个节点');
		}
		function b32_onClick() {
			var node = DynamicTree_tree1.getUnChecked();
			$('#dd').html('您选中了' + node.length + '个节点');
		}
		function b33_onClick() {
			var node = DynamicTree_tree1.getIndeterminate();
			$('#dd').html('您选中了' + node.length + '个节点');
		}
		function b4_onClick() {
			DynamicTree_tree1.refresh();
		}
		function b51_onClick() {
			DynamicTree_tree1.collapseAll();
		}
		function b52_onClick() {
			var node = DynamicTree_tree1.getSelected();
			node && DynamicTree_tree1.collapse(node.id);
		}
		function b53_onClick() {
			DynamicTree_tree1.expandAll();
		}
		function b54_onClick() {
			var node = DynamicTree_tree1.getSelected();
			node && DynamicTree_tree1.expand(node.id);
		}
		function b55_onClick() {
			DynamicTree_tree1.expandTo('1111');
			DynamicTree_tree1.select('1111');
		}
		function b6_onClick() {
			DynamicTree_tree1.expandTo('1111');
			DynamicTree_tree1.select('1111');
		}
		function b61_onClick() {
			DynamicTree_tree1.expandTo('1111');
			DynamicTree_tree1.check('1111');
		}
		function b62_onClick() {
			DynamicTree_tree1.expandTo('1111');
			DynamicTree_tree1.check([ '11', '11111', '11112', '11114' ]);
		}
		function b71_onClick() {
			var node = DynamicTree_tree1.getSelected() || DynamicTree_tree1.getRoot();
			DynamicTree_tree1.append(node.id, [ {
				id : node.id + new Date().getTime(),
				text : 'New Item'
			} ]);
		}
		function b72_onClick() {
			var node = DynamicTree_tree1.getSelected();
			node && DynamicTree_tree1.remove(node.id);
		}
		function DynamicTree_tree1_onBeforeRefresh() {
			d1 = new Date();
			return true;
		}
		function DynamicTree_tree1_onLoad() {
			$('#dd').html('render used(include network) :' + (new Date() - d1));
		}
		function DynamicTree_tree1_onSelect(_target, node) {
			$('#dd').html('select ' + node.id + '-' + node.text);
		}

		function DynamicTree_tree1_beforeExpandNode(_target, node) {
			//alert(node.status); 0表示未选中 1表示选中 2表示半选中
			$('#dd').html('beforeExpandNode');
			return true;
		}
	</script>

</snow:page>
