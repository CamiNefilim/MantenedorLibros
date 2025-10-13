CREATE TABLE IF NOT EXISTS public.categorias
(
    id SERIAL PRIMARY KEY,
    nombre TEXT NOT NULL
);

INSERT INTO public.categorias(nombre) VALUES ('Juvenil');
INSERT INTO public.categorias(nombre) VALUES ('Terror');
INSERT INTO public.categorias(nombre) VALUES ('Ficción');
INSERT INTO public.categorias(nombre) VALUES ('Novela');
COMMIT;

CREATE TABLE IF NOT EXISTS public.libros
(
    id SERIAL PRIMARY KEY,
    titulo TEXT NOT NULL,
    categoria_id INTEGER NOT NULL,
    anio_publicacion INTEGER,
    autor TEXT
    CONSTRAINT fk_libros_categorias FOREIGN KEY (categoria_id)
        REFERENCES public.categorias(id)
        ON DELETE RESTRICT
);

SELECT id, titulo, categoria_id, anio_publicacion, autor FROM public.libros;

CREATE TABLE IF NOT EXISTS perfiles (
    id SERIAL PRIMARY KEY,
    nombre TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    usuario TEXT NOT NULL,
    contrasena TEXT NOT NULL,
    perfil_id INTEGER NOT NULL REFERENCES perfiles(id)
);

INSERT INTO perfiles (nombre) VALUES ('ADMIN'), ('USER');

INSERT INTO usuarios (usuario, contrasena, perfil_id)
VALUES
('admin', '$2a$10$RUMhfFTFM0DfqwinLG3wJudAaqMLVexRea.Bai5Zhiqyydt/fo5bG', 1), -- contraseña "admin123"
('user', '$2a$10$IRQfGCFCF8IVfOuNgy52vOqDArxZYzNrHd2xgldjf1caemWf7xY36', 2);  -- contraseña "user123"

select * from usuarios;
