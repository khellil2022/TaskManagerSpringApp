package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.model.TaskRepository;



@RestController
public class TaskController {
	@Autowired
	TaskRepository taskrep;	
	@GetMapping("/tasks")
	public List<Task> getTasks()
	{
		return taskrep.findAll();
	}
	
//	@GetMapping("/tasks/{assignedto}")
//	public Task getTasks(@PathVariable String assignedto)
//	{
//		Optional<Task> taskobj=taskrep.findByAssignedto(assignedto);
//		Task retreiveobj=null;
//		if(taskobj.isPresent())
//		{
//			 retreiveobj=taskobj.get();
//		}
//		return retreiveobj;	
//	} 
	@GetMapping("/tasks/assignedto")
	public ResponseEntity<List<Task>> getTasksByAssignedto (@RequestParam String assignedto){
		return new ResponseEntity<List<Task>> (taskrep.findByAssignedto(assignedto), HttpStatus.OK);
	}
	@GetMapping("/tasks/tasktitle")
	public ResponseEntity<List<Task>> getTasksByTasktitle (@RequestParam String tasktitle){
		return new ResponseEntity<List<Task>> (taskrep.findByTasktitle(tasktitle), HttpStatus.OK);
	}
	
	@PostMapping("/addTask")
	public Task addTask(@RequestBody Task tskobj)
	{
		Task t=taskrep.save(tskobj);
		return t;
	}

	@PutMapping("/updateTask")
	public Task updateTask(@RequestBody Task obj)
	{
		int taskid=obj.getTaskid();
		Optional<Task> updatetask=taskrep.findById(taskid);
		Task updatedTask=null;
		if(updatetask.isPresent())
		{
			updatedTask=updatetask.get();
			updatedTask.setTaskid(obj.getTaskid());
			updatedTask.setTasktitle(obj.getTasktitle());
			updatedTask.setDuration(obj.getDuration());
			updatedTask.setAssignedto(obj.getAssignedto());
			taskrep.save(updatedTask);
		}
		return updatedTask;
	}
	
	@DeleteMapping("/delete/{taskid}")
	public void  deleteTask(@PathVariable int taskid)
	{
	Optional<Task> deletetask=taskrep.findById(taskid);
	
		if(deletetask.isPresent())
		{ 
			deletetask=Optional.ofNullable(deletetask.get());
		taskrep.deleteById(taskid);
		}
	}
}
