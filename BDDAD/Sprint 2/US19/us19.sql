SELECT DISTINCT CP.id AS id_parcela,
                C.tipo AS tipo_cultura,
                FP.designacao AS fator_producao,
                EF.componente_quimico AS substancia,
                EFT.perc AS quantidade,
                OP.data AS data_operacao

FROM Cultura_Parcela CP

         LEFT JOIN Cultura C ON CP.idCultura = C.idCultura

         JOIN Cultura_Parcela CP ON CP.id = CP.id

         JOIN Operacoes OP ON OP.idcultura = OP.idCultura

         JOIN Fator_Producao FP ON OP.designacao = FP.designacao

         JOIN Elemento_Ficha_Tecnica EFT ON FP.idFicha = EFT.idFicha

         JOIN Elemento EF ON EFT.componente_quimico = EF.componente_quimico

WHERE CP.id = 108
  AND OP.data BETWEEN TO_DATE('01/01/2019', 'dd-mm-yyyy') AND TO_DATE('06/07/2023' , 'dd-mm-yyyy')

ORDER BY CP.id, FP.designacao, EF.componente_quimico, EFT.perc, OP.data;