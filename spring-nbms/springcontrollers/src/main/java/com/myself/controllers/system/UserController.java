package com.myself.controllers.system;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myself.acceptors.CmdResult;
import com.myself.controllers.AbstractSystemController;
import com.myself.dto.system.SystemDto;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;
import com.myself.persistences.entity.system.User;

@Controller
@RequestMapping(value = "/system/user")
public class UserController extends AbstractSystemController<User> {

    @RequestMapping(value = "create" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public CmdResult create(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
        return doController(systemDto, beforeController, (absBusinessObj) -> getUserAcceptor().creates(absBusinessObj));
    }

    @RequestMapping(value = "modify" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public CmdResult modify(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
        return doController(systemDto, beforeController, (absBusinessObj) -> getUserAcceptor().modifies(absBusinessObj));
    }

    @RequestMapping(value = "query" + SUFFIX, produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public List<Tree> query(@ModelAttribute("systemDto") SystemDto systemDto) throws Exception {
        Tree tree = systemDto.getTree();
        return getMenuAcceptor().queryTrees(tree);
    }

    @Override
    protected String getDirectory() {
        return "system/user/";
    }

}
