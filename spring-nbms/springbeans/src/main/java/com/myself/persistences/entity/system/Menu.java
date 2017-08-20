package com.myself.persistences.entity.system;

import com.myself.persistences.entity.Tree;

public class Menu extends Tree {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String opCode;

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }
}
