truncate mh_medidas;

#paciente1
INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),92.4,34.5,75,11,1,(select ID_CARETA from mh_paciente where ID_PACIENTE=1),1,120,70);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),93.4,34.5,75,11,1,(select ID_CARETA from mh_paciente where ID_PACIENTE=1),1,110,50);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),94.4,34.5,75,11,1,(select ID_CARETA from mh_paciente where ID_PACIENTE=1),1,180,90);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),95.4,34.5,75,11,1,(select ID_CARETA from mh_paciente where ID_PACIENTE=1),1,160,50);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),96.4,34.5,75,11,1,(select ID_CARETA from mh_paciente where ID_PACIENTE=1),1,190,60);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),97.4,34.5,75,11,1,(select ID_CARETA from mh_paciente where ID_PACIENTE=1),1,135,77);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),98.4,34.5,75,11,1,(select ID_CARETA from mh_paciente where ID_PACIENTE=1),1,140,90);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),99.4,34.5,75,11,(select ID_CARETA from mh_paciente where ID_PACIENTE=1),1,1,125,85);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),92.4,34.5,75,11,(select ID_CARETA from mh_paciente where ID_PACIENTE=1),1,1,130,80);
SELECT SLEEP(1);

#paciente2
INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),93.4,34.5,75,11,2,(select ID_CARETA from mh_paciente where ID_PACIENTE=2),1,190,60);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),94.4,34.5,75,11,2,(select ID_CARETA from mh_paciente where ID_PACIENTE=2),1,135,77);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),95.4,34.5,75,11,2,(select ID_CARETA from mh_paciente where ID_PACIENTE=2),1,140,90);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),96.4,34.5,75,11,2,(select ID_CARETA from mh_paciente where ID_PACIENTE=2),1,125,85);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),97.4,34.5,75,11,2,(select ID_CARETA from mh_paciente where ID_PACIENTE=2),1,130,80);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),98.4,34.5,75,11,2,(select ID_CARETA from mh_paciente where ID_PACIENTE=2),1,120,70);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),99.4,34.5,75,11,2,(select ID_CARETA from mh_paciente where ID_PACIENTE=2),1,110,50);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),92.4,34.5,75,11,2,(select ID_CARETA from mh_paciente where ID_PACIENTE=2),1,180,90);
SELECT SLEEP(1);

INSERT INTO MH_MEDIDAS (FECHA_MEDICION,SATURACION_OXIGENO,TEMPERATURA,FREC_CARDIACA,FREC_RESPIRATORIA,ID_PACIENTE,ID_CARETA,ALERTA,PRE_ART_SISTOLICA,PRE_ART_DIASTOLICA)
VALUES(now(),92.4,34.5,75,11,2,(select ID_CARETA from mh_paciente where ID_PACIENTE=2),1,160,50);




