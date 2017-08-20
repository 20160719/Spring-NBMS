package com.myself.controllers.system;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myself.acceptors.BusinessResult;
import com.myself.controllers.AbstractSystemController;
import com.myself.dto.system.SystemDto;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;

@Controller
@RequestMapping(value = "/system/org")
public class OrgController extends AbstractSystemController<Tree> {

    @RequestMapping(value = "create" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public BusinessResult create(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
        return doController(systemDto, beforeController, (absBusinessObj) -> getOrgAcceptor().creates(absBusinessObj));
    }

    @RequestMapping(value = "modify" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public BusinessResult modify(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
        return doController(systemDto, beforeController, (absBusinessObj) -> getOrgAcceptor().modifies(absBusinessObj));
    }

    @RequestMapping(value = "delete" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public BusinessResult delete(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
        return doController(systemDto, beforeController, (absBusinessObj) -> getOrgAcceptor().deletes(absBusinessObj));
    }

    @RequestMapping(value = "query" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> query(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
        Tree tree = systemDto.getTree();
        return getOrgAcceptor().queryTrees(tree);
    }

    @Override
    protected String getDirectory() {
        return "system/org/";
    }

}
