CREATE TABLE ExploracaoAgricola (
                                    exploracaoAgricola_id varchar2(225) NOT NULL,
                                    designacao varchar2(225) NOT NULL,
                                    PRIMARY KEY (exploracaoAgricola_id));

CREATE TABLE Edificio (
                          edificio_id varchar2(225) NOT NULL,
                          exploracaoAgricola_id varchar2(225) NOT NULL,
                          tipoEdificio_id varchar2(225),
                          unidade_id varchar2(225),
                          area number(10),
                          PRIMARY KEY (edificio_id));

CREATE TABLE TipoEdificio (
                              tipoEdificio_id varchar2(225) NOT NULL,
                              nome varchar2(225) NOT NULL,
                              PRIMARY KEY (tipoEdificio_id));

CREATE TABLE Parcela (
                         parcela_id varchar2(225) NOT NULL,
                         exploracaoAgricola_id varchar2(225) NOT NULL,
                         unidade_id varchar2(225) NOT NULL,
                         designacao varchar2(225) NOT NULL,
                         area number(10) NOT NULL,
                         PRIMARY KEY (parcela_id));

CREATE TABLE Operacoes (
                           operacoes_id varchar2(225) NOT NULL,
                           parcela_id varchar2(225) NOT NULL,
                           fatorProducao_id varchar2(225),
                           unidade_id varchar2(225) NOT NULL,
                           tipoOperacao_id varchar2(225) NOT NULL,
                           modo varchar2(255),
                           data date NOT NULL,
                           quantidade number(10) NOT NULL,
                           PRIMARY KEY (operacoes_id));

CREATE TABLE TipoOperacao (
                              tipoOperacao_id varchar2(225) NOT NULL,
                              designacao varchar2(225) NOT NULL,
                              PRIMARY KEY (tipoOperacao_id));

CREATE TABLE Parcela_Cultura (
                                 parcela_id varchar2(225) NOT NULL,
                                 cultura_id varchar2(225) NOT NULL,
                                 unidade_id varchar2(225) NOT NULL,
                                 dataInicio date NOT NULL,
                                 dataFim date,
                                 quantidade number(10),
                                 PRIMARY KEY (parcela_id, cultura_id));

CREATE TABLE Cultura (
                         cultura_id varchar2(225) NOT NULL,
                         tipoCultura_id varchar2(225) NOT NULL,
                         informacaoCultura_id varchar2(255) NOT NULL,
                         nomeCultura_id varchar2(225) NOT NULL,
                         variedade varchar2(225) NOT NULL,
                         PRIMARY KEY (cultura_id));

CREATE TABLE TipoCultura (
                             tipoCultura_id varchar2(225) NOT NULL,
                             designacao varchar2(225) NOT NULL,
                             PRIMARY KEY (tipoCultura_id));

CREATE TABLE FatorProducao (
                               fatorProducao_id varchar2(225) NOT NULL,
                               fabricante_id varchar2(225) NOT NULL,
                               fichaTecnica_id varchar2(225) NOT NULL,
                               tipoFatorProducao_id varchar2(225) NOT NULL,
                               formulacao_id varchar2(225) NOT NULL,
                               aplicacao_id varchar2(225) NOT NULL,
                               nomeFatorProducao varchar2(225) NOT NULL,
                               PRIMARY KEY (fatorProducao_id));

CREATE TABLE Fabricante (
                            fabricante_id varchar2(225) NOT NULL,
                            nomeFabricante varchar2(255) NOT NULL,
                            PRIMARY KEY (fabricante_id));

CREATE TABLE FichaTecnica (
                              fichaTecnica_id varchar2(225) NOT NULL,
                              nome varchar2(225) NOT NULL,
                              quantidade varchar2(225) NOT NULL,
                              PRIMARY KEY (fichaTecnica_id));

CREATE TABLE TipoFatorProducao (
                                   tipoFatorProducao_id varchar2(225) NOT NULL,
                                   designacao varchar2(225) NOT NULL,
                                   PRIMARY KEY (tipoFatorProducao_id));

CREATE TABLE Formulacao (
                            formulacao_id varchar2(225) NOT NULL,
                            formula varchar2(225) NOT NULL,
                            PRIMARY KEY (formulacao_id));

CREATE TABLE Elemento (
                          elemento_id varchar2(225) NOT NULL,
                          fatorProducao_id varchar2(225) NOT NULL,
                          fichaTecnica_id varchar2(225) NOT NULL,
                          nomeElemento varchar2(225) NOT NULL,
                          percentagem number(10) NOT NULL,
                          PRIMARY KEY (elemento_id, fatorProducao_id));

CREATE TABLE Unidade (
                         unidade_id varchar2(225) NOT NULL,
                         unidade varchar2(225) NOT NULL,
                         PRIMARY KEY (unidade_id));

CREATE TABLE InformacaoCultura (
                                   informacaoCultura_id varchar2(255) NOT NULL,
                                   tempoSementeira varchar2(225),
                                   tempoPoda varchar2(225),
                                   tempoFloracao varchar2(225),
                                   tempoColheita varchar2(225),
                                   PRIMARY KEY (informacaoCultura_id));

CREATE TABLE NomeCultura (
                             nomeCultura_id varchar2(225) NOT NULL,
                             nomeComum varchar2(225) NOT NULL,
                             PRIMARY KEY (nomeCultura_id));

CREATE TABLE Aplicacao (
                           aplicacao_id varchar2(225) NOT NULL,
                           designacaoAplicacao varchar2(225) NOT NULL,
                           PRIMARY KEY (aplicacao_id));

ALTER TABLE Edificio ADD CONSTRAINT FKEdificio872614 FOREIGN KEY (exploracaoAgricola_id) REFERENCES ExploracaoAgricola (exploracaoAgricola_id);
ALTER TABLE Edificio ADD CONSTRAINT FKEdificio748605 FOREIGN KEY (tipoEdificio_id) REFERENCES TipoEdificio (tipoEdificio_id);
ALTER TABLE Parcela ADD CONSTRAINT FKParcela937228 FOREIGN KEY (exploracaoAgricola_id) REFERENCES ExploracaoAgricola (exploracaoAgricola_id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes349750 FOREIGN KEY (parcela_id) REFERENCES Parcela (parcela_id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes653131 FOREIGN KEY (tipoOperacao_id) REFERENCES TipoOperacao (tipoOperacao_id);
ALTER TABLE Parcela_Cultura ADD CONSTRAINT FKParcela_Cu923152 FOREIGN KEY (parcela_id) REFERENCES Parcela (parcela_id);
ALTER TABLE Parcela_Cultura ADD CONSTRAINT FKParcela_Cu205516 FOREIGN KEY (cultura_id) REFERENCES Cultura (cultura_id);
ALTER TABLE Cultura ADD CONSTRAINT FKCultura602192 FOREIGN KEY (tipoCultura_id) REFERENCES TipoCultura (tipoCultura_id);
ALTER TABLE FatorProducao ADD CONSTRAINT FKFatorProdu775060 FOREIGN KEY (fabricante_id) REFERENCES Fabricante (fabricante_id);
ALTER TABLE FatorProducao ADD CONSTRAINT FKFatorProdu848504 FOREIGN KEY (fichaTecnica_id) REFERENCES FichaTecnica (fichaTecnica_id);
ALTER TABLE FatorProducao ADD CONSTRAINT FKFatorProdu600901 FOREIGN KEY (tipoFatorProducao_id) REFERENCES TipoFatorProducao (tipoFatorProducao_id);
ALTER TABLE FatorProducao ADD CONSTRAINT FKFatorProdu435039 FOREIGN KEY (formulacao_id) REFERENCES Formulacao (formulacao_id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes436033 FOREIGN KEY (fatorProducao_id) REFERENCES FatorProducao (fatorProducao_id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes596290 FOREIGN KEY (unidade_id) REFERENCES Unidade (unidade_id);
ALTER TABLE Parcela_Cultura ADD CONSTRAINT FKParcela_Cu323387 FOREIGN KEY (unidade_id) REFERENCES Unidade (unidade_id);
ALTER TABLE Cultura ADD CONSTRAINT FKCultura86324 FOREIGN KEY (informacaoCultura_id) REFERENCES InformacaoCultura (informacaoCultura_id);
ALTER TABLE Edificio ADD CONSTRAINT FKEdificio573623 FOREIGN KEY (unidade_id) REFERENCES Unidade (unidade_id);
ALTER TABLE Parcela ADD CONSTRAINT FKParcela411876 FOREIGN KEY (unidade_id) REFERENCES Unidade (unidade_id);
ALTER TABLE Cultura ADD CONSTRAINT FKCultura150217 FOREIGN KEY (nomeCultura_id) REFERENCES NomeCultura (nomeCultura_id);
ALTER TABLE Elemento ADD CONSTRAINT FKElemento297863 FOREIGN KEY (fichaTecnica_id) REFERENCES FichaTecnica (fichaTecnica_id);
ALTER TABLE Elemento ADD CONSTRAINT FKElemento432594 FOREIGN KEY (fatorProducao_id) REFERENCES FatorProducao (fatorProducao_id);
ALTER TABLE FatorProducao ADD CONSTRAINT FKFatorProdu756535 FOREIGN KEY (aplicacao_id) REFERENCES Aplicacao (aplicacao_id);

