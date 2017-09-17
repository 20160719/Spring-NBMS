package com.myself.controllers.system;

import com.myself.acceptors.CmdResult;
import com.myself.controllers.AbstractSystemController;
import com.myself.exception.CustomException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/system/cache")
public class CacheController extends AbstractSystemController<Void> {


    @RequestMapping(value = "refreshAllCache" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public CmdResult refreshAllCache() throws CustomException {
        refreshMenuCache();
        refreshRoleCache();
        refreshOrgCache();
        return null;
    }

    @RequestMapping(value = "refreshMenuCache" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public CmdResult refreshMenuCache() throws CustomException {
        getMenuAcceptor().refreshCache();
        return null;
    }
    
    @RequestMapping(value = "refreshRoleCache" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public CmdResult refreshRoleCache() throws CustomException {
        getRoleAcceptor().refreshCache();
        return null;
    }
    
    @RequestMapping(value = "refreshOrgCache" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public CmdResult refreshOrgCache() throws CustomException {
        getOrgAcceptor().refreshCache();
        return null;
    }

    @Override
    protected String getDirectory() {
        return null;
    }

}
