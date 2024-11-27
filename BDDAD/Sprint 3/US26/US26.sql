CREATE OR REPLACE TRIGGER log_insert_operacoes
  AFTER INSERT ON Operacoes
  FOR EACH ROW
DECLARE
next_idLog NUMBER;
BEGIN
  -- Get the next idLog
SELECT COALESCE(MAX(idLog), 0) + 1 INTO next_idLog FROM LogOperacoes;

-- Insert into LogOperacoes
INSERT INTO LogOperacoes (instante, tipoOperacao, idOperacao, idLog)
VALUES (SYSTIMESTAMP, 'INSERCAO', :NEW.idOperacao, next_idLog);

EXCEPTION
  WHEN OTHERS THEN
    RAISE;
END log_insert_operacoes;
/


DECLARE
next_idOperacao NUMBER;
BEGIN
  -- Get the next idOperacao
SELECT COALESCE(MAX(idOperacao), 0) + 1 INTO next_idOperacao FROM Operacoes;

-- Insert into Operacoes
INSERT INTO Operacoes (idOperacao, data, quantidade, unidade, designacao, idCultura, id)
VALUES (next_idOperacao, TO_DATE('20/12/2023', 'dd/mm/yyyy'), 1.0, 'kg', null, 11, 102);

INSERT INTO Colheita (idOperacao)
VALUES (next_idOperacao);


-- The trigger for LogOperacoes will handle the insertion into LogOperacoes

COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    RAISE;
END;
/



-- Trigger para UPDATE na tabela Operacoes
CREATE OR REPLACE TRIGGER log_update_operacoes
BEFORE UPDATE ON Operacoes
                  FOR EACH ROW
DECLARE
next_idLog NUMBER;
BEGIN
    -- Check if the update changes the unique key (idOperacao)
    IF :OLD.idOperacao <> :NEW.idOperacao THEN
        RAISE_APPLICATION_ERROR(-20001, 'Cannot change the value of idOperacao during update.');
END IF;

SELECT COALESCE(MAX(idLog), 0) + 1 INTO next_idLog FROM LogOperacoes;

INSERT INTO LogOperacoes (instante, TipoOperacao, idOperacao, idLog)
VALUES (SYSTIMESTAMP, 'ATUALIZACAO', :OLD.idOperacao, next_idLog);
END;
/

-- Bloco anónimo UPDATE
DECLARE
v_idOperacao NUMBER := 93; -- Substitua pelo ID da operação que deseja atualizar
    next_idLog NUMBER;
BEGIN
    -- Check if the idOperacao exists before attempting the update
SELECT COUNT(*)
INTO v_idOperacao
FROM Operacoes
WHERE idOperacao = v_idOperacao;

IF v_idOperacao = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Operacao não encontrada.');
        RETURN;
END IF;

SELECT COALESCE(MAX(idLog), 0) + 1 INTO next_idLog FROM LogOperacoes;

-- Update only the non-key columns
UPDATE Operacoes
SET
    data = TO_DATE('12/03/2020', 'dd/mm/yyyy'),
    quantidade = 1.0,
    unidade = 'kg',
    designacao = NULL,
    idCultura = 13,
    id = 106
WHERE idOperacao = v_idOperacao;

COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END;
/
