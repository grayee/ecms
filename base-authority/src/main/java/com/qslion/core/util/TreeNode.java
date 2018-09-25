/**
 *
 */
package com.qslion.core.util;

import java.util.List;
import java.util.Map;

/**
 * 修改备注：
 */

public class TreeNode {

    private enum NodeState {
        /**
         * 打开状态
         */
        open,
        closed
    }

    private String id;

    /**
     * easy ui tree
     */

    //显示的节点文本
    private String text;
    //显示的节点图标CSS类ID
    private String iconCls;
    //节点状态
    private NodeState state = NodeState.open;
    //被添加到节点的自定义属性
    private Map<String, Object> attributes;

    //是否选中
    private boolean checked;

    //子节点数据
    private List<TreeNode> children;

    public TreeNode() {
        super();
    }

    public TreeNode(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
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

    public NodeState getState() {
        return state;
    }

    public void setState(NodeState state) {
        this.state = state;
    }


    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
