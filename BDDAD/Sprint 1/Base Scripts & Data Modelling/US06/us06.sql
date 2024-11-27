SELECT FPP.id,
       C.classificacao,
       COUNT(DISTINCT FPP.designacao) AS NumeroDeFatores
FROM Fator_Producao_Parcela FPP
         JOIN Classificacao C ON classificacao = C.classificacao
         JOIN Operacoes OP ON id = FPP.id
WHERE OP.data BETWEEN TO_DATE('01/01/2022', 'dd/mm/yyyy') AND TO_DATE('31/12/2023', 'dd/mm/yyyy')
GROUP BY FPP.id, C.classificacao
