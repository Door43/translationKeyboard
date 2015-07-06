package org.distantshoresmedia.model.daoModels;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import org.distantshoresmedia.model.daoModels.KeyCharacter;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table KEY_CHARACTER.
*/
public class KeyCharacterDao extends AbstractDao<KeyCharacter, Long> {

    public static final String TABLENAME = "KEY_CHARACTER";

    /**
     * Properties of entity KeyCharacter.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ModMask = new Property(1, Integer.class, "modMask", false, "MOD_MASK");
        public final static Property Unicode = new Property(2, Integer.class, "unicode", false, "UNICODE");
        public final static Property KeyPositionId = new Property(3, long.class, "keyPositionId", false, "KEY_POSITION_ID");
    };

    private DaoSession daoSession;

    private Query<KeyCharacter> keyPosition_KeyPositionsQuery;

    public KeyCharacterDao(DaoConfig config) {
        super(config);
    }
    
    public KeyCharacterDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'KEY_CHARACTER' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'MOD_MASK' INTEGER," + // 1: modMask
                "'UNICODE' INTEGER," + // 2: unicode
                "'KEY_POSITION_ID' INTEGER NOT NULL );"); // 3: keyPositionId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'KEY_CHARACTER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, KeyCharacter entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer modMask = entity.getModMask();
        if (modMask != null) {
            stmt.bindLong(2, modMask);
        }
 
        Integer unicode = entity.getUnicode();
        if (unicode != null) {
            stmt.bindLong(3, unicode);
        }
        stmt.bindLong(4, entity.getKeyPositionId());
    }

    @Override
    protected void attachEntity(KeyCharacter entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public KeyCharacter readEntity(Cursor cursor, int offset) {
        KeyCharacter entity = new KeyCharacter( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // modMask
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // unicode
            cursor.getLong(offset + 3) // keyPositionId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, KeyCharacter entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setModMask(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setUnicode(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setKeyPositionId(cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(KeyCharacter entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(KeyCharacter entity) {
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
    
    /** Internal query to resolve the "keyPositions" to-many relationship of KeyPosition. */
    public List<KeyCharacter> _queryKeyPosition_KeyPositions(long keyPositionId) {
        synchronized (this) {
            if (keyPosition_KeyPositionsQuery == null) {
                QueryBuilder<KeyCharacter> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.KeyPositionId.eq(null));
                keyPosition_KeyPositionsQuery = queryBuilder.build();
            }
        }
        Query<KeyCharacter> query = keyPosition_KeyPositionsQuery.forCurrentThread();
        query.setParameter(0, keyPositionId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getKeyPositionDao().getAllColumns());
            builder.append(" FROM KEY_CHARACTER T");
            builder.append(" LEFT JOIN KEY_POSITION T0 ON T.'KEY_POSITION_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected KeyCharacter loadCurrentDeep(Cursor cursor, boolean lock) {
        KeyCharacter entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        KeyPosition keyPosition = loadCurrentOther(daoSession.getKeyPositionDao(), cursor, offset);
         if(keyPosition != null) {
            entity.setKeyPosition(keyPosition);
        }

        return entity;    
    }

    public KeyCharacter loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<KeyCharacter> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<KeyCharacter> list = new ArrayList<KeyCharacter>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<KeyCharacter> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<KeyCharacter> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
