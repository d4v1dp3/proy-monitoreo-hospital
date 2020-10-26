/* ------------------------------------------------------------------------ */
/*  Base de datos para el Sistema de Monitoreo del Estado de Salud de       */
/*  Pacientes COVID-19 en Hospitales                                        */
/*  Autor: Perez Federico Jose Joel							      		    */
/*  Creado : 26-Oct-2020 						 				    	    */
/*  DBMS   : MySql 														    */
/* ------------------------------------------------------------------------ */



select * from mh_hospital;
select * from mh_medidas;
select * from mh_paciente;
select * from mh_careta;
select * from mh_persona;
select * from mh_genero;
select * from mh_hospital;

/*Careta*/
INSERT INTO mh_careta (ID_CARETA, FECHA_MANUFACTURA) VALUES (1001, "2020-01-26");
INSERT INTO mh_careta (ID_CARETA, FECHA_MANUFACTURA) VALUES (1002, "2020-01-25");

/*Persona*/
INSERT INTO MH_PERSONA (ID_PERSONA,ID_GENERO,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,CURP) VALUES (3,2,'JUAN','FLORES','CAMPOS','JFC80202020');
INSERT INTO MH_PERSONA (ID_PERSONA,ID_GENERO,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,CURP) VALUES (4,1,'ANA','VALENCIA','HERNANDEZ','AVHI070707');

/*Hospital*/
INSERT INTO mh_hospital (ID_HOSPITAL, NOMBRE, UBICACION_GEO, TEL_EMERGENCIAS, DIR_CALLE, NUMERO, COLONIA, MUNICIPIO, ESTADO)
		VALUES(100, "Conjunto Colonia IMSS (puerta 8)", "1224,23445", "5585327489", "Calz. Manuel Villalongín", 117, "Cuauhtémoc", "Mexico", "Ciudad de Mexico");
INSERT INTO mh_hospital (ID_HOSPITAL, NOMBRE, UBICACION_GEO, TEL_EMERGENCIAS, DIR_CALLE, NUMERO, COLONIA, MUNICIPIO, ESTADO)
		VALUES(101, "Imss Villalonguin", "1234.0,9873.1", "5573867490", "Sexta Avenida", 14, "Cuauhtémoc", "Mexico", "Ciudad de Mexico");

delete  from mh_hospital where ID_HOSPITAL=101;

/*Estado Paciente*/
select * from mh_estadopaciente;
INSERT INTO mh_estadopaciente (ID_ESTADOPACIENTE, DESCRIPCION) VALUES(404, "El paciente no se ha encontrado.");
INSERT INTO mh_estadopaciente (ID_ESTADOPACIENTE, DESCRIPCION) VALUES(200, "El paciente esta estable en camilla.");

/*Paciente*/
select * from mh_paciente;
INSERT INTO mh_paciente (ID_PACIENTE,ID_PERSONA, DIR_CALLE, ID_HOSPITAL, DIR_NUMERO, DIR_INTERIOR, TEL_FIJO, TEL_CEL, ID_CARETA, ID_ESTADOPACIENTE) 
				VALUES (1,3, "ROMERO SANCHEZ", 100, 114, 5, 6654334555, 5563776900, 1001, 200);
INSERT INTO mh_paciente (ID_PACIENTE,ID_PERSONA, DIR_CALLE, ID_HOSPITAL, DIR_NUMERO, DIR_INTERIOR, TEL_FIJO, TEL_CEL, ID_CARETA, ID_ESTADOPACIENTE) 
				VALUES (2,4, "PLAZA JUAREZ", 101, 32, 2, 5564334555, 5543141673, 1002, 200);