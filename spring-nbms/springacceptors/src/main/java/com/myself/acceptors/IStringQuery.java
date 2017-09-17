package com.myself.acceptors;

import org.springframework.dao.DataAccessException;

public abstract interface IStringQuery {

    public abstract String query() throws DataAccessException;

}
