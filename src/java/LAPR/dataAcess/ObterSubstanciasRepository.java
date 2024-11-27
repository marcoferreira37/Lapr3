package LAPR.dataAcess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObterSubstanciasRepository {

    public ObterSubstanciasRepository() {
    }

    public List<String> substanciasList = new ArrayList<>();

    public List<String> obterSubstanciasRegister(int ano) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        String sqlQuery = "SELECT DISTINCT fp.designacao " +
                "FROM Fator_Producao fp " +
                "WHERE fp.designacao NOT IN ( " +
                "    SELECT DISTINCT fp.designacao " +
                "    FROM Fator_Producao_Receita fpr " +
                "         JOIN Receita r ON fpr.idReceita = r.idReceita " +
                "         JOIN Operacoes o ON o.idOperacao = r.idReceita " +
                "    WHERE EXTRACT(YEAR FROM o.data) = ? " +
                ") " +
                "AND fp.designacao IN ( " +
                "    SELECT DISTINCT fp.designacao " +
                "    FROM Fator_Producao_Receita fpr " +
                "         JOIN Receita r ON fpr.idReceita = r.idReceita " +
                "         JOIN Operacoes o ON o.idOperacao = r.idReceita " +
                "    WHERE EXTRACT(YEAR FROM o.data) != ? " +
                ")";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, ano);
            preparedStatement.setInt(2, ano);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String designacao = resultSet.getString("designacao");
                    substanciasList.add(designacao);
                }
            }

            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  substanciasList;
    }

    public List<String> getSubstanciasList() {
        return this.substanciasList;
    }

}
