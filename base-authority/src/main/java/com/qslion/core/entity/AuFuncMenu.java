package com.qslion.core.entity;

import com.qslion.framework.bean.GridProperty;
import com.qslion.framework.entity.NestTreeEntity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "au_function_menu")
public class AuFuncMenu extends NestTreeEntity{

    private static final long serialVersionUID = -7190498524965167386L;

    @GridProperty(index = 1, value = "function_menu_name")
    private String name;
    @GridProperty(index = 2, value = "function_menu_url")
    private String url;
    private String iconClass;
    private String keyword;
    private String hotKey;
    @GridProperty(index = 3, value = "function_menu_type")
    private MenuType type;
    private String code;
    private Short treeLevel;
    private Integer orderCode;
    @GridProperty(index = 4, value = "function_menu_remark")
    private String remark;
    private boolean isLeaf;
    @GridProperty(index = 5, value = "function_menu_status")
    private MenuStatus status;
    @GridProperty(index = 6, value = "function_menu_enableDate")
    private Date enableDate;
    private boolean isSsl;
    private boolean isPublic;
    private AuResource auResource;


    @Id
    @Column(length = 32, nullable = true)
    @GeneratedValue(generator = "foreigner")
    @GenericGenerator(name = "foreigner", strategy = "foreign", parameters = {
            @Parameter(name = "property", value = "auResource")})
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public AuResource getAuResource() {
        return this.auResource;
    }

    public void setAuResource(AuResource auResource) {
        this.auResource = auResource;
    }

    @Column(name = "type", nullable = false, length = 1)
    public MenuType getType() {
        return this.type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    @Column(name = "name", length = 50)
    public String getName() {
        return this.name;
    }

    @Column(name = "icon_class", length = 50)
    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "keyword", length = 50)
    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Column(name = "hot_key", length = 50)
    public String getHotKey() {
        return this.hotKey;
    }

    public void setHotKey(String hotKey) {
        this.hotKey = hotKey;
    }

    @Column(name = "url", length = 1000)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "code", length = 300)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Column(name = "tree_level", precision = 3, scale = 0)
    public Short getTreeLevel() {
        return this.treeLevel;
    }

    public void setTreeLevel(Short treeLevel) {
        this.treeLevel = treeLevel;
    }

    @Column(name = "order_code")
    public Integer getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    @Column(name = "remark", length = 300)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "is_leaf", nullable = false, length = 1)
    public boolean isLeaf() {
        return this.isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Column(name = "status", nullable = false, length = 1)
    public MenuStatus getStatus() {
        return this.status;
    }

    public void setStatus(MenuStatus enableStatus) {
        this.status = status;
    }

    @Column(name = "enable_date", length = 19)
    public Date getEnableDate() {
        return this.enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    @Column(name = "is_ssl", length = 5)
    public boolean isSsl() {
        return this.isSsl;
    }

    public void setSsl(boolean isSsl) {
        this.isSsl = isSsl;
    }

    @Column(name = "is_public", length = 5)
    public boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public enum MenuStatus {
        disable(0, "禁用"), enable(1, "启用"),;
        private Integer id;
        private String name;

        MenuStatus(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Optional<MenuStatus> get(Integer id) {
            return Arrays.stream(MenuStatus.values()).filter(menuStatus -> menuStatus.getId() == id).findFirst();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum MenuType {
        function_menu(1, "功能菜单"), page_button(2, "页面按钮");
        private Integer id;
        private String name;

        MenuType(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Optional<MenuType> get(Integer id) {
            return Arrays.stream(MenuType.values()).filter(menuType -> menuType.getId() == id).findFirst();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}