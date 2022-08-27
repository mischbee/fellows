package com.fellows.common.model;

public interface ISearchable {
    default Class<?> getType(){
        return this.getClass();
    };

}
