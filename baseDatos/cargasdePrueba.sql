/* ------------------------------------------------------------------------ */
/*  Base de datos para el Sistema de Monitoreo del Estado de Salud de       */
/*  Pacientes COVID-19 en Hospitales                                        */
/*  Autor: Perez Federico Jose Joel							      		    */
/*  Creado : 26-Oct-2020 						 				    	    */
/*  DBMS   : MySql 														    */
/* ------------------------------------------------------------------------ */

/*Careta*/
INSERT INTO MH_CARETA (FECHA_MANUFACTURA) VALUES ("2020-01-26");
INSERT INTO MH_CARETA (FECHA_MANUFACTURA) VALUES ("2020-01-25");

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


commit;
                