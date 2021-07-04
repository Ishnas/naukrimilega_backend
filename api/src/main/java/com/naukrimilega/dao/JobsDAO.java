package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.model.FilterByType;
import com.naukrimilega.firebaseutil.model.FilterParam;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.JobDetails;
import com.naukrimilega.models.query.Category;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobsDAO {
    private final IDatabaseService databaseService;
    TypeReference<HashMap<String, Object>> typeRef;
    TypeReference<JobDetails> typeRefJobDetails;

    @Inject
    public JobsDAO(IDatabaseService databaseService) {
        this.databaseService = databaseService;
        typeRef = new TypeReference<HashMap<String, Object>>() {};
        typeRefJobDetails = new TypeReference<JobDetails>() {};
    }
    public Boolean addJob(String nodeName, JobDetails jobDetails) {
        databaseService.saveData(nodeName, jobDetails);
        return true;
    }
    public Boolean updateJob(String nodeName, JobDetails jobDetails) {
        databaseService.updateData(nodeName, jobDetails);
        return true;
    }

    public Boolean deleteJobForDate(String nodeName, String uuid) {
        String finalNodePath = nodeName + "/" + uuid;
        databaseService.deleteData(finalNodePath);
        return true;
    }

    public List<JobDetails> fetchJobsBy(String nodeName, Category type, String typeValue) throws Exception {
        Map<FilterParam, Object> qMap = prepareParamMapFrom(type, typeValue);
        return getResultList(nodeName, qMap);
    }

    private List<JobDetails> getResultList(String nodeName, Map<FilterParam, Object> qMap) {
        List<JobDetails> jobDetailsList = new ArrayList<>();
        Object payload = databaseService.getDataWithFilters(nodeName, FilterByType.CHILD, qMap);
        HashMap<String, Object> jobDetailsMap = new ObjectMapper().convertValue(payload, typeRef);
        for(String key: jobDetailsMap.keySet()) {
            JobDetails jobDetails = new ObjectMapper().convertValue(jobDetailsMap.get(key), typeRefJobDetails);
            jobDetailsList.add(jobDetails);
        }
        return jobDetailsList;
    }

    private Map<FilterParam, Object> prepareParamMapFrom(Category category, String categoryValue) throws Exception {
        Map<FilterParam, Object> qMap = new HashMap<>();

        String categoryVal = category.getActualValue();

        qMap.put(FilterParam.CHILD, categoryVal);
        qMap.put(FilterParam.START_AT, categoryValue);

//        switch (category) {
//            case DATE: qMap.put(FilterParam.CHILD, DBConstants.PUBLISHED_ON); break;
//            case STATE: qMap.put(FilterParam.CHILD, DBConstants.STATE);
//                        qMap.put(FilterParam.EQUAL_TO, categoryValue);
//                        break;
//            case CATEGORY: qMap.put(FilterParam.CHILD, DBConstants.CATEGORY);
//                           qMap.put(FilterParam.EQUAL_TO, categoryValue);
//                           break;
//            case CITY: qMap.put(FilterParam.CHILD, DBConstants.CITY); break;
//            case DESIGNATION : qMap.put(FilterParam.CHILD, DBConstants.DESIGNATION); break;
//            case EDUCATION: qMap.put(FilterParam.CHILD, DBConstants.EDUCATION); break;
//            case TOPCOMPANIES: qMap.put(FilterParam.CHILD, DBConstants.TOPCOMPANIES); break;
//            case ENGINEERINGSTREAMS: qMap.put(FilterParam.CHILD, DBConstants.ENGINEERINGSTREAMS); break;
//            case TAG_FRESHERS: qMap.put(FilterParam.CHILD, DBConstants.TAG_FRESHERS); break;
//            case TAG_GOVT_JOBS: qMap.put(FilterParam.CHILD, DBConstants.TAG_GOVT_JOBS); break;
//            default: throw new Exception("Not supported yet");
//        }
        return qMap;
    }
}
