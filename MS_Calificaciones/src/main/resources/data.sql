INSERT IGNORE INTO calificaciones (nota_id, estudiante_id, asignatura_codigo, periodo_academico, valor_nota, ponderacion, es_nota_final, tipo_evaluacion) 
VALUES 
(1, '12345678-9', 'APY3141', '2026-1', 5.8, 0.25, false, 'SOLEMNE'),
(2, '12345678-9', 'APY3141', '2026-1', 6.2, 0.15, false, 'TALLER'),
(3, '12345678-9', 'APY3141', '2026-1', 6.0, 0.40, true, 'NOTA_FINAL'),
(4, '98765432-1', 'MAT4120', '2026-1', 3.5, 0.30, false, 'CONTROL');