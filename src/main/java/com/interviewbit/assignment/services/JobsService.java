package com.interviewbit.assignment.services;

import com.interviewbit.assignment.enitities.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JobsService {
    private List<StudentVO> studentsList = new ArrayList<>(Arrays.asList(
            new StudentVO(123,"Students","Student1","s@gmail.com","9999999"),
            new StudentVO(124,"Student","Students1","s2@gmail.com","8888888")
    ));

    private  List<JobsVO> jobsList = new ArrayList<>(Arrays.asList(
            new JobsVO(2345,"ABC", "Hyderabad", "3-5","java,sql", "mid senior", "perm","1111111"),
             new JobsVO(8524,"xyz", "Banglore", "3-5","java,sql", "mid senior", "cont","5555555")
    ));

    public List<StudentVO> getAllStudents(){
        return studentsList;
    }

    public List<JobsVO> getAllJobs(){
        return jobsList;
    }

    public void addStudent(StudentVO studentVO){
        studentsList.add(studentVO);
    }
    public void addJob(JobsVO jobsVO){
        jobsList.add(jobsVO);
    }

    public boolean getStudent(Long id){
        boolean flag = false;
        for(StudentVO studentVO : studentsList){
            if(studentVO.getStudentID() ==id){
                flag = true;
                break;
            }
        }
        return flag;
    }
    public JobsResponse getJobs(StudentRequest studentRequest){
        filter f = studentRequest.getFilter();
        JobsResponse jobsResponse = new JobsResponse();

        List<JobsVO> jobslist = new ArrayList<>();

   if(f.getLocation().equals("any") && f.getCompanyName().equals("any")){
       for(JobsVO jobs: jobsList){
           if(jobs.getExperience().equals(f.getExperience())&&jobs.getSkill().equals(f.getSkill())){
            jobslist.add(jobs);
           }

       }
   }
   else if(f.getLocation().equals("any") && !f.getCompanyName().equals("any")){
       for(JobsVO jobs: jobsList){
           if(jobs.getExperience().equals(f.getExperience())&&jobs.getSkill().equals(f.getSkill())&&
           jobs.getCompany().equals(f.getCompanyName())){
               jobslist.add(jobs);
           }

       }
   }
   else {
       for(JobsVO jobs: jobsList){
           if(jobs.getExperience().equals(f.getExperience())&&jobs.getSkill().equals(f.getSkill())&&
                   jobs.getCompany().equals(f.getCompanyName()) && jobs.getLocation().equals(f.getLocation())){
               jobslist.add(jobs);
           }

       }
   }
    jobsResponse.setJobslist(jobslist);
   return jobsResponse;
    }


}
