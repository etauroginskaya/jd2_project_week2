package com.gmail.etauroginskaya.repository.impl;

import com.gmail.etauroginskaya.repository.DocumentRepository;
import com.gmail.etauroginskaya.repository.connection.ConnectionHandler;
import com.gmail.etauroginskaya.repository.exception.DatabaseException;
import com.gmail.etauroginskaya.repository.model.Document;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private static final Logger logger = LogManager.getLogger(DocumentRepositoryImpl.class);

    private final ConnectionHandler connectionHandler;
    private static final String CONNECTION_ERROR_MESSAGE = "Connection Failed! Check output console.";

    public DocumentRepositoryImpl(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    @Override
    public Document addDocument(Document document) {
        Document saveDocument = new Document();
        try (Connection connection = connectionHandler.getConnection()) {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO document(unique_number, description) VALUES(?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, document.getUnique_number());
                statement.setString(2, document.getDescription());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    logger.error("Creating document failed, no rows affected.");
                    throw new DatabaseException("Creating document failed, no rows affected.");
                }
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        saveDocument.setId(rs.getLong(1));
                    } else {
                        logger.error("Creating document failed, no ID obtained.");
                        throw new DatabaseException("Creating document failed, no ID obtained.");
                    }
                }
                saveDocument.setDescription(document.getDescription());
                saveDocument.setUnique_number(document.getUnique_number());
                connection.commit();
                return saveDocument;
            } catch (Exception e) {
                connection.rollback();
                logger.error(CONNECTION_ERROR_MESSAGE, e);
                throw new DatabaseException(CONNECTION_ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            logger.error(CONNECTION_ERROR_MESSAGE, e);
            throw new DatabaseException(CONNECTION_ERROR_MESSAGE);
        }
    }

    @Override
    public Document getDocumentById(Long id){
        Document document = new Document();
        try (Connection connection = connectionHandler.getConnection()) {
            connection.setAutoCommit(false);
            String sql = "SELECT * FROM document WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        document.setId(rs.getLong(1));
                        document.setUnique_number(rs.getString(2));
                        document.setDescription(rs.getString(3));
                        document.setDeleted(rs.getInt(4));
                    } else {
                        logger.warn("Document with id (" + id + ") no found.");
                    }
                }
                connection.commit();
                return document;
            } catch (Exception e) {
                connection.rollback();
                logger.error(CONNECTION_ERROR_MESSAGE, e);
                throw new DatabaseException(CONNECTION_ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            logger.error(CONNECTION_ERROR_MESSAGE, e);
            throw new DatabaseException(CONNECTION_ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteDocumentById(Long id) {
        try (Connection connection = connectionHandler.getConnection()) {
            connection.setAutoCommit(false);
            String sql = "UPDATE document SET deleted = true WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    logger.error("Deleting document failed, no rows affected.");
                    throw new DatabaseException("Deleting document failed, no rows affected.");
                }
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                logger.error(CONNECTION_ERROR_MESSAGE, e);
                throw new DatabaseException(CONNECTION_ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            logger.error(CONNECTION_ERROR_MESSAGE, e);
            throw new DatabaseException(CONNECTION_ERROR_MESSAGE);
        }
    }
}
