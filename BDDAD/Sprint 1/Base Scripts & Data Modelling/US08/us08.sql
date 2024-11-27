SELECT fp.designacao AS "ProdutoNomeComercial", MAX(COUNT(fp.designacao)) AS "Número de aplicações"
FROM  Operacoes op INNER JOIN Fator_Producao fp ON op.designacao = fp.designacao
WHERE op.data BETWEEN TO_DATE('01/01/2016', 'dd/mm/yyyy') AND TO_DATE('31/12/2023', 'dd/mm/yyyy')
GROUP BY fp.designacao
ORDER BY "Número de aplicações" DESC
