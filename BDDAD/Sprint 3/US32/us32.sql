CREATE OR REPLACE PROCEDURE prcRegistarOperacaoRega(
    p_data              IN Operacoes.data%TYPE,
    p_quantidade        IN Operacoes.quantidade%TYPE,
    p_unidade           IN Operacoes.unidade%TYPE,
    p_designacao        IN Operacoes.designacao%TYPE,
    p_idCultura         IN Operacoes.idCultura%TYPE,
    p_id                IN Operacoes.id%TYPE,
    p_idSetor           IN Rega.idSetor%TYPE,
    p_idReceita         IN Fertirrega.idReceita%TYPE,
    p_horas             IN Rega.horas%TYPE,
    p_duracao           IN Rega.duracao%TYPE
)
AS
  l_idOperacao         Operacoes.idOperacao%TYPE;
BEGIN

SELECT MAX(idOperacao) + 1 INTO l_idOperacao FROM Operacoes;

INSERT INTO Operacoes (idOperacao, data, quantidade, unidade, designacao, idCultura, id)
VALUES (l_idOperacao, p_data, p_quantidade, p_unidade, p_designacao, p_idCultura, p_id);

IF p_idSetor IS NOT NULL AND p_horas IS NOT NULL AND p_idReceita IS NULL THEN
    INSERT INTO Rega (idOperacao, idSetor, duracao, horas)
    VALUES (l_idOperacao, p_idSetor, p_duracao, p_horas);
END IF;

  IF p_idSetor IS NOT NULL AND p_horas IS NOT NULL AND p_idReceita IS NOT NULL THEN
    INSERT INTO Rega (idOperacao, idSetor, duracao, horas)
    VALUES (l_idOperacao, p_idSetor, p_duracao, p_horas);
INSERT INTO Fertirrega (idOperacao, idReceita)
VALUES (l_idOperacao, p_idReceita);
END IF;

COMMIT;

DBMS_OUTPUT.PUT_LINE('Operação de Rega registada com sucesso.');
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
ROLLBACK;
END;
/


DECLARE
l_message VARCHAR2(100);
BEGIN

  prcRegistarOperacaoRega(
    p_data => TO_DATE('2023-01-01', 'YYYY-MM-DD'),
    p_quantidade => 20,
    p_unidade => 'L',
    p_designacao => NULL,
    p_idCultura => 11,
    p_id => 102,
    p_idSetor => 10,
    p_idReceita => 10,
    p_horas => 08.00,
    p_duracao => 120
  );

  l_message := 'Operação de Rega registada com sucesso.';
  DBMS_OUTPUT.PUT_LINE(l_message);
EXCEPTION
  WHEN OTHERS THEN
    l_message := 'Erro: ' || SQLERRM;
    DBMS_OUTPUT.PUT_LINE(l_message);
ROLLBACK;
END;
/