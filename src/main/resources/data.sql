
INSERT INTO USUARIO (DTYPE, ID, EMAIL, NOME, SENHA, SOBRENOME, PONTUACAO_DO_ALUNO) 
VALUES ('Usuario', 1, 'teste@gmail.com', 'Usuario',/*(12345)*/ '$2a$10$rUmx8yw4tF9JF0FsRh8wWepjOTD6HWF.1gA.kI/qjgGToWSbNsKN6', 'Teste', 100);

/* Palavras já cadastradas*/
INSERT INTO PALAVRA (ID, DEFINICAO, INGLES, PORTUGUES, TERMO_TECNICO)
VALUES (1, 'PALAVRA 1', 'HOUSE', 'CASA', 0),
(2, 'PALAVRA 2', 'IF', 'SE', 1),
(3, 'PALAVRA 3', 'WHILE', 'ENQUANTO', 0),
(4, 'PALAVRA 4', 'ELSE', 'SE NÃO', 1);

/* Conteúdos já cadastrados*/
INSERT INTO CONTEUDO (ID, TITULO)
VALUES (1, 'LOOPS'),
(2, 'ONDETABOU'),
(3, 'kk');

INSERT INTO PALAVRA_CONTEUDOS(PALAVRAS_ID, CONTEUDOS_ID)
VALUES (1, 3), (2, 3), (3, 3), (4, 1);