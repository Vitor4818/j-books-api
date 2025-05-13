-- 1. Criar a tabela status_livro
CREATE TABLE status_livro (
    status_id INT PRIMARY KEY,
    nome_status VARCHAR(50) NOT NULL
);

-- 2. Inserir os dados
INSERT ALL
  INTO status_livro (status_id, nome_status) VALUES (1, 'Lido')
  INTO status_livro (status_id, nome_status) VALUES (2, 'Lendo')
  INTO status_livro (status_id, nome_status) VALUES (3, 'Na fila')
SELECT * FROM dual;



ALTER TABLE livros
ADD status_id NUMBER;


ALTER TABLE livros
ADD CONSTRAINT fk_status_livro
FOREIGN KEY (status_id)
REFERENCES status_livro(status_id);