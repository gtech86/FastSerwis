package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import pl.grabowski.fastserwis.dto.IdNameCategoryDTO;
import pl.grabowski.fastserwis.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<Category, IdNameCategoryDTO>{
}
