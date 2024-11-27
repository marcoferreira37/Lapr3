SELECT DISTINCT CP.id AS id,
                FP.designacao AS fator_producao,
                SF.designacao AS substancia,
                SFT.perc AS quantidade,
                OP.data AS data_operacao
FROM Cultura_Parcela CP
         JOIN Operacoes OP ON CP.id = OP.id
         JOIN Fator_Producao FP ON OP.designacao = FP.designacao
         JOIN Subtancia_Ficha_Tecnica SFT ON FP.idFicha = SFT.idFicha
         JOIN Substancia SF ON SFT.designacao = SF.designacao
WHERE OP.data BETWEEN TO_DATE('01/01/2019', 'dd-mm-yyyy') AND TO_DATE('06/07/2023' , 'dd-mm-yyyy')
  AND CP.id=105

ORDER BY CP.id, FP.designacao, SF.designacao, SFT.perc, OP.data;