/* ------------------------------------------------------------------------ */
/*  Base de datos para el Sistema de Monitoreo del Estado de Salud de       */
/*  Pacientes COVID-19 en Hospitales                                        */
/*  Autor: Perez Federico Jose Joel							      		    */
/*  Creado : 26-Oct-2020 						 				    	    */
/*  DBMS   : MySql 														    */
/* ------------------------------------------------------------------------ */

/*Careta*/
INSERT INTO MH_CARETA (ID_CARETA,NO_SERIE,FECHA_MANUFACTURA) VALUES ("1","213747","2020-01-26");
INSERT INTO MH_CARETA (ID_CARETA,NO_SERIE,FECHA_MANUFACTURA) VALUES ("2","213748","2020-01-25");
INSERT INTO MH_CARETA (ID_CARETA,NO_SERIE,FECHA_MANUFACTURA) VALUES ("3","213749","2020-01-26");
INSERT INTO MH_CARETA (ID_CARETA,NO_SERIE,FECHA_MANUFACTURA) VALUES ("4","213750","2020-01-25");
INSERT INTO MH_CARETA (ID_CARETA,NO_SERIE,FECHA_MANUFACTURA) VALUES ("5","213751","2020-01-25");

/*Careta Hospital*/
INSERT INTO MH_CARETA_HOSPITAL (FECHA_ASIGNACION,ID_CARETA,ID_HOSPITAL) VALUES ("2021-01-20","1","1");
INSERT INTO MH_CARETA_HOSPITAL (FECHA_ASIGNACION,ID_CARETA,ID_HOSPITAL) VALUES ("2021-01-20","2","1");
INSERT INTO MH_CARETA_HOSPITAL (FECHA_ASIGNACION,ID_CARETA,ID_HOSPITAL) VALUES ("2021-01-20","3","1");
INSERT INTO MH_CARETA_HOSPITAL (FECHA_ASIGNACION,ID_CARETA,ID_HOSPITAL) VALUES ("2021-01-20","4","1");

/*Persona*/
INSERT INTO MH_PERSONA (ID_GENERO,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,CURP) 
VALUES (2,'JUAN','FLORES','CAMPOS','JFC80202020');
INSERT INTO MH_PERSONA (ID_GENERO,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,CURP) 
VALUES (1,'ANA','VALENCIA','HERNANDEZ','AVHI070707');

INSERT INTO MH_HOSPITAL (NOMBRE, UBICACION_GEO, TEL_EMERGENCIAS, DIR_CALLE, NUMERO, COLONIA, MUNICIPIO, ESTADO)
		VALUES("Conjunto Colonia IMSS (puerta 8)", "1224,23445", "5585327489", "Calz. Manuel Villalongín", 117, "Cuauhtémoc", "Mexico", "Ciudad de Mexico");


INSERT INTO MH_PACIENTE (ID_PERSONA, DIR_CALLE, ID_HOSPITAL, DIR_NUMERO, DIR_INTERIOR, TEL_FIJO, TEL_CEL, ID_CARETA, ID_ESTADOPACIENTE) 
				VALUES (3, "ROMERO SANCHEZ", 1, 114, 5, 6654334555, 5563776900, 1, 1);
INSERT INTO MH_PACIENTE (ID_PERSONA, DIR_CALLE, ID_HOSPITAL, DIR_NUMERO, DIR_INTERIOR, TEL_FIJO, TEL_CEL, ID_CARETA, ID_ESTADOPACIENTE) 
				VALUES (4, "PLAZA JUAREZ", 1, 32, 2, 5564334555, 5543141673, 2, 1);


/*Antecedentes*/
INSERT INTO MH_ANTECEDENTES (ID_PACIENTE, DIABETES, CANCER, ASMA, VIH, HAS, EPOC, EMBARAZO, ARTRITIS, ENFAUTOINMUNE, FECHA)
			VALUES (1, TRUE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, '2021-01-20');

/*Insertando Usuario de paciente y rol*/
INSERT INTO MH_USUARIO (ID_USUARIO, CONTRASENIA, ACTIVO, ID_PERSONA)
VALUES ("paciente3", "paciente3", true, 3);

INSERT INTO MH_USUARIO (ID_USUARIO, CONTRASENIA, ACTIVO, ID_PERSONA)
VALUES ("paciente4", "paciente4", true, 4);

INSERT INTO MH_USUARIO_ROL (ID_USUARIO, ID_ROL)
VALUES ('paciente3', 4);
INSERT INTO MH_USUARIO_ROL (ID_USUARIO, ID_ROL)
VALUES ('paciente4', 4);


/*Insertando un médico*/
INSERT INTO MH_PERSONA (ID_GENERO,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,CURP) 
VALUES (2,'Alberto','Montero','Montero','MOMA80202020');

INSERT INTO MH_MEDICO (CEDULA_PROF,ID_PERSONA,EMAIL,CELULAR)
VALUES (123456,(SELECT ID_PERSONA FROM MH_PERSONA WHERE CURP = 'MOMA80202020'), 'albertomm@prueba.test',55555555555);

/*relación medico hospital*/
INSERT INTO MH_HOSPITAL_MEDICO (ID_HOSPITAL, ID_MEDICO)
VALUES (1,(SELECT ID_MEDICO FROM MH_MEDICO WHERE CEDULA_PROF = 123456));

/*usuario médico de pruebas y rol de médico*/
INSERT INTO MH_USUARIO (ID_USUARIO, CONTRASENIA, ACTIVO, ID_PERSONA)
VALUES ('medico1','medico1',1,(SELECT ID_PERSONA FROM MH_PERSONA WHERE CURP = 'MOMA80202020'));

INSERT INTO MH_USUARIO_ROL (ID_USUARIO, ID_ROL)
VALUES ('medico1', 3);

/*relacion pacientes medico */
INSERT INTO MH_PACIENTE_MEDICO(ID_PACIENTE, ID_MEDICO, FECHA_INICIO)
VALUES ((SELECT ID_PACIENTE FROM MH_PACIENTE WHERE ID_PERSONA = 3),1,(SELECT SYSDATE()));

INSERT INTO MH_PACIENTE_MEDICO(ID_PACIENTE, ID_MEDICO, FECHA_INICIO)
VALUES ((SELECT ID_PACIENTE FROM MH_PACIENTE WHERE ID_PERSONA = 4),1,(SELECT SYSDATE()));

commit;                