CREATE OR REPLACE FUNCTION fncUS14(
    v_operacao IN Operacoes.operacao%TYPE,
    v_data IN Operacoes.data%TYPE,
    v_quantidade IN Operacoes.quantidade%TYPE,
    v_unidade IN Operacoes.unidade%TYPE,
    v_designacao IN Operacoes.designacao%TYPE,
    v_idCultura IN Operacoes.idCultura%TYPE,
    v_id IN Operacoes.id%TYPE,
    v_modo IN Aplicacao_Fator_Producao.modo%TYPE,
    v_area IN Aplicacao_Fator_Producao.area%TYPE,
    v_unidades IN Aplicacao_Fator_Producao.unidades%TYPE)

    RETURN Operacoes.idOperacao%TYPE

    AS v_idOperacao Operacoes.idOperacao%TYPE;
BEGIN
SELECT MAX(idOperacao) + 1 INTO v_idOperacao FROM Operacoes;

INSERT INTO Operacoes (idOperacao, operacao, data, quantidade, unidade, designacao, idCultura, id)
VALUES (v_idOperacao, v_operacao, v_data, v_quantidade, v_unidade, v_designacao, v_idCultura, v_id);

INSERT INTO Aplicacao_Fator_Producao (idOperacao, modo , area, unidades)
VALUES (v_idOperacao, v_modo, v_area, v_unidades);


COMMIT;
RETURN v_idOperacao;

EXCEPTION
        WHEN OTHERS THEN
            -- Tratamento de erro, se necessário
            ROLLBACK;
RETURN -1;
END;


-- Bloco anônimo para testar
/
SET SERVEROUTPUT ON;

DECLARE
v_operacao Operacoes.operacao%TYPE := 'Aplicacao_Fator_Producao';
    v_data Operacoes.data%TYPE := TO_DATE('20-10-2022', 'DD-MM-YYYY');
    v_quantidade Operacoes.quantidade%TYPE := 600;
    v_unidade Operacoes.unidade%TYPE := 'un';
    v_designacao Operacoes.designacao%TYPE := 'BIOFERTIL N6';
    v_idCultura Operacoes.idCultura%TYPE := 34;
    v_id Operacoes.id%TYPE := 108;
    v_modo Aplicacao_Fator_Producao.modo%TYPE := 'Solo';
    v_area Aplicacao_Fator_Producao.area%TYPE := 1.3;
    v_unidades Aplicacao_Fator_Producao.unidades%TYPE := 'ha';

    v_result Operacoes.idOperacao%TYPE;

BEGIN
    -- Chamar a função
    v_result := fncUS14(v_operacao,v_data, v_quantidade, v_unidade, v_designacao, v_idCultura, v_id, v_modo, v_area, v_unidades);

    -- Verificar o resultado
    IF v_result = -1 THEN
        DBMS_OUTPUT.PUT_LINE('Ocorreu um erro. Rollback executado.');
ELSE
        DBMS_OUTPUT.PUT_LINE('Operação inserida com sucesso. ID da Operação: ' || v_result);
END IF;
END;
/