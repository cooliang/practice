package net.cooliang.dubbo.api.rpc;

public class PageParam<T> extends Param<T> {

	private static final long serialVersionUID = 1L;

	private int pageIndex;
	private int pageSize;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
