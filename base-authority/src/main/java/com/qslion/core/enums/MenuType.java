package com.qslion.core.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * 菜单类型
 *
 * @author Gray.Z
 * @date 2018/9/20.
 */
public enum MenuType {

  /**
   * 目录
   */
  CATALOG(0, "目录"),
  /**
   * 功能菜单
   */
  FUNCTION_MENU(1, "功能菜单"),
  /**
   * 页面按钮
   */
  PAGE_BUTTON(2, "页面按钮");

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