

-- procedure
CREATE OR REPLACE PROCEDURE anularOperacao(
    p_idOperacao IN NUMBER,
    p_status IN VARCHAR2 := 'PENDING')

    AS
    v_idParcela NUMBER;
    v_idCultura NUMBER;
    v_podeAnular BOOLEAN := TRUE;
    v_dataPrevista DATE;
    v_contador NUMBER;

BEGIN
    -- Retrieve dataPrevista based on idOperacao
SELECT data, id, idCultura
INTO v_dataPrevista, v_idParcela, v_idCultura
FROM Operacoes
WHERE idOperacao = p_idOperacao;

DBMS_OUTPUT.PUT_LINE('Data Prevista = ' || TO_CHAR(v_dataPrevista, 'dd/mm/yyyy'));
    DBMS_OUTPUT.PUT_LINE('SYSDATE = ' || TO_CHAR(SYSDATE, 'dd/mm/yyyy'));

    -- Compare only the date part of SYSDATE
    IF v_dataPrevista IS NOT NULL AND TRUNC(SYSDATE) < TRUNC(v_dataPrevista) + 3 THEN
        DBMS_OUTPUT.PUT_LINE('Date condition satisfied');

    -- Check if there are dependent operations
FOR dep_operacao IN (
        SELECT COUNT(*)
        INTO v_contador
        FROM Operacoes
        WHERE idOperacao <> p_idOperacao
          AND status = p_status
          AND data > v_dataPrevista
          AND id = v_idParcela
          AND idCultura = v_idCultura
        ) LOOP
            v_podeAnular := FALSE;
            EXIT;
END LOOP;

        IF v_contador = 0 AND v_podeAnular THEN
            DBMS_OUTPUT.PUT_LINE('Não existem operações dependentes. Procedendo com a anulação.');

UPDATE Operacoes
SET status = 'CANCELED'
WHERE idOperacao = p_idOperacao;

COMMIT;
DBMS_OUTPUT.PUT_LINE('Operação anulada com sucesso.');
ELSE
            DBMS_OUTPUT.PUT_LINE('Não é possível anular a operação. Existem operações dependentes.');
END IF;
ELSE
UPDATE Operacoes
SET status = 'PENDING'
WHERE idOperacao = p_idOperacao;

DBMS_OUTPUT.PUT_LINE('Não é possível anular a operação. A data prevista é inválida ou não passaram-se 3 dias.');
END IF;
END anularOperacao;


-- Bloco anônimo para testar a procedure anularOperacao
SET SERVEROUTPUT ON;

DECLARE
v_idOperacao NUMBER := 400;
    v_statusAntes VARCHAR2(50);
    v_statusDepois VARCHAR2(50);
BEGIN
    -- Obtém o status atual da operação antes de chamar a procedure
SELECT status INTO v_statusAntes FROM Operacoes WHERE idOperacao = v_idOperacao;

-- Chama a procedure para anular a operação
anularOperacao(p_idOperacao => v_idOperacao, p_status => 'PENDING');

    -- Obtém o status atual da operação após a execução da procedure
SELECT status INTO v_statusDepois FROM Operacoes WHERE idOperacao = v_idOperacao;

-- Exibe o status antes e depois
DBMS_OUTPUT.PUT_LINE('Status antes da operação: ' || v_statusAntes);
    DBMS_OUTPUT.PUT_LINE('Status depois da operação: ' || v_statusDepois);

    -- Verifica se a operação foi anulada com sucesso
    IF v_statusDepois = 'CANCELED' THEN
        DBMS_OUTPUT.PUT_LINE('Operação alterada no status: CANCELED');
ELSE
        DBMS_OUTPUT.PUT_LINE('A operação não foi anulada com sucesso.');
END IF;
END;
/


