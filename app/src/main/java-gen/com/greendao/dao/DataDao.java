package com.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.greendao.bean.Data;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DATA".
*/
public class DataDao extends AbstractDao<Data, Long> {

    public static final String TABLENAME = "DATA";

    /**
     * Properties of entity Data.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ShopName = new Property(1, String.class, "ShopName", false, "SHOP_NAME");
        public final static Property Freight = new Property(2, String.class, "freight", false, "FREIGHT");
        public final static Property Image = new Property(3, String.class, "image", false, "IMAGE");
        public final static Property Name = new Property(4, String.class, "name", false, "NAME");
        public final static Property Color = new Property(5, String.class, "color", false, "COLOR");
        public final static Property Price = new Property(6, String.class, "price", false, "PRICE");
        public final static Property Reduction = new Property(7, String.class, "reduction", false, "REDUCTION");
        public final static Property Status = new Property(8, String.class, "status", false, "STATUS");
        public final static Property Number = new Property(9, String.class, "number", false, "NUMBER");
    };


    public DataDao(DaoConfig config) {
        super(config);
    }
    
    public DataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"SHOP_NAME\" TEXT NOT NULL ," + // 1: ShopName
                "\"FREIGHT\" TEXT," + // 2: freight
                "\"IMAGE\" TEXT," + // 3: image
                "\"NAME\" TEXT," + // 4: name
                "\"COLOR\" TEXT," + // 5: color
                "\"PRICE\" TEXT," + // 6: price
                "\"REDUCTION\" TEXT," + // 7: reduction
                "\"STATUS\" TEXT," + // 8: status
                "\"NUMBER\" TEXT);"); // 9: number
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DATA\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Data entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getShopName());
 
        String freight = entity.getFreight();
        if (freight != null) {
            stmt.bindString(3, freight);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(4, image);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(5, name);
        }
 
        String color = entity.getColor();
        if (color != null) {
            stmt.bindString(6, color);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(7, price);
        }
 
        String reduction = entity.getReduction();
        if (reduction != null) {
            stmt.bindString(8, reduction);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(9, status);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(10, number);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Data readEntity(Cursor cursor, int offset) {
        Data entity = new Data( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // ShopName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // freight
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // image
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // color
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // price
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // reduction
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // status
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // number
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Data entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setShopName(cursor.getString(offset + 1));
        entity.setFreight(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setImage(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setColor(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPrice(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setReduction(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setStatus(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setNumber(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Data entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Data entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
