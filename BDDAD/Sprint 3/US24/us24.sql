--Necessário colocar um timeStamp para o instante da criação
ALTER TABLE Operacoes ADD (instanteRegisto TIMESTAMP);

--Criar o trigger que na hora da criação atribui a hora do sistema
CREATE OR REPLACE TRIGGER trg_insert_operacoes
BEFORE INSERT ON Operacoes
FOR EACH ROW
BEGIN
    :NEW.instanteRegisto := CURRENT_TIMESTAMP;
END;
/

-- Inserindo uma linha
INSERT INTO Operacoes (idOperacao , data , quantidade , unidade , designacao, idCultura, id)VALUES (1006,TO_DATE('10/07/2018', 'dd/mm/yyyy')  ,  3.5 , 'm3' , null,24, 104  ) ;

-- Na última entrada será possível confirmar que já estão a ser marcadas as horas e a data de inserção
SELECT * FROM Operacoes;