CREATE OR REPLACE TRIGGER us25BeforeOperacoes

  BEFORE INSERT ON Operacoes
  FOR EACH ROW

DECLARE
v_last_idOperacao Operacoes.idOperacao%TYPE;
  exception1 EXCEPTION;

BEGIN
  -- Obtém o último idOperacao
SELECT MAX(idOperacao) INTO v_last_idOperacao FROM Operacoes;

-- Atribui um novo idOperacao
:NEW.idOperacao := COALESCE(v_last_idOperacao, 0) + 1;

  IF (v_last_idOperacao - :NEW.idOperacao) <= 0 THEN
    RAISE exception1;
END IF;

EXCEPTION
  WHEN exception1 THEN
    -- Handle the exception by filling the gap
    :NEW.idOperacao := v_last_idOperacao + 1;

WHEN OTHERS THEN
    -- Handle other exceptions if necessary
    RAISE;

END us25BeforeOperacoes;





--bloco anonimo
SET SERVEROUTPUT ON;
DECLARE
v_last_idOperacao Operacoes.idOperacao%TYPE;
  v_new_idOperacao Operacoes.idOperacao%TYPE;
  exception1 EXCEPTION;

BEGIN
  -- Obtém o último idOperacao
SELECT MAX(idOperacao) INTO v_last_idOperacao FROM Operacoes;

-- Atribui um novo idOperacao
v_new_idOperacao := v_last_idOperacao + 1;

  -- Verifica se há uma lacuna na numeração de idOperacao
  IF v_last_idOperacao IS NOT NULL AND v_new_idOperacao <> v_last_idOperacao + 1 THEN
    RAISE exception1;
ELSE
    -- Operação bem-sucedida
    DBMS_OUTPUT.PUT_LINE('Operação bem-sucedida');
END IF;

EXCEPTION
  WHEN exception1 THEN
    RAISE_APPLICATION_ERROR(-20001, 'Há uma lacuna na numeração de idOperacao');

WHEN OTHERS THEN
    RAISE;
END;
/
