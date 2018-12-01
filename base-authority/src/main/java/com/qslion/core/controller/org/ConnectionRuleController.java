/**
 *
 */
package com.qslion.core.controller.org;

import com.google.common.collect.Lists;
import com.qslion.core.entity.AuConnectionRule;
import com.qslion.core.service.ConnectionRuleService;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 连接规则控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@RestController
@RequestMapping(value = "/org/connection/rule")
public class ConnectionRuleController extends BaseController<AuConnectionRule> {

    @Autowired
    private ConnectionRuleService connectionRuleService;

    @GetMapping(value = "/list")
    public Pager<AuConnectionRule> list(@RequestParam Pageable pageable) {
        return connectionRuleService.findPage(pageable);
    }

    @PostMapping
    public Long insert(@RequestBody AuConnectionRule connectionRule) {
        AuConnectionRule auConnectionRule = connectionRuleService.save(connectionRule);
        return auConnectionRule.getId();
    }

    @DeleteMapping
    public boolean deletes(Long[] ids) {
        List<AuConnectionRule> ruleList = Lists.newArrayList(ids).stream().map(id -> {
            AuConnectionRule rule = new AuConnectionRule();
            rule.setId(id);
            return rule;
        }).collect(Collectors.toList());
        connectionRuleService.delete(ruleList);
        return true;
    }

    @PutMapping
    public boolean update(@RequestBody AuConnectionRule rule) {
        AuConnectionRule connectionRule = connectionRuleService.update(rule);
        return connectionRule == null;
    }
    
    @GetMapping(value = "/detail/{id}")
    public AuConnectionRule detail(@PathVariable Long id) {
        return connectionRuleService.findById(id);
    }

}
