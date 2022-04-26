package com.interviewbit.assignment.controller;

import com.interviewbit.assignment.enitities.*;
import com.interviewbit.assignment.services.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.rmi.CORBA.StubDelegate;
import java.lang.reflect.Method;
import java.util.List;

@RestController
public class JobsController {
    @Autowired
    private JobsService jobsService;

    @RequestMapping("/students")
    public List<StudentVO> all_students(){
        return jobsService.getAllStudents();

    }

    @RequestMapping("/jobs")
    public List<JobsVO> all_jobs(){
        return jobsService.getAllJobs();

    }
    @RequestMapping(method = RequestMethod.POST,value = "/student")
    public ResponseEntity<HttpStatus> addStudent(@RequestBody StudentVO studentVO){
         try{
             jobsService.addStudent(studentVO);
             return new ResponseEntity<>(HttpStatus.CREATED);
         }
         catch (Exception e){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }

    }
    @RequestMapping(method = RequestMethod.POST,value = "/job")
    public ResponseEntity<HttpStatus> addJob(@RequestBody JobsVO jobsVO){
        try{
            jobsService.addJob(jobsVO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/getJobs")
    public ResponseEntity<JobsResponse> returnJobs(@RequestBody StudentRequest studentRequest){
        if(!jobsService.getStudent(studentRequest.getStudentId())){
            StudentError error = new StudentError(401,"No Student found");
            return new ResponseEntity(error,HttpStatus.EXPECTATION_FAILED);
        }
        else if(jobsService.getJobs(studentRequest).getJobslist().size() ==0){
            StudentError error = new StudentError(404,"No Jobs found");
            return new ResponseEntity(error,HttpStatus.EXPECTATION_FAILED);
        }
        else {
            return new ResponseEntity(jobsService.getJobs(studentRequest),HttpStatus.OK);
        }
    }

}
