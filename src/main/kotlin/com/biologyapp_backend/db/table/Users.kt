package com.biologyapp_backend.db.table

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = uuid("id").autoGenerate()
    val login = varchar("login", 12)
    val email = varchar("email", 12)
    val password = varchar("password", 12)
    val token = varchar("token", 50)
    val firstName = varchar("firstName", 20)
    val secondName = varchar("secondName", 20)
    val birthday = varchar("birthday", 12)
    val phone = varchar("phone", 12)
    val clas = integer("clas")

    override val primaryKey = PrimaryKey(id)
}
