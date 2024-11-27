SELECT tfp.designacao as Tipo_Fator_Producao, COUNT(o.operacoes_id) as numeroDeAplicacoes
FROM Operacoes o
         JOIN FatorProducao f ON o.fatorProducao_id = f.fatorProducao_id
         JOIN TipoFatorProducao tfp ON f.tipoFatorProducao_id = tfp.tipoFatorProducao_id
WHERE o.data >= TO_DATE('2016-01-01', 'YYYY-MM-DD')
  AND o.data <= TO_DATE('2022-10-26', 'YYYY-MM-DD') -- Substitua com o intervalo de tempo desejado
GROUP BY tfp.designacao;
-- Modelo 1190538

-- Modelo Atual (inserts2.sql)
SELECT FP.designacao, O.operacao, FP.aplicacao, COUNT(*) AS numero_de_aplicacoes
FROM Operacoes O
JOIN Fator_Producao FP ON O.designacao = FP.designacao
WHERE O.data BETWEEN TO_DATE('01/01/2016', 'dd/mm/yyyy') AND TO_DATE('31/12/2023', 'dd/mm/yyyy')
GROUP BY FP.aplicacao, FP.designacao, O.operacao
ORDER BY COUNT(*) DESC;


-- Output devolvido pela query sql no mysql do oracle:

--DESIGNACAO	            | OPERACAO	            |APLICACAO	 |      NUMERO_DE_APLICACOES|
--Calda Bordalesa ASCENZA	| Aplicação  fitofármaco|	Fungicida|	           10         |
--ESTA Kieserit	           | Fertilização           |	Adubo solo|         	6        |
--Patentkali	           |Fertilização           |	Adubo solo|         	6        |
--EPSO Microtop	          |Fertilização	           |Adubo foliar+Fertirrega|	2        |
--Biocal Composto	      |Fertilização 	       |Correção solo|           	1        |

---------------------------------------------------------------------
---------------------------------------------------------------------

