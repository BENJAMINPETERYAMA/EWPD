package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;

public interface HippaSegmentValidator {

	/**
	 * @param mapping
	 * @return
	 */
	public List validate(Mapping mapping) throws DomainException;
}
