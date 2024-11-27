CREATE OR REPLACE FUNCTION fncObterSubstanciasDeFatoresDeProducaoNaoUtilizadas(
    p_anoIndicado IN NUMBER
) RETURN SYS_REFCURSOR AS
    subsNaoUtilizadas SYS_REFCURSOR;
BEGIN
OPEN subsNaoUtilizadas FOR
SELECT DISTINCT
    fp.designacao
FROM
    Fator_Producao fp
WHERE
  -- O FATOR DE PRODUCAO NAO É USADO NAQUELE ANO
        fp.designacao NOT IN (
        -- OPERACOES FERTILIZACAO E FITOFARMACO NO ANO INDICADO
        SELECT DISTINCT
            fp.designacao
        FROM
            Fator_Producao_Receita fpr
                JOIN Receita r ON fpr.idReceita = r.idReceita
                JOIN Operacoes o ON o.idOperacao = r.idReceita -- Correção nesta linha
        WHERE
                EXTRACT(YEAR FROM o.data) = p_anoIndicado
    )
  -- E O FATOR DE PRODUCAO É USADO EM OUTROS ANOS
  AND fp.designacao IN (
    -- OPERACOES FERTILIZACAO E FITOFARMACO EM OUTROS ANOS
    SELECT DISTINCT
        fp.designacao
    FROM
        Fator_Producao_Receita fpr
            JOIN Receita r ON fpr.idReceita = r.idReceita
            JOIN Operacoes o ON o.idOperacao = r.idReceita -- Correção nesta linha
    WHERE
        EXTRACT(YEAR FROM o.data) != p_anoIndicado
    );

RETURN subsNaoUtilizadas;
END;
/

DECLARE
cursorSubsNaoUtilizadas SYS_REFCURSOR;
    designacao VARCHAR2(50);
BEGIN 
    cursorSubsNaoUtilizadas := fncObterSubstanciasDeFatoresDeProducaoNaoUtilizadas(2023);  
 
    LOOP
FETCH cursorSubsNaoUtilizadas INTO designacao;
        EXIT WHEN cursorSubsNaoUtilizadas%NOTFOUND; 
        
        -- Faça o que for necessário com os dados recuperados 
        DBMS_OUTPUT.PUT_LINE('Designação: ' || designacao);
END LOOP;

CLOSE cursorSubsNaoUtilizadas;
END;
/