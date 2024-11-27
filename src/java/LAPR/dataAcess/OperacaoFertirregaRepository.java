package LAPR.dataAcess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class OperacaoFertirregaRepository {
    public OperacaoFertirregaRepository(){}

    public void fertirregaRegister(Date data, int quantidade, String unidade, String designacao, int idCultura, int id, int idSetor, int idReceita, double horas, int duracao) throws SQLException {
        Connection connection= DatabaseConnection.getInstance().getConnection();
        try(CallableStatement callableStatement = connection.prepareCall("{ call prcRegistarOperacaoRega(?,?,?,?,?,?,?,?,?,?)}")){

            java.sql.Date sqlDate= new java.sql.Date(data.getTime());
            callableStatement.setDate(1,sqlDate);
            callableStatement.setInt(2,quantidade);
            callableStatement.setString(3,unidade);
            callableStatement.setString(4,designacao);
            callableStatement.setInt(5,idCultura);
            callableStatement.setInt(6,id);
            callableStatement.setInt(7,idSetor);
            callableStatement.setInt(8,idReceita);
            callableStatement.setDouble(9,horas);
            callableStatement.setInt(10,duracao);

            callableStatement.execute();
            connection.commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
