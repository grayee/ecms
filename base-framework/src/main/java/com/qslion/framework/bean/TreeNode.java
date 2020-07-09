/**
 *
 */
package com.qslion.framework.bean;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * treeNode
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
public class TreeNode {

    public enum NodeState {
        /**
         * 打开状态
         */
        OPEN,
        CLOSED
    }

    private Long id;

    /**
     * 显示的节点文本
     */
    private String text;
    /**
     * /显示的节点图标CSS类ID
     */
    private String iconCls;
    /**
     * 节点状态
     */
    private NodeState state = NodeState.OPEN;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 被添加到节点的自定义属性
     */
    private Map<String, Object> attributes;

    /**
     * 是否选中
     */
    private boolean checked;

    /**
     * 子节点数据
     */
    private List<TreeNode> children;

    public TreeNode() {
        super();
    }

    public TreeNode(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getState() {
        return state.name().toLowerCase();
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

    public void addAttribute(String key, Object value) {
        if (this.attributes == null) {
            this.attributes = Maps.newHashMap();
        }
        this.attributes.put(key, value);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
