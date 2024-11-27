SELECT
    p.designacao AS Parcela,
    COUNT(op.idOperacao) AS NumeroDeOperacoesDeRega
FROM
    Parcela p
JOIN
    Operacoes_Parcela op ON p.id = op.id
JOIN
    Operacoes o ON op.idOperacao = o.idOperacao
WHERE
    o.operacao = 'Rega'
    AND O.data BETWEEN TO_DATE('01/01/2010', 'dd/mm/yyyy') AND TO_DATE('31/12/2030', 'dd/mm/yyyy')
GROUP BY
    p.designacao
ORDER BY
    NumeroDeOperacoesDeRega DESC
FETCH FIRST 1 ROWS ONLY;