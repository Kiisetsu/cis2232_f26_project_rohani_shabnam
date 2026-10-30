-- ------------------------------------------------------------------------------
-- Pet Adoption App 
-- Course: CIS2232
-- ------------------------------------------------------------------------------

DROP DATABASE IF EXISTS cis2232_pet_adoption_app;
CREATE DATABASE cis2232_pet_adoption_app;
USE cis2232_pet_adoption_app;

-- ------------------------------------------------------------------------------
-- Main table holding the animal records (fields specified by the BA).
-- The adoption fee is calculated by the program, so it is not stored.
-- ------------------------------------------------------------------------------

CREATE TABLE Animal
(
    id      int(5),
    name    varchar(50) NOT NULL COMMENT 'Animal name',
    sex     varchar(10) NOT NULL COMMENT 'Sex of the animal',
    species varchar(20) NOT NULL COMMENT 'Species, e.g. Cat or Dog',
    colour  varchar(30) NOT NULL COMMENT 'Colour of the animal',
    coat    varchar(20) NOT NULL COMMENT 'Coat: Short, Medium, Long or Bald',
    age     double      NOT NULL COMMENT 'Age in years'
) COMMENT 'This table holds the animals available for adoption';

ALTER TABLE Animal
    ADD PRIMARY KEY (id);
ALTER TABLE Animal
    MODIFY id int(5) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;

INSERT INTO Animal (id, name, sex, species, colour, coat, age)
VALUES (1, 'Milo', 'Male', 'Cat', 'Orange', 'Short', 1.5),
       (2, 'Luna', 'Female', 'Cat', 'Black', 'Long', 4),
       (3, 'Buddy', 'Male', 'Dog', 'Golden', 'Medium', 3),
       (4, 'Daisy', 'Female', 'Dog', 'White', 'Long', 9),
       (5, 'Sphynx Sam', 'Male', 'Cat', 'Pink', 'Bald', 2);


CREATE TABLE CodeType
(
    codeTypeId         int(3)       COMMENT 'This is the primary key for code types',
    englishDescription varchar(100) NOT NULL COMMENT 'English description',
    frenchDescription  varchar(100) DEFAULT NULL COMMENT 'French description',
    createdDateTime    datetime     DEFAULT NULL,
    createdUserId      varchar(20)  DEFAULT NULL,
    updatedDateTime    datetime     DEFAULT NULL,
    updatedUserId      varchar(20)  DEFAULT NULL
) COMMENT 'This tables holds the code types that are available for the application';

ALTER TABLE CodeType
    ADD PRIMARY KEY (codeTypeId);

INSERT INTO CodeType (codeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, ''),
       (2, 'Species', 'Species FR', sysdate(), '', CURRENT_TIMESTAMP, ''),
       (3, 'Coat Types', 'Coat Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');

CREATE TABLE CodeValue
(
    codeTypeId              int(3)       NOT NULL COMMENT 'see code_type table',
    codeValueSequence       int(3)       NOT NULL,
    englishDescription      varchar(100) NOT NULL COMMENT 'English description',
    englishDescriptionShort varchar(20)  NOT NULL COMMENT 'English abbreviation for description',
    frenchDescription       varchar(100) DEFAULT NULL COMMENT 'French description',
    frenchDescriptionShort  varchar(20)  DEFAULT NULL COMMENT 'French abbreviation for description',
    sortOrder               int(3)       DEFAULT NULL COMMENT 'Sort order if applicable',
    createdDateTime         datetime     DEFAULT NULL,
    createdUserId           varchar(20)  DEFAULT NULL,
    updatedDateTime         datetime     DEFAULT NULL,
    updatedUserId           varchar(20)  DEFAULT NULL
) COMMENT ='This will hold code values for the application.';

ALTER TABLE CodeValue
    ADD PRIMARY KEY (codeTypeId, codeValueSequence);

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort,
                       frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId,
                       updatedDateTime, updatedUserId)
VALUES (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', sysdate(), 'admin', sysdate(), 'admin'),
       (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', sysdate(), 'admin', sysdate(), 'admin'),
       (2, 1, 'Cat', 'Cat', 'CatFR', 'CatFR', sysdate(), 'admin', sysdate(), 'admin'),
       (2, 2, 'Dog', 'Dog', 'DogFR', 'DogFR', sysdate(), 'admin', sysdate(), 'admin'),
       (3, 1, 'Short', 'Short', 'ShortFR', 'ShortFR', sysdate(), 'admin', sysdate(), 'admin'),
       (3, 2, 'Medium', 'Medium', 'MediumFR', 'MediumFR', sysdate(), 'admin', sysdate(), 'admin'),
       (3, 3, 'Long', 'Long', 'LongFR', 'LongFR', sysdate(), 'admin', sysdate(), 'admin'),
       (3, 4, 'Bald', 'Bald', 'BaldFR', 'BaldFR', sysdate(), 'admin', sysdate(), 'admin');