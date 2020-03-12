package com.qslion.authority.core.controller.au;

import com.google.common.collect.Lists;
import com.qslion.authority.core.entity.AuOrgRelation;
import com.qslion.authority.core.entity.AuRole;
import com.qslion.authority.core.entity.AuUser;
import com.qslion.authority.core.service.AuRoleService;
import com.qslion.authority.core.service.AuUserService;
import com.qslion.authority.core.service.AuOrgRelationService;
import com.qslion.authority.custom.entity.AuEmployee;
import com.qslion.authority.custom.service.AuEmployeeService;
import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.framework.util.Constants;
import io.swagger.annotations.Api;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 用户控制类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Api(value = "用户Controller", description = "用户Controller", tags = {"用户控制器"})
@ResponseResult
@RestController
@RequestMapping(value = "/au/user")
public class AuUserController extends BaseController<AuUser> {

    @Autowired
    private AuUserService auUserService;
    @Autowired
    private AuRoleService roleService;
    @Autowired
    private AuOrgRelationService auOrgRelationService;
    @Autowired
    private AuEmployeeService auEmployeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.servlet.multipart.location}")
    private String fileUploadPath;

    /**
     * 列表
     */
    @PostMapping(value = "/list")
    public Pager<AuUser> list(@RequestBody Pageable pageable) {
        return auUserService.findPage(pageable);
    }


    /**
     * 增加
     *
     * @param file 接受上传的文件,此处为用户头像
     */
    @PostMapping("/avatar")
    public String saveAvatar(@RequestParam(value = "avatar", required = false) MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_";
        if (file != null) {
            //根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            fileName = fileName + file.getOriginalFilename();
            if (file.getSize() > 1024000L) {
                //超过1Mb,抛出异常
                throw new BusinessException(ResultCode.FAIL);
            }
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf(Constants.FILE_NAME_SEPERRATOR) + 1);
            List<String> imgTypes = Lists.newArrayList("png", "jpeg", "bpm");
            if (!imgTypes.contains(suffixName)) {
                //文件类型校验,抛出异常
                throw new BusinessException(ResultCode.FAIL);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Path classPath = Paths.get(ResourceUtils.getURL("classpath:").toURI());
                String destFileName = fileUploadPath + sdf.format(new Date()) + File.separator + fileName;
                Path destFilePath = Paths.get(classPath.toAbsolutePath().toString(), destFileName);
                if (Files.notExists(destFilePath.getParent())) {
                    Files.createDirectory(destFilePath.getParent());
                }
                Files.write(destFilePath, file.getBytes());
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    /**
     * 增加
     */
    @PostMapping
    public Long save(@Validated @RequestBody AuUser user) {
        String encrypt = passwordEncoder.encode(user.getPassword());
        user.setPassword(encrypt);
        AuUser auUser = auUserService.save(user);
        return auUser.getId();
    }

    /**
     * 通过关联职员档案增加
     */
    @PostMapping(value = "/ref")
    public Long refSave(@Validated @RequestBody AuUser user) {
        String encrypt = passwordEncoder.encode(user.getPassword());
        user.setPassword(encrypt);
        AuOrgRelation relation = auOrgRelationService.findById(Long.valueOf(user.getUsername()));
        AuEmployee emp = auEmployeeService.findById(relation.getOrgId());
        user.setUsername(emp.getEnglishName());
        user.setEmail(emp.getEmail());
        user.setMobile(emp.getMobilePhone());
        AuUser auUser = auUserService.save(user);
        return auUser.getId();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public boolean delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            ids.forEach(id -> auUserService.delete(id));
            return true;
        }
        return false;
    }

    /**
     * 更新
     */
    @PutMapping
    public boolean update(@RequestBody AuUser user) {
        String encrypt = passwordEncoder.encode(user.getPassword());
        user.setPassword(encrypt);
        AuUser auUser = auUserService.update(user);
        return auUser == null;
    }

    /**
     * 查看，明细
     */
    @GetMapping(value = "/detail/{id}")
    public AuUser detail(@PathVariable Long id) {
        return auUserService.findById(id);
    }


    /**
     * 用户关联角色
     */
    @PostMapping(value = "/role/{userId}")
    public Boolean grantRoleAuth(@PathVariable Long userId, @RequestBody Long[] relationIds) {
        AuUser user = auUserService.findById(userId);
        List<AuOrgRelation> relations = auOrgRelationService.findList(relationIds);
        Long[] roleIds = relations.stream().map(AuOrgRelation::getOrgId).collect(Collectors.toList()).toArray(new Long[0]);
        List<AuRole> roles = roleService.findList(roleIds);
        user.getRoles().addAll(roles);
        return auUserService.saveOrUpdate(user) == null;
    }
}