/**
 *
 */
package com.qslion.core.controller.org;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.service.PartyService;
import com.qslion.core.util.TreeNode;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.QueryFilter;
import com.qslion.framework.bean.QueryFilter.Operator;
import com.qslion.framework.controller.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 团体关系控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@RestController
@RequestMapping(value = "/org/party_relation")
public class PartyRelationController extends BaseController<AuPartyRelation> {

    @Autowired
    public PartyRelationService partyRelationService;

    @Autowired
    public ConnectionRuleService connectionRuleService;
    @Autowired
    public PartyService partyService;


    @RequestMapping(value = "/list/{partyRelationTypeId}")
    public Pager<AuPartyRelation> list(@PathVariable int partyRelationTypeId, @RequestParam Pageable pageable) {
        QueryFilter queryFilter = new QueryFilter("auPartyRelationType", Operator.eq, partyRelationTypeId);
        if (pageable.getQueryFilters().isEmpty()) {
            pageable.setQueryFilters(Lists.newArrayList(queryFilter));
        } else {
            pageable.getQueryFilters().add(queryFilter);
        }
        return partyRelationService.findPage(pageable);
    }

    @GetMapping(value = "/detail/{id}")
    public AuPartyRelation input(@PathVariable Long id) {
        return partyRelationService.findById(id);
    }

    @PostMapping
    public Long save(@RequestBody AuPartyRelation partyRelation) {
        /*************************
         * 团体关系添加规则：
         * 1.判断该关系是否存在
         * 2.根据父子团体类型ID和团体关系类型ID验证是否符合团体连接规则
         * 3.添加团体关系
         * 4.更新团体关系的叶子节点状态
         * *****************************/
        //设置子团体及其类型ID
      /*  String partyId = entity.getAuParty().getId();
        String relTypeId = entity.getAuPartyRelationType().getId();
        String parentRelId = entity.getId() == null ? "" : entity.getId();

        AuParty party = this.partyService.get(partyId);
        String childTypeId = party.getAuPartyType().getId();
        entity.setAuParty(party);
        entity.setPartytypeId(party.getAuPartyType().getId());
        entity.setName(party.getName());
        entity.setRelationTypeKeyword(party.getPartytypeKeyword());

        String checkMsg = "";
        if (parentRelId != null && !"".equals(parentRelId)) {
            //设置父团体团体关系
            AuPartyRelation parentRelation = this.partyRelationService.get(entity.getId());
            entity.setParentCode(parentRelation.getId());
            entity.setParentPartyid(parentRelation.getAuParty().getId());
            //校验连接规则
            party = this.partyService.get(parentRelation.getAuParty().getId());
            String parentTypeId = party.getAuPartyType().getId();
            boolean flag = connectionRuleService.checkRule(parentTypeId, childTypeId, relTypeId);
            if (flag) {
                entity.setIsLeaf("1");
                entity.setTypeIsLeaf("1");
                entity.setIsChief("1");
                entity.setIsInherit("1");
                this.partyRelationService.save(entity);
                //第四步
                parentRelation.setIsLeaf("0");
                this.partyRelationService.update(parentRelation);
            } else {
                checkMsg = "添加失败，没有满足条件的连接规则!";
            }
        } else {
            //设置根节点
            boolean flag = connectionRuleService.checkRule(childTypeId, "", relTypeId);
            if (flag) {
                entity.setIsLeaf("1");
                entity.setTypeIsLeaf("1");
                entity.setIsChief("1");
                entity.setIsInherit("1");
                this.partyRelationService.save(entity);
            } else {
                checkMsg = "添加根节点失败，没有满足条件的连接规则!";
            }
        }*/
        return null;//forward("index", true) + "?relationTypeId=" + relTypeId + "&checkMsg=" + URLEncoder.encode(checkMsg, "UTF-8");
    }

    @DeleteMapping
    public boolean deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) {
        String relationTypeId = request.getParameter("relationTypeId");
        // partyRelationService.delete(ids);
        logger.info("--------------------deletes!!!!!------------------------");
        return true;
    }

    //更新
    @RequestMapping(value = "/admin/party/relation/update.jspx")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model,
        @ModelAttribute("entity") AuPartyRelation entity) {
        partyRelationService.update(entity);
        return "";
    }

    @GetMapping(value = "/type")
    public List<AuPartyRelationType> getPartyRelationTypes() {
        return Lists.newArrayList(AuPartyRelationType.values());
    }

    //局部关系树
    public String getPartyRelationTree(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String relationTypeId =
            request.getParameter("relationTypeId") == null ? "" : request.getParameter("relationTypeId");
        List<TreeNode> resultList = this.partyRelationService.getPartyRelationTree(relationTypeId, false);
        return JSON.toJSONString(resultList);
    }

    //全局关系树
    @RequestMapping(value = "/admin/party/relation/tree.jspx")
    public String getGlobalPartyRelationTree(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<TreeNode> resultList = this.partyRelationService.getGlobalRelationTree();
        model.addAttribute("data", JSON.toJSONString(resultList));
        return "";
    }
}
