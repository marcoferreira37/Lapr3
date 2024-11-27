
DROP TABLE Aplicacao CASCADE CONSTRAINTS;
DROP TABLE Aplicacao_Fitofarmaco CASCADE CONSTRAINTS;
DROP TABLE Classificacao CASCADE CONSTRAINTS;
DROP TABLE Colheita CASCADE CONSTRAINTS;
DROP TABLE Cultura CASCADE CONSTRAINTS;
DROP TABLE Cultura_Parcela CASCADE CONSTRAINTS;
DROP TABLE Cultura_Produto CASCADE CONSTRAINTS;
DROP TABLE Edificio CASCADE CONSTRAINTS;
DROP TABLE Elemento CASCADE CONSTRAINTS;
DROP TABLE Elemento_Ficha_Tecnica CASCADE CONSTRAINTS;
DROP TABLE Exploracao_Agricola CASCADE CONSTRAINTS;
DROP TABLE Fabricante CASCADE CONSTRAINTS;
DROP TABLE Fator_Producao CASCADE CONSTRAINTS;
DROP TABLE Fator_Producao_Parcela CASCADE CONSTRAINTS;
DROP TABLE Fertilizacao CASCADE CONSTRAINTS;
DROP TABLE Ficha_Tecnica CASCADE CONSTRAINTS;
DROP TABLE Formulacao CASCADE CONSTRAINTS;
DROP TABLE Incorporacao_No_Solo CASCADE CONSTRAINTS;
DROP TABLE Monda CASCADE CONSTRAINTS;
DROP TABLE Operacoes CASCADE CONSTRAINTS;
DROP TABLE Parcela CASCADE CONSTRAINTS;
DROP TABLE Plantacao CASCADE CONSTRAINTS;
DROP TABLE Plantas CASCADE CONSTRAINTS;
DROP TABLE Poda CASCADE CONSTRAINTS;
DROP TABLE Produto CASCADE CONSTRAINTS;
DROP TABLE Rega CASCADE CONSTRAINTS;
DROP TABLE Sementeira CASCADE CONSTRAINTS;
DROP TABLE Setor CASCADE CONSTRAINTS;
DROP TABLE Setor_Cultura_Parcela CASCADE CONSTRAINTS;
DROP TABLE Substancia CASCADE CONSTRAINTS;
DROP TABLE Subtancia_Ficha_Tecnica CASCADE CONSTRAINTS;
DROP TABLE TipoEdificio CASCADE CONSTRAINTS;







CREATE TABLE Aplicacao (aplicacao varchar2(225) NOT NULL, PRIMARY KEY (aplicacao));
CREATE TABLE Aplicacao_Fitofarmaco (idOperacao number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Classificacao (classificacao varchar2(225) NOT NULL, PRIMARY KEY (classificacao));
CREATE TABLE Colheita (idOperacao number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Cultura (tipo varchar2(225) NOT NULL, cultura varchar2(225) NOT NULL, idCultura number(10) NOT NULL, PRIMARY KEY (idCultura));
CREATE TABLE Cultura_Parcela (idCultura number(10) NOT NULL, id number(10) NOT NULL,quantidade number(10), unidades varchar2(225) NOT NULL, dataInicio date NOT NULL, dataFinal date, PRIMARY KEY (idCultura, id));
CREATE TABLE Cultura_Produto (idCultura number(10) NOT NULL, idProduto number(10) NOT NULL, PRIMARY KEY (idCultura, idProduto));
CREATE TABLE Edificio (id number(10) NOT NULL, tipo varchar2(255) NOT NULL, designacao varchar2(225), dimensao number(10), unidade varchar2(225), PRIMARY KEY (id));
CREATE TABLE Elemento (componente_quimico varchar2(225) NOT NULL, PRIMARY KEY (componente_quimico));
CREATE TABLE Elemento_Ficha_Tecnica (idFicha number(10) NOT NULL, componente_quimico varchar2(225) NOT NULL, perc number(10), PRIMARY KEY (idFicha, componente_quimico));
CREATE TABLE Exploracao_Agricola (designacao varchar2(225) NOT NULL, PRIMARY KEY (designacao));
CREATE TABLE Fabricante (idFabricante number(10) NOT NULL, designacao varchar2(225) NOT NULL UNIQUE, PRIMARY KEY (idFabricante));
CREATE TABLE Fator_Producao (designacao varchar2(225) NOT NULL, idFicha number(10) NOT NULL, formato varchar2(225), idFabricante number(10) NOT NULL, aplicacao varchar2(225) NOT NULL, classificacao varchar2(225) NOT NULL, PRIMARY KEY (designacao));
CREATE TABLE Fator_Producao_Parcela (designacao varchar2(225) NOT NULL, id number(10) NOT NULL, PRIMARY KEY (designacao, id));
CREATE TABLE Fertilizacao (modo varchar2(255) NOT NULL, idOperacao number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Ficha_Tecnica (idFicha number(10) NOT NULL, PRIMARY KEY (idFicha));
CREATE TABLE Formulacao (formato varchar2(225) NOT NULL, PRIMARY KEY (formato));
CREATE TABLE Monda (idOperacao number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Incorporacao_No_Solo (idOperacao number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Operacoes (idOperacao number(10) NOT NULL, data date NOT NULL, quantidade number(10), unidade varchar2(225), operacao varchar2(225) NOT NULL, designacao varchar2(225), idCultura number(10) NOT NULL, id number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Parcela (id number(10) NOT NULL, designacao varchar2(225), dimensao number(10), unidade varchar2(225) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Plantacao (idOperacao number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Plantas (especie varchar2(225) NOT NULL, nomeComum varchar2(225) NOT NULL, variedade varchar2(225) NOT NULL, tipoPlantacao varchar2(225) NOT NULL, tempoPoda varchar2(225), tempoFloracao varchar2(225), tempoColheita varchar2(225), tempoSementeira varchar2(225), idCultura number(10) NOT NULL, PRIMARY KEY (idCultura));
CREATE TABLE Poda (idOperacao number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Produto (idProduto number(10)NOT NULL, nomeComercial number(10) NOT NULL, PRIMARY KEY (idProduto));
CREATE TABLE Rega (idOperacao number(10) NOT NULL, idSetor number(10) NOT NULL, duracao number(10) NOT NULL, horas number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Sementeira (idOperacao number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Setor (idSetor number(10) NOT NULL, dataInicio date NOT NULL, dataFim date, caudalMaximo number(10), PRIMARY KEY (idSetor));
CREATE TABLE Setor_Cultura_Parcela (idSetor number(10) NOT NULL, idCultura number(10) NOT NULL, id number(10) NOT NULL, PRIMARY KEY (idSetor, idCultura, id));
CREATE TABLE Substancia (designacao varchar2(225) NOT NULL, PRIMARY KEY (designacao));
CREATE TABLE Subtancia_Ficha_Tecnica (designacao varchar2(225) NOT NULL, idFicha number(10) NOT NULL, perc number(10), PRIMARY KEY (designacao, idFicha));
CREATE TABLE TipoEdificio (tipo varchar2(225) NOT NULL, designacao varchar2(255) NOT NULL, PRIMARY KEY (designacao));
CREATE TABLE Aplicacao_Fator_Producao (idOperacao number(10) NOT NULL,modo varchar2(255), area number(10), unidades varchar2(255), PRIMARY KEY (idOperacao));
CREATE TABLE Mobilizacao_Solo (idOperacao number(10) NOT NULL, PRIMARY KEY (idOperacao));
CREATE TABLE Semeadura (idOperacao number(10) NOT NULL, PRIMARY KEY (idOperacao));



ALTER TABLE Monda ADD CONSTRAINT FKMonda845537 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Semeadura ADD CONSTRAINT FKSemeadura845557 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Mobilizacao_Solo ADD CONSTRAINT FKMobilizacao_Solo824739 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Aplicacao_Fator_Producao ADD CONSTRAINT FKAplicacao_FP859737 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Cultura_Produto ADD CONSTRAINT FKCultura_Pr94844 FOREIGN KEY (idCultura) REFERENCES Cultura (idCultura);
ALTER TABLE Cultura_Produto ADD CONSTRAINT FKCultura_Pr219627 FOREIGN KEY (idProduto) REFERENCES Produto (idProduto);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes797382 FOREIGN KEY (idCultura, id) REFERENCES Cultura_Parcela (idCultura, id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes357565 FOREIGN KEY (designacao) REFERENCES Fator_Producao (designacao);
ALTER TABLE Setor_Cultura_Parcela ADD CONSTRAINT FKSetor_Cult601416 FOREIGN KEY (idSetor) REFERENCES Setor (idSetor);
ALTER TABLE Setor_Cultura_Parcela ADD CONSTRAINT FKSetor_Cult3260 FOREIGN KEY (idCultura, id) REFERENCES Cultura_Parcela (idCultura, id);
ALTER TABLE Rega ADD CONSTRAINT FKRega54332 FOREIGN KEY (idSetor) REFERENCES Setor (idSetor);
ALTER TABLE Incorporacao_No_Solo ADD CONSTRAINT FKIncorporac855863 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Aplicacao_Fitofarmaco ADD CONSTRAINT FKAplicacao_373349 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Colheita ADD CONSTRAINT FKColheita476791 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Plantacao ADD CONSTRAINT FKPlantacao100260 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Poda ADD CONSTRAINT FKPoda959990 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Fertilizacao ADD CONSTRAINT FKFertilizac247357 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Rega ADD CONSTRAINT FKRega909925 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Sementeira ADD CONSTRAINT FKSementeira295721 FOREIGN KEY (idOperacao) REFERENCES Operacoes (idOperacao);
ALTER TABLE Subtancia_Ficha_Tecnica ADD CONSTRAINT FKSubtancia_774438 FOREIGN KEY (idFicha) REFERENCES Ficha_Tecnica (idFicha);
ALTER TABLE Subtancia_Ficha_Tecnica ADD CONSTRAINT FKSubtancia_282450 FOREIGN KEY (designacao) REFERENCES Substancia (designacao);
ALTER TABLE Fator_Producao ADD CONSTRAINT FKFator_Prod582315 FOREIGN KEY (classificacao) REFERENCES Classificacao (classificacao);
ALTER TABLE Fator_Producao ADD CONSTRAINT FKFator_Prod931394 FOREIGN KEY (aplicacao) REFERENCES Aplicacao (aplicacao);
ALTER TABLE Fator_Producao ADD CONSTRAINT FKFator_Prod990114 FOREIGN KEY (idFabricante) REFERENCES Fabricante (idFabricante);
ALTER TABLE Fator_Producao ADD CONSTRAINT FKFator_Prod561275 FOREIGN KEY (formato) REFERENCES Formulacao (formato);
ALTER TABLE Cultura_Parcela ADD CONSTRAINT FKCultura_Pa477584 FOREIGN KEY (id) REFERENCES Parcela (id);
ALTER TABLE Cultura_Parcela ADD CONSTRAINT FKCultura_Pa123927 FOREIGN KEY (idCultura) REFERENCES Cultura (idCultura);
ALTER TABLE Elemento_Ficha_Tecnica ADD CONSTRAINT FKElemento_F903742 FOREIGN KEY (idFicha) REFERENCES Ficha_Tecnica (idFicha);
ALTER TABLE Elemento_Ficha_Tecnica ADD CONSTRAINT FKElemento_F629689 FOREIGN KEY (componente_quimico) REFERENCES Elemento (componente_quimico);
ALTER TABLE Fator_Producao_Parcela ADD CONSTRAINT FKFator_Prod380854 FOREIGN KEY (id) REFERENCES Parcela (id);
ALTER TABLE Fator_Producao_Parcela ADD CONSTRAINT FKFator_Prod814277 FOREIGN KEY (designacao) REFERENCES Fator_Producao (designacao);
ALTER TABLE Edificio ADD CONSTRAINT FKEdificio367873 FOREIGN KEY (designacao) REFERENCES TipoEdificio (designacao);
ALTER TABLE Parcela ADD CONSTRAINT FKParcela793873 FOREIGN KEY (designacao) REFERENCES Exploracao_Agricola (designacao);
ALTER TABLE Plantas ADD CONSTRAINT FKPlantas619877 FOREIGN KEY (idCultura) REFERENCES Cultura (idCultura);
ALTER TABLE Fator_Producao ADD CONSTRAINT FKFator_Prod180551 FOREIGN KEY (idFicha) REFERENCES Ficha_Tecnica (idFicha);


