package LAPR.dataAcess;

import oracle.jdbc.OracleType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class MaiorConsumoAguaRepository {

    public MaiorConsumoAguaRepository(){}

    public void maiorConsumoAguaRegister(int ano) throws SQLException {
        Connection connection= DatabaseConnection.getInstance().getConnection();
        try(CallableStatement callableStatement = connection.prepareCall("{ call MaiorConsumoAguaPorCultura(?)}")){

            callableStatement.setInt(1,ano);

            callableStatement.execute();
            connection.commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
