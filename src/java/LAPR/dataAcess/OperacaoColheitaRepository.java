package LAPR.dataAcess;

import oracle.jdbc.OracleType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class OperacaoColheitaRepository {
    public OperacaoColheitaRepository(){}

    public void colheitaRegister( Date data, String operacao, int quantidade, String unidade, String designacao, int idCultura, int id) throws SQLException {
        Connection connection= DatabaseConnection.getInstance().getConnection();
        try(CallableStatement callableStatement = connection.prepareCall("{? = call fncUS13(?,?,?,?,?,?,?)}")){

            java.sql.Date sqlDate= new java.sql.Date(data.getTime());
            callableStatement.setDate(3,sqlDate);
            callableStatement.setString(2,operacao);
            callableStatement.setInt(4,quantidade);
            callableStatement.setString(5,unidade);
            callableStatement.setString(6,designacao);
            callableStatement.setInt(7,idCultura);
            callableStatement.setInt(8, id);
            callableStatement.registerOutParameter(1, OracleType.NUMBER);


            callableStatement.execute();
            connection.commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
