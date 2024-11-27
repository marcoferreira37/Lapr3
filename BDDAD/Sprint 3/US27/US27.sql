CREATE OR REPLACE TRIGGER triggerLog_OperacaoProibirAlteracaoOuApagar
    BEFORE DELETE OR UPDATE ON LogOperacoes
    FOR EACH ROW
DECLARE
erro EXCEPTION;
BEGIN
    RAISE erro;

EXCEPTION
    WHEN erro THEN
        IF DELETING THEN
            RAISE_APPLICATION_ERROR(-20001, 'Não é permitido remover informação dos Logs');
        ELSIF UPDATING THEN
            RAISE_APPLICATION_ERROR(-20002, 'Não é permitido alterar a informação dos Logs');
END IF;
END;
/


--bloco anonimo
SET SERVEROUTPUT ON;
DECLARE
l_idOperacao NUMBER := 16;
    l_idLog NUMBER := 7;
    l_instante TIMESTAMP(0) := CURRENT_TIMESTAMP;
BEGIN

BEGIN
INSERT INTO LogOperacoes (idOperacao, instante, tipoOperacao, idLog)
VALUES (l_idOperacao, l_instante, 'Tipo1', l_idLog);
DBMS_OUTPUT.PUT_LINE('Operação inserida com sucesso');
EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('IDLog já existe, escolha um valor único');
END;


BEGIN
UPDATE LogOperacoes SET tipoOperacao = 'Tipo2' WHERE idOperacao = l_idOperacao AND idLog = l_idLog;
DBMS_OUTPUT.PUT_LINE('Operação alterada com sucesso');
EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Operação não pode ser alterada');
END;


BEGIN
DELETE FROM LogOperacoes WHERE idOperacao = l_idOperacao AND idLog = l_idLog;
DBMS_OUTPUT.PUT_LINE('Operação apagada com sucesso');
EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Operação não pode ser apagada');
END;


COMMIT;
END;
/