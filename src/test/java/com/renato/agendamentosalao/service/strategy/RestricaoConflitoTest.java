package com.renato.agendamentosalao.service.strategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestricaoConflitoTest {

	@InjectMocks
	private RestricaoConflito restricaoConlito;

	@Mock
	private Connection mockConnection;

	@Mock
	private PreparedStatement mockPreparedStatement;

	@Mock
	private ResultSet mockResultSet;
	
	private MockedStatic<DriverManager> mockedDriverManager;

	private final String sql = "SELECT COUNT(*) FROM agendamento WHERE hora_data_inicio = ? AND ativo = true";
	private final String dataFormatada = LocalDateTime.of(2023, 5, 29, 10, 0)
			.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

	@BeforeEach
	public void setUp() throws SQLException {
		
		mockedDriverManager = Mockito.mockStatic(DriverManager.class);
		when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);

		when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
	}
	
	@AfterEach
    public void tearDown() {
        mockedDriverManager.close();
    }

	@Test
    public void testIsRestrito_True() throws SQLException{
    	when(mockResultSet.next()).thenReturn(true);
    	when(mockResultSet.getInt(1)).thenReturn(1);
    	
    	assertTrue(restricaoConlito.isRestrito(LocalDateTime.of(2023, 5, 29, 10, 0)));
    	verify(mockPreparedStatement).setString(1, dataFormatada);
    }

	@Test
	public void testIsRestrito_False() throws SQLException{
		when(mockResultSet.next()).thenReturn(true);
		when(mockResultSet.getInt(1)).thenReturn(0);
		
		assertFalse(restricaoConlito.isRestrito(LocalDateTime.of(2023, 5, 29, 10, 0)));
		verify(mockPreparedStatement).setString(1, dataFormatada);
	}
	
	
	
//	@Test
//	public void testIsRestrito_SQLException() throws SQLException  {
//		
//		when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenThrow(new SQLException(""));
//
//	    //when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Database error"));
//	    // Assuming the method should return false on SQL error
//	    assertFalse(restricaoConlito.isRestrito(LocalDateTime.of(2023, 5, 29, 10, 0)));
//	}
}
