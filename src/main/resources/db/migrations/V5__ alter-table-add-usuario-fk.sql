ALTER TABLE livros
ADD usuario_id NUMBER;

ALTER TABLE livros
ADD CONSTRAINT fk_livros_usuario
FOREIGN KEY (usuario_id) REFERENCES usuarios(id);
