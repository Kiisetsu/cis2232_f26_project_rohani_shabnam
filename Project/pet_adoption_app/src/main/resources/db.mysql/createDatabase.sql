DROP DATABASE IF EXISTS cis2232_pet_adoption_app;
CREATE DATABASE cis2232_pet_adoption_app;
USE cis2232_pet_adoption_app;

-- ------------------------------------------------------------
-- Table to hold pet adoption information
-- ------------------------------------------------------------

CREATE TABLE PetAdoption
(
    id              INT(5),
    name            VARCHAR(50) NOT NULL COMMENT 'Name of the animal',
    sex             VARCHAR(10) NOT NULL COMMENT 'Male or Female',
    species         VARCHAR(10) NOT NULL COMMENT 'Cat or Dog',
    colour          VARCHAR(50) NOT NULL COMMENT 'Primary coat colour or pattern',
    coat            VARCHAR(10) NOT NULL COMMENT 'Short, Medium, Long, or Bald',
    age             DECIMAL(4,2) NOT NULL COMMENT 'Animal age in years',
    adoptionFee     DECIMAL(8,2) DEFAULT NULL COMMENT 'Calculated adoption fee'
) COMMENT 'This table holds pet adoption details and calculated fees';

ALTER TABLE PetAdoption
    ADD PRIMARY KEY (id);

ALTER TABLE PetAdoption
    MODIFY id INT(4) NOT NULL AUTO_INCREMENT
        COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;


-- ------------------------------------------------------------
-- CodeType table
-- ------------------------------------------------------------

CREATE TABLE CodeType
(
    codeTypeId          INT(3),
    englishDescription  VARCHAR(100) NOT NULL,
    frenchDescription   VARCHAR(100) DEFAULT NULL,
    createdDateTime     DATETIME DEFAULT NULL,
    createdUserId       VARCHAR(20) DEFAULT NULL,
    updatedDateTime     DATETIME DEFAULT NULL,
    updatedUserId       VARCHAR(20) DEFAULT NULL
) COMMENT 'This table holds code types available for the application';

ALTER TABLE CodeType
    ADD PRIMARY KEY (codeTypeId);


INSERT INTO CodeType
(codeTypeId, englishDescription, frenchDescription,
 createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES
    (1, 'Pet Species', 'Espèces animales', NOW(), 'admin', NOW(), 'admin'),
    (2, 'Pet Coat Length', 'Longueur du pelage', NOW(), 'admin', NOW(), 'admin'),
    (3, 'Pet Sex', 'Sexe de l’animal', NOW(), 'admin', NOW(), 'admin');


-- ------------------------------------------------------------
-- CodeValue table
-- ------------------------------------------------------------

CREATE TABLE CodeValue
(
    codeTypeId              INT(3) NOT NULL,
    codeValueSequence       INT(3) NOT NULL,
    englishDescription      VARCHAR(100) NOT NULL,
    englishDescriptionShort VARCHAR(20) NOT NULL,
    frenchDescription       VARCHAR(100) DEFAULT NULL,
    frenchDescriptionShort  VARCHAR(20) DEFAULT NULL,
    sortOrder               INT(3) DEFAULT NULL,
    createdDateTime         DATETIME DEFAULT NULL,
    createdUserId           VARCHAR(20) DEFAULT NULL,
    updatedDateTime         DATETIME DEFAULT NULL,
    updatedUserId           VARCHAR(20) DEFAULT NULL
) COMMENT 'This table holds code values for the application';

ALTER TABLE CodeValue
    ADD PRIMARY KEY (codeTypeId, codeValueSequence);


-- ------------------------------------------------------------
-- Pet Species
-- ------------------------------------------------------------

INSERT INTO CodeValue
(codeTypeId, codeValueSequence, englishDescription,
 englishDescriptionShort, frenchDescription,
 frenchDescriptionShort, sortOrder, createdDateTime,
 createdUserId, updatedDateTime, updatedUserId)
VALUES
    (1, 1, 'Cat', 'Cat', 'Chat', 'Chat', 1, NOW(), 'admin', NOW(), 'admin'),
    (1, 2, 'Dog', 'Dog', 'Chien', 'Chien', 2, NOW(), 'admin', NOW(), 'admin');


-- ------------------------------------------------------------
-- Pet Coat Length
-- ------------------------------------------------------------

INSERT INTO CodeValue
(codeTypeId, codeValueSequence, englishDescription,
 englishDescriptionShort, frenchDescription,
 frenchDescriptionShort, sortOrder, createdDateTime,
 createdUserId, updatedDateTime, updatedUserId)
VALUES
    (2, 1, 'Short', 'Short', 'Court', 'Court', 1, NOW(), 'admin', NOW(), 'admin'),
    (2, 2, 'Medium', 'Medium', 'Moyen', 'Moyen', 2, NOW(), 'admin', NOW(), 'admin'),
    (2, 3, 'Long', 'Long', 'Long', 'Long', 3, NOW(), 'admin', NOW(), 'admin'),
    (2, 4, 'Bald', 'Bald', 'Sans poils', 'Bald', 4, NOW(), 'admin', NOW(), 'admin');


-- ------------------------------------------------------------
-- Pet Sex
-- ------------------------------------------------------------

INSERT INTO CodeValue
(codeTypeId, codeValueSequence, englishDescription,
 englishDescriptionShort, frenchDescription,
 frenchDescriptionShort, sortOrder, createdDateTime,
 createdUserId, updatedDateTime, updatedUserId)
VALUES
    (3, 1, 'Male', 'M', 'Mâle', 'M', 1, NOW(), 'admin', NOW(), 'admin'),
    (3, 2, 'Female', 'F', 'Femelle', 'F', 2, NOW(), 'admin', NOW(), 'admin');