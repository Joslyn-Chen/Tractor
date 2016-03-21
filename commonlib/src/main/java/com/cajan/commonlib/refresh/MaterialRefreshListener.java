package com.cajan.commonlib.refresh;

public abstract class MaterialRefreshListener {
    public void onfinish(){}
    public abstract void onRefresh(MaterialRefreshLayout materialRefreshLayout);
    public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout){}
}
