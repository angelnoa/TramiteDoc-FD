--
-- PostgreSQL database dump
--

-- Started on 2008-09-13 17:35:39 Hora est. del Pacífico de SA

SET client_encoding = 'SQL_ASCII';
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 1572 (class 0 OID 8139040)
-- Dependencies: 1221
-- Data for Name: oficinas; Type: TABLE DATA; Schema: public; Owner: postgres
--



SET search_path = tramite, pg_catalog;

--
-- TOC entry 1573 (class 0 OID 8157878)
-- Dependencies: 1240
-- Data for Name: oficinas; Type: TABLE DATA; Schema: tramite; Owner: postgres
--

INSERT INTO oficinas (codigo_oficina, codigo_fondo, nombre_corto, fondo, oficina, descripcion_oficina, siglas_oficina, doc_entrada, doc_salida, doc_interno, es_activo, modalidad_salida, nivel, cod_dpto, cod_prov, cod_dist) VALUES ('0001', '01', 'MESA  ', '01', '0001', 'MESA DE PARTES', 'MESAPART', 'S', 'S', 'S', 'S', '3', '2', NULL, NULL, NULL);
INSERT INTO oficinas (codigo_oficina, codigo_fondo, nombre_corto, fondo, oficina, descripcion_oficina, siglas_oficina, doc_entrada, doc_salida, doc_interno, es_activo, modalidad_salida, nivel, cod_dpto, cod_prov, cod_dist) VALUES ('0002', '01', 'SECR  ', '01', '0002', 'SECRETARIA', 'SECRE', 'N', 'S', 'S', 'S', '1', '2', NULL, NULL, NULL);
INSERT INTO oficinas (codigo_oficina, codigo_fondo, nombre_corto, fondo, oficina, descripcion_oficina, siglas_oficina, doc_entrada, doc_salida, doc_interno, es_activo, modalidad_salida, nivel, cod_dpto, cod_prov, cod_dist) VALUES ('0003', '01', 'PRES  ', '01', '0003', 'PRESIDENCIA', 'PRESI', 'N', 'S', 'S', 'S', '1', '2', NULL, NULL, NULL);


-- Completed on 2008-09-13 17:35:39 Hora est. del Pacífico de SA

--
-- PostgreSQL database dump complete
--

