CREATE TABLE person_data(
id int(11) NOT NULL AUTO_INCREMENT,
first_name varchar(100) NOT NULL,
second_name varchar(100) NOT NULL,
pesel varchar(11) NOT NULL,
PRIMARY KEY (id)
);
CREATE TABLE father(
id int(11) NOT NULL AUTO_INCREMENT,
person_data_id int(11),
birth_date date,
PRIMARY KEY (id),
KEY father_person_data (person_data_id),
CONSTRAINT father_person_data FOREIGN KEY (person_data_id) REFERENCES person_data (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE family(
id int(11) NOT NULL AUTO_INCREMENT,
father_id int(11),
PRIMARY KEY (id),
KEY family_father (father_id),
CONSTRAINT family_father FOREIGN KEY (father_id) REFERENCES father (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE child(
id int(11) NOT NULL AUTO_INCREMENT,
person_data_id int(11),
sex varchar(10),
family_id int(11),
PRIMARY KEY (id),
KEY child_person_data (person_data_id),
KEY child_family (family_id),
CONSTRAINT child_person_data FOREIGN KEY (person_data_id) REFERENCES person_data (id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT child_family FOREIGN KEY (family_id) REFERENCES family (id)
);