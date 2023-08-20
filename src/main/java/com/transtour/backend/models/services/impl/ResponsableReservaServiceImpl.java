package com.transtour.backend.models.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IResponsableReservaDao;
import com.transtour.backend.models.dto.response.ResponsableReservaResponse;
import com.transtour.backend.models.entity.ResponsableReserva;
import com.transtour.backend.models.services.IResponsableReservaService;

@Service
public class ResponsableReservaServiceImpl implements IResponsableReservaService{

	@Autowired
	private IResponsableReservaDao responsableDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional
	public ResponsableReservaResponse save(ResponsableReservaResponse responsableReservaDto) {
		ResponsableReserva responsable = modelMapper.map(responsableReservaDto, ResponsableReserva.class);
		responsable = responsableDao.save(responsable);
		return modelMapper.map(responsable, ResponsableReservaResponse.class);
	}

}
