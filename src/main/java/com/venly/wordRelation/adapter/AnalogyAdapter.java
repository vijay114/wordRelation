package com.venly.wordRelation.adapter;

import com.venly.wordRelation.dto.AnalogyDTO;
import com.venly.wordRelation.mapper.AnalogyMapper;
import com.venly.wordRelation.repository.AnalogyRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for analogy entity, which will perform required repository action
 * after converting required dto to entity and will also return dto for queries tasks
 *
 * @author Vijay
 */
@Service("analogyAdapter")
public class AnalogyAdapter {

    @Autowired
    AnalogyRepository analogyRepository;

    AnalogyMapper MAPPER = Mappers.getMapper(AnalogyMapper.class);

    /**
     * Method to create analogy
     *
     * @param analogyDTO
     * @return AnalogyDTO
     */
    public AnalogyDTO create(AnalogyDTO analogyDTO) {
        var analogy = MAPPER.fromDto(analogyDTO);
        // if id is set in request, remove the same
        analogyDTO.setId(null);
        // trim and set all words and relation to lowercase
        analogyDTO.setWord1(analogyDTO.getWord1().trim().toLowerCase());
        analogyDTO.setWord2(analogyDTO.getWord2().trim().toLowerCase());
        analogyDTO.setRelation(analogyDTO.getRelation().trim().toLowerCase());
        // save the analogy
        analogy = analogyRepository.save(analogy);
        return MAPPER.fromEntity(analogy);
    }

    /**
     * Method to list all the analogies
     *
     * @return List<AnalogyDTO>
     */
    public List<AnalogyDTO> findAll() {
        return MAPPER.fromEntities(analogyRepository.findAll());
    }

    /**
     * Method to list all the analogies in Inverse
     *
     * @return List<AnalogyDTO>
     */
    public List<AnalogyDTO> findAllInverse() {
        var analogies = MAPPER.fromEntities(analogyRepository.findAll());
        List<AnalogyDTO> reverseAnalogies = new ArrayList<>();
        for(AnalogyDTO analogy: analogies) {
            var newAnalogy = AnalogyDTO.builder().id(analogy.getId()).word1(analogy.getWord2()).word2(analogy.getWord1()).relation(analogy.getRelation()).build();
            reverseAnalogies.add(newAnalogy);
        }
        return reverseAnalogies;
    }

    /**
     * Method to find analogies by relation
     *
     * @param relation
     * @return List<AnalogyDTO>
     */
    public List<AnalogyDTO> findAllByRelation(String relation) {
        return MAPPER.fromEntities(analogyRepository.findAllByRelation(relation));
    }

    /**
     * Method to count analogies by words and relation
     *
     * @param word1
     * @param word2
     * @return Long
     */
    public Long countByWord1AndWord2(String word1, String word2) {
        try{
            return analogyRepository.countByWord1AndWord2(word1, word2);
        } catch (Exception e) {
            return 0L;
        }
    }

}
