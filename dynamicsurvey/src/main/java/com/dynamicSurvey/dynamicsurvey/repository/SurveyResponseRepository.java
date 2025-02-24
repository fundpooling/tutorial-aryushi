//package com.dynamicSurvey.dynamicsurvey.repository;
//
//import com.dynamicSurvey.dynamicsurvey.model.SurveyResponse;
//
//import java.util.List;
//
//public class SurveyResponseRepository {
//    public SurveyResponse save(SurveyResponse response) {
//    }
//
//    public List<SurveyResponse> findAll() {
//    }
//}
package com.dynamicSurvey.dynamicsurvey.repository;

import com.dynamicSurvey.dynamicsurvey.model.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {
}
