package com.github.cataclysmuprising.springdatajpatutorial.criteria;

import com.github.cataclysmuprising.springdatajpatutorial.entity.QAbstractEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.CollectionUtils;

import java.util.Set;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Data
@ToString(exclude = {"DEFAULT_MAX_ROWS"})
public abstract class AbstractCriteria {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final int DEFAULT_MAX_ROWS = 100;

    protected String keyword;
    protected Long id;
    protected Set<Long> includeIds;
    protected Set<Long> excludeIds;
    protected String sortProperty;
    protected String sortType;
    private Long recordRegId;
    private Long recordUpdId;
    private DateTime recordRegTimeFrom;
    private DateTime recordRegTimeTo;
    private DateTime recordUpdTimeFrom;
    private DateTime recordUpdTimeTo;

    BooleanBuilder getCommonFilter(QAbstractEntity entity) {
        BooleanBuilder predicate = new BooleanBuilder();
        if (id != null) {
            predicate.and(entity.id.eq(this.id));
        }
        if (recordRegId != null) {
            predicate.and(entity.recordRegId.eq(this.recordRegId));
        }
        if (recordUpdId != null) {
            predicate.and(entity.recordUpdId.eq(this.recordUpdId));
        }
        if (recordRegTimeFrom != null) {
            predicate.and(entity.recordRegTime.after(recordRegTimeFrom));
        }
        if (recordRegTimeTo != null) {
            predicate.and(entity.recordRegTime.before(recordRegTimeTo));
        }
        if (recordUpdTimeFrom != null) {
            predicate.and(entity.recordUpdTime.after(recordUpdTimeFrom));
        }
        if (recordUpdTimeTo != null) {
            predicate.and(entity.recordUpdTime.before(recordUpdTimeTo));
        }
        if (!CollectionUtils.isEmpty(this.includeIds)) {
            predicate.and(entity.id.in(includeIds));
        }
        if (!CollectionUtils.isEmpty(this.excludeIds)) {
            predicate.and(entity.id.notIn(excludeIds));
        }
        return predicate;
    }

    public Pageable getPager(Integer offset, Integer limit) {
        if (offset == null || limit == null) {
            return null;
        }
        int page = offset > 0 ? offset / limit : 0;
        limit = limit > 0 ? limit : DEFAULT_MAX_ROWS;

        if (StringUtils.isBlank(sortProperty)) {
            return PageRequest.of(page, limit);
        } else {
            if (StringUtils.isBlank(sortType)) {
                sortType = "ASC";
            }
            Sort.Direction direction = ASC.toString().equalsIgnoreCase(sortType) ? ASC : DESC;
            return PageRequest.of(page, limit, Sort.by(Order.by(sortProperty).with(direction)));
        }

    }


    public Sort getSort(String sortProperty, String sortType) {
        if (StringUtils.isBlank(sortType)) {
            sortType = "ASC";
        }
        Sort.Direction direction = ASC.toString().equalsIgnoreCase(sortType) ? ASC : DESC;
        return Sort.by(Order.by(sortProperty).with(direction));
    }

    public abstract Predicate getFilter();
}
