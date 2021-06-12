package com.naukrimilega.service.jobs;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.naukrimilega.dao.JobsDAO;
import com.naukrimilega.models.JobDetails;
import com.naukrimilega.models.query.Category;
import com.naukrimilega.models.query.QueryData;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class JobsService {

    private final JobsDAO jobsDAO;
    @Inject
    public JobsService(JobsDAO jobsDAO) {

        this.jobsDAO = jobsDAO;
    }

    /**
     * Create a job given jobtype category and the data to be inserted
     * @param jobDetails
     * @return Boolean
     */
    public Boolean addJobDetails(JobDetails jobDetails) {
        String nodeName = deriveJobsNodeName();
        jobDetails.setJobId(generateRandomStringForId());
        return jobsDAO.addJob(nodeName, jobDetails);
    }

    /**
     * Retrieve a job given job type
     * @return List(JobDetails)
     */

    public List<JobDetails> fetchJobsResponse(Category category, String typeValue) throws Exception {
        String nodeName = deriveJobsNodeName();

        if(category==Category.CATEGORY || category==Category.CITY || category==Category.DATE || category==Category.DESIGNATION ||
            category==Category.EDUCATION || category==Category.ENGINEERINGSTREAMS || category==Category.STATE ||
            category==Category.TAG_FRESHERS || category==Category.TAG_GOVT_JOBS || category==Category.TOPCOMPANIES){

            return jobsDAO.fetchJobsBy(nodeName, category, typeValue);
        }else
            throw new RuntimeException("Not supported fetchJobsResponse()");

//        switch (category) {
//            case DATE: return jobsDAO.fetchJobsBy(nodeName, category, typeValue);
//            case STATE: return jobsDAO.fetchJobsBy(nodeName, category, typeValue);
//            case TAG_FRESHERS: return jobsDAO.fetchJobsBy(nodeName, category, typeValue);
//            case TAG_GOVT_JOBS: return jobsDAO.fetchJobsBy(nodeName, category, typeValue);
//            default: throw new RuntimeException("Not supported fetchJobsResponse()");
//        }
    }

    /**
     * Update a job, doesn't create extra random valued node
     * @param jobDetails
     * @return Boolean
     */

    public Boolean updateJobDetails(JobDetails jobDetails) {
        String nodeName = deriveJobsNodeName();
        jobDetails.setJobId(generateRandomStringForId());
        return jobsDAO.updateJob(nodeName, jobDetails);
    }

    /**
     * Delete jobs for a given date and jobType
     * @param queryData
     * @return Boolean
     */

    public Boolean deleteJobs(QueryData queryData) throws Exception {
        //        String nodeName = deriveNodeName();
        //        if(queryData.getDeleteJobsOfDate() == null)
        //            throw new Exception("Date missing..");
        //        else
        //            return jobsDAO.deleteJobForDate(nodeName, queryData.getUuid());
        return false;
    }

    private String deriveJobsNodeName() {
        return "jobs";
    }

    private String generateRandomStringForId() {
        return UUID.randomUUID().toString() + LocalDateTime.now().toString();
    }
}
