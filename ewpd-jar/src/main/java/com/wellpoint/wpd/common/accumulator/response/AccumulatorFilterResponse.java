package com.wellpoint.wpd.common.accumulator.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


public class AccumulatorFilterResponse extends WPDResponse{
	private List accumList;
	List list = null;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getAccumList() {
		return accumList;
	}

	public void setAccumList(List accumList) {
		this.accumList = accumList;
	}

	
}
