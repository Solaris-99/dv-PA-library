-- Employee
INSERT INTO user (name, surname, email, password, dni) VALUES("Pepe","Perez", "pepe@gmail.com","$2a$10$qA79t0u8iQwGzMnwOy1QH.5.QgiWZVuz4dxl38UVoPoSHCbAKfE5y",1234567); -- pass: 1234
INSERT INTO employee (id_user, salary) VALUES(1,800);
-- End user
INSERT INTO user (name, surname, email, password, dni) VALUES("Juan","Martinez", "jmartinez@gmail.com","$2a$10$qA79t0u8iQwGzMnwOy1QH.5.QgiWZVuz4dxl38UVoPoSHCbAKfE5y",1234567); -- pass: 1234

-- Insert authors
INSERT INTO author (name) VALUES
('Jorge Luis Borges'),
('Julio Cortázar'),
('Gabriel García Márquez'),
('Isabel Allende'),
('Mario Vargas Llosa');

-- Insert publishers
INSERT INTO publisher (name) VALUES
('Editorial Sudamericana'),
('Alfaguara'),
('Planeta'),
('Penguin Random House'),
('Siglo XXI Editores');

-- Insert books
INSERT INTO book (title, year, id_author, id_publisher, total_copies, available_copies) VALUES
('El Aleph', 1949, 1, 1, 10, 10),
('Ficciones', 1944, 1, 2, 8, 8),
('Rayuela', 1963, 2, 3, 12, 12),
('Cien años de soledad', 1967, 3, 2, 15, 15),
('El amor en los tiempos del cólera', 1985, 3, 4, 10, 10),
('La casa de los espíritus', 1982, 4, 5, 14, 14),
('Paula', 1994, 4, 3, 9, 9),
('La ciudad y los perros', 1963, 5, 1, 11, 11),
('La fiesta del chivo', 2000, 5, 2, 13, 13);