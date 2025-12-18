-- V2: Migrations para adicionar a coluna de Rank na tabela de cadastros

ALTER TABLE tb_cadastro
ADD COLUMN rank VARCHAR(255);