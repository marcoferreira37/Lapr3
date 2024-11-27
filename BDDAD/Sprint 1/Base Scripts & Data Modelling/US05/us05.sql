SELECT C.cultura AS "Produto", SUM(O.quantidade) AS "Quantidade"
FROM Operacoes_Parcela OP
JOIN Operacoes O ON OP.idOperacao = O.idOperacao
JOIN Operacoes_Cultura OC ON O.idOperacao = OC.idOperacao
JOIN Cultura C on OC.idCultura = C.idCultura
WHERE OP.id = 103
  AND O.data BETWEEN TO_DATE('01/01/2010', 'dd/mm/yyyy') AND TO_DATE('31/12/2030', 'dd/mm/yyyy')
  AND O.operacao ='Colheita'

GROUP BY C.cultura
