package com.nakul.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nakul.demo.DAO.AlienRepo;
import com.nakul.demo.model.Alien;

@RestController
public class AlienController {
	
	@Autowired
    AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping("/addAlien")
	public String saveAlien(Alien alien) {
		
		repo.save(alien);
		return "home.jsp";
	}
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam Integer aid) {
		
		ModelAndView mv=new ModelAndView("show.jsp");
		Alien alien=repo.findById(aid).orElse(new Alien());
		mv.addObject(alien);
		System.out.println(repo.findByTech("fsfds"));
		System.out.println("Greater than="+repo.findByAidGreaterThan(23));
		System.out.println(repo.findByTechSorted("java"));
		return mv;
	}
	
//	//print data in json format
//	@RequestMapping("/aliens2")
//	@ResponseBody
//	public List<Alien> getAliens2() {
//		
//		return repo.findAll();
//	}
//	
//	//print data by tostring method
//	@RequestMapping("/aliens")
//	@ResponseBody
//	public String getAliens() {
//		
//		return repo.findAll().toString();
//	}
//	
//	//tostring method data by aid
//	@RequestMapping("/alien/{aid}")
//	@ResponseBody
//	public String getAlienById(@PathVariable int aid) {
//		
//		return repo.findById(aid).toString();
//	}
//	
//	//json formatted data by aid
//	@RequestMapping("/alien2/{aid}")
//	@ResponseBody
//	public Optional<Alien> getAlienById2(@PathVariable int aid) {
//		
//		return repo.findById(aid);
//	}
	
	@PostMapping(path = "/alien",consumes = "application/json")
	public Alien saveAlienR(@RequestBody Alien alien) {
		
		repo.save(alien);
		return alien;
	}
	
	@GetMapping("/aliens")
	public List<Alien> getAlienR() {
	
		return repo.findAll();
	}
	
	@GetMapping("/alien/{aid}")
	public Optional<Alien> getAlienByIdR(@PathVariable int aid) {
		
		return repo.findById(aid);
	}
	
	@DeleteMapping("/alien/{aid}")
	public String deleteAlienR(@PathVariable int aid) {
		
		Alien a=repo.getOne(aid);
		repo.delete(a);
		return "deleted";
	}
	
	@PutMapping("/alien")
	public Alien updateorSaveAlienR(@RequestBody Alien alien) {
		
		repo.save(alien);
		return alien;
	}
}
