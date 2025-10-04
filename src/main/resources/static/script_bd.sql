CREATE TABLE IF NOT EXISTS public.categorias
(
    id SERIAL PRIMARY KEY,
    nombre TEXT NOT NULL
);

INSERT INTO public.categorias(categoria) VALUES ('Juvenil', true);
INSERT INTO public.categorias(categoria) VALUES ('Terror', true);
INSERT INTO public.categorias(categoria) VALUES ('Ficción', true);
INSERT INTO public.categorias(categoria) VALUES ('Novela', true);
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