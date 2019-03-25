/**
 *
 */
package com.qslion.core.controller.org;

import com.google.common.collect.Lists;
import com.qslion.core.entity.AuParty;
import com.qslion.core.service.PartyService;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.QueryFilter;
import com.qslion.framework.bean.QueryFilter.Operator;
import com.qslion.framework.controller.BaseController;
import io.swagger.annotations.Api;
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
 * 团体控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Api(value="团体Controller",description="团体Controller",tags={"团体控制器"})
@RestController
@RequestMapping(value = "/org/party")
public class PartyController extends BaseController<AuParty> {

    @Autowired
    private PartyService partyService;

    @GetMapping(value = "/list/{partyTypeId}")
    public Pager<AuParty> list(@PathVariable int partyTypeId, @RequestParam Pageable pageable) {
        QueryFilter queryFilter = new QueryFilter("auPartyType", Operator.equal, partyTypeId);
        if (pageable.getQueryFilters().isEmpty()) {
            pageable.setQueryFilters(Lists.newArrayList(queryFilter));
        } else {
            pageable.getQueryFilters().add(queryFilter);
        }
        return partyService.findPage(pageable);
    }

    @GetMapping(value = "/detail/{id}")
    public AuParty detail(@PathVariable Long id) {
        return partyService.findById(id);
    }

    @PostMapping
    public Long save(@RequestBody AuParty party) {
        AuParty auParty = partyService.save(party);
        return auParty.getId();
    }

    @DeleteMapping
    public boolean deletes(Long[] ids) {
        List<AuParty> partyList = Lists.newArrayList(ids).stream().map(id -> {
            AuParty party = new AuParty();
            party.setId(id);
            return party;
        }).collect(Collectors.toList());
        partyService.delete(partyList);
        return true;
    }

    @PutMapping
    public boolean update(@RequestBody AuParty entity) {
        AuParty auParty = partyService.update(entity);
        return auParty == null;
    }

    @GetMapping(value = "/{id}")
    public AuParty getById(@PathVariable Long id) {
        return partyService.findById(id);
    }

}
