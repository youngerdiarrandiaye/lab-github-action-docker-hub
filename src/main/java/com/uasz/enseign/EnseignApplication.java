package com.uasz.enseign;

import com.uasz.enseign.dto.Maquette.*;
import com.uasz.enseign.service.Maquette.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class EnseignApplication implements CommandLineRunner {

	private final UEService ueService;
	private final SemestreService semestreService;
	private final ClasseService classeService;
	private final FiliereService filiereService;
	private final GroupeService groupeService;

	public EnseignApplication(UEService ueService, SemestreService semestreService, ClasseService classeService, FiliereService filiereService, GroupeService groupeService) {
		this.ueService = ueService;
		this.semestreService = semestreService;
		this.classeService = classeService;
		this.filiereService = filiereService;
		this.groupeService = groupeService;
	}

	public static void main(String[] args) {
		// Lancement de l'application Spring Boot
		SpringApplication.run(EnseignApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) {
		try {
			// Appel de la méthode pour ajouter des exemples d'UE
			ajouterExemplesUE();
			ajouterClasses();
			ajouterGroupe();
			ajouterFilieres();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Méthode pour ajouter des exemples d'UE via le service
	private void ajouterExemplesUE() {
		// Création d'objets UE
		UEDTO ue1 = new UEDTO();
		ue1.setLibelle("Reseau et Telecoms");
		ue1.setCode("INF351");
		ue1.setCredit(9);
		ue1.setCoefficient(4);
		ue1.setDescription("Reseau");
		ue1.setCreatedAt(new Date());

		UEDTO ue3 = new UEDTO();
		ue3.setLibelle("SDD");
		ue3.setCode("SDD351");
		ue3.setCredit(9);
		ue3.setCoefficient(4);
		ue3.setDescription("COMPTA");
		ue3.setCreatedAt(new Date());

		UEDTO ue4 = new UEDTO();
		ue4.setLibelle("IAGE");
		ue4.setCode("IAGE351");
		ue4.setCredit(9);
		ue4.setCoefficient(4);
		ue4.setDescription("FINANCE");
		ue4.setCreatedAt(new Date());

		UEDTO ue2 = new UEDTO();
		ue2.setLibelle("Genie Logiciel");
		ue2.setCode("INF352");
		ue2.setCredit(5);
		ue2.setCoefficient(3);
		ue2.setDescription("Genie");
		ue2.setCreatedAt(new Date());
		// Enregistrement dans la base de données via le service
		ueService.saveUE(ue1);ueService.saveUE(ue2);ueService.saveUE(ue3);ueService.saveUE(ue4);

	}
	// Dans votre classe EnseignApplication ou tout autre endroit approprié
// Création d'une liste pour stocker les groupes
	private void ajouterGroupe(){
	List<GroupeDTO> groupes = new ArrayList<>();
	// Création des instances de GroupeDTO
	GroupeDTO groupe1 = new GroupeDTO();
		groupe1.setLibelle("Groupe 1");
		groupe1.setEffectif(30);
		groupe1.setDescription("Groupe pour la classe A");
	GroupeDTO groupe2 = new GroupeDTO();
		groupe2.setLibelle("Groupe 2");
		groupe2.setEffectif(25);
		groupe2.setClasse(classeService.getClasseById(Long.valueOf(1)));
		groupe2.setDescription("Groupe pour la classe B");
	GroupeDTO groupe3 = new GroupeDTO();
		groupe3.setLibelle("Groupe 3");
		groupe3.setEffectif(20);
		groupe3.setClasse(classeService.getClasseById(Long.valueOf(3)));
		groupe3.setDescription("Groupe pour la classe C");
	GroupeDTO groupe4 = new GroupeDTO();
		groupe4.setLibelle("Groupe 4");
		groupe4.setEffectif(35);
		groupe4.setClasse(classeService.getClasseById(Long.valueOf(2)));
		groupe4.setDescription("Groupe pour la classe D");
		groupes.add(groupe1);
		groupes.add(groupe2);
		groupes.add(groupe3);
		groupes.add(groupe4);
	// Enregistrement des groupes dans la base de données via le service approprié
		for (GroupeDTO groupe : groupes) {
		groupeService.createGroupe(groupe);
	}}

	// Dans la classe EnseignApplication
	public void ajouterClasses() {

		// Création d'une liste pour stocker les semestres
		List<SemestreDTO> semestres = new ArrayList<>();
		// Création des instances de SemestreDTO
		SemestreDTO semestre1 = new SemestreDTO();
		semestre1.setLibelle("Semestre 1");
		semestre1.setDescription("Premier semestre de l'année académique");

		SemestreDTO semestre2 = new SemestreDTO();
		semestre2.setLibelle("Semestre 2");
		semestre2.setDescription("Deuxième semestre de l'année académique");

		SemestreDTO semestre3 = new SemestreDTO();
		semestre3.setLibelle("Semestre 3");
		semestre3.setDescription("Troisième semestre de l'année académique");

		// Ajout des semestres à la liste
		semestres.add(semestre1);
		semestres.add(semestre2);
		semestres.add(semestre3);

		// Enregistrement des semestres dans la base de données via le service approprié
		for (SemestreDTO semestre : semestres) {
			semestreService.createSemestre(semestre);
		}

		// Création d'une liste pour stocker les classes
		List<ClasseDTO> classes = new ArrayList<>();
		// Création des instances de ClasseDTO
		ClasseDTO classe1 = new ClasseDTO();
		classe1.setLibelle("Informatique");
		classe1.setEffectif(30);
		classe1.setNbreGroupe(5);
		classe1.setSemestre(semestreService.getAllSemestres().get(1));
		classe1.setDescription("Classe d'informatique");

		ClasseDTO classe2 = new ClasseDTO();
		classe2.setLibelle("Mathématiques");
		classe2.setEffectif(25);
		classe2.setNbreGroupe(4);
		classe2.setSemestre(semestreService.getAllSemestres().get(2));
		classe2.setDescription("Classe de mathématiques");

		ClasseDTO classe3 = new ClasseDTO();
		classe3.setLibelle("Physique");
		classe3.setEffectif(20);
		classe3.setNbreGroupe(3);
		classe3.setSemestre(semestreService.getAllSemestres().get(2));
		classe3.setDescription("Classe de physique");

		ClasseDTO classe4 = new ClasseDTO();
		classe4.setLibelle("Biologie");
		classe4.setEffectif(22);
		classe4.setNbreGroupe(4);
		classe4.setSemestre(semestreService.getAllSemestres().get(1));
		classe4.setDescription("Classe de biologie");



		// Ajout des classes à la liste
		classes.add(classe1);
		classes.add(classe2);
		classes.add(classe3);
		classes.add(classe4);
		// Enregistrement des classes dans la base de données via le service
		for (ClasseDTO classe : classes) {
			classeService.createClasse(classe);
		}
	}

	// Dans votre classe EnseignApplication ou tout autre endroit approprié
	public void ajouterFilieres() {
		// Création d'une liste pour stocker les filières
		List<FiliereDTO> filieres = new ArrayList<>();
		// Création des instances de FiliereDTO
		FiliereDTO filiere1 = new FiliereDTO();
		filiere1.setNom("Informatique");
		FiliereDTO filiere2 = new FiliereDTO();
		filiere2.setNom("Génie Civil");
		FiliereDTO filiere3 = new FiliereDTO();
		filiere3.setNom("Génie Mécanique");
		FiliereDTO filiere4 = new FiliereDTO();
		filiere4.setNom("Génie Electrique");
		// Ajout des filières à la liste
		filieres.add(filiere1);
		filieres.add(filiere2);
		filieres.add(filiere3);
		filieres.add(filiere4);
		// Enregistrement des filières dans la base de données via le service approprié
		for (FiliereDTO filiere : filieres) {
			filiereService.createFiliere(filiere);
		}
	}




}
