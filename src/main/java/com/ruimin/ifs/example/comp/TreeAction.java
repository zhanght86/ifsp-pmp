package com.ruimin.ifs.example.comp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.example.process.bean.NodeAttribute;
import com.ruimin.ifs.framework.core.bean.TreeNode;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.servlet.CommQueryServletRequest;

@SnowDoc(desc = "FunctionTreeAction")
@ActionResource
public class TreeAction extends SnowAction {

	@Action
	@SnowDoc(desc = "search")
	public List<TreeNode> listAll(QueryParamBean queryBean) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		CommQueryServletRequest commQueryServletRequest = queryBean.getCqRequest();
		String _id = commQueryServletRequest.getParameter("_id");
		if (StringUtils.isBlank(_id)) {
			_id = "1";
		}
		TreeNode root = new TreeNode();
		root.setId(_id);
		root.setText("节点" + System.currentTimeMillis() + _id);
		// 生成数据
		String async = commQueryServletRequest.getParameter("async");
		if ("true".equals(async)) {
			if (!"1111".equals(_id)) {
				list = genChildrenNode(root, 1, 4);
			}
		} else {
			list = genChildrenNode(root, 3, 4);
		}

		for (TreeNode n : list) {// 将节点11112设成异步加载节点
			if (n.getId().equals("1111")) {
				n.setHasChild(false);
			} else {
				n.setHasChild(true);
			}
			if (n.getId().length() == 2) {
				n.setCanSelected(false);
			}
		}

		return list;
	}

	/**
	 * 模拟构造树节点数据
	 * 
	 * @param parent
	 *            父节点
	 * @param level
	 *            层次
	 * @param N
	 *            每层节点数
	 */
	public List<TreeNode> genChildrenNode(TreeNode parent, int level, int N) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		if (level < 1)
			return list;
		for (int i = 0; i < N; i++) {
			TreeNode node1 = new TreeNode();
			node1.setId(parent.getId() + (i + 1));
			node1.setText(parent.getText() + (i + 1));
			// node1.appendTo(parent);
			node1.setPid(parent.getId());
			node1.setAttributes("我是name" + i);
			list.addAll(genChildrenNode(node1, level - 1, N));
			list.add(node1);
		}
		return list;
	}

	public List<TreeNode> initStaticDataAysc(String id) {// 异步方式
		List<TreeNode> list = new ArrayList<TreeNode>();
		TreeNode root = new TreeNode();
		root.setId("1");
		root.setText("节点" + "1");
		if (StringUtils.isBlank(id)) {// 为空查询第一级
			for (TreeNode treeNode : treeList) {
				if (StringUtils.isEmpty(treeNode.getPid())) {
					treeNode.setHasChild(this.isHashChild(treeNode.getId()));
					list.add(treeNode);
				}
			}
		} else {
			for (TreeNode treeNode : treeList) {
				if (StringUtils.isNotBlank(treeNode.getPid()) && StringUtils.equals(id, treeNode.getPid())) {
					treeNode.setHasChild(this.isHashChild(treeNode.getId()));
					list.add(treeNode);
				}
			}
		}
		return list;
	}

	private boolean isHashChild(String id) {
		boolean bl = false;
		for (TreeNode treeNode : treeList) {
			if (StringUtils.isNotBlank(treeNode.getPid()) && StringUtils.equals(id, treeNode.getPid())) {
				bl = true;
				break;
			}
		}
		return bl;
	}

	public List<TreeNode> initStaticDataAll() {// 同步方式
		return treeListNoCheck;
	}

	@SuppressWarnings("serial")
	private static List<TreeNode> treeList = new ArrayList<TreeNode>() {
		{
			TreeNode node = new TreeNode();
			node = new TreeNode();
			node.setId("1");
			node.setText("目录一");
			node.setHasChild(true);
			add(node);

			node = new TreeNode();
			node.setId("2");
			node.setText("被选中的目录");
			node.setChecked(true);
			add(node);

			node = new TreeNode();
			node.setId("3");
			node.setText("带图标目录");
			node.setIconCls("icon-add");
			add(node);

			TreeNode node1 = new TreeNode();
			node1.setId("11");
			node1.setText("子目录1");
			node1.setPid("1");
			add(node1);

			node1 = new TreeNode();
			node1.setId("12");
			node1.setText("子目录2");
			node1.setPid("1");
			add(node1);

			node1.setId("21");
			node1.setText("子目录3");
			node1.setChecked(true);
			node1.setPid("2");
			add(node1);

			node1 = new TreeNode();
			node1.setId("22");
			node1.setText("子目录4");
			node1.setPid("2");
			add(node1);

			TreeNode node2 = new TreeNode();
			node2.setId("221");
			node2.setText("子目录4-1-URL有图标");
			node2.setIconCls("icon-add");
			node2.setPid("22");
			NodeAttribute otherAttr = new NodeAttribute();
			otherAttr.setUrl("/demo/ftl/textinput_tab.ftl");
			node2.setAttributes(otherAttr);
			add(node2);

			node2 = new TreeNode();
			node2.setId("222");
			node2.setText("子目录4-1-URL");
			otherAttr = new NodeAttribute();
			otherAttr.setUrl("/demo/ftl/datagrid_default_tab.ftl");
			node2.setAttributes(otherAttr);
			node2.setPid("22");
			add(node2);

			node2 = new TreeNode();
			node2.setId("223");
			node2.setText("子目录4-2");
			node2.setPid("22");
			add(node2);
		}
	};

	@SuppressWarnings("serial")
	private static List<TreeNode> treeListNoCheck = new ArrayList<TreeNode>() {
		{
			TreeNode node = new TreeNode();
			node = new TreeNode();
			node.setId("1");
			node.setText("目录一");
			node.setHasChild(true);
			add(node);

			node = new TreeNode();
			node.setId("2");
			node.setText("被选中的目录");
			node.setChecked(true);
			add(node);

			node = new TreeNode();
			node.setId("3");
			node.setText("带图标目录");
			node.setIconCls("icon-add");
			add(node);

			TreeNode node1 = new TreeNode();
			node1.setId("11");
			node1.setText("子目录1");
			node1.setPid("1");
			add(node1);

			node1 = new TreeNode();
			node1.setId("12");
			node1.setText("子目录2");
			node1.setPid("1");
			add(node1);

			node1.setId("21");
			node1.setText("子目录3");
			node1.setChecked(true);
			node1.setPid("2");
			add(node1);

			node1 = new TreeNode();
			node1.setId("22");
			node1.setText("子目录4");
			node1.setPid("2");
			add(node1);

			TreeNode node2 = new TreeNode();
			node2.setId("221");
			node2.setText("子目录4-1-URL有图标");
			node2.setIconCls("icon-add");
			node2.setPid("22");
			NodeAttribute otherAttr = new NodeAttribute();
			otherAttr.setUrl("/demo/ftl/textinput_tab.ftl");
			node2.setAttributes(otherAttr);
			add(node2);

			node2 = new TreeNode();
			node2.setId("222");
			node2.setText("子目录4-1-URL");
			otherAttr = new NodeAttribute();
			otherAttr.setUrl("/demo/ftl/datagrid_default_tab.ftl");
			node2.setAttributes(otherAttr);
			node2.setPid("22");
			add(node2);

			node2 = new TreeNode();
			node2.setId("223");
			node2.setText("子目录4-2");
			node2.setPid("22");
			add(node2);
		}
	};

}
