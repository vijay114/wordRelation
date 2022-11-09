package com.venly.wordRelation.mapper;

import com.venly.wordRelation.dto.AnalogyDTO;
import com.venly.wordRelation.entity.Analogy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper interface to map Dto to Entity and vice versa
 *
 * @author Vijay
 */
@Mapper
public interface AnalogyMapper {

    Analogy fromDto(AnalogyDTO analogyDTO);

    AnalogyDTO fromEntity(Analogy analogy);

    List<Analogy> fromDTOs(List<AnalogyDTO> analogyDTOS);

    List<AnalogyDTO> fromEntities(List<Analogy> analogies);

}
