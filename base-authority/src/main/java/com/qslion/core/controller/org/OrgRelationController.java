/**
 *
 */
package com.qslion.core.controller.org;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qslion.core.entity.AuParty;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.entity.AuUser;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.core.service.PartyRelationService;
import com.qslion.core.service.PartyService;
import com.qslion.framework.bean.*;
import com.qslion.framework.bean.QueryFilter.Operator;
import com.qslion.framework.controller.BaseController;
import io.swagger.annotations.Api;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@Api(value = "团体关系Controller", description = "团体关系Controller", tags = {"团体关系控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/org/relation")
public class OrgRelationController extends BaseController<AuPartyRelation> {
    @Autowired
    public PartyRelationService partyRelationService;
    @Autowired
    public ConnectionRuleService connectionRuleService;
    @Autowired
    public PartyService partyService;

    @RequestMapping(value = "/list/{relationTypeId}")
    public Pager<AuPartyRelation> list(@PathVariable int relationTypeId, @RequestParam Pageable pageable) {
        QueryFilter queryFilter = new QueryFilter("auPartyRelationType", Operator.equal, relationTypeId);
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

    @DeleteMapping
    public boolean deletes(HttpServletRequest request, HttpServletResponse response, ModelMap model, String[] ids) {
        String relationTypeId = request.getParameter("relationTypeId");
        // partyRelationService.delete(ids);
        logger.info("--------------------deletes!!!!!------------------------");
        return true;
    }

    @PutMapping
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                         @ModelAttribute("entity") AuPartyRelation entity) {
        partyRelationService.update(entity);
        return "";
    }

    @GetMapping(value = "/type")
    public List<AuPartyRelationType> getPartyRelationTypes() {
        return Lists.newArrayList(AuPartyRelationType.values());
    }

    @RequestMapping(value = "/tree/{relationType}")
    public List<TreeNode> getPartyRelationTree(@PathVariable(required = false) AuPartyRelationType relationType, @AuthenticationPrincipal AuUser user) {
        List<TreeNode> resultList;
        if (relationType == null) {
            resultList = this.partyRelationService.getGlobalRelationTree(user.getRoles());
        } else {
            resultList = this.partyRelationService.getPartyRelationTree(relationType, user.getRoles());
        }
        return resultList;
    }
}
