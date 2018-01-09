CREATE OR REPLACE TYPE ObEtudiantTy AS OBJECT(
	num INT,  	 
	nom VARCHAR2 (20),    
	prenom VARCHAR2 (20),   
	debutAnnee DATE,	  
	finAnnee DATE,      
	stagiaire VARCHAR2(20)
);


CREATE OR REPLACE TYPE ObAdresseTy AS OBJECT(
	numRue INT,
	nomRue VARCHAR2 (20),
	ville VARCHAR2 (20),
	departement VARCHAR2 (20)
);


CREATE OR REPLACE TYPE ObEntrepriseTy AS OBJECT(
	num INT,
	nom VARCHAR2 (20),
	tel INT,
	adresse ObAdresseTy
);


CREATE OR REPLACE  TYPE ObStageTy  AS OBJECT(    
	num INT,
	dateDebut DATE,
	dateFin DATE,
	etudiant REF ObEtudiantTy,
	entreprise REF ObEntrepriseTy
);


CREATE OR REPLACE TYPE ObStatistiqueTy AS OBJECT(
	stageAvec INT,
	stageSans INT,
	stageEntreprise INT,
	nonStageAnneeX INT,
	stageGeographique INT,
	stageMoyenne FLOAT,
	stageTotal INT
);





CREATE TABLE ETUDIANT of ObEtudiantTy(
	num PRIMARY KEY
);


CREATE TABLE ENTREPRISE of ObEntrepriseTy(
	num PRIMARY KEY
);


CREATE TABLE STAGE of ObStageTy(
	num  PRIMARY KEY
);


CREATE TABLE STATISTIQUE of ObStatistiqueTy;