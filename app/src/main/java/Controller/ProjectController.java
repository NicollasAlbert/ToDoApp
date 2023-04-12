/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Project;
import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProjectController {

    public void save(Project project) {

        String sql = "INSERT INTO projects(name, description, createAt, updateAt)"
                + "VALUES(?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreateAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));

        } catch (Exception ex) {

            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);

        } finally {

            ConnectionFactory.closeConnection(connection, statement);

        }

    }

    public void update(Project project) {
        
        String sql = "UPDATE projects SET name =?, description =?, createAt = ?, updateAt = ?"
                + "WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreateAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            statement.setInt(5, project.getId());

        } catch (Exception ex) {

            throw new RuntimeException("Erro ao atualizar a tarefa " + ex.getMessage(), ex);

        } finally {

            ConnectionFactory.closeConnection(connection, statement);

        }
        
    }

    public void removeById(int projectId) throws SQLException {
    }

    public List<Project> getAll(int id) {

        return null;
    }

}
