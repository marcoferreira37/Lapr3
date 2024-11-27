SELECT CP.id AS id_parcela,
       OP.operacao AS tipo_operacao,
       OP.data AS data_operacao
FROM Cultura_Parcela CP
         JOIN Operacoes OP ON CP.id = OP.id
WHERE CP.id = 105
  AND OP.data BETWEEN TO_DATE('01-01-2022', 'DD-MM-YYYY') AND TO_DATE('31-12-2023', 'DD-MM-YYYY')
ORDER BY CP.id, OP.operacao, OP.data;
