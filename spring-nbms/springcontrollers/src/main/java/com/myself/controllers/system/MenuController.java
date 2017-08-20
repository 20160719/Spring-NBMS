package com.myself.controllers.system;

import com.myself.controllers.AbstractSystemController;
import com.myself.dto.system.SystemDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.myself.acceptors.BusinessResult;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;

import java.util.List;

@Controller
@RequestMapping(value = "/system/menu")
public class MenuController extends AbstractSystemController<Tree> {

	@RequestMapping(value = "create" + SUFFIX, method = RequestMethod.POST)
	@ResponseBody
	public BusinessResult create(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
		return doController(systemDto, beforeController, (absBusinessObj) -> getMenuAcceptor().creates(absBusinessObj));
	}

	@RequestMapping(value = "modify" + SUFFIX, method = RequestMethod.POST)
	@ResponseBody
	public BusinessResult modify(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
		return doController(systemDto, beforeController, (absBusinessObj) -> getMenuAcceptor().modifies(absBusinessObj));
	}

	@RequestMapping(value = "delete" + SUFFIX, method = RequestMethod.POST)
	@ResponseBody
	public BusinessResult delete(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
		return doController(systemDto, beforeController, (absBusinessObj) -> getMenuAcceptor().deletes(absBusinessObj));
	}
	
	@RequestMapping(value = "query" + SUFFIX, produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> query(@ModelAttribute("systemDto") SystemDto systemDto) throws CustomException {
		Tree tree = systemDto.getTree();
		return getMenuAcceptor().queryTrees(tree);
	}

	@RequestMapping(value = "queryMenuSeq" + SUFFIX, produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public BusinessResult queryMenuSeq() throws CustomException {
		return getMenuAcceptor().querySeq();
	}
	
	@Override
	protected String getDirectory() {
		return "system/menu/";
	}

}
