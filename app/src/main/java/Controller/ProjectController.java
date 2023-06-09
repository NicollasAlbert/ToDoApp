package Controller;

import Model.Project;
import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            statement.execute();

        } catch (Exception ex) {

            throw new RuntimeException("Erro ao salvar o projeto " 
                    + ex.getMessage(), ex);

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
            statement.execute();

        } catch (Exception ex) {

            throw new RuntimeException("Erro ao atualizar o projeto " 
                    + ex.getMessage(), ex);

        } finally {

            ConnectionFactory.closeConnection(connection, statement);

        }
        
    }

    public void removeById(int projectId) throws SQLException {
        
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
        
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.execute();
        
        } catch (Exception ex) {
        
            throw new RuntimeException("Erro ao deletar o projeto " 
                    + ex.getMessage(), ex);
        
        } finally {
        
            ConnectionFactory.closeConnection(connection, statement);
        
        }
        
    }

    public List<Project> getAll() {

        String sql = "SELECT * FROM projects";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<Project> projects = new ArrayList<Project>();
        
        try {
        
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
            
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreateAt(resultSet.getDate("createAt"));
                project.setUpdateAt(resultSet.getDate("updateAt"));
                
                projects.add(project);
            
            }
        
        
        } catch (Exception ex) {
        
            throw new RuntimeException("Erro ao inserir o projeto " 
                    + ex.getMessage(), ex);
        
        } finally {
        
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        
        }
        
        return projects;
    }

}
