/* ------------------------------------------------------------------------ */
/*  Base de datos para el Sistema de Monitoreo del Estado de Salud de       */
/*  Pacientes COVID-19 en Hospitales                                        */
/*  Autor: Iliac Huerta Trujillo			      		    */
/*  Creado : 28-Sep-2020 04:15:34 p.m. 				    	    */
/*  DBMS   : MySql 							    */
/* ------------------------------------------------------------------------ */

/*--Borrar tablas*/
TRUNCATE TABLE MH_MENU_OPCION;
TRUNCATE TABLE MH_ROL_MENU;
TRUNCATE TABLE MH_USUARIO_ROL;
TRUNCATE TABLE MH_BITACORA;
TRUNCATE TABLE MH_HOSPITAL_MEDICO;
TRUNCATE TABLE MH_PACIENTE_MEDICO;
TRUNCATE TABLE MH_ANTECEDENTES;
TRUNCATE TABLE MH_VALORES_REFERENCIA;

DELETE FROM MH_PACIENTE;
DELETE FROM MH_MEDICO;
DELETE FROM MH_USUARIO;
DELETE FROM MH_PERSONA;
DELETE FROM MH_GENERO;

DELETE FROM MH_OPCION;
DELETE FROM MH_MENU;
DELETE FROM MH_ROL;
DELETE FROM MH_ESTADOPACIENTE;

/*--- Definiciones de Roles*/
INSERT INTO MH_ROL (ID_ROL, DESCRIPCION) VALUES (1,'ADMINISTRADOR');
INSERT INTO MH_ROL (ID_ROL, DESCRIPCION) VALUES (2,'APOYO');
INSERT INTO MH_ROL (ID_ROL, DESCRIPCION) VALUES (3,'MEDICO');
INSERT INTO MH_ROL (ID_ROL, DESCRIPCION) VALUES (4,'PACIENTE');
INSERT INTO MH_ROL (ID_ROL, DESCRIPCION) VALUES (5,'ADMON_HOSPITAL');

/* -- Definición de estado de paciente-- */
INSERT INTO MH_ESTADOPACIENTE (ID_ESTADOPACIENTE, DESCRIPCION) VALUES(1, 'ESTABLE');
INSERT INTO MH_ESTADOPACIENTE (ID_ESTADOPACIENTE, DESCRIPCION) VALUES(2, 'GRAVE');
INSERT INTO MH_ESTADOPACIENTE (ID_ESTADOPACIENTE, DESCRIPCION) VALUES(3, 'DECESO');
INSERT INTO MH_ESTADOPACIENTE (ID_ESTADOPACIENTE, DESCRIPCION) VALUES(4, 'ALTA');

/* -- Definición de estado de careta-- */
INSERT INTO MH_ESTADOCARETA (ID_ESTADOCARETA, DESCRIPCION) VALUES(1, 'DISPONIBLE');
INSERT INTO MH_ESTADOCARETA (ID_ESTADOCARETA, DESCRIPCION) VALUES(2, 'ASIGNADO');
INSERT INTO MH_ESTADOCARETA (ID_ESTADOCARETA, DESCRIPCION) VALUES(3, 'AVERIADO');

/*---- Definiciones de menus*/
INSERT INTO MH_MENU (ID_MENU, DESCRIPCION, RUTA_ICONO, POSICION) VALUES (1,'General','fa fa-cog',999);
INSERT INTO MH_MENU (ID_MENU, DESCRIPCION, RUTA_ICONO, POSICION) VALUES (2,'Administrador','fas fa-user-ninja',1);
INSERT INTO MH_MENU (ID_MENU, DESCRIPCION, RUTA_ICONO, POSICION) VALUES (3,'Médico','fas fa-user-md',2);
INSERT INTO MH_MENU (ID_MENU, DESCRIPCION, RUTA_ICONO, POSICION) VALUES (4,'Paciente','fas fa-bed',2);

/*-- Definición de Opciones*/
Insert into MH_OPCION (ID_OPCION, DESCRIPCION, RUTA_ICONO, ACCION) Values (1, 'Salir', 'ui-icon-close', '#{sesionMB.cerrarSesion}');
Insert into MH_OPCION (ID_OPCION, DESCRIPCION, RUTA_ICONO, ACCION) Values (2, 'Gestión Usuarios', 'fas fa-id-card', '/faces/facelets/admon/gestionUsuarios.xhtml');
Insert into MH_OPCION (ID_OPCION, DESCRIPCION, RUTA_ICONO, ACCION) Values (3, 'Gestión Hospital', 'fas fa-hospital', '/faces/facelets/admon/gestionHospital.xhtml');
Insert into MH_OPCION (ID_OPCION, DESCRIPCION, RUTA_ICONO, ACCION) Values (4, 'Pacientes', 'fas fa-procedures', '/faces/facelets/medico/listaPacientesMedico.xhtml');
Insert into MH_OPCION (ID_OPCION, DESCRIPCION, RUTA_ICONO, ACCION) Values (5, 'Gestión Medicos', 'fas fa-user-md', '/faces/facelets/admon/gestionMedicos.xhtml');
Insert into MH_OPCION (ID_OPCION, DESCRIPCION, RUTA_ICONO, ACCION) Values (6, 'Mis Datos', 'pi pi-id-card', '/faces/facelets/paciente/datosPaciente.xhtml');
Insert into MH_OPCION (ID_OPCION, DESCRIPCION, RUTA_ICONO, ACCION) Values (7, 'Valores de Referencia', 'pi pi-sliders-h', '/faces/facelets/admon/valoresReferencia.xhtml');
Insert into MH_OPCION (ID_OPCION, DESCRIPCION, RUTA_ICONO, ACCION) Values (8, 'Gestion Dispositivos', 'fa fa-hdd-o', '/faces/facelets/admon/gestionDispositivos.xhtml');
Insert into MH_OPCION (ID_OPCION, DESCRIPCION, RUTA_ICONO, ACCION) Values (9, 'Gestión Pacientes', 'fas fa-bed', '/faces/facelets/admon/gestionPacientes.xhtml');

/*--Asociación de Rol con Menu*/
INSERT INTO MH_ROL_MENU (ID_MENU, ID_ROL) VALUES (1,1); /*--Todos los roles tienen el menú general*/
INSERT INTO MH_ROL_MENU (ID_MENU, ID_ROL) VALUES (2,1);
INSERT INTO MH_ROL_MENU (ID_MENU, ID_ROL) VALUES (1,2);
INSERT INTO MH_ROL_MENU (ID_MENU, ID_ROL) VALUES (1,3);
INSERT INTO MH_ROL_MENU (ID_MENU, ID_ROL) VALUES (1,4);
INSERT INTO MH_ROL_MENU (ID_MENU, ID_ROL) VALUES (1,5);
INSERT INTO MH_ROL_MENU (ID_MENU, ID_ROL) VALUES (3,3);
INSERT INTO MH_ROL_MENU (ID_MENU, ID_ROL) VALUES (4,4);


/*Definición de opciones para cada menú*/
INSERT INTO MH_MENU_OPCION (ID_MENU_OPCION,ID_MENU,ID_OPCION,ID_SUB_MENU,POSICION) VALUES (1,1,1,null,999);
INSERT INTO MH_MENU_OPCION (ID_MENU_OPCION,ID_MENU,ID_OPCION,ID_SUB_MENU,POSICION) VALUES (2,2,2,null,2);
INSERT INTO MH_MENU_OPCION (ID_MENU_OPCION,ID_MENU,ID_OPCION,ID_SUB_MENU,POSICION) VALUES (3,2,3,null,1);
INSERT INTO MH_MENU_OPCION (ID_MENU_OPCION,ID_MENU,ID_OPCION,ID_SUB_MENU,POSICION) VALUES (4,3,4,null,1);
INSERT INTO MH_MENU_OPCION (ID_MENU_OPCION,ID_MENU,ID_OPCION,ID_SUB_MENU,POSICION) VALUES (5,2,5,null,3);
INSERT INTO MH_MENU_OPCION (ID_MENU_OPCION,ID_MENU,ID_OPCION,ID_SUB_MENU,POSICION) VALUES (6,4,6,null,3);
INSERT INTO MH_MENU_OPCION (ID_MENU_OPCION,ID_MENU,ID_OPCION,ID_SUB_MENU,POSICION) VALUES (7,2,7,null,6);
INSERT INTO MH_MENU_OPCION (ID_MENU_OPCION,ID_MENU,ID_OPCION,ID_SUB_MENU,POSICION) VALUES (8,2,8,null,5);
INSERT INTO MH_MENU_OPCION (ID_MENU_OPCION,ID_MENU,ID_OPCION,ID_SUB_MENU,POSICION) VALUES (9,2,9,null,4);

/*--- Definición catálogo de géneros*/
INSERT INTO MH_GENERO (ID_GENERO,DESCRIPCION) VALUES(1,'FEMENINO');
INSERT INTO MH_GENERO (ID_GENERO,DESCRIPCION) VALUES(2,'MASCULINO');
INSERT INTO MH_GENERO (ID_GENERO,DESCRIPCION) VALUES(3,'N/E');


/*-- Persona inicial*/
INSERT INTO MH_PERSONA (ID_PERSONA,ID_GENERO,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,CURP,EDAD) VALUES (1,3,'JHON','DOE','DOE','JDD80202020',21);
INSERT INTO MH_PERSONA (ID_PERSONA,ID_GENERO,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,CURP,EDAD) VALUES (2,2,'ILIAC','HUERTA','TRUJILLO','HUTI070707',30);


/*-- Usuario */
INSERT INTO MH_USUARIO (ID_USUARIO,CONTRASENIA,CIFRA,ACTIVO,ID_PERSONA,EMAIL) VALUES ('johndoe','johndoe','wnE7YskDeRve/Fpqmd8E1DMN5JG7x6DKalAHM35KYCg=',TRUE,1,'correo@gmail.com');
INSERT INTO MH_USUARIO (ID_USUARIO,CONTRASENIA,CIFRA,ACTIVO,ID_PERSONA,EMAIL) VALUES ('ihuerta','ihuerta123','b5rQLeOcb90x7aTLWtcRvkgw+fgWn//wqrKA9p0YiIM=',TRUE,2,'correo@gmail.com');


/*---usuario rol*/

INSERT INTO MH_USUARIO_ROL (ID_USUARIO,ID_ROL) VALUES ('johndoe',1);
INSERT INTO MH_USUARIO_ROL (ID_USUARIO,ID_ROL) VALUES ('ihuerta',1);
INSERT INTO MH_USUARIO_ROL (ID_USUARIO,ID_ROL) VALUES ('ihuerta',2);
INSERT INTO MH_USUARIO_ROL (ID_USUARIO,ID_ROL) VALUES ('ihuerta',5);

#Valores de referencia
INSERT INTO MH_VALORES_REFERENCIA (SAT_OXIGENO_NORMAL_MIN,SAT_OXIGENO_NORMAL_MAX,SAT_OXIGENO_WARNING_MIN,SAT_OXIGENO_WARNING_MAX,SAT_OXIGENO_ALERT_MIN,SAT_OXIGENO_ALERT_MAX,
								   TEMPERATURA_NORMAL_MIN,TEMPERATURA_NORMAL_MAX,TEMPERATURA_WARNING_MIN,TEMPERATURA_WARNING_MAX,TEMPERATURA_ALERT_MIN,TEMPERATURA_ALERT_MAX,
                                   CAPNOGRAFIA_NORMAL_MIN,CAPNOGRAFIA_NORMAL_MAX,CAPNOGRAFIA_WARNING_MIN,CAPNOGRAFIA_WARNING_MAX,CAPNOGRAFIA_ALERT_MIN,CAPNOGRAFIA_ALERT_MAX,
                                   FREC_CARDIACA_NORMAL_MIN,FREC_CARDIACA_NORMAL_MAX,FREC_CARDIACA_WARNING_MIN,FREC_CARDIACA_WARNING_MAX,FREC_CARDIACA_ALERT_MIN,FREC_CARDIACA_ALERT_MAX,
                                   FREC_RESPIRATORIA_NORMAL_MIN,FREC_RESPIRATORIA_NORMAL_MAX,FREC_RESPIRATORIA_WARNING_MIN,FREC_RESPIRATORIA_WARNING_MAX,FREC_RESPIRATORIA_ALERT_MIN,FREC_RESPIRATORIA_ALERT_MAX,
                                   ID_VALREF,
                                   PRE_ART_SISTOLICA_NORMAL_MIN,PRE_ART_SISTOLICA_NORMAL_MAX,PRE_ART_SISTOLICA_WARNING_MIN,PRE_ART_SISTOLICA_WARNING_MAX,PRE_ART_SISTOLICA_ALERT_MIN,PRE_ART_SISTOLICA_ALERT_MAX,
                                   PRE_ART_DIASTOLICA_NORMAL_MIN,PRE_ART_DIASTOLICA_NORMAL_MAX,PRE_ART_DIASTOLICA_WARNING_MIN,PRE_ART_DIASTOLICA_WARNING_MAX,PRE_ART_DIASTOLICA_ALERT_MIN,PRE_ART_DIASTOLICA_ALERT_MAX)
VALUES(92.0,100.0,81.0,92.0,0.0,81.0,
       33.0,37.0,37.0,38.0,38.0,50.0,
       35,37,40,45,50,60,
       60,80,81,99,100,130,
       16,18,19,20,21,30,
       1,
       100,120,121,139,140,190,
       60,80,81,89,90,100);
       
       
/*Para el inicio de sesion de un usuario en el sistema*/
INSERT INTO MH_EVENTOBITACORA (ID_EVENTO, DESCRIPCION)
VALUES ('1', 'INICIA_SESION');

/*Para el registro de un paciente en el sistema por el administrador */
INSERT INTO MH_EVENTOBITACORA (ID_EVENTO, DESCRIPCION)
VALUES ('2', 'REGISTRA_PACIENTE');
/*Para el registro de un medico en el sistema por el administrador */
INSERT INTO MH_EVENTOBITACORA (ID_EVENTO, DESCRIPCION)
VALUES ('3', 'REGISTRA_MEDICO');
/*Para el registro de un medico en el sistema por el administrador */
INSERT INTO MH_EVENTOBITACORA (ID_EVENTO, DESCRIPCION)
VALUES ('4', 'REGISTRA_DISPOSITIVO');

/*Para la actualizacion del estado de un paciente en el sistema por el medico */
INSERT INTO MH_EVENTOBITACORA (ID_EVENTO, DESCRIPCION)
VALUES ('5', 'ACTUALIZA_ESTADO_PACIENTE');

/*Para identificar el Paciente registrado*/
INSERT INTO MH_EVENTOBITACORA (ID_EVENTO, DESCRIPCION)
VALUES ('6', 'PACIENTE_REGISTRADO');
/*Para identificar el medico registrado*/
INSERT INTO MH_EVENTOBITACORA (ID_EVENTO, DESCRIPCION)
VALUES ('7', 'MEDICO_REGISTRADO');


/*Para senalar el cambio de estado del paciente*/
INSERT INTO MH_EVENTOBITACORA (ID_EVENTO, DESCRIPCION)
VALUES ('8', 'PACIENTE_ALTA');
INSERT INTO MH_EVENTOBITACORA (ID_EVENTO, DESCRIPCION)
VALUES ('9', 'PACIENTE_DECESO');

commit;
