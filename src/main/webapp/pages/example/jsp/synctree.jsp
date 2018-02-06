<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="动态树预览">
	<snow:layout id="">
		<snow:Layoutleft id="" width="400">
			<snow:button id="b4" desc="刷新"></snow:button>
			<snow:button id="b1" desc="勾选全选"></snow:button>
			<snow:button id="b2" desc="反选全部"></snow:button>
			<snow:button id="b12" desc="勾选选中节点"></snow:button>
			<snow:button id="b121" desc="勾选选中节点(级联)"></snow:button>
			<snow:button id="b22" desc="反选选中节点"></snow:button>
			<snow:button id="b61" desc="勾选根节点"></snow:button>
			<snow:button id="b62" desc="勾选前三个节点"></snow:button>
			<!-- 		<hr /> -->
			<snow:button id="b31" desc="获取Checked节点">
			</snow:button>
			<snow:button id="b32" desc="获取UnChecked节点">
			</snow:button>
			<snow:button id="b33" desc="获取Indeterminate节点">
			</snow:button>
			<!-- 		<hr /> -->
			<snow:button id="b51" desc="折叠全部">
			</snow:button>
			<snow:button id="b52" desc="折叠选中的节点">
			</snow:button>
			<snow:button id="b53" desc="展开全部">
			</snow:button>
			<snow:button id="b54" desc="展开选中的节点">
			</snow:button>
			<snow:button id="b55" desc="定位到Node111">
			</snow:button>
			<!-- 		<hr /> -->
			<snow:button id="b71" desc="追加节点">
			</snow:button>
			<snow:button id="b72" desc="删除节点">
			</snow:button>
			<div id="dd"></div>
			<div id="ev"></div>
		</snow:Layoutleft>
		<snow:Layoutcenter id="">
			<snow:dataset id="Tree" path="com.ruimin.ifs.example.dataset.Tree" init="true" parameters="async=false"></snow:dataset>
			<snow:tree dataset="Tree" id="tree" async="false" checkboxs="true"></snow:tree>
		</snow:Layoutcenter>
	</snow:layout>

	<script>
		var d1 = new Date();

		function Menu_mm_onClick(item, data) {
			var t = data.tree;
			var n = data.node;
			tree_tree.remove(n.id);
		}
		function b1_onClick() {
			tree_tree.checkAll();
		}
		function b2_onClick() {
			tree_tree.unCheckAll();
		}
		function b12_onClick() {
			var record = tree_tree.getSelected();
			record && tree_tree.check(record.getValue("_id"));
		}
		function b121_onClick() {
			var record = tree_tree.getSelected();
			record && tree_tree.check(record.getValue("_id"), true);
		}
		function b22_onClick() {
			var record = tree_tree.getSelected();
			record && tree_tree.uncheck(record.getValue("_id"));
		}
		function b221_onClick() {
			var record = tree_tree.getSelected();
			record && tree_tree.uncheck(record.getValue("_id"), true);
		}
		function b31_onClick() {
			var node = tree_tree.getChecked();
			$('#dd').html('您选中了' + node.length + '个节点');
		}
		function b32_onClick() {
			var node = tree_tree.getUnChecked();
			$('#dd').html('您选中了' + node.length + '个节点');
		}
		function b33_onClick() {
			var node = tree_tree.getIndeterminate();
			$('#dd').html('您选中了' + node.length + '个节点');
		}
		function b4_onClick() {
			tree_tree.refresh();
			//Tree_dataset.flushData();
		}
		function b51_onClick() {
			tree_tree.collapseAll();
		}
		function b52_onClick() {
			var node = tree_tree.getSelected();
			node && tree_tree.collapse(node.getValue("_id"));
		}
		function b53_onClick() {
			tree_tree.expandAll();
		}
		function b54_onClick() {
			var node = tree_tree.getSelected();
			node && tree_tree.expand(node.getValue("_id"));
		}
		function b55_onClick() {
			tree_tree.expandTo('1111');
			tree_tree.select('1111');
		}
		function b6_onClick() {
			tree_tree.expandTo('1111');
			tree_tree.select('1111');
		}
		function b61_onClick() {
			var roots = tree_tree.getRoots();
			for (var i = 0, len = roots.length; i < len; i++) {
				tree_tree.check(roots[i].getValue("_id"));
			}
		}
		function b62_onClick() {
			//tree_tree.expandTo('1111');
			tree_tree.check([ '11', '112', '111' ]);
		}
		function b71_onClick() {
			var node = tree_tree.getSelected();
			Tree_dataset.insertRecord();
			Tree_dataset.setValue("_text", "New")
		}
		function b72_onClick() {
			Tree_dataset.deleteRecord();
		}
		function tree_tree_onBeforeRefresh() {
			d1 = new Date();
			return true;
		}
		function Tree_dataset_flushDataPost() {
			tree_tree.select(tree_tree.getRoot());
		}

		//事件名称规则:[树id]_[事件名称]，如:tree_tree_onSelect
		//目前支持以下事件
		var events = [ "beforeExpand", "beforeCollapse", "beforeSelect", "beforeCheck", "beforeUnCheck", "beforeCheckAll", "beforeUnCheckAll", "beforeSelect", "afterExpand", "afterCollapse", "onSelect", "afterCheck", "afterUnCheck", "afterCheckAll", "afterUnCheckAll", "onSelect" ];
		$(events).each(function() {
			var t = this;
			window["tree_tree_" + t] = (function() {
				return function(data) {
					log("fire event:  " + t + " \t " + data);
				}
			})();
		});

		function log(s) {
			$('#ev').html($('#ev').html() + "<br>" + s)
		}
	</script>

</snow:page>
