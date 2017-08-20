package com.myself.persistences.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 用于 角色 组织机构  菜单 图书类型等树形结构类
 * @author Administrator
 *
 */

public class Tree extends TargetHis {
	
	private static final long serialVersionUID = 1L;
	// 树形ID
	protected String id;
	// 树形父ID
	protected String parentId;
	// 树形名称
	protected String name;
	// 树形值
	protected String value;
	// 树形层级
	protected Integer level;
	// 树形是否有效
	protected String valid;
	// 树形是否可见
	protected String visible;
	// 树形序列
	protected String seq;
	// 类型 用于菜单(0.显示权限 1.数据权限)
	private String type;
	//操作码 用于菜单
	private String opCode;
	
	private List<Tree> children;

	public boolean equalContent(Tree tree) {
		boolean flag = true;
		flag = (StringUtils.isNotBlank(getId()) && StringUtils.isNotBlank(tree.getId())
				&& getId().equals(tree.getId()))
				|| (StringUtils.isNotBlank(getParentId()) && StringUtils.isNotBlank(tree.getParentId())
						&& getParentId().equals(tree.getParentId()))
				|| (StringUtils.isNotBlank(getName()) && StringUtils.isNotBlank(tree.getName())
						&& getName().equals(tree.getName()));
		return flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	@Override
	public String toString() {
		return "Tree [id=" + id + ", parentId=" + parentId + ", name=" + name
				+ ", value=" + value + ", level=" + level + ", valid=" + valid
				+ ", visible=" + visible + ", seq=" + seq + ", type=" + type
				+ ", opCode=" + opCode + ", children=" + children + "]";
	}

	
}
