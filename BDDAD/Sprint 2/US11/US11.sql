CREATE OR REPLACE FUNCTION prcRegisterSemeadura(
    pData							Operacoes.data%type,
    pOperacao						Operacoes.operacao%type,
    pQuantidade						Operacoes.quantidade%type,
    pUnidade						Operacoes.unidade%type,
    pDesignacao						Operacoes.designacao%type,
    pIdCultura						Operacoes.idCultura%type,
    pId								Operacoes.id%type)

     RETURN Operacoes.idOperacao%type
       AS v_new_id Operacoes.idOperacao%type;
BEGIN
SELECT MAX(idOperacao) +1 INTO v_new_id FROM Operacoes;

INSERT INTO Operacoes(idOperacao,data,operacao,quantidade,unidade,designacao,idCultura,id)
VALUES (v_new_id, pData,pOperacao,pQuantidade,pUnidade,pDesignacao,pIdCultura,pId);

INSERT INTO Semeadura(idOperacao)
VALUES (v_new_id);

COMMIT;

RETURN v_new_id;

EXCEPTION
    WHEN OTHERS THEN
        -- Tratamento de erro, se necessário
        ROLLBACK;
RETURN -1;
END;


-- Bloco anónimo para testar
/
set serveroutput on;

DECLARE
v_data          Operacoes.data%type := TO_DATE('20-10-2022', 'DD-MM-YYYY');
	v_operacao		Operacoes.operacao%type := 'semeadura';
    v_quantidade    Operacoes.quantidade%type := 600;
    v_unidade       Operacoes.unidade%type := 'un';
    v_designacao    Operacoes.designacao%type := 'ESTA Kieserit';
	v_idCultura		Operacoes.idCultura%type := 12;
	v_id			Operacoes.id%type := 102;
    v_result        Operacoes.idOperacao%type;

BEGIN

    -- Call the function
    v_result := prcRegisterSemeadura(v_data, v_operacao, v_quantidade, v_unidade, v_designacao, v_idCultura, v_id);

    -- Check the result
    IF v_result = -1 THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred. Rollback executed.');
ELSE
        DBMS_OUTPUT.PUT_LINE('Operation inserted successfully. OperacaoID: ' || v_result);
END IF;

END;
/