package com.upwork.url.shortener.infraestructure.adapter.output.persistence.mapper;

import com.upwork.url.shortener.domain.model.Url;
import com.upwork.url.shortener.infraestructure.adapter.output.persistence.entity.UrlEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UrlPersistenceMapper {
    UrlEntity toUrlEntity(Url url);

    Url toUrl(UrlEntity urlEntity);
}
