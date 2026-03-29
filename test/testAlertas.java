
import bd.DAOAlertas;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bevod
 */
public class testAlertas {
    @Test
    public void testInsertar() throws Exception {
        DAOAlertas dao = new DAOAlertas();
        dao.insertarAlerta(1, "TEST");
        assertTrue(true);
    }
}
