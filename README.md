# Database #

* SQLite supports standard relational database features like SQL syntax, transactions and prepared statements.In order to access this database, you don't need to establish any kind of connections for it like JDBC,ODBC e.t.c
* The android.database package contains all necessary classes for working with databases. The android.database.sqlite package contains the SQLite specific classes.
* SQLite is a opensource SQL database that stores data to a text file on a device. Android comes in with built in SQLite database implementation.
* SQLite supports the data types TEXT (similar to String in Java), INTEGER (similar to long in Java) and REAL (similar to double in Java). All other types must be converted into one of these fields before getting saved in the database. SQLite itself does not validate if the types written to the columns are actually of the defined type, e.g. you can write an integer into a string column and vice versa.
* The database requires limited memory at runtime (approx. 250 KByte) which makes it a good candidate from being embedded into other runtimes.
###### Class - SQLiteOpenHelper
* For managing all the operations related to the database , an helper class has been given and is called **SQLiteOpenHelper**. It automatically manages the creation and update of the database. Its syntax is given below

**Methods**
* **onCreate()** - is called by the framework, if the database is accessed but not yet created.
* **onUpgrade()** - called, if the database version is increased in your application code. This method allows you to update an existing database schema or to drop the existing database and recreate it via the onCreate() method.
* **insert()**, **update()** and **delete()** methods.
* **rawQuery()** directly accepts an SQL select statement as input.
* **query()** provides a structured interface for specifying the SQL query.
* The SQLiteOpenHelper class provides the **getReadableDatabase()** and **getWriteableDatabase()** methods to get access to an SQLiteDatabase object; either in read or write mode.
###### Class - Cursor
* A query returns a **Cursor** object. A Cursor represents the result of a query and basically points to one row of the query result. This way Android can buffer the query results efficiently; as it does not have to load all data into memory.

**Methods**
* To get the number of elements of the resulting query use the getCount() method.
* To move between individual data rows, you can use the **moveToFirst()** and **moveToNext()** methods. The **isAfterLast()** method allows to check if the end of the query result has been reached.
* Cursor provides typed **get*()** methods, e.g. **getLong(columnIndex)**, **getString(columnIndex)** to access the column data for the current position of the result. The "columnIndex" is the number of the column you are accessing.
##### Conclusions
* if you want to execute something in database without concerning its output (e.g create/alter tables), then use **execSQL**, but if you are expecting some results in return against your query (e.g. select records) then use **rawQuery**.
* Looking at SQLiteDatabase.java in the android source shows that the **query(..)** ends up calling the QueryBuilder to build the query as a single string and then it essentially calls **rawQuery()**. They should be roughly equivalent, assuming that you also did the same work to build your own statement.
