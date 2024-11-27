INSERT INTO ExploracaoAgricola(exploracaoAgricola_id, designacao) VALUES (1, 'Agricultura Sustentável');

INSERT INTO TipoEdificio(tipoEdificio_id, nome) VALUES (1, 'Armazém');
INSERT INTO TipoEdificio(tipoEdificio_id, nome) VALUES (2, 'Garagem');
INSERT INTO TipoEdificio(tipoEdificio_id, nome) VALUES (3, 'Moinho');

INSERT INTO Unidade(unidade_id, unidade) VALUES (1, 'ha');
INSERT INTO Unidade(unidade_id, unidade) VALUES (2, 'm2');
INSERT INTO Unidade(unidade_id, unidade) VALUES (3, 'm3');

INSERT INTO Edificio(edificio_id, exploracaoAgricola_id, tipoEdificio_id, unidade_id, area)
VALUES(201,1,1,2,600);
INSERT INTO Edificio(edificio_id, exploracaoAgricola_id, tipoEdificio_id, unidade_id, area)
VALUES(202,1,1,2,800);
INSERT INTO Edificio(edificio_id, exploracaoAgricola_id, tipoEdificio_id, unidade_id, area)
VALUES(203,1,1,2,900);
INSERT INTO Edificio(edificio_id, exploracaoAgricola_id, tipoEdificio_id, unidade_id, area)
VALUES(204,1,1,NULL,NULL);
INSERT INTO Edificio(edificio_id, exploracaoAgricola_id, tipoEdificio_id, unidade_id, area)
VALUES(205,1,1,3,15);

INSERT INTO Parcela(parcela_id, exploracaoAgricola_id, unidade_id, designacao, area)
VALUES(101,1,1,'Campo da bouça',1.2);
INSERT INTO Parcela(parcela_id, exploracaoAgricola_id, unidade_id, designacao, area)
VALUES(102,1,1,'Campo grande',3);
INSERT INTO Parcela(parcela_id, exploracaoAgricola_id, unidade_id, designacao, area)
VALUES(103,1,1,'Campo do poço',1.5);
INSERT INTO Parcela(parcela_id, exploracaoAgricola_id, unidade_id, designacao, area)
VALUES(104,1,1,'Lameiro da ponte',0.8);
INSERT INTO Parcela(parcela_id, exploracaoAgricola_id, unidade_id, designacao, area)
VALUES(105,1,1,'Lameiro do moinho',1.1);
INSERT INTO Parcela(parcela_id, exploracaoAgricola_id, unidade_id, designacao, area)
VALUES(106,1,1,'Horta Nova',0.3);

INSERT INTO TipoCultura(tipoCultura_id, designacao) VALUES (1,'Permanente');
INSERT INTO TipoCultura(tipoCultura_id, designacao) VALUES (2,'Temporário');

INSERT INTO InformacaoCultura(informacaoCultura_id, tempoSementeira, tempoPoda, tempoFloracao, tempoColheita)
VALUES(1, NULL, 'Novembro a dezembro', 'Fevereiro a março', 'Julho a agosto');
INSERT INTO InformacaoCultura(informacaoCultura_id, tempoSementeira, tempoPoda, tempoFloracao, tempoColheita)
VALUES(2, NULL, 'Novembro a dezembro', 'Março a abril', 'Agosto a setembro');
INSERT INTO InformacaoCultura(informacaoCultura_id, tempoSementeira, tempoPoda, tempoFloracao, tempoColheita)
VALUES(3, NULL, 'Janeiro', 'Abril a maio', 'Novembro a dezembro');
INSERT INTO InformacaoCultura(informacaoCultura_id, tempoSementeira, tempoPoda, tempoFloracao, tempoColheita)
VALUES(4, NULL, NULL, NULL, '80 dias');
INSERT INTO InformacaoCultura(informacaoCultura_id, tempoSementeira, tempoPoda, tempoFloracao, tempoColheita)
VALUES(5, 'Abril a junho', NULL, NULL, 'Julho a setembro');
INSERT INTO InformacaoCultura(informacaoCultura_id, tempoSementeira, tempoPoda, tempoFloracao, tempoColheita)
VALUES(6, 'Março a setembro', NULL, NULL, 'Junho a fevereiro');
INSERT INTO InformacaoCultura(informacaoCultura_id, tempoSementeira, tempoPoda, tempoFloracao, tempoColheita)
VALUES(7, NULL, NULL, NULL, 'Outubro a novembro');
INSERT INTO InformacaoCultura(informacaoCultura_id, tempoSementeira, tempoPoda, tempoFloracao, tempoColheita)
VALUES(8, 'Feveiro a abril, agosto e outubro', NULL, NULL, '90 dias');
INSERT INTO InformacaoCultura(informacaoCultura_id, tempoSementeira, tempoPoda, tempoFloracao, tempoColheita)
VALUES(9,NULL,NULL,NULL,NULL);

INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Prunus domestica','Ameixoeira');
INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Prunus armeniaca','Damasqueiro');
INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Malus domestica','Macieira');
INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Pyrus pyrifolia','Pera Nashi');
INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Daucus carota subsp. Sativus','Cenoura');
INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Lupinus luteus','Tremoço');
INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Lupinus albus','Tremoço');
INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Zea mays','Milho');
INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Brassica rapa','Nabo Greleiro');
INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Olea europaea','Oliveira');
INSERT INTO NomeCultura(nomeCultura_id, nomeComum) VALUES ('Brassica rapa L.','Nabo');

INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(1,1,1,'Prunus domestica','RAINHA CLAUDIA CARANGUEJEIRA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(2,1,1,'Prunus domestica','PRESIDENT');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(3,1,1,'Prunus domestica','STANLEY');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(4,1,1,'Prunus domestica','ANGELENO');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(5,1,1,'Prunus domestica','BLACK BEAUTY');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(6,1,1,'Prunus domestica','BLACK STAR');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(7,1,1,'Prunus domestica','BLACK GOLD');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(8,1,1,'Prunus domestica','BLACK DIAMOND');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(9,1,1,'Prunus domestica','BLACK AMBER');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(10,1,1,'Prunus domestica','BLACK SPLENDOR');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(11,1,1,'Prunus domestica','FORTUNA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(12,1,1,'Prunus domestica','FRIAR');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(13,1,1,'Prunus domestica','EL DORADO');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(14,1,1,'Prunus domestica','ELEPHANT HEART');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(15,1,1,'Prunus domestica','GOLDEN JAPAN');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(16,1,1,'Prunus domestica','HARRY PITCHON');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(17,1,1,'Prunus domestica','LAETITIA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(18,1,1,'Prunus domestica','METLEY');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(19,1,1,'Prunus domestica','MIRABELLE DE NANCY');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(20,1,1,'Prunus domestica','QUEEN ROSE');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(21,1,1,'Prunus domestica','RED BEAUT');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(22,1,1,'Prunus domestica','SANTA ROSA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(23,1,1,'Prunus domestica','SHIRO');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(24,1,1,'Prunus domestica','SUNGOLD');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(25,1,1,'Prunus domestica','WILDSON PERFECTION');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(26,1,1,'Prunus domestica','AUTUMUN GIANT');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(28,1,1,'Prunus armeniaca','BULIDA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(29,1,1,'Prunus armeniaca','CANINO');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(30,1,1,'Prunus armeniaca','LIABAUD');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(31,1,1,'Prunus armeniaca','MAILLOT JAUNE');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(32,1,1,'Prunus armeniaca','POLONAIS');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(33,1,2,'Malus domestica','AKANE');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(34,1,2,'Malus domestica','BELGODEN');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(35,1,2,'Malus domestica','BRAVO DE ESMOLFE');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(36,1,2,'Malus domestica','CASA NOVA DE ALCOBAÇA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(37,1,2,'Malus domestica','EROVAN');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(38,1,2,'Malus domestica','FUJI');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(39,1,2,'Malus domestica','GRANNY SMITH');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(40,1,2,'Malus domestica','GOLDEN DELICIOUS');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(41,1,2,'Malus domestica','HI-EARLY');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(42,1,2,'Malus domestica','JONAGORED');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(43,1,2,'Malus domestica','LYSGOLDEN');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(44,1,2,'Malus domestica','MUTSU');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(45,1,3,'Malus domestica','PORTA DA LOJA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(46,1,2,'Malus domestica','REINETTE OU CANADA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(47,1,2,'Malus domestica','REINETTE OU GRAND FAY');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(48,1,2,'Malus domestica','RISCASDINHA DE PALMELA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(49,1,2,'Malus domestica','ROYAL GALA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(50,1,2,'Malus domestica','REDCHIEF');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(51,1,2,'Malus domestica','STARKING');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(52,1,2,'Malus domestica','SUMMER RED');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(53,1,2,'Malus domestica','WELL´SPUR DELICIOUS');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(54,1,2,'Malus domestica','NOIVA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(55,1,2,'Malus domestica','OLHO ABERTO');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(56,1,2,'Malus domestica','CAMOESA ROSA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(57,1,2,'Malus domestica','MALÁPIO');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(58,1,2,'Malus domestica','GRONHO DOCE');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(59,1,2,'Malus domestica','PÉ DE BOI');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(60,1,2,'Malus domestica','PINOVA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(61,1,2,'Malus domestica','PARDO LINDO');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(62,1,2,'Malus domestica','PIPO DE BASTO');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(63,1,2,'Malus domestica','PRIMA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(64,1,2,'Malus domestica','QUERINA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(65,1,2,'Malus domestica','VISTA BELLA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(66,1,2,'Malus domestica','GOLDEN SMOOTHEE');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(67,1,2,'Malus domestica','GOLDEN SUPREMA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(68,1,2,'Malus domestica','GLOSTER69');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(69,1,2,'Malus domestica','FREEDOM');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(70,1,9,'Pyrus pyrifolia','SNINSEIKI');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(71,1,9,'Pyrus pyrifolia','KUMOI');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(72,1,9,'Pyrus pyrifolia','HOSUI');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(73,1,9,'Pyrus pyrifolia','NIJISSEIKI');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(74,2,4,'Daucus carota subsp. Sativus','Carson Hybrid');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(75,2,4,'Daucus carota subsp. Sativus','Red Cored Chantenay');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(76,2,4,'Daucus carota subsp. Sativus','Danvers Half Long');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(77,2,4,'Daucus carota subsp. Sativus','Imperator 58');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(78,2,4,'Daucus carota subsp. Sativus','Danvers Half Long');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(79,2,4,'Daucus carota subsp. Sativus','Sugarsnax Hybrid');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(80,2,4,'Daucus carota subsp. Sativus','Nelson Hybrid');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(81,2,4,'Daucus carota subsp. Sativus','Scarlet Nantes');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(82,2,5,'Lupinus luteus','Amarelo');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(83,2,9,'Lupinus albus','Branco');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(84,2,5,'Zea mays','MAS 24.C');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(85,2,5,'Zea mays','Doce Golden Bantam');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(86,2,6,'Brassica rapa','Senhora Conceição');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(87,1,7,'Olea europaea','COBRANÇOSA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(88,1,7,'Olea europaea','ARBEQUINA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(89,1,7,'Olea europaea','HOJIBLANCA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(90,1,7,'Olea europaea','NEGRINHA DO FREIXO');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(91,1,7,'Olea europaea','PICUAL');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(92,1,7,'Olea europaea','MAÇANILHA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(93,1,7,'Olea europaea','CONSERVA DE ELVAS');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(94,1,7,'Olea europaea','COBRANÇOSA');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(95,1,7,'Olea europaea','Galega');
INSERT INTO Cultura(cultura_id, tipoCultura_id, informacaoCultura_id, nomeCultura_id, variedade)
VALUES(96,2,8,'Brassica rapa L.','S. Cosme');

INSERT INTO TipoFatorProducao(tipoFatorProducao_id, designacao) VALUES (1, 'Fitofármaco');
INSERT INTO TipoFatorProducao(tipoFatorProducao_id, designacao) VALUES (2, 'Adubo');
INSERT INTO TipoFatorProducao(tipoFatorProducao_id, designacao) VALUES (3, 'Corretor');

INSERT INTO Aplicacao(aplicacao_id, designacaoAplicacao) VALUES (1, 'Fungicida');
INSERT INTO Aplicacao(aplicacao_id, designacaoAplicacao) VALUES (2, 'Abudo Solo');
INSERT INTO Aplicacao(aplicacao_id, designacaoAplicacao) VALUES (3, 'Adubo Foliar + Fertirrega');
INSERT INTO Aplicacao(aplicacao_id, designacaoAplicacao) VALUES (4, 'Adubo Foliar');
INSERT INTO Aplicacao(aplicacao_id, designacaoAplicacao) VALUES (5, 'Correção Solo');

INSERT INTO Fabricante(fabricante_id, nomeFabricante) VALUES (1, 'ASCENZA');
INSERT INTO Fabricante(fabricante_id, nomeFabricante) VALUES (2, 'Bayer');
INSERT INTO Fabricante(fabricante_id, nomeFabricante) VALUES (3, 'K+S');
INSERT INTO Fabricante(fabricante_id, nomeFabricante) VALUES (4, 'Biocal');

INSERT INTO Formulacao(formulacao_id, formula) VALUES (1,'Pó molhável');
INSERT INTO Formulacao(formulacao_id, formula) VALUES (2,'Granulado');
INSERT INTO Formulacao(formulacao_id, formula) VALUES (3,'Pó');

INSERT INTO FichaTecnica(fichaTecnica_id, nome, quantidade) VALUES (1,'Ficha Informativa No. 1',200);

INSERT INTO FatorProducao(fatorProducao_id, fabricante_id, fichaTecnica_id, tipoFatorProducao_id, formulacao_id, aplicacao_id, nomeFatorProducao)
VALUES ('Calda Bordalesa ASCENZA',1,1,1,1,1,'ASCENZA Factors');
INSERT INTO FatorProducao(fatorProducao_id, fabricante_id, fichaTecnica_id, tipoFatorProducao_id, formulacao_id, aplicacao_id, nomeFatorProducao)
VALUES ('Enxofre Bayer 80 WG',2,1,1,1,1,'BAYER Factors');
INSERT INTO FatorProducao(fatorProducao_id, fabricante_id, fichaTecnica_id, tipoFatorProducao_id, formulacao_id, aplicacao_id, nomeFatorProducao)
VALUES ('Patentkali',3,1,2,2,2,'K+S Factors 1');
INSERT INTO FatorProducao(fatorProducao_id, fabricante_id, fichaTecnica_id, tipoFatorProducao_id, formulacao_id, aplicacao_id, nomeFatorProducao)
VALUES ('ESTA Kiersit',3,1,2,2,2,'K+S Factors 2');
INSERT INTO FatorProducao(fatorProducao_id, fabricante_id, fichaTecnica_id, tipoFatorProducao_id, formulacao_id, aplicacao_id, nomeFatorProducao)
VALUES ('EPSO Microtop',3,1,3,2,3,'K+S Factors 3');
INSERT INTO FatorProducao(fatorProducao_id, fabricante_id, fichaTecnica_id, tipoFatorProducao_id, formulacao_id, aplicacao_id, nomeFatorProducao)
VALUES ('EPSO Top',3,1,2,2,4,'K+S Factors 4');
INSERT INTO FatorProducao(fatorProducao_id, fabricante_id, fichaTecnica_id, tipoFatorProducao_id, formulacao_id, aplicacao_id, nomeFatorProducao)
VALUES ('Biocal CaCo3',4,1,3,2,5,'Biocal Factors 1');
INSERT INTO FatorProducao(fatorProducao_id, fabricante_id, fichaTecnica_id, tipoFatorProducao_id, formulacao_id, aplicacao_id, nomeFatorProducao)
VALUES ('Biocal Composto',4,1,3,3,5,'Biocal Factors 2');

INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (1,'Calda Bordalesa ASCENZA',1,'CU',20);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (2,'Enxofre Bayer 80 WG',1,'S',80);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (3,'Patentkali',1,'K',24.9);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (4,'Patentkali',1,'Mg',6);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (5,'Patentkali',1,'S',17.6);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (6,'ESTA Kiersit',1,'Mg',15.1);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (7,'ESTA Kiersit',1,'S',20.8);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (8,'EPSO Microtop',1,'Mg',9);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (9,'EPSO Microtop',1,'S',12.40);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (10,'EPSO Microtop',1,'B',0.9);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (11,'EPSO Microtop',1,'Mn',1);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (12,'EPSO Top',1,'Mg',9.6);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (13,'EPSO Top',1,'S',13);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (14,'Biocal CaCo3',1,'CaCO3',88.2);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (15,'Biocal CaCo3',1,'MgCO3',1.9);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (16,'Biocal Composto',1,'CaCO3',71.7);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (17,'Biocal Composto',1,'MgCO3',14.8);
INSERT INTO Elemento(elemento_id, fatorProducao_id, fichaTecnica_id, nomeElemento, percentagem)
VALUES (18,'Biocal Composto',1,'MgO',7.9);

INSERT INTO TipoOperacao(tipoOperacao_id, designacao)
VALUES (1, 'Plantação');
INSERT INTO TipoOperacao(tipoOperacao_id, designacao)
VALUES (2, 'Rega');
INSERT INTO TipoOperacao(tipoOperacao_id, designacao)
VALUES (3, 'Fertilização');
INSERT INTO TipoOperacao(tipoOperacao_id, designacao)
VALUES (4, 'Poda');
INSERT INTO TipoOperacao(tipoOperacao_id, designacao)
VALUES (5, 'Colheita');
INSERT INTO TipoOperacao(tipoOperacao_id, designacao)
VALUES (6, 'Sementeira');
INSERT INTO TipoOperacao(tipoOperacao_id, designacao)
VALUES (7, 'Incorporação no solo');

INSERT INTO Unidade(unidade_id, unidade)
VALUES (4, 'un');
INSERT INTO Unidade(unidade_id, unidade)
VALUES (5, 'kg');

-- Insere dados na tabela Operacoes
INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(1, 102, NULL, 4, 1, NULL, TO_DATE('2016-10-06', 'YYYY-MM-DD'), 30);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(2, 102, NULL, 4, 1, NULL, TO_DATE('2016-10-10', 'YYYY-MM-DD'), 20);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(3, 104, NULL, 4, 1, NULL, TO_DATE('2017-01-07', 'YYYY-MM-DD'), 90);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(4, 104, NULL, 4, 1, NULL, TO_DATE('2017-01-08', 'YYYY-MM-DD'), 60);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(5, 104, NULL, 4, 3, NULL, TO_DATE('2017-01-08', 'YYYY-MM-DD'), 40);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(6, 104, NULL, 5, 3, NULL, TO_DATE('2017-07-10', 'YYYY-MM-DD'), 3);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(7, 104, NULL, 5, 3, NULL, TO_DATE('2017-08-10', 'YYYY-MM-DD'), 3.5);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(8, 104, NULL, 5, 4, NULL, TO_DATE('2017-09-10', 'YYYY-MM-DD'), 3);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(9, 102, NULL, 4, 4, NULL, TO_DATE('2017-11-04', 'YYYY-MM-DD'), 30);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(10, 102, NULL, 4, 5, NULL, TO_DATE('2017-11-04', 'YYYY-MM-DD'), 20);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(11, 102, 'Patentkali', 5, 3, NULL, TO_DATE('2017-12-10', 'YYYY-MM-DD'), 15);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(12, 102, 'Patentkali', 5, 3, NULL, TO_DATE('2017-12-10', 'YYYY-MM-DD'), 10);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(13, 104, NULL, 4, 2, NULL, TO_DATE('2018-07-10', 'YYYY-MM-DD'), 3.5);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(14, 104, NULL, 4, 2, NULL, TO_DATE('2018-08-10', 'YYYY-MM-DD'), 4);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(15, 104, NULL, 4, 2, NULL, TO_DATE('2018-09-02', 'YYYY-MM-DD'), 4);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(16, 104, NULL, 4, 2, NULL, TO_DATE('2018-09-10', 'YYYY-MM-DD'), 4);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(17, 102, NULL, 4, 4, NULL, TO_DATE('2018-11-17', 'YYYY-MM-DD'), 30);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(18, 102, NULL, 4, 4, NULL, TO_DATE('2018-11-17', 'YYYY-MM-DD'), 20);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(19, 104, NULL, 4, 1, NULL, TO_DATE('2018-12-10', 'YYYY-MM-DD'), 30);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(20, 104, NULL, 5, 2, NULL, TO_DATE('2019-07-03', 'YYYY-MM-DD'), 4);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(21, 104, NULL, 5, 2, NULL, TO_DATE('2019-08-10', 'YYYY-MM-DD'), 4.5);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(22, 102, NULL, 4, 4, NULL, TO_DATE('2019-11-15', 'YYYY-MM-DD'), 30);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(23, 102, NULL, 4, 4, NULL, TO_DATE('2019-11-15', 'YYYY-MM-DD'), 20);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(24, 106, NULL, 4, 5, NULL, TO_DATE('2020-05-05', 'YYYY-MM-DD'), 2200);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(25, 106, NULL, 4, 5, NULL, TO_DATE('2020-05-15', 'YYYY-MM-DD'), 1400);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(26, 106, NULL, 4, 3, NULL, TO_DATE('2020-06-02', 'YYYY-MM-DD'), 0.6);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(27, 106, NULL, 5, 5, NULL, TO_DATE('2020-08-28', 'YYYY-MM-DD'), 600);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(28, 106, NULL, 4, 5, NULL, TO_DATE('2020-09-07', 'YYYY-MM-DD'), 1800);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(29, 106, NULL, 4, 3, NULL, TO_DATE('2020-09-20', 'YYYY-MM-DD'), 0.6);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(30, 101, NULL, 4, 6, NULL, TO_DATE('2020-10-10', 'YYYY-MM-DD'), 36);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(31, 106, NULL, 4, 5, NULL, TO_DATE('2020-10-10', 'YYYY-MM-DD'), 0.9);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(32, 102, NULL, 4, 4, NULL, TO_DATE('2020-11-10', 'YYYY-MM-DD'), 30);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(33, 102, NULL, 4, 4, NULL, TO_DATE('2020-11-10', 'YYYY-MM-DD'), 20);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(34, 106, NULL, 4, 5, NULL, TO_DATE('2020-11-15', 'YYYY-MM-DD'), 600);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(35, 104, NULL, 4, 1, NULL, TO_DATE('2020-12-05', 'YYYY-MM-DD'), 70);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(36, 104, NULL, 4, 1, NULL, TO_DATE('2020-12-05', 'YYYY-MM-DD'), 50);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(37, 102, 'Patentkali', 5, 3, 'Solo', TO_DATE('2020-12-10', 'YYYY-MM-DD'), 10);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(38, 102, 'Patentkali', 5, 3, 'Solo', TO_DATE('2020-12-10', 'YYYY-MM-DD'), 7);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(39, 104, NULL, 4, 5, NULL, TO_DATE('2021-05-05', 'YYYY-MM-DD'), 2200);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(40, 104, NULL, 4, 5, NULL, TO_DATE('2021-05-15', 'YYYY-MM-DD'), 1400);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(41, 106, NULL, 4, 3, NULL, TO_DATE('2021-06-02', 'YYYY-MM-DD'), 0.6);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(42, 104, 'EPSO Microtop', 5, 3, 'Foliar', TO_DATE('2021-05-02', 'YYYY-MM-DD'), 10);

INSERT INTO Operacoes(operacoes_id, parcela_id, fatorProducao_id, unidade_id, tipoOperacao_id, modo, data, quantidade)
VALUES(43, 101, NULL, 4, 6, NULL, TO_DATE('2021-08-21', 'YYYY-MM-DD'), 3300);
