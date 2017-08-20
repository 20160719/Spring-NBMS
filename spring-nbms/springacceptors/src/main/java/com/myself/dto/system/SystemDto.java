package com.myself.dto.system;

import com.myself.dto.EntityDto;
import com.myself.persistences.entity.Tree;

public class SystemDto extends EntityDto {

    private Tree tree;

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

}
