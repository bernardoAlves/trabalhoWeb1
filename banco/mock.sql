
CREATE TABLE usuario(idUsuario SERIAL, nome VARCHAR(30), senha VARCHAR(30), PRIMARY KEY(idUsuario));

INSERT INTO usuario (nome, senha) VALUES
('AnaSilva', 'senhaAna123'),
('BrunoCosta', 'bruno@2026'),
('CarlosSouza', 'carlosS456'),
('DanielaLima', 'dani#lima7'),
('EduardoRocha', 'eduRocha99'),
('FernandaAlves', 'fe_alves20'),
('GabrielCruz', 'gabrielC77'),
('HeloísaReis', 'heloReis01'),
('IgorMiranda', 'igorM!8899'),
('JulianaMelo', 'juMelo_321');

CREATE TABLE topico(idTopico SERIAL, titulo VARCHAR, autor int, dataTopico TIMESTAMP, corpoTopico VARCHAR,
resolvido boolean, PRIMARY KEY(idTopico), FOREIGN KEY(autor) REFERENCES usuario(idUsuario));


INSERT INTO topico (titulo, autor, dataTopico, corpoTopico, resolvido) VALUES
('Erro ao conectar no banco', 1, current_timestamp, 'Estou recebendo timeout ao tentar conectar no PostgreSQL.', true),
('Melhor framework frontend em 2026', 2, current_timestamp, 'Qual framework vocês recomendam para um projeto SPA este ano?', false),
('Dúvida em Git Rebase', 3, current_timestamp, 'Alguém pode me explicar a diferença real entre Merge e Rebase?', true),
('Como centralizar uma div?', 1, current_timestamp, 'Brincadeiras à parte, qual a forma mais moderna usando Flexbox?', true),
('API REST vs GraphQL', 5, current_timestamp, 'Vale a pena migrar uma API legada para GraphQL hoje em dia?', false),
('deploy na AWS dando erro', 4, current_timestamp, 'Minha instância EC2 não está respondendo na porta 8080.', false),
('Livros de arquitetura de software', 7, current_timestamp, 'Quais livros além do Clean Architecture vocês recomendam?', true),
('Performance de queries SQL', 8, current_timestamp, 'Como identificar qual index está faltando em uma tabela grande?', false),
('Aprender Python ou Go?', 9, current_timestamp, 'Para desenvolvimento de microsserviços, qual performa melhor?', false),
('Dark mode com Tailwind', 10, current_timestamp, 'Como sincronizar o dark mode do Tailwind com a preferência do sistema?', true);

CREATE TABLE comentario(idComentario SERIAL, autor int, topico int, dataComentario TIMESTAMP, corpoComentario VARCHAR,
PRIMARY KEY(idComentario), FOREIGN KEY(autor) REFERENCES usuario(idUsuario), FOREIGN KEY(topico) REFERENCES topico(idTopico));


INSERT INTO comentario (autor, topico, dataComentario, corpoComentario) VALUES
(2, 1, CURRENT_TIMESTAMP, 'Verificou se as credenciais no arquivo .env estão corretas?'),
(1, 1, CURRENT_TIMESTAMP, 'Era isso mesmo! Tinha um espaço em branco na senha. Obrigado!'),
(5, 2, CURRENT_TIMESTAMP, 'Eu iria de React ou Vue, o ecossistema deles continua gigante.'),
(3, 2, CURRENT_TIMESTAMP, 'Se quiser algo mais performático, dá uma olhada no Svelte.'),
(6, 3, CURRENT_TIMESTAMP, 'O rebase reescreve o histórico, deixa o log bem mais limpo.'),
(1, 3, CURRENT_TIMESTAMP, 'Boa! Entendi perfeitamente agora.'),
(8, 4, CURRENT_TIMESTAMP, 'display: flex; justify-content: center; align-items: center; não tem erro!'),
(4, 6, CURRENT_TIMESTAMP, 'Dá uma olhada no seu Security Group na AWS, pode ser porta fechada.'),
(2, 9, CURRENT_TIMESTAMP, 'Para microsserviços pesados e concorrência, Go é imbatível.'),
(10, 9, CURRENT_TIMESTAMP, 'Python é ótimo se você for trabalhar com IA ou ciência de dados junto.');