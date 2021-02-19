
--CREATE SEQUENCE SEQ_MATERIAL start with 1 increment by 1 minvalue 1 maxvalue 999999999 cycle;


DELETE FROM STATUS
DELETE FROM TIPO
DELETE FROM UNIDADE

INSERT INTO STATUS (NOME) VALUES ('ATIVO')
INSERT INTO STATUS (NOME) VALUES ('DEFEITUOSO')
INSERT INTO STATUS (NOME) VALUES ('EXTRAVIADO')
INSERT INTO STATUS (NOME) VALUES ('BAIXADO')

INSERT INTO TIPO (NOME) VALUES ('PEÇA_REPOSIÇÃO')
INSERT INTO TIPO (NOME) VALUES ('SUPRIMENTO')

INSERT INTO UNIDADE (CD, SIGLA, DESCRICAO) VALUES ( 174, 'SEDSA', 'DESENVOLVIMENTO DE SISTEMAS' )
INSERT INTO UNIDADE (CD, SIGLA, DESCRICAO) VALUES ( 140,  'SEVUE', 'VONTAÇÃO' )

INSERT INTO MATERIAL (ID , CODIGOBARRAS, DESCRICAO, MODELO, NOME, STATUS_ID, TEM_DEVOLUCAO, TIPO_ID ) VALUES( SEQ_MATERIAL.nextval, '111111111', 'TESTE INSERT', 'MODELO INSERT ', 'BATERIA', 1, TRUE, 2 )


INSERT INTO MATERIAL (ID , CODIGOBARRAS, DESCRICAO, MODELO, NOME, STATUS_ID, TEM_DEVOLUCAO, TIPO_ID ) VALUES( SEQ_MATERIAL.nextval,'222222222', 'TESTE INSERT', 'MODELO INSERT ', 'FLASH CARD', 1, TRUE, 2 )

INSERT INTO MATERIAL (ID , CODIGOBARRAS, DESCRICAO, MODELO, NOME, STATUS_ID, TEM_DEVOLUCAO, TIPO_ID ) VALUES( SEQ_MATERIAL.nextval,'333333333', 'TESTE INSERT', 'MODELO INSERT ', 'MR', 1, TRUE, 2 )

INSERT INTO MATERIAL (ID , DESCRICAO, MODELO, NOME, STATUS_ID, TEM_DEVOLUCAO, TIPO_ID ) VALUES(SEQ_MATERIAL.nextval,  'TESTE INSERT', 'MODELO INSERT ', 'JACARÉ', 1, TRUE, 2 )

INSERT INTO MATERIAL (ID , DESCRICAO, MODELO, NOME, STATUS_ID, TEM_DEVOLUCAO, TIPO_ID ) VALUES( SEQ_MATERIAL.nextval, 'TESTE INSERT', 'MODELO INSERT ', 'FASTON', 1, TRUE, 2 )

INSERT INTO MATERIAL (ID , DESCRICAO, MODELO, NOME, STATUS_ID, TEM_DEVOLUCAO, TIPO_ID ) VALUES(SEQ_MATERIAL.nextval,  'TESTE INSERT', 'MODELO INSERT ', 'BOBINA', 1, TRUE, 2 )

INSERT INTO MATERIAL (ID ,DESCRICAO, MODELO, NOME, STATUS_ID, TEM_DEVOLUCAO, TIPO_ID ) VALUES( SEQ_MATERIAL.nextval, 'TESTE INSERT', 'MODELO INSERT ', 'MALETAS', 1, TRUE, 2 )

