-- ============================================================
-- USUÁRIOS E ROLES
-- ============================================================

INSERT INTO usuarios (id, username, password)
VALUES (1, 'admin', 'admin');

INSERT INTO usuarios (id, username, password)
VALUES (2, 'user', 'user');

-- Roles
INSERT INTO roles (id, role)
VALUES (1, 'ADMIN');

INSERT INTO roles (id, role)
VALUES (2, 'USER');

-- Associação usuário → role
INSERT INTO usuario_roles (user_id, role_id)
VALUES (1, 1);

INSERT INTO usuario_roles (user_id, role_id)
VALUES (2, 2);

-- ============================================================
-- AUTORES
-- ============================================================

INSERT INTO autores (id, nome, email, biografia, dataNascimento)
VALUES (1, 'Machado de Assis', 'machado@literatura.com', 'Autor de Dom Casmurro e Memórias Póstumas de Brás Cubas', '1839-06-21');

INSERT INTO autores (id, nome, email, biografia, dataNascimento)
VALUES (2, 'Jane Austen', 'austen@literatura.com', 'Autora de Orgulho e Preconceito e Razão e Sensibilidade', '1775-12-16');

INSERT INTO autores (id, nome, email, biografia, dataNascimento)
VALUES (3, 'George Orwell', 'orwell@literatura.com', 'Autor de 1984 e A Revolução dos Bichos', '1903-06-25');

INSERT INTO autores (id, nome, email, biografia, dataNascimento)
VALUES (4, 'J.K. Rowling', 'rowling@literatura.com', 'Autora da série Harry Potter', '1965-07-31');

INSERT INTO autores (id, nome, email, biografia, dataNascimento)
VALUES (5, 'J.R.R. Tolkien', 'tolkien@literatura.com', 'Autor de O Senhor dos Anéis e O Hobbit', '1892-01-03');

INSERT INTO autores (id, nome, email, biografia, dataNascimento)
VALUES (6, 'Clarice Lispector', 'clarice@literatura.com', 'Autora de A Hora da Estrela e Laços de Família', '1920-12-10');

-- ============================================================
-- LIVROS
-- ============================================================

INSERT INTO livros (id, titulo, isbn, numeroPaginas, dataPublicacao, disponivel, autor_id)
VALUES (1, 'Dom Casmurro', '9788535914849', 256, '1899-01-01', TRUE, 1);

INSERT INTO livros (id, titulo, isbn, numeroPaginas, dataPublicacao, disponivel, autor_id)
VALUES (2, 'Orgulho e Preconceito', '9780141439518', 279, '1813-01-28', TRUE, 2);

INSERT INTO livros (id, titulo, isbn, numeroPaginas, dataPublicacao, disponivel, autor_id)
VALUES (3, '1984', '9780451524935', 328, '1949-06-08', TRUE, 3);

INSERT INTO livros (id, titulo, isbn, numeroPaginas, dataPublicacao, disponivel, autor_id)
VALUES (4, 'Harry Potter e a Pedra Filosofal', '9780747532699', 309, '1997-06-26', TRUE, 4);

INSERT INTO livros (id, titulo, isbn, numeroPaginas, dataPublicacao, disponivel, autor_id)
VALUES (5, 'O Senhor dos Anéis: A Sociedade do Anel', '9780618640157', 423, '1954-07-29', FALSE, 5);

INSERT INTO livros (id, titulo, isbn, numeroPaginas, dataPublicacao, disponivel, autor_id)
VALUES (6, 'A Hora da Estrela', '9788520929977', 96, '1977-10-01', TRUE, 6);

-- ============================================================
-- EMPRÉSTIMOS
-- ============================================================

INSERT INTO emprestimos (id, nomeUsuario, emailUsuario, dataEmprestimo, dataDevolucaoPrevista, livro_id)
VALUES (1, 'Lucas Fortes', 'lucas@example.com', '2025-11-01', '2025-11-15', 1);

INSERT INTO emprestimos (id, nomeUsuario, emailUsuario, dataEmprestimo, dataDevolucaoPrevista, livro_id)
VALUES (2, 'Ana Souza', 'ana@example.com', '2025-11-02', '2025-11-16', 2);

INSERT INTO emprestimos (id, nomeUsuario, emailUsuario, dataEmprestimo, dataDevolucaoPrevista, livro_id)
VALUES (3, 'Carlos Mendes', 'carlos@example.com', '2025-11-03', '2025-11-17', 5);

INSERT INTO emprestimos (id, nomeUsuario, emailUsuario, dataEmprestimo, dataDevolucaoPrevista, livro_id)
VALUES (4, 'Mariana Alves', 'mariana@example.com', '2025-11-04', '2025-11-18', 3);

INSERT INTO emprestimos (id, nomeUsuario, emailUsuario, dataEmprestimo, dataDevolucaoPrevista, livro_id)
VALUES (5, 'João Pereira', 'joao@example.com', '2025-11-05', '2025-11-20', 4);
