package com.myself.controllers;

import com.myself.acceptors.BusinessResult;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.exception.CustomException;

public abstract interface IDoController<T> {

    public abstract BusinessResult doController(AbsBusinessObj<T> absBusinessObj) throws CustomException;

}
