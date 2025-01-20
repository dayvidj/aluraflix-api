ALTER TABLE videos
ADD COLUMN categoria_id BIGINT;

ALTER TABLE videos
ADD CONSTRAINT fk_categoria
FOREIGN KEY (categoria_id) REFERENCES categorias(id);