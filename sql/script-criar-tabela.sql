DROP TABLE IF EXISTS CLISER CASCADE;

CREATE TABLE CLISER (
	CS_ID SERIAL,
	CS_NOME VARCHAR(140),
	CS_MENSAGEM VARCHAR(1000),
	CONSTRAINT PK_CLISER PRIMARY KEY (CS_ID)
);