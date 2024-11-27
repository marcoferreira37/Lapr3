CREATE OR REPLACE FUNCTION inserir_receita (
    p_id_receita NUMBER,
    p_designacao SYS.ODCIVARCHAR2LIST,
    p_fabricante SYS.ODCIVARCHAR2LIST,
    p_quantidade SYS.ODCINUMBERLIST,
    p_unidade SYS.ODCIVARCHAR2LIST
) RETURN VARCHAR2
IS
BEGIN
    -- Iniciar transação
BEGIN
        -- Inserir receita
INSERT INTO Receita (idReceita) VALUES (p_id_receita);

-- Loop através dos fatores de produção
FOR i IN 1..p_designacao.COUNT
        LOOP
            -- Verificar se o fator de produção existe
            DECLARE
v_count NUMBER;
BEGIN
SELECT COUNT(*) INTO v_count
FROM Fator_Producao
WHERE designacao = p_designacao(i);

IF v_count = 0 THEN
                    -- Rollback e retornar erro se o fator de produção não existe
                    RAISE_APPLICATION_ERROR(-20001, 'Erro: Fator de Produção ' || p_designacao(i) || ' não existe.');
END IF;
END;

            -- Inserir na tabela Fator_Producao_Receita
INSERT INTO Fator_Producao_Receita (designacao, idReceita, quantidade, unidade)
VALUES (p_designacao(i), p_id_receita, p_quantidade(i), p_unidade(i));

-- Verificar se a quantidade é maior que 0
IF p_quantidade(i) <= 0 THEN
                -- Rollback e retornar erro se a quantidade é menor ou igual a 0
                RAISE_APPLICATION_ERROR(-20001, 'Erro: A quantidade deve ser maior que 0 para ' || p_designacao(i));
END IF;
END LOOP;

        -- Commit se todas as verificações passarem
COMMIT;
EXCEPTION
        WHEN OTHERS THEN
            -- Rollback em caso de erro não tratado
            ROLLBACK;
            RAISE;
END;

    -- Retorna sucesso
RETURN 'Sucesso: Receita inserida com sucesso.';
END;
/


DECLARE
result VARCHAR2(255);

    -- Parâmetros para a função
    p_id_receita NUMBER := 50;
    p_designacao SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST('Techiferti MOL', 'Kiplant AllGrip', 'soluSOP 52');
    p_fabricante SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST('Techiferti', 'Asfertglobal', 'K+S');
    p_quantidade SYS.ODCINUMBERLIST := SYS.ODCINUMBERLIST(60.0, 2.0, 2.5);
    p_unidade SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST('l/ha', 'l/ha', 'kg/ha');
BEGIN
    -- Chamada da função
    result := inserir_receita(p_id_receita, p_designacao, p_fabricante, p_quantidade, p_unidade);

    -- Exibição do resultado
    DBMS_OUTPUT.PUT_LINE(result);
END;
/


SELECT * FROM Receita;
SELECT * FROM Fator_Producao_Receita;

