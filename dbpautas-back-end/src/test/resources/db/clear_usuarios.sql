DELETE FROM usuarios WHERE EXISTS (SELECT 1 FROM usuarios);