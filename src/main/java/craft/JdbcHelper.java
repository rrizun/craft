package craft;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.sql.*;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.primitives.*;
import com.google.gson.*;

// spring jdbc template like
public class JdbcHelper {
  private final DataSource dataSource;

  public JdbcHelper(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * query
   * @param classOfT
   * @param sql
   * @param args
   * 
   * @param <T>
   * @return list
   * @throws Exception
   */
  public <T> List<T> query(Class<T> classOfT, String sql, Object... args) throws Exception {
    final Connection c = dataSource.getConnection();
    try {
      final PreparedStatement s = c.prepareStatement(sql);
      try {
        List<T> list = Lists.newArrayList();
        int index = 0;
        for (Object arg : args)
          s.setObject(++index, arg);
        ResultSet rs = s.executeQuery();
        while (rs.next()) {
          JsonElement rowElement = defaultJsonElement(classOfT);

          // bind to json object row
          if (rowElement.isJsonObject()) {
            for (int column = 1; column <= rs.getMetaData().getColumnCount(); ++column) {
              String columnName = rs.getMetaData().getColumnName(column);
              // assume primitive..
              JsonElement columnElement = gson.toJsonTree(rs.getObject(column));
              // is it a json object?
              for (Field field : classOfT.getFields()) {
                if (field.getName().equals(columnName)) {
                  if (defaultJsonElement(field.getType()).isJsonObject())
                    columnElement = gson.fromJson(rs.getString(column), JsonElement.class); // yes
                }
              }
              if (columnElement != null) {
                if (!columnElement.isJsonNull())
                  rowElement.getAsJsonObject().add(columnName, columnElement);
              }
            }
          }

          // bind to json primitive row
          if (rowElement.isJsonPrimitive())
            rowElement = gson.toJsonTree(rs.getObject(1));

          list.add(gson.fromJson(rowElement, classOfT));
        }
        return list;
      } finally {
        s.close();
      }
    } finally {
      c.close();
    }
  }

  /**
   * execute
   * 
   * @param sql
   * @param args
   * @return affectedRowCount
   * @throws Exception
   */
  //###TODO RETURN AFFECTED ROW COUNT
  //###TODO RETURN AFFECTED ROW COUNT
  //###TODO RETURN AFFECTED ROW COUNT
  public int execute(String sql, Object... args) throws Exception {
    //###TODO RETURN AFFECTED ROW COUNT
    //###TODO RETURN AFFECTED ROW COUNT
    //###TODO RETURN AFFECTED ROW COUNT
    final Connection c = dataSource.getConnection();
    try {
      final PreparedStatement s = c.prepareStatement(sql);
      try {
        int index = 0;
        for (Object arg : args) {
          s.setObject(++index, arg);
        }
        
        // log(s.toString());
        return s.executeUpdate();
      } finally {
        s.close();
      }
    } finally {
      c.close();
    }
  }

  /**
   * defaultJsonElement
   * 
   * @param type
   * @return
   * @throws Exception
   */
  private JsonElement defaultJsonElement(Class<?> type) throws Exception {
    Object src = Defaults.defaultValue(Primitives.unwrap(type));
    if (src == null) {
      // try to find default constructor
      for (Constructor<?> ctor : type.getConstructors()) {
        ctor.setAccessible(true);
        if (ctor.getParameterCount() == 0)
          src = ctor.newInstance();
      }
    }
    if (src == null) {
      Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
      Field f = unsafeClass.getDeclaredField("theUnsafe");
      f.setAccessible(true);
      final Object unsafe = f.get(null);
      final Method allocateInstance = unsafeClass.getMethod("allocateInstance", Class.class);
      src = allocateInstance.invoke(unsafe, type);
    }
    return gson.toJsonTree(src);
  }

  private Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive(src.toInstant().toString());
    }
  }).create();
}
