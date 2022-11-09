package com.venly.wordRelation.service;

import com.venly.wordRelation.adapter.AnalogyAdapter;
import com.venly.wordRelation.dto.AnalogyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalogyService {

    @Autowired
    AnalogyAdapter analogyAdapter;

    /**
     * Method to create analogy
     *
     * @param analogyDTO
     * @return AnalogyDTO
     * @throws Exception
     */
    public AnalogyDTO create(AnalogyDTO analogyDTO) throws Exception {
        if (!isNewUniqueRelation(analogyDTO)) {
            throw new Exception("Relation already exist between the given two words");
        }
        return analogyAdapter.create(analogyDTO);
    }

    /**
     * Method to list all word relation or list all relation filtered by provided relation
     *
     * @param relation
     * @return
     */
    public List<AnalogyDTO> list(String relation) {
        try {
            if (relation == null || relation.trim().length() == 0) {
                return analogyAdapter.findAll();
            } else {
                return analogyAdapter.findAllByRelation(relation);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching word relations " + e.getMessage());
            return new ArrayList<>();
        }

    }

    /**
     * Method to list all word relation and inverse of the relation
     *
     * @return List<AnalogyDTO>
     */
    public List<AnalogyDTO> listAndInverse() {
        try {
            List<AnalogyDTO> analogyDTOList = new ArrayList<>();
            analogyDTOList.addAll(analogyAdapter.findAll());
            analogyDTOList.addAll(analogyAdapter.findAllInverse());
            return analogyDTOList;
        } catch (Exception e) {
            log.error("An error occurred while fetching word relations " + e.getMessage());
            return new ArrayList<>();
        }
    }


    /**
     * Method to validate if the new relation is unique
     *
     * @param analogyDTO
     * @return boolean
     */
    private boolean isNewUniqueRelation(AnalogyDTO analogyDTO) {
        Long count = analogyAdapter.countByWord1AndWord2(
                analogyDTO.getWord1(), analogyDTO.getWord2());
        if (count > 0) return false;
        count = analogyAdapter.countByWord1AndWord2(
                analogyDTO.getWord2(), analogyDTO.getWord1());
        if (count > 0) return false;
        return true;
    }


}
