DROP DATABASE IF EXISTS AGE_DOR;

CREATE DATABASE IF NOT EXISTS AGE_DOR;
USE AGE_DOR;
# -----------------------------------------------------------------------------
#       TABLE : CATEGORIES
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CATEGORIES
 (
   ID INTEGER(2) NOT NULL  ,
   NOM VARCHAR(128) NULL  ,
   MONTANT DECIMAL(10,2) NULL  
   , PRIMARY KEY (ID) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : DEPENSES
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS DEPENSES
 (
   ID BIGINT(4) NOT NULL  ,
   ID_CAT INTEGER(2) NOT NULL  ,
   DATE_DEPENSE DATETIME NULL  ,
   NOM VARCHAR(128) NULL  ,
   MONTANT DECIMAL(10,2) NULL  
   , PRIMARY KEY (ID) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE DEPENSES
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_DEPENSES_CATEGORIES
     ON DEPENSES (ID_CAT ASC);


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE DEPENSES 
  ADD FOREIGN KEY FK_DEPENSES_CATEGORIES (ID_CAT)
      REFERENCES CATEGORIES (ID) ;

