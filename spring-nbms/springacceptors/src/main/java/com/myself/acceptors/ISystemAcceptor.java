package com.myself.acceptors;

import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;

import java.util.List;

public interface ISystemAcceptor<T> extends IAbstractAcceptor<T> {

    public abstract List<Tree> queryTrees(Tree tree) throws Exception;

    public abstract List<Tree> queryTrees() throws CustomException;

    public abstract CmdResult querySeq() throws CustomException;

    public abstract void refreshCache() throws CustomException;

}
