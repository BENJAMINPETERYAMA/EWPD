package com.wellpoint.wpd.common.indicativemapping.request;



import java.util.List;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.indicativemapping.vo.IndicativeDetailVO;


public class IndicativeDetailRequest extends WPDRequest {
	
	private String indicativeSegment;

	private String region;
	
	private String action;
	
	private List<IndicativeDetailVO> inidcativeDetailListToCompare;


	public String getIndicativeSegment() {
		return indicativeSegment;
	}


	public void setIndicativeSegment(String indicativeSegment) {
		this.indicativeSegment = indicativeSegment;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public List<IndicativeDetailVO> getInidcativeDetailListToCompare() {
		return inidcativeDetailListToCompare;
	}


	public void setInidcativeDetailListToCompare(
			List<IndicativeDetailVO> inidcativeDetailListToCompare) {
		this.inidcativeDetailListToCompare = inidcativeDetailListToCompare;
	}

	
	@Override
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

}
