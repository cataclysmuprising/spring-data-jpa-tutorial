package com.github.cataclysmuprising.springdatajpatutorial.util.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.github.cataclysmuprising.springdatajpatutorial.domain.AbstractEntity;
import com.github.cataclysmuprising.springdatajpatutorial.dto.AbstractDTO;
import com.github.cataclysmuprising.springdatajpatutorial.dto.StudentDTO;
import com.github.cataclysmuprising.springdatajpatutorial.exception.ValidationFailedException;

public class ObjectMapperUtil {
	private static final Logger logger = LogManager.getLogger(ObjectMapperUtil.class);

	private static ObjectMapper objectMapper;
	private static Validator validator;
	static {
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JodaModule());
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static <E extends AbstractEntity, D extends AbstractDTO> D mapEntityIntoDTO(E entity, Class<D> clazz) {
		return objectMapper.convertValue(entity, clazz);
	}

	public static <E extends AbstractEntity, D extends AbstractDTO> Iterable<D> mapEntityIntoDTO(Iterable<E> entities, Class<D> clazz) {
		List<D> dtos = new ArrayList<>();
		entities.forEach(e -> dtos.add(mapEntityIntoDTO(e, clazz)));
		return dtos;
	}

	public static <D extends AbstractDTO, E extends AbstractEntity> E mapDTOIntoEntity(D dto, Class<E> clazz) {
		E obj = objectMapper.convertValue(dto, clazz);
		Set<ConstraintViolation<E>> constraintViolations = validator.validate(obj);
		if (constraintViolations.size() > 0) {
			throw new ValidationFailedException(constraintViolations);
		}
		return obj;
	}

	public static <D extends AbstractDTO, E extends AbstractEntity> Iterable<E> mapDTOIntoEntity(Iterable<D> dtos, Class<E> clazz) {
		List<E> entities = new ArrayList<>();
		dtos.forEach(dto -> entities.add(mapDTOIntoEntity(dto, clazz)));
		return entities;
	}

	public static <T> void showEntriesOfCollection(Iterable<StudentDTO> results) {
		if (results != null) {
			results.forEach(result -> logger.debug(" >>> " + result.toString()));
		}
	}

}
