package com.myself.controllers.system;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myself.acceptors.BusinessResult;
import com.myself.busiobj.system.SystemObj;
import com.myself.controllers.AbstractSystemController;
import com.myself.dto.system.SystemDto;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.system.Permission;

@Controller
@RequestMapping(value = "/system/permission")
public class PermissionController extends AbstractSystemController<Permission> {

    @RequestMapping(value = "create" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public BusinessResult create(@RequestParam("targetJson") String targetJson) throws CustomException {
        SystemDto sysDto = getSystemDto();
        sysDto.setTargetJson(targetJson);
        SystemObj<Permission> sysObj = beforeAction(sysDto);
        return getPermsAcceptor().creates(sysObj);
    }

    @RequestMapping(value = "modify" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public BusinessResult modify(@RequestParam("targetJson") String targetJson) throws CustomException {
        SystemDto sysDto = getSystemDto();
        sysDto.setTargetJson(targetJson);
        SystemObj<Permission> sysObj = beforeAction(sysDto);
        return getPermsAcceptor().modifies(sysObj);
    }

    @RequestMapping(value = "delete" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public BusinessResult delete(@RequestParam("targetJson") String targetJson) throws CustomException {
        SystemDto sysDto = getSystemDto();
        sysDto.setTargetJson(targetJson);
        SystemObj<Permission> sysObj = beforeAction(sysDto);
        return getPermsAcceptor().deletes(sysObj);
    }

    @RequestMapping(value = "query" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public List<Permission> query(@RequestParam("targetJson") String targetJson) throws CustomException {
        SystemDto sysDto = getSystemDto();
        sysDto.setTargetJson(targetJson);
        SystemObj<Permission> sysObj = beforeAction(sysDto);
        System.out.println(sysObj);
        return getPermsAcceptor().queryPermissions();
    }

    @Override
    protected String getDirectory() {
        return null;
    }

}
