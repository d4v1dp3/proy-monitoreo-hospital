/* ------------------------------------------------------------------------ */
/*  Base de datos para el Sistema de Monitoreo del Estado de Salud de       */
/*  Pacientes COVID-19 en Hospitales                                        */
/*  Autor  :						      		    */
/*  Creado :     						 				    	    */
/*  DBMS   : MySql 														    */
/* ------------------------------------------------------------------------ */

/*--Mediciones*/

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ID_MEDICION,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES((SELECT SYSDATE()),97.8,39.4,70,11,1,1,1,1,120,70);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ID_MEDICION,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES((SELECT SYSDATE()),91.8,40.4,100,3,2,2,2,1,150,90);

