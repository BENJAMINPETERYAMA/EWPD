package com.wellpoint.ets.ebx.simulation.util.x12parser.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;

public class X12ParserUtil {
	static ResourceBundle res;
	static {
		 res = PropertyResourceBundle.getBundle("x12Translation");
	}
	
	public static synchronized SegmentMetaData[] getPositionSortedSegments(SegmentMetaData[] segmentInfoEnums){
		Arrays.sort(segmentInfoEnums, new SegmentPositionComparator<SegmentMetaData>());		
		return segmentInfoEnums;

	}
	private static class SegmentPositionComparator<T extends SegmentMetaData> implements Comparator<T> {
		@Override
		public int compare(T o1, T o2) {
			return o1.getSegmentPosition().compareToIgnoreCase( o2.getSegmentPosition()); 
		}
	}
	public static X12Segment createInstance(
			Class<? extends X12Segment> segmentClass) throws X12ParserException {
		try {
			return segmentClass.newInstance();
		} catch (InstantiationException e) {
			throw new X12ParserException("InstantiationException while creating " +
					"instance of "+segmentClass.toString() ,e);
		} catch (IllegalAccessException e) {
			throw new X12ParserException("IllegalAccessException while creating instance" +
					" of "+segmentClass.toString() ,e);
		}
	}
	public static String getX12Description(String key) {	
		if (res.containsKey(key)) {
			return res.getString(key);
		}
		else {
			return "";
		}
	}
}
