package ca.sheridancollege.mislim.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.sheridancollege.mislim.beans.Breed;
import ca.sheridancollege.mislim.beans.Dog;
import ca.sheridancollege.mislim.repositories.DogShowRepository;

@Controller
public class DogShowController {

	@Autowired
	@Lazy
	DogShowRepository dogShowRepo;

	@GetMapping("/")
	public String goRoot() {

		return "root";
	}

	@GetMapping("/userPage")
	public String goHome() {

		return "userPage";
	}

	@GetMapping(value = { "/addDog" })
	public ModelAndView goToAddDog() {
		Dog dog = new Dog();
		dog.setGender("Male");// default selection for radio button
		dog.setClassSpecialty("Class");// default selection
		ModelAndView modelAndView = new ModelAndView("addDog", "dog", dog);
		modelAndView.addObject("breeds", dogShowRepo.getBreeds());
		return modelAndView;
	}

	@PostMapping("/addDog")
	public String addNewDog(@ModelAttribute Dog dog, Authentication authentication) {
		ArrayList<String> roles = new ArrayList<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		System.out.println(roles);
		if (roles.contains("ROLE_OWNER")) {
			dog.setOwnerName(authentication.getName());
		}
		dogShowRepo.addDog(dog);
		return "redirect:/addDog";
	}

	@GetMapping("/addBreed")
	public String goToAddBreed(Model model) {
		model.addAttribute("breed", new Breed());
		return "newBreed";
	}

	@PostMapping("/addBreed")
	public String addNewBreed(@ModelAttribute Breed breed) {
		dogShowRepo.addBreed(breed);
		return "redirect:/addBreed";
	}

	@GetMapping("/viewDogs")
	public ModelAndView viewContact(Authentication authentication, Model model) {
		ArrayList<String> roles = new ArrayList<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		ModelAndView mav;
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		ArrayList<String> list = new ArrayList<String>();
		if (roles.contains("ROLE_ADMIN")) {
			dogs.addAll(dogShowRepo.getDogs());
			list.addAll(dogShowRepo.getDogBreedName());
		}
		if (roles.contains("ROLE_OWNER")) {
			dogs.addAll(dogShowRepo.getDogByOwner((String) authentication.getName()));
			list.addAll(dogShowRepo.getBreedByOwner((String) authentication.getName()));
		}
		mav = new ModelAndView("viewDogs", "dogs", dogs);
//		ArrayList<String> list = dogShowRepo.getDogBreedName();
		String[] array = list.toArray(new String[list.size()]);
		mav.addObject("breeds", array);

		return mav;
	}

	@GetMapping("/editDog/{id}")
	public String editDog(@PathVariable int id, Model model) {
		Dog dog = dogShowRepo.getDogById(id);
		model.addAttribute("dog", dog);
		model.addAttribute("breeds", dogShowRepo.getBreeds());
		return "editDog";
	}

	@PostMapping("/editDog")
	public String modifyDog(@ModelAttribute Dog dog) {
		dogShowRepo.editDog(dog);
		return "redirect:/viewDogs";
	}

	@GetMapping("/delete/{id}")
	public String deletePage(@PathVariable int id, Model model) {
		dogShowRepo.deleteById(id);
		return "redirect:/viewDogs";
	}

	@GetMapping(value = { "/showList" })
	public ModelAndView getShowList(Model model) {
		ModelAndView modelAndView = new ModelAndView("showList", "list", dogShowRepo.getShowList());
		// System.out.println(dogShowRepo.getShowList());

		return modelAndView;
	}
}
