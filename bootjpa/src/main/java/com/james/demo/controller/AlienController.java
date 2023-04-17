package com.james.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.james.demo.dao.AlienRepo;
import com.james.demo.model.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	//normal request mapping
	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	
	//normal request mapping
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		return "home.jsp";

	}
	//normal request mapping
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
		
		System.out.println(repo.findByAidGreaterThan(10));
		System.out.println(repo.findByTechSorted("java"));
		System.out.println(repo.findByTechSorted("c#"));

		mv.addObject(alien);
		return mv;

	}
	//REST mapping GET METHOD
	@GetMapping("/aliens")
	public List<Alien> getAliens() {
		return repo.findAll();
	}
	//REST mapping GET METHOD
	@RequestMapping("/alien/{aid}")
	public Optional<Alien> getAlienREST(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
	//REST mapping POST METHOD
	@PostMapping("/alien")
	public Alien addAlienREST(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	//REST mapping DELETE METHOD
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable("aid") int aid) {
		Alien a = repo.getReferenceById(aid);
		repo.delete(a);
		return "deleted";
	}
	//REST mapping PUT METHOD
	@PutMapping("/alien")
	public Alien updateAlienREST(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
}
