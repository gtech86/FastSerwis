package pl.grabowski.fastserwis.dto;

import lombok.Data;
import pl.grabowski.fastserwis.model.Category;

import javax.persistence.Column;

@Data
public class IdNameCategoryDTO {
    private Long categoryId;
    private String categoryName;
}
