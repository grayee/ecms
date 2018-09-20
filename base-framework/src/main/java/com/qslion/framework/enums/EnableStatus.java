package com.qslion.framework.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * 启用状态
 *
 * @author Gray.Z
 * @date 2018/9/20.
 */
public enum EnableStatus {

  /**
   * 禁用
   */
  DISABLE(0, "禁用"),
  /**
   * 启用
   */
  ENABLE(1, "启用"),

  ;
  private Integer id;
  private String name;

  EnableStatus(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Optional<EnableStatus> get(Integer id) {
    return Arrays.stream(EnableStatus.values()).filter(menuStatus -> menuStatus.getId() == id).findFirst();
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
