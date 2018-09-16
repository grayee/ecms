/**
 *
 */
package com.qslion.core.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 修改备注：
 */

public class LoginSessionVo {
    private String party_id;
    private String login_id;
    private String password;
    private String name;
    private String is_admin;
    private String agent_status;
    private String relationtype_id;
    private List relation_vo_list;
    private String[] owner_org_arr;
    private Map owner_fild_map;
    private Map owner_recd_map;
    private Map owner_butn_map;
    private Map owner_menu_map;
    private Map owner_func_map;
    private Map owner_menu_url_map;
    private Map all_menu_url_map;
    private String[] owner_org_arr_admin;
    private Map owner_fild_map_admin;
    private Map owner_recd_map_admin;
    private Map owner_butn_map_admin;
    private Map owner_menu_map_admin;
    private Map owner_func_map_admin;

    public void setParty_id(String party_id) {
        this.party_id = party_id;
    }

    public String getParty_id() {
        return this.party_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getLogin_id() {
        return this.login_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getIs_admin() {
        return this.is_admin;
    }

    public void setAgent_status(String agent_status) {
        this.agent_status = agent_status;
    }

    public String getAgent_status() {
        return this.agent_status;
    }

    public Map getAll_menu_url_map() {
        return this.all_menu_url_map;
    }

    public void setAll_menu_url_map(Map all_menu_url_map) {
        this.all_menu_url_map = all_menu_url_map;
    }

    public Map getOwner_butn_map() {
        return this.owner_butn_map;
    }

    public void setOwner_butn_map(Map owner_butn_map) {
        this.owner_butn_map = owner_butn_map;
    }

    public Map getOwner_fild_map() {
        return this.owner_fild_map;
    }

    public void setOwner_fild_map(Map owner_fild_map) {
        this.owner_fild_map = owner_fild_map;
    }

    public Map getOwner_menu_map() {
        if (this.owner_menu_map == null)
            return null;

        String[] keys = (String[]) this.owner_menu_map.keySet().toArray(new String[0]);
        for (int i = 0; i < keys.length; ++i) {
            String key = keys[i];
            key = key.substring(0, key.length() - 3);
            while (key.length() > 0) {
                if (!(this.owner_menu_map.keySet().contains(key)))
                    this.owner_menu_map.put(key, "");

                key = key.substring(0, key.length() - 3);
            }
        }
        return this.owner_menu_map;
    }

    public void setOwner_menu_map(Map owner_menu_map) {
        this.owner_menu_map = owner_menu_map;
    }

    public Map getOwner_menu_url_map() {
        return this.owner_menu_url_map;
    }

    public void setOwner_menu_url_map(Map owner_menu_url_map) {
        this.owner_menu_url_map = owner_menu_url_map;
    }

    public String[] getOwner_org_arr() {
        return this.owner_org_arr;
    }

    public void setOwner_org_arr(String[] owner_org_arr) {
        this.owner_org_arr = owner_org_arr;
    }

    public Map getOwner_recd_map() {
        return this.owner_recd_map;
    }

    public void setOwner_recd_map(Map owner_recd_map) {
        this.owner_recd_map = owner_recd_map;
    }

    public List getRelation_vo_list() {
        return this.relation_vo_list;
    }

    public void setRelation_vo_list(List relation_vo_list) {
        this.relation_vo_list = relation_vo_list;
    }

    public Map getOwner_butn_map_admin() {
        return this.owner_butn_map_admin;
    }

    public void setOwner_butn_map_admin(Map owner_butn_map_admin) {
        this.owner_butn_map_admin = owner_butn_map_admin;
    }

    public Map getOwner_fild_map_admin() {
        return this.owner_fild_map_admin;
    }

    public void setOwner_fild_map_admin(Map owner_fild_map_admin) {
        this.owner_fild_map_admin = owner_fild_map_admin;
    }

    public Map getOwner_menu_map_admin() {
        if (this.owner_menu_map_admin == null)
            return null;

        String[] keys = (String[]) this.owner_menu_map_admin.keySet().toArray(new String[0]);
        for (int i = 0; i < keys.length; ++i) {
            String key = keys[i];
            key = key.substring(0, key.length() - 3);
            while (key.length() > 0) {
                if (!(this.owner_menu_map_admin.keySet().contains(key)))
                    this.owner_menu_map_admin.put(key, "");

                key = key.substring(0, key.length() - 3);
            }
        }
        return this.owner_menu_map_admin;
    }

    public void setOwner_menu_map_admin(Map owner_menu_map_admin) {
        this.owner_menu_map_admin = owner_menu_map_admin;
    }

    public String[] getOwner_org_arr_admin() {
        return this.owner_org_arr_admin;
    }

    public void setOwner_org_arr_admin(String[] owner_org_arr_admin) {
        this.owner_org_arr_admin = owner_org_arr_admin;
    }

    public Map getOwner_recd_map_admin() {
        return this.owner_recd_map_admin;
    }

    public void setOwner_recd_map_admin(Map owner_recd_map_admin) {
        this.owner_recd_map_admin = owner_recd_map_admin;
    }

    public Map getOwner_func_map() {
        this.owner_func_map = new HashMap();
        this.owner_func_map.putAll(this.owner_menu_map);
        this.owner_func_map.putAll(this.owner_butn_map);

        String[] keys = (String[]) this.owner_func_map.keySet().toArray(new String[0]);
        for (int i = 0; i < keys.length; ++i) {
            String key = keys[i];
            key = key.substring(0, key.length() - 3);
            while (key.length() > 0) {
                if (!(this.owner_func_map.keySet().contains(key)))
                    this.owner_func_map.put(key, "");

                key = key.substring(0, key.length() - 3);
            }
        }
        return this.owner_func_map;
    }

    public void setOwner_func_map(Map owner_func_map) {
        this.owner_func_map = owner_func_map;
    }

    public Map getOwner_func_map_admin() {
        this.owner_func_map_admin = new HashMap();
        this.owner_func_map_admin.putAll(this.owner_menu_map_admin);
        this.owner_func_map_admin.putAll(this.owner_butn_map_admin);

        String[] keys = (String[]) this.owner_func_map_admin.keySet().toArray(new String[0]);
        for (int i = 0; i < keys.length; ++i) {
            String key = keys[i];
            key = key.substring(0, key.length() - 3);
            while (key.length() > 0) {
                if (!(this.owner_func_map_admin.keySet().contains(key)))
                    this.owner_func_map_admin.put(key, "");

                key = key.substring(0, key.length() - 3);
            }
        }
        return this.owner_func_map_admin;
    }

    public void setOwner_func_map_admin(Map owner_func_map_admin) {
        this.owner_func_map_admin = owner_func_map_admin;
    }

    public String getRelationtype_id() {
        return this.relationtype_id;
    }

    public void setRelationtype_id(String relationtype_id) {
        this.relationtype_id = relationtype_id;
    }
}
