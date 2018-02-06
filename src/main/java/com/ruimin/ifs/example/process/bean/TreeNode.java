package com.ruimin.ifs.example.process.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * use com.ruimin.ifs.framework.core.bean.TreeNode instead.
 *
 */
@Deprecated
public class TreeNode {
	public static final String CHECK_STATUS_CHECKED = "1"; // 选中
	public static final String CHECK_STATUS_UNCHECKED = "0"; // 未选中
	public static final String CHECK_STATUS_INDETERMINATE = "2"; // 模糊胡,半选中
	/** 必须属性 */
	private String id; // ID,唯一标识,必须
	private String text = ""; // 节点文本,必须
	private boolean hasChild; // 是否有子节点,必须

	/** 可选属性 */
	private String state = "closed";// 是否展开 open:展开;closed:关闭.默认是关闭
	private boolean canSelected;// 选中状态
	private String pid = ""; // 父ID
	private String iconCls = "";// 图标
	private Object attributes = "";// 其它属性
	private List<TreeNode> children = new ArrayList<TreeNode>();// 子节点
	private boolean checked;// 是否被选中
	private String checkedStatus = "";// /选中状态, 0-未选中;1-选中;2-半选

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isCanSelected() {
		return canSelected;
	}

	public void setCanSelected(boolean canSelected) {
		this.canSelected = canSelected;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
		this.checkedStatus = CHECK_STATUS_CHECKED;
	}

	public String getCheckedStatus() {
		return checkedStatus;
	}

	public void setCheckedStatus(String checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public TreeNode appendTo(TreeNode node) {
		return node.addChild(this);
	}

	public TreeNode addChild(TreeNode node) {
		children.add(node);
		node.setPid(this.id);
		this.setHasChild(true);
		return this;
	}

	public TreeNode removeChild(TreeNode node) {
		children.remove(node);
		node.setPid(null);
		this.setHasChild(!this.children.isEmpty());
		return this;
	}

	public List<TreeNode> getChildren() {
		return this.children;
	}

	public static String toHTML(boolean checkboxs, int indent, List<TreeNode> nodes) {
		StringBuffer sb = new StringBuffer();
		for (TreeNode c : nodes) {
			sb.append("<li id='" + c.id + "' >");
			sb.append(c.toHTML(indent, checkboxs));
			sb.append("</li>");
		}
		return sb.toString();
	}

	public String toHTML(boolean checkboxs, int indent) {
		StringBuffer sb = new StringBuffer();
		sb.append("<li id='" + this.id + "' >");
		sb.append(this.toHTML(indent, checkboxs));
		sb.append("</li>");
		return sb.toString();
	}

	private String toHTML(int deep, boolean checkboxs) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div node-id=\"" + this.id + "\" class=\"tree-node\" >");
		for (int i = 0; i < deep; i++) {
			sb.append("<span class=\"tree-indent\" />");
		}
		if (!this.children.isEmpty() || this.hasChild) {
			sb.append("<span class=\"tree-hit ")
					.append("closed".equals(this.state) || this.hasChild ? "tree-collapsed" : "tree-expanded")
					.append("\" />");
		} else {
			sb.append("<span class=\"tree-indent\" />");
		}
		sb.append("<span class=\"tree-icon " + (this.children.isEmpty() && !this.hasChild ? "tree-file" : "tree-folder")
				+ " ").append("closed".equals(this.state) || this.hasChild ? "" : "tree-folder-open")
				.append(this.iconCls).append("\" />");
		if (checkboxs) {
			sb.append("<span class=\"tree-checkbox tree-checkbox"
					+ ("".equals(this.checkedStatus) ? "0" : this.checkedStatus) + "\" />");
		}
		sb.append("<span class=\"tree-title\">").append(this.text).append("</span>");
		sb.append("</div>");
		if (!this.children.isEmpty()) {
			sb.append("<ul style=\"display: ").append("closed".equals(this.state) ? "none" : "block").append(";\" >");
			for (TreeNode c : this.getChildren()) {
				sb.append("<li id='" + c.id + "' >");
				sb.append(c.toHTML(deep + 1, checkboxs));
				sb.append("</li>");
			}
			sb.append("</ul>");
		}

		return sb.toString();
	}
}
