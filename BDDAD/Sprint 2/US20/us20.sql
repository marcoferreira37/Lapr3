CREATE OR REPLACE FUNCTION getMonthlyIrrigationTotalForParcela(
    v_parcelaId Operacoes.idCultura%type,
    v_startDate IN DATE,
    v_endDate IN DATE
)
RETURN SYS_REFCURSOR
AS
    v_cursor SYS_REFCURSOR;
BEGIN
    OPEN v_cursor FOR
        SELECT
            TO_CHAR(data, 'YYYY-MM') AS month,
            SUM(quantidade) AS total_irrigation
        FROM
            Operacoes
        WHERE
            operacao = 'Rega'
            AND idCultura = v_parcelaId
            AND data BETWEEN v_startDate AND v_endDate
        GROUP BY
            TO_CHAR(data, 'YYYY-MM')
        ORDER BY
            TO_CHAR(data, 'YYYY-MM');

    RETURN v_cursor;
END;
/


DECLARE
    v_parcelaId Operacoes.idCultura%type := 12; -- Specify the parcela ID
    v_startDate DATE := TO_DATE('01/07/2019', 'DD/MM/YYYY');
    v_endDate DATE := TO_DATE('31/08/2019', 'DD/MM/YYYY');
    v_resultCursor SYS_REFCURSOR;
    v_month VARCHAR2(7);
    v_total_irrigation Operacoes.quantidade%type;
BEGIN
    -- Call the function
    v_resultCursor := getMonthlyIrrigationTotalForParcela(v_parcelaId, v_startDate, v_endDate);

    -- Fetch and display the results
    LOOP
        FETCH v_resultCursor INTO v_month, v_total_irrigation;
        EXIT WHEN v_resultCursor%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('Month: ' || v_month || ', Total Irrigation: ' || v_total_irrigation);
    END LOOP;

    -- Close the cursor
    CLOSE v_resultCursor;
END;
/
