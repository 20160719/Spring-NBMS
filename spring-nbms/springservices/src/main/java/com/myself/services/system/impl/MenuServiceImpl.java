package com.myself.services.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.myself.persistences.entity.Tree;
import com.myself.services.AbstractSystemService;
import com.myself.services.system.IMenuService;

@Service(value = "menuServiceImpl")
public class MenuServiceImpl extends AbstractSystemService<Tree> implements
		IMenuService {

	@Override
	public Integer creates(List<Tree> list) throws Exception {
		return getMenuMapper().creates(list);
	}

	@Override
	public Integer deletes(List<Tree> list) throws Exception {
		return getMenuMapper().deletes(list);
	}

	@Override
	public Integer modifies(List<Tree> list) throws Exception {
		return getMenuMapper().modifies(list);
	}

	@Override
	public Tree load(Tree obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tree> queries(Tree obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tree> query(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tree> queryTrees() throws Exception {
		return getMenuMapper().queryTrees();
	}

	@Override
	public String queryMenuSeq() throws Exception {
		return querySeqByName("MENU");
	}
}
