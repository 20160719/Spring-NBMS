package com.myself.controllers;

import com.myself.busiobj.AbsBusinessObj;
import com.myself.dto.EntityDto;
import com.myself.exception.CustomException;

public abstract interface IBeforeController<T> {

    public abstract AbsBusinessObj<T> beforeController(EntityDto entityDto) throws CustomException;

}
