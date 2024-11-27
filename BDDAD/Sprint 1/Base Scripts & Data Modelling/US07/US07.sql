SELECT OPP.id, OP.operacao,
       COUNT(DISTINCT OPP.idOperacao) AS NumeroDeOperacoes

FROM Operacoes_Parcela OPP
         JOIN Operacoes OP ON OPP.idOperacao = OP.idOperacao
         JOIN Parcela P ON OPP.id = P.id
WHERE OP.data BETWEEN TO_DATE( '18/12/2021' , 'dd/mm/yyyy') AND TO_DATE( '20/09/2022' , 'dd/mm/yyyy')
GROUP BY OPP.id , OP.operacao