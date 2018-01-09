/* Question 1 : Récupérer le nombre d'étudiants avec stage cette année */
CREATE OR REPLACE FUNCTION stageAvec 
  RETURN INTEGER IS nbStage INTEGER;

  BEGIN 
      SELECT COUNT (*) INTO nbStage
      FROM Etudiant e
      WHERE e.stagiaire = 'oui' 		
      AND TO_CHAR(e.finAnnee, 'YYYY') = TO_CHAR(sysdate, 'YYYY');	

  RETURN nbStage;
END stageAvec;




/* Question 2 : Récupérer le nombre d'étudiants sans stage cette année */
CREATE OR REPLACE FUNCTION stageSans 
  RETURN INTEGER IS nbStage INTEGER;

  BEGIN
      SELECT COUNT(*) INTO nbStage
      FROM Etudiant e
      WHERE e.stagiaire = 'non'
      AND TO_CHAR(e.finAnnee, 'YYYY') = TO_CHAR(sysdate, 'YYYY');

  RETURN nbStage;
END stageSans;



    
/* Question 3 : Récupérer le nombre d'étudiants sans stage à une certaine date pour une année choisie par l'utilisateur */    
CREATE OR REPLACE FUNCTION stageSansAnnee(annee INT) 
  RETURN INTEGER IS nbStage INTEGER;

  BEGIN
      SELECT COUNT(*) INTO nbStage
      FROM Etudiant e
      WHERE e.stagiaire = 'non'
      AND TO_CHAR(e.finannee, 'YYYY') = annee;

  RETURN nbStage;
END stageSansAnnee;





/* Question 6 : Le nombre de stages par zone géographique choisi par l'utilisateur (departement, ville) */
CREATE OR REPLACE FUNCTION stageGeographiqueChoisi(departement VARCHAR2, ville VARCHAR2 ) 
  RETURN INTEGER IS nbStage INTEGER;
  
  BEGIN 
    SELECT Count(*) INTO nbStage
    FROM Stage S
    WHERE DEREF(S.Entreprise).adresse.departement = departement
    AND DEREF(S.Entreprise).adresse.ville = ville;

  RETURN nbStage;
END stageGeographiqueChoisi;




/* Question 7 : Le nombre de stages pour toutes les zones géographiques */
CREATE OR REPLACE FUNCTION stageGeographique 
  RETURN INTEGER IS nbStage INTEGER;

  BEGIN 
    SELECT COUNT(*) INTO nbStage 
    FROM Stage s
    GROUP BY DEREF(s.entreprise).adresse.departement;
  RETURN nbStage;

END stageGeographique;