--
-- PostgreSQL database dump
--

-- Started on 2008-09-22 17:59:20 Hora est. del Pacífico de SA

SET client_encoding = 'SQL_ASCII';
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = true;

--
-- TOC entry 1222 (class 1259 OID 8139045)
-- Dependencies: 6
-- Name: personal; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE personal (
    codigo_personal character varying(255) NOT NULL,
    codigo_fondo character varying(255),
    nombre_personal character varying(80) NOT NULL,
    cargo_personal character varying(80),
    c_usuario character varying(255),
    grado_personal character varying(80),
    es_usuario character varying(255),
    codigo_oficina character varying(255),
    es_responsable character varying(255)
);


ALTER TABLE public.personal OWNER TO postgres;

SET search_path = tramite, pg_catalog;

--
-- TOC entry 1249 (class 1259 OID 8241614)
-- Dependencies: 5
-- Name: personal; Type: TABLE; Schema: tramite; Owner: postgres; Tablespace: 
--

CREATE TABLE personal (
    codigo_personal integer NOT NULL,
    codigo_fondo character(2),
    nombre_personal character varying(80),
    cargo_personal character varying(40),
    c_usuario character varying(30),
    grado_personal character varying(80),
    es_usuario character(1),
    codigo_oficina integer,
    es_responsable character(1)
);


ALTER TABLE tramite.personal OWNER TO postgres;

SET search_path = public, pg_catalog;

--
-- TOC entry 1572 (class 0 OID 8139045)
-- Dependencies: 1222
-- Data for Name: personal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('1', '01', 'ALEX CALAGUA', 'ENCARGADO DEL MESA DE PARTES', 'alex    ', 'SEÑOR', 'S', '1', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('2', '01', 'GERMAN HUAMAN', 'ENCARGADO DE LA PRESIDENCIA', 'german  ', 'SEÑOR', 'S', '2', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('3', '01', 'NILTON ULLOA', 'JEFE DE SISTEMAS', 'nilton  ', 'SEÑOR.', 'S', '3', 'S');
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES ('4', '01', 'YESICA ESPINOSA', 'SECRETARIA', 'yesica  ', 'SRTA', 'S', '4', 'S');


SET search_path = tramite, pg_catalog;

--
-- TOC entry 1573 (class 0 OID 8241614)
-- Dependencies: 1249
-- Data for Name: personal; Type: TABLE DATA; Schema: tramite; Owner: postgres
--

INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES (6, '01', 'MARTHA QUEVEDO', 'SECRETARIA', 'mquevedo', NULL, NULL, 2, NULL);
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES (7, '01', 'SONIA SALIROSAS', 'SECRETARIA', 'ssalirosas', NULL, NULL, 14, NULL);
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES (9, '01', 'FERNANDO ORTEGA', 'SECRETARIO', 'fortega', NULL, NULL, 2, NULL);
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES (10, '01', 'AUGUSTO MELLADO', 'PRESIDENTE', 'amellado', NULL, NULL, 14, NULL);
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES (11, '01', 'JORGE DEL CARPIO', 'DIRECTOR', 'jdelcarpio', NULL, NULL, 5, NULL);
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES (12, '01', 'ANDREA COICCA', 'SECRETARIA', 'acoicca', NULL, NULL, 8, NULL);
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES (5, '01', 'RICARDO RODRIGUEZ', 'RESPONSABLE MESA DE PARTES', 'rrodriguez', NULL, NULL, 1, NULL);
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES (8, '01', 'YESSIE FERNANDEZ', 'SECRETARIA', 'yfernandez', NULL, NULL, 5, NULL);
INSERT INTO personal (codigo_personal, codigo_fondo, nombre_personal, cargo_personal, c_usuario, grado_personal, es_usuario, codigo_oficina, es_responsable) VALUES (13, '01', 'GERARD CHAPELLE', 'DIRECTOR', 'gchapelle', NULL, NULL, 8, NULL);


-- Completed on 2008-09-22 17:59:21 Hora est. del Pacífico de SA

--
-- PostgreSQL database dump complete
--

