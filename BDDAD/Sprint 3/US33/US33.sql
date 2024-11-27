—USBD33
CREATE OR REPLACE PROCEDURE MaiorConsumoAguaPorCultura(
    ano_consulta IN NUMBER
) AS
    v_total_max_consumo NUMBER;
BEGIN
    SELECT MAX(total_consumo_agua)
    INTO v_total_max_consumo
    FROM (
        SELECT SUM(R.duracao) AS total_consumo_agua
        FROM Cultura C
        JOIN Operacoes O ON C.idCultura = O.idCultura
        JOIN Rega R ON O.idOperacao = R.idOperacao
        WHERE TO_CHAR(O.data, 'YYYY') = TO_CHAR(ano_consulta)
        GROUP BY C.idCultura
    );

    FOR rec IN (
        SELECT C.idCultura, C.cultura, SUM(R.duracao) AS total_consumo_agua
        FROM Cultura C
        JOIN Operacoes O ON C.idCultura = O.idCultura
        JOIN Rega R ON O.idOperacao = R.idOperacao
        WHERE TO_CHAR(O.data, 'YYYY') = TO_CHAR(ano_consulta)
        GROUP BY C.idCultura, C.cultura
        HAVING SUM(R.duracao) = v_total_max_consumo
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('ID da Cultura com Maior Consumo de Água: ' || rec.idCultura);
        DBMS_OUTPUT.PUT_LINE('Cultura: ' || rec.cultura);
        DBMS_OUTPUT.PUT_LINE('Total de Consumo de Água: ' || rec.total_consumo_agua || ' unidades');
    END LOOP;

    IF v_total_max_consumo IS NULL THEN
        DBMS_OUTPUT.PUT_LINE('Nenhum dado encontrado para o ano indicado.');
    END IF;
END;
/


BEGIN
    MaiorConsumoAguaPorCultura(2023); -- Altere o ano conforme necessário
END;
/