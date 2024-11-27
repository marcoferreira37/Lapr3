SELECT PL.especie AS Especie,
       P.nomeComercial AS Produto_Colhido
FROM Operacoes OP
         JOIN Colheita CL ON CL.idOperacao = OP.idOperacao
         JOIN Cultura C ON OP.idCultura = C.idCultura
         JOIN Produto P ON P.idCultura = OP.idCultura
         JOIN Plantas PL ON C.idCultura = PL.idCultura
WHERE OP.id = 108
  AND OP.data >= TO_DATE('20/05/2023', 'DD-MM-YYYY')
  AND OP.data <= TO_DATE('06/11/2023', 'DD-MM-YYYY')
GROUP BY PL.especie, P.nomeComercial;