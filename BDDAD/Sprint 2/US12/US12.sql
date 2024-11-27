-- USBD12: Como Gestor Agrícola, quero registar uma operação de monda

CREATE OR REPLACE FUNCTION fncUS12( v_data IN Operacoes.data%type,
    								v_operacao IN Operacoes.operacao%type,
                                    v_quantidade IN Operacoes.quantidade%type,
                                    v_unidade IN Operacoes.unidade%type,
                                    v_designacao IN Operacoes.designacao%type,
    								v_idCultura IN Operacoes.idCultura%type,
    								v_id IN Operacoes.id%type)

    RETURN Operacoes.idOperacao%type

AS
    v_idOperacao Operacoes.idOperacao%type;

BEGIN
SELECT MAX(idOperacao) + 1 INTO v_idOperacao FROM Operacoes;

INSERT INTO Operacoes (idOperacao, operacao, data, quantidade, unidade, designacao, idCultura, id)
VALUES (v_idOperacao, v_operacao, v_data, v_quantidade, v_unidade, v_designacao, v_idCultura, v_id);

INSERT INTO Monda (idOperacao)
VALUES (v_idOperacao);

COMMIT;
return v_idOperacao;

EXCEPTION
    WHEN OTHERS THEN
        -- Tratamento de erro, se necessário
        ROLLBACK;
return -1;
end;

-- Bloco anónimo para testar
/
set serveroutput on;

DECLARE
v_data          Operacoes.data%type := TO_DATE('20-10-2022', 'DD-MM-YYYY');
	v_operacao		Operacoes.operacao%type := 'Monda';
    v_quantidade    Operacoes.quantidade%type := 600;
    v_unidade       Operacoes.unidade%type := 'un';
    v_designacao    Operacoes.designacao%type := 'ESTA Kieserit';
	v_idCultura		Operacoes.idCultura%type := 12;
	v_id			Operacoes.id%type := 102;
    v_result        Operacoes.idOperacao%type;

BEGIN

    -- Call the function
    v_result := fncUS12(v_data, v_operacao, v_quantidade, v_unidade, v_designacao, v_idCultura, v_id);

    -- Check the result
    IF v_result = -1 THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred. Rollback executed.');
ELSE
        DBMS_OUTPUT.PUT_LINE('Operation inserted successfully. OperacaoID: ' || v_result);
END IF;

END;
/