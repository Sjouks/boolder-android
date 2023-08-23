package com.boolder.boolder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.boolder.boolder.data.database.dao.AreaDao
import com.boolder.boolder.data.database.dao.LineDao
import com.boolder.boolder.data.database.dao.ProblemDao
import com.boolder.boolder.data.database.dao.TickDao
import com.boolder.boolder.data.database.entity.AreasEntity
import com.boolder.boolder.data.database.entity.LineEntity
import com.boolder.boolder.data.database.entity.ProblemEntity
import com.boolder.boolder.data.database.entity.TickEntity

@Database(
    entities = [
        AreasEntity::class,
        LineEntity::class,
        ProblemEntity::class
    ],
    version = 8, // increment version number everytime the boolder.db database changes (schema or data)
    exportSchema = true
)
abstract class BoolderAppDatabase : RoomDatabase() {
    abstract fun areaDao(): AreaDao
    abstract fun lineDao(): LineDao
    abstract fun problemDao(): ProblemDao
}


@Database(entities = [TickEntity::class], version = 1, exportSchema = true)
abstract class TickDatabase : RoomDatabase() {
    abstract fun tickDao(): TickDao
}
