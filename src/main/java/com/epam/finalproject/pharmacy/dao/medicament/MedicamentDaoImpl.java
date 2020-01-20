package com.epam.finalproject.pharmacy.dao.medicament;

import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.mapper.MedicamentRowMapper;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public class MedicamentDaoImpl extends AbstractDao<Medicament> implements MedicamentDao{


    private static final String INSERT_NEW_OR_UPDATE = "INSERT INTO medicines (name, price) VALUES(?,?)";
    public static final String JOIN_ORDER_DETAILS_AND_ORDER =
            "SELECT m.id, m.name, m.form, m.dosage, m.recipe, m.amountInPack, od.price, od.quantity " +
                    "FROM orderdetails AS od " +
                    "JOIN medicines AS m ON od.medicamentId=m.id " +
                    "WHERE od.orderId=?";


    public MedicamentDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getValuesItemByStringForQuery(Medicament medicament) {
        StringBuilder fieldsValuesForQuery = new StringBuilder();
        fieldsValuesForQuery.append("'")
                .append(medicament.getName()).append("', '")
                .append(medicament.getForm().name()).append("', '")
                .append(medicament.getDosage()).append("', '")
                .append(medicament.isRecipe() ? 1 : 0).append("', '")
                .append(medicament.getAmountInPack()).append("', '")
                .append(medicament.getPrice()).append("', '")
                .append(medicament.getQuantity()).append("'");
        return fieldsValuesForQuery.toString();
    }

    @Override
    protected String getTableName() {
        return Medicament.NAME_TABLE_IN_DB;
    }


       @Override
    public void removeById(Long id) {

    }

    @Override
    public List<Medicament> getAllMedicamentForOrder(long orderId) throws DaoException {
        return executeQuery(JOIN_ORDER_DETAILS_AND_ORDER, new MedicamentRowMapper(), orderId);
    }
}
