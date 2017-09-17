package com.myself.controllers.system;

import com.myself.acceptors.CmdResult;
import com.myself.controllers.AbstractSystemController;
import com.myself.dto.system.SystemDto;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/system/bookType")
public class BookTypeController extends AbstractSystemController<Tree> {

    @RequestMapping(value = "create" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public CmdResult create(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
        return doController(systemDto, beforeController, (absBusObj) -> getBookTypeAcceptor().creates(absBusObj));
    }

    @RequestMapping(value = "modify" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public CmdResult modify(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
        return doController(systemDto, beforeController, (absBusObj) -> getBookTypeAcceptor().modifies(absBusObj));
    }

    @RequestMapping(value = "delete" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public CmdResult delete(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
        return doController(systemDto, beforeController, (absBusObj) -> getBookTypeAcceptor().deletes(absBusObj));
    }

    @RequestMapping(value = "query" + SUFFIX, method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> query(@ModelAttribute("systemDto") SystemDto systemDto) throws Exception {
        Tree tree = systemDto.getTree();
        return getBookTypeAcceptor().queryTrees(tree);
    }

    @Override
    protected String getDirectory() {
        return null;
    }

}
