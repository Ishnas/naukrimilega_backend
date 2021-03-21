package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.model.FilterByType;
import com.naukrimilega.firebaseutil.model.FilterParam;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.JobDetails;
import com.naukrimilega.models.query.Category;
import com.naukrimilega.utils.DBConstants;

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

    public List<JobDetails> fetchJobsBy(String nodeName, Category type, Object typeValue) throws Exception {
        Map<FilterParam, Object> qMap = prepareParamMapFrom(type);
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

    private Map<FilterParam, Object> prepareParamMapFrom(Category category) throws Exception {
        Map<FilterParam, Object> qMap = new HashMap<>();
        switch (category) {
            case DATE: qMap.put(FilterParam.CHILD, DBConstants.FIELD_PUBLISHED_ON); break;
            case STATE: qMap.put(FilterParam.CHILD, DBConstants.FIELD_STATE); break;
            case TAG_FRESHERS: qMap.put(FilterParam.CHILD, DBConstants.FIELD_TAG_FRESHERS); break;
            case TAG_GOVT_JOBS: qMap.put(FilterParam.CHILD, DBConstants.FIELD_TAG_GOVT_JOBS); break;
            default: throw new Exception("Not supported yet");
        }
        return qMap;
    }

}
