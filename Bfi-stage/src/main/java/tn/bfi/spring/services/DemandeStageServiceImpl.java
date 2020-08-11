package tn.bfi.spring.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.bfi.spring.entities.StageDemande;
import tn.bfi.spring.entities.Stagiaire;
import tn.bfi.spring.entities.TypeSatge;
import tn.bfi.spring.repository.StageDemandeRepository;

@Service
public class DemandeStageServiceImpl implements IDemandeStageService {

	private static final Logger L= LogManager.getLogger(DemandeStageServiceImpl.class);
	@Autowired
	StageDemandeRepository demandesatgeRepository;
	@Autowired
	IStagiaireService test;
	@Autowired
	StagiaireServiceImpl serstage;
	Stagiaire x;
	
	
	public String cvname;
	
	
	@Override
	public List<StageDemande> getDemandeByNameTypeJPQL(String name, TypeSatge type) {
		return demandesatgeRepository.getDemandeByNameTypeJPQL(name, type);
	}

	@Override
	public void uploadFile(MultipartFile file) {
		try {
			StageDemande x = new StageDemande();
			cvname = file.getOriginalFilename();
			file.transferTo(new File ("C:\\pdf\\"+file.getOriginalFilename()));
			System.out.print(cvname+"uplodefile");
		
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public StageDemande ajouterDemande(StageDemande demande) {
		demande.setCv(cvname);
	System.out.print(cvname+"ajouteeeeeeeeer");
		return demandesatgeRepository.save(demande);
		}
	

	@Override
	public void supprimerDemande(long id) {
		demandesatgeRepository.deleteById(id);
		
	}

	@Override
	public List<StageDemande> retrieveAllDemande() {
		return (List<StageDemande>)demandesatgeRepository.findAll();
	}

	@Override
	public StageDemande affichage(long id) {
		StageDemande u= demandesatgeRepository.findById(id).orElse(null);;
		L.info("retrive Admin by id ++++:"+u);
		return u;
	};
	

	@Override
	public StageDemande update(StageDemande demande) {
		return demandesatgeRepository.save(demande);
	}

	@Override
	public Long nbrdemande() {		
		return demandesatgeRepository.count();
	}



	

}
