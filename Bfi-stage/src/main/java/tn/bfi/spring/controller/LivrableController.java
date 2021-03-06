package tn.bfi.spring.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.bfi.spring.entities.Livrable;
import tn.bfi.spring.entities.StageDemande;
import tn.bfi.spring.repository.LivrableRepository;
import tn.bfi.spring.services.IDemandeStageService;
import tn.bfi.spring.services.ILivrableService;
@CrossOrigin(origins = "*")
@RestController
public class LivrableController {
	@Autowired
	ILivrableService Ilivrable;
	@Autowired
	LivrableRepository livrab;
	

	
	// URL : http://localhost:8081/SpringMVC/servlet/getSalaireByEmployeIdJPQL/2 ad from client

	@GetMapping(value = "getLivrableByDemandeJPQL/{idemp}")
	@ResponseBody
	public List<Livrable> getLivrableJPQL(@PathVariable("idemp") Long demandeId) {
		
		return Ilivrable.getLivrableByDemandeJPQL(demandeId);
	}
	
	@PostMapping("/add-Livrablefile")
	public void uploadFile(@RequestParam("file")MultipartFile file){
		Ilivrable.uploadlivrable(file);
		
	}
	
	// Ajouter User : http://localhost:8081/SpringMVC/servlet/add-StageDemande
			@PostMapping("/add-Livrable")
			@ResponseBody
			public Livrable addLivrable(@RequestBody Livrable u) {
			Livrable liv = Ilivrable.ajouterLivrable(u);
			return liv;
			}
			
			@GetMapping(value = "/tesst/{idemp}")
			@ResponseBody
			public StageDemande affecter(@PathVariable("idemp") Long demandeId) {
				return Ilivrable.getDemandeById(demandeId);
			}
			
			  @GetMapping(path="/photoProduct/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
			    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
			        Livrable p=livrab.findById(id).get();
			        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/bfi/"+p.getFile()));
			    }
		


}
